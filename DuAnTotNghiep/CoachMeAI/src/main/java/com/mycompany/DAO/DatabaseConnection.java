/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.DAO;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.sql.Connection;
// SQL
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


// Collection
import java.util.ArrayList;
import java.util.List;

// Swing UI
import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

// AWT
import java.awt.*;
import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

/**
 *
 * @author vipgl
 */
public class DatabaseConnection {

    static String url = "jdbc:sqlserver://TEMPEST:1433;databaseName = Coach_Me_AI;trustServerCertificate = true";//"jdbc:sqlserver://TUONGMINH-PC:1433;databaseName = Coach_Me_AI; user=sa; password=123456789; trustServerCertificate = true"
    static String user = "sa";  // ✅ Thay bằng tài khoản đúng của bạn
    static String password = "123456"; // ✅ Thay bằng mật khẩu đúng

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
}
