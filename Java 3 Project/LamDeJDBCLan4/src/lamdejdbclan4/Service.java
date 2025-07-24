/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lamdejdbclan4;

import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author drago
 */
public class Service {

    ArrayList<Model> listsp = new ArrayList<>();

    String url = "jdbc:sqlserver://Tempest:1433;databasename=ThiCuoiMon;trustServerCertificate=true;user=sa;password=123456";
    String select = "Select * from sp";
    String insert = "Insert into sp values(?,?,?,?)";
    String delete = "Delete sp where id = ?";
    String update = "Update sp set name =?, status =?, price =? where id =?";
    String search = "Select * from sp where price between ? and ?";
    String searchName = "Select * from sp where name like ?";

    public ArrayList<Model> getAll() {
        return listsp;
    }

    public void LoadTableData() {
        listsp.clear();
        try {
            Connection con = DriverManager.getConnection(url);
            PreparedStatement stm = con.prepareStatement(select);

            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("Id");
                String name = rs.getString("name");
                String status = rs.getString("status");
                int price = rs.getInt("price");

                listsp.add(new Model(id, name, status, price));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void add(Model m) {
        try {
            Connection con = DriverManager.getConnection(url);
            PreparedStatement stm = con.prepareStatement(insert);
            stm.setInt(1, m.id);
            stm.setString(2, m.name);
            stm.setString(3, m.status);
            stm.setDouble(4, m.money);
            int rs = stm.executeUpdate();

            if (rs > 0) {
                System.out.println("Successs");
            } else {
                System.out.println("Fail");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update(Model m) {
        if (m.status.equalsIgnoreCase("hết hàng")) {
            m.setMoney(0.0);
        }
        try {
            Connection con = DriverManager.getConnection(url);
            PreparedStatement stm = con.prepareStatement(update);
            stm.setInt(4, m.id);
            stm.setString(1, m.name);
            stm.setString(2, m.status);
            stm.setDouble(3, m.money);
            int rs = stm.executeUpdate();

            if (rs > 0) {
                System.out.println("Successs");
            } else {
                System.out.println("Fail");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(Model m) {
        try {
            Connection con = DriverManager.getConnection(url);
            PreparedStatement stm = con.prepareStatement(delete);
            stm.setInt(1, m.getId());

            int rs = stm.executeUpdate();
            Model mxoa = null;
            if (rs > 0) {
                for (Model model : listsp) {
                    if (model.getId() == m.id) { // Use equals method here
                        mxoa = model;
                    }
                }
                listsp.remove(mxoa);
                JOptionPane.showMessageDialog(null, "Thành công");
            } else {
                JOptionPane.showMessageDialog(null, "Thất bại");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Model> search(Double min, Double max) {
        ArrayList<Model> listSearch = new ArrayList<>();

        try {
            Connection con = DriverManager.getConnection(url);
            PreparedStatement stm = con.prepareStatement(search);
            stm.setDouble(1, min);
            stm.setDouble(2, max);

            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String status = rs.getString("status");
                int money = rs.getInt("price");

                listSearch.add(new Model(id, name, status, money));

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listSearch;
    }

    public ArrayList<Model> searchName(String nameSearch) {
        ArrayList<Model> listSearch = new ArrayList<>();

        try {
            Connection con = DriverManager.getConnection(url);
            PreparedStatement stm = con.prepareStatement(searchName);
            stm.setString(1, "%" + nameSearch + "%");

            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String status = rs.getString("status");
                int money = rs.getInt("price");

                listSearch.add(new Model(id, name, status, money));

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listSearch;
    }

}
