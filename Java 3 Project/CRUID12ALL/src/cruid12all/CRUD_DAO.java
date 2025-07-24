/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cruid12all;

import java.sql.*;
import java.util.List;

/**
 *
 * @author drago
 */
public class CRUD_DAO {

    static String connectionUrl = "jdbc:sqlserver://TEMPEST:1433;databaseName=sd1803_SOF2042;user=sa;password=123456;trustServerCertificate=true";

    // static cho phep 1 thanh phan class co the goi truc tiep
    // ko can khoi tao doi tuong goi ten qua class
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(connectionUrl);

    }

    public static void excutequery(String query, List<Object> params) {
        try {
            Connection conn = getConnection();
            PreparedStatement stm = conn.prepareStatement(query);
            // injact data tu params vao cac dau ? cua statemant
            for (int i = 0; i < params.size(); i++) { // lap qua params
                stm.setObject(i + 1, params.get(i)); // inject tu phan tu cua params
            }
            if (query.toLowerCase().trim().startsWith("select")) {
                System.out.println("ok");
                ResultSet rs = stm.executeQuery();
                while (rs.next()) {
                    System.out.println(rs.getObject(1) + "    " + rs.getObject(2) + "    " + rs.getObject(3) + "    " + rs.getObject(4)); // lay ra vi tri cot dau tien
                }

            } else {
                int row = stm.executeUpdate();
                System.out.println("ban da " + query.trim().split(" ")[0] + "  " + row + "ban ghi");// split chp phep cat chuoi thanh 1 mang
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
