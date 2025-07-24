/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package sqlconnectiondemo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author drago
 */
public class SQLConnectionDemo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String url = "jdbc:sqlserver://Tempest:1433;databasename=ABC;trustServerCertificate=true;user=sa;password=123456";

        try (Connection con = DriverManager.getConnection(url); Statement stmt = con.createStatement();) {
            String SQL = "SELECT TOP 10 * FROM Test";
            ResultSet rs = stmt.executeQuery(SQL);

            while (rs.next()) {
                System.out.println(rs.getString("ID")+" "+ rs.getString("Name"));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
