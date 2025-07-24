/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package thicuoimonn;

import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author drago
 */
public class Service {

    ArrayList<Model> listpb = new ArrayList<>();
    String url = "jdbc:sqlserver://Tempest:1433;databasename=FINAL_SOF203_FA24;trustServerCertificate=true;user=sa;password=123456";
    String select = "Select * from pb";
    String insert = "Insert into pb values(?,?,?,?)";
    String edit = "update pb set name =?, status = ?, number = ? where id = ?";
    String Search = "   Select * from pb where name like ?";
    String check = "Select * from pb where id =?";

    public ArrayList<Model> getAll() {
        return listpb;
    }

    public boolean checkTrung(Model m) {
        boolean error = false;
        try {
            Connection con = DriverManager.getConnection(url);
            PreparedStatement stm = con.prepareStatement(check);
            stm.setString(1, m.id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return error;
    }

    public void LoadDataTAble() {
        listpb.clear();
        try {
            Connection con = DriverManager.getConnection(url);
            PreparedStatement stm = con.prepareStatement(select);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
//                String id = rs.getString("id");
//                String name = rs.getString("name");
//                String status = rs.getString("Status");
//                int number = rs.getInt("Number");
                Model pb = new Model(rs.getString("id"), rs.getString("name"), rs.getString("Status"), rs.getInt("Number"));
                listpb.add(pb);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void add(Model m) {
        if (checkTrung(m)) {
            JOptionPane.showMessageDialog(null, "Đã trùng id");
            return;
        }
        try {
            Connection con = DriverManager.getConnection(url);
            PreparedStatement stm = con.prepareStatement(insert);
            stm.setString(1, m.id);
            stm.setString(2, m.name);
            stm.setString(3, m.status);
            stm.setInt(4, m.number);
            int rs = stm.executeUpdate();

            if (rs > 0) {
                System.out.println("thành công");
            } else {
                System.out.println("fail");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void edit(Model m) {
        if (m.number == 0) {
            m.setStatus("Đã giải tán");
        }

        if (m.status.equalsIgnoreCase("Đã giải tán")) {
            m.setNumber(0);
        }

        try {
            Connection con = DriverManager.getConnection(url);
            PreparedStatement stm = con.prepareStatement(edit);
            stm.setString(4, m.id);
            stm.setString(1, m.name);
            stm.setString(2, m.status);
            stm.setInt(3, m.number);
            int rs = stm.executeUpdate();

            if (rs > 0) {
                System.out.println("thành công");
            } else {
                System.out.println("fail");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }                                   

    public ArrayList<Model> Search(String nameSearch) {
        ArrayList<Model> listSearch = new ArrayList<>();
        try {
            Connection con = DriverManager.getConnection(url);
            PreparedStatement stm = con.prepareStatement(Search);
            stm.setString(1, "%" + nameSearch + "%");

            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                String id = rs.getString("id");
                String name = rs.getString("name");
                String status = rs.getString("Status");
                int number = rs.getInt("Number");

                listSearch.add(new Model(id, name, status, number));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listSearch;
    }

}
