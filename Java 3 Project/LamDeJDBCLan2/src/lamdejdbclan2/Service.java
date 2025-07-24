/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lamdejdbclan2;

import java.sql.*;
import java.util.*;

/**
 *
 * @author drago
 */
public class Service {

    ArrayList<Model> listTK = new ArrayList<>();

    public ArrayList<Model> getAll() {
        return listTK;
    }

    String url = "jdbc:sqlserver://Tempest:1433;databasename=LamdeJDBC;trustServerCertificate=true;user=sa;password=123456";
    String select = "Select * from tk";
    String delete = "Delete tk where ID = ?";
    String insert = "Insert tk values(?,?,?,?)";
    String edit = "Update tk set Name =?, Phone =?, Status =? where ID = ?";
    String check = "Select COUNT(*) from tk where id =?";

    public boolean checkTrung(Model m) {
        try (Connection con = DriverManager.getConnection(url); PreparedStatement stmt = con.prepareStatement(check)) {
            stmt.setString(1, m.id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public void autoAdd() {
        
        listTK.clear();
        try (Connection con = DriverManager.getConnection(url); Statement stmt = con.createStatement()) {
            ResultSet rs = stmt.executeQuery(select);

            while (rs.next()) {
                String id = rs.getString("ID");
                String name = rs.getString("Name");
                String phone = rs.getString("PHONE");
                String status = rs.getString("STATUS");

                listTK.add(new Model(id, name, phone, status));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void add(Model m) {
        if (checkTrung(m)) {
            System.out.println("Đã trùng");
            return;
        }
        try (Connection con = DriverManager.getConnection(url); PreparedStatement stme = con.prepareStatement(insert)) {
            stme.setString(1, m.id);
            stme.setString(2, m.name);
            stme.setString(3, m.phone);
            stme.setString(4, m.status);
            int rs = stme.executeUpdate();

            if (rs > 0) {
                listTK.add(m);
            } else {
                System.out.println("Fail");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void edit(Model m) {
        try (Connection con = DriverManager.getConnection(url); PreparedStatement stmt = con.prepareStatement(edit)) {
            stmt.setString(1, m.name);
            stmt.setString(2, m.phone);
            stmt.setString(3, m.status);
            stmt.setString(4, m.id);

            int rs = stmt.executeUpdate();

            if (rs > 0) {
                System.out.println("Thành công");

            } else {
                System.out.println("Fail");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(Model m) {
        try (Connection con = DriverManager.getConnection(url); PreparedStatement stmt = con.prepareStatement(delete)) {
            stmt.setString(1, m.id);
            int rs = stmt.executeUpdate();

            if (rs > 0) {
                Model mxoa = null;
                for (Model model : listTK) {
                    if (model.id.equals(m.id)) {
                        mxoa = model;
                    }
                }

                listTK.remove(mxoa);
            } else {
                System.out.println("Fail");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
