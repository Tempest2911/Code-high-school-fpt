/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package btjdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;

/**
 *
 * @author drago
 */
public class BTJDBC {

    static String connectionUrl = "jdbc:sqlserver://Tempest:1433;databasename=Account;trustServerCertificate=true;user=sa;password=123456";
    static String selectQuery = "SELECT TOP 10 * FROM account where username = ? and password = ?";
    static String insertQuery = "Insert into account values(?,?)";
    static String updateQuery = "update account set password = ? where username = ?";
    static String deleteQuery = "delete account where username = ?";

    public static boolean checkLogin(String username, String password) {
        try (Connection con = DriverManager.getConnection(connectionUrl); PreparedStatement stmt = con.prepareStatement(selectQuery);) {
            stmt.setString(1, username); //truyền giá trị cho dấu ?
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                System.out.println(rs.getString("username") + " " + rs.getString("password"));
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean createAccount(String username, String password) {
        try (Connection con = DriverManager.getConnection(connectionUrl); PreparedStatement stmt = con.prepareStatement(insertQuery);) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            int result = stmt.executeUpdate(); //lấy dữ liệu khi chạy truy vấn

            if (result > 0) { //kiểm tra result có dữ liệu hay không
                System.out.println("Bạn đã thêm được " + result + " dòng");
                return true;
            } else {
                System.out.println("Thất bại");
            }
        } catch (SQLException e) {
            System.out.println("Thất bại");
            e.printStackTrace();
        }
        return false;
    }

    public static boolean updateAccount(String username, String password) {
        try (Connection con = DriverManager.getConnection(connectionUrl); PreparedStatement stmt = con.prepareStatement(updateQuery);) {
            stmt.setString(2, username);
            stmt.setString(1, password);
            int result = stmt.executeUpdate();

            if (result > 0) {
                System.out.println("Bạn đã sửa được " + result + " dòng");
                return true;
            } else {
                System.out.println("Thất bại");
            }
        } catch (SQLException e) {
            System.out.println("Thất bại");
            e.printStackTrace();
        }
        return false;
    }

    public static boolean deleteAccount(String username, String password) {
        try (Connection con = DriverManager.getConnection(connectionUrl); PreparedStatement stmt = con.prepareStatement(deleteQuery);) {
            stmt.setString(1, username);
            int result = stmt.executeUpdate();

            if (result > 0) {
                System.out.println("Bạn đã xóa được " + result + " dòng");
                return true;
            } else {
                System.out.println("Thất bại");
            }
        } catch (SQLException e) {
            System.out.println("Thất bại");
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) {
//        checkLogin("phong", "123456");
//        createAccount("hung", "123456");
//        updateAccount("admin", "123");

    }

}
