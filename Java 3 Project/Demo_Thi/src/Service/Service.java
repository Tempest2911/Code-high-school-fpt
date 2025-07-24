/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import java.util.ArrayList;
import java.sql.*;
import Model.Model;
import javax.swing.JOptionPane;

/**
 *
 * @author drago
 */
public class Service {

    ArrayList<Model> listTK = new ArrayList<>();

    String url = "jdbc:sqlserver://Tempest:1433;databasename=DongVat;trustServerCertificate=true;user=sa;password=123456";
    String select = "Select * from dv";
    String insert = "Insert into dv values(?,?,?,?)";
    String delete = "Delete dv where id = ?";
    String update = "Update dv set name = ?, age = ?, food = ? where id = ? ";
    String Search = "Select * from dv where Name like ?";
    String check = "Select * from dv where id =?";

    public boolean check(Model m) {
        boolean hasError = false;
        try {
            Connection con = DriverManager.getConnection(url);
            PreparedStatement stm = con.prepareStatement(check);
            stm.setString(1, m.getId());
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return hasError;
    }

    public ArrayList<Model> getAll() {
        return listTK;
    }

    public void LoadDataTable() {
        listTK.clear();
        try {
            Connection con = DriverManager.getConnection(url);
            PreparedStatement stm = con.prepareStatement(select);

            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                String id = rs.getString("ID");
                String name = rs.getString("name");
                String age = rs.getString("age");
                String food = rs.getString("food");

                listTK.add(new Model(id, name, age, food));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void add(Model m) {
        if (check(m)) {
            JOptionPane.showMessageDialog(null, "Đã trùng");
            return;
        }
        try {
            Connection con = DriverManager.getConnection(url);
            PreparedStatement stm = con.prepareStatement(insert);
            stm.setString(1, m.getId());
            stm.setString(2, m.getName());
            stm.setString(3, m.getAge());
            stm.setString(4, m.getFood());
            int rs = stm.executeUpdate();

            if (rs > 0) {
                listTK.add(m);
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
            stm.setString(1, m.getId());
            int rs = stm.executeUpdate();
            Model mxoa = null;
            if (rs > 0) {
                for (Model model : listTK) {
                    if (model.getId().equals(m.getId())) {
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

    public void update(Model m) {
        try {
            Connection con = DriverManager.getConnection(url);
            PreparedStatement stm = con.prepareStatement(update);
            stm.setString(1, m.getName());
            stm.setString(2, m.getAge());
            stm.setString(3, m.getFood());
            stm.setString(4, m.getId());
            int rs = stm.executeUpdate();
            if (rs > 0) {
                System.out.println("Success");
            } else {
                System.out.println("Fail");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Model> search(String name) {
        ArrayList<Model> listSearch = new ArrayList<>();

        try {
            Connection con = DriverManager.getConnection(url);
            PreparedStatement stm = con.prepareStatement("Select * from dv where name like ?");
            stm.setString(1, "%" + name + "%");
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                String id = rs.getString("id");
                String foundName = rs.getString("name");
                String age = rs.getString("age");
                String food = rs.getString("food");

                listSearch.add(new Model(id, foundName, age, food));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listSearch;
    }

}
