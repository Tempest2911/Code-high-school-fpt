/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lamdejdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author drago
 */
public class Service {

    ArrayList<Model> listTK = new ArrayList<>();

    public ArrayList<Model> getAll() {
        return listTK;
    }

    String url = "jdbc:sqlserver://Tempest:1433;databasename=LamDeJDBC;trustServerCertificate=true;user=sa;password=123456";
    String SelectQuery = "Select * from tk";
    String insertQuery = "Insert into tk values(?,?,?,?)";
    String deleteQuery = "delete tk where ID = ?";
    String checkQuery = "SELECT COUNT(*) FROM tk WHERE ID = ?";

    public void autoAdd() {
        try (Connection con = DriverManager.getConnection(url); PreparedStatement stmt = con.prepareStatement(SelectQuery)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String ma = rs.getString("ID");
                String name = rs.getString("NAME");
                String phone = rs.getString("Phone");
                String status = rs.getString("Status");

                listTK.add(new Model(ma, name, phone, status));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void add(Model m) {
        if (checkTrung(m)) {
            JOptionPane.showMessageDialog(null, "Đã trùng");
            return;
        }
        try (Connection con = DriverManager.getConnection(url); PreparedStatement stmt = con.prepareStatement(insertQuery)) {

            stmt.setString(1, m.ma);
            stmt.setString(2, m.name);
            stmt.setString(3, m.phone);
            stmt.setString(4, m.status);

            int rowsAffected = stmt.executeUpdate(); // Sử dụng executeUpdate cho INSERT

            if (rowsAffected > 0) { // Kiểm tra xem có hàng nào được chèn hay không
                System.out.println("Thêm thành công");
                listTK.add(m); // Thêm đối tượng vào danh sách
            } else {
                System.out.println("Thất bại");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(Model m) {
        try (Connection con = DriverManager.getConnection(url); PreparedStatement stmt = con.prepareStatement(deleteQuery)) {

            stmt.setString(1, m.ma);

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) { //kiểm tra result có dữ liệu hay không
                System.out.println("Xóa thành công");

                Model mxoa = null;
                for (Model model1 : listTK) {
                    if (model1.getMa().equals(m.ma)) {
                        mxoa = model1;
                    }
                }
                listTK.remove(mxoa);
            } else {
                System.out.println("Thất bại");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean checkTrung(Model m) {
        try (Connection con = DriverManager.getConnection(url); PreparedStatement stmt = con.prepareStatement(checkQuery)) {
            stmt.setString(1, m.ma);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
