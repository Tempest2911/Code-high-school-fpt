/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author nguyenvandan
 */
public class Service1forAll {

    static String connectionUrl = "jdbc:sqlserver://TEMPEST:1433;databaseName = Coach_Me_AI; user=sa; password=123456; trustServerCertificate = true";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(connectionUrl);
    }

    public static Object executeQuery(String Query, List<Object> parmas) {
        try {
            Connection con = getConnection();
            PreparedStatement stmt = con.prepareStatement(Query);
            for (int i = 0; i < parmas.size(); i++) {
                stmt.setObject(i + 1, parmas.get(i));
            }
            if (Query.toLowerCase().trim().startsWith("select")) {
                ResultSet rs = stmt.executeQuery();
                return rs;
            } else {
                int row = stmt.executeUpdate();
                if (row > 0) {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
