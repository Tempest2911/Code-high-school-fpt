/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lamdejdbclan3;

import java.util.ArrayList;
import java.sql.*;
import javax.swing.JOptionPane;

/**
 *
 * @author drago
 */
public class Service {

    ArrayList<Model> listNV = new ArrayList<>();

    String url = "jdbc:sqlserver://Tempest:1433;databasename=NhanVienJDBC;trustServerCertificate=true;user=sa;password=123456";

    String select = "Select * from nv";

    String insert = "Insert into nv values(?,?,?,?)";

    String edit = "Update nv set name=?,status =?, money=? where id =?";

    String delete = "Delete nv where id =?";

    String Search = "Select * from nv where money between ? and ?";


    public ArrayList<Model> getAll() {
        return listNV;
    }

    public void LoadTableData() {
        listNV.clear();
        try {
            Connection con = DriverManager.getConnection(url);
            PreparedStatement stm = con.prepareStatement(select);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                String id = rs.getString("id");
                String name = rs.getString("name");
                String status = rs.getString("status");
                double money = rs.getDouble("money");

                listNV.add(new Model(id, name, status, money));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void add(Model m) {
        try {
            Connection con = DriverManager.getConnection(url);
            PreparedStatement stm = con.prepareStatement(insert);
            stm.setString(1, m.id);
            stm.setString(2, m.name);
            stm.setString(3, m.status);
            stm.setDouble(4, m.money);
            int rs = stm.executeUpdate();

            if (rs > 0) {
                System.out.println("Thanhf coong");
            } else {
                System.out.println("Fail");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void edit(Model m) {
        if (m.status.equals("Nghỉ làm")) {
            m.setMoney(0.0);
        }
        try {
            Connection con = DriverManager.getConnection(url);
            PreparedStatement stm = con.prepareStatement(edit);
            stm.setString(4, m.id);
            stm.setString(1, m.name);
            stm.setString(2, m.status);
            stm.setDouble(3, m.money);
            int rs = stm.executeUpdate();

            if (rs > 0) {
                System.out.println("Thanhf coong");
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
            stm.setString(1, m.id);

            int rs = stm.executeUpdate();
            Model mxoa = null;
            if (rs > 0) {
                for (Model model : listNV) {
                    if (model.getId().equals(m.id)) {
                        mxoa = model;
                    }
                }
                listNV.remove(mxoa);
                System.out.println("thanhf coong");
            } else {
                System.out.println("Fail");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Model> search(Double min, Double max) {
        ArrayList<Model> listSearch = new ArrayList<>();

        try {
            Connection con = DriverManager.getConnection(url);
            PreparedStatement stm = con.prepareStatement(Search);
            stm.setDouble(1, min);
            stm.setDouble(2, max);

            ResultSet rs = stm.executeQuery();

            if (rs.next()) {
                String id = rs.getString("id");
                String name = rs.getString("name");
                String status = rs.getString("status");
                double money = rs.getDouble("money");

                listSearch.add(new Model(id, name, status, money));
            } else {
                System.out.println("Fail");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listSearch;
    }
}
