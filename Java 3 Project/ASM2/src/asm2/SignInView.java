/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package asm2;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author drago
 */
public class SignInView extends javax.swing.JFrame {

    public SignInView() {
        initComponents();
        signIn();
    }
    String checkUserQuery = "SELECT username FROM tk WHERE username = ?";
    String checkPasswordQuery = "SELECT * FROM tk WHERE username = ? AND password = ?";

    public void signIn() {

        JTextField txt_Username = new JTextField();
        JPasswordField txt_Password = new JPasswordField();

        JLabel Username = new JLabel("Username");
        JLabel Password = new JLabel("Password");
        JLabel text = new JLabel("No account?");
        JLabel KH = new JLabel("Kích hoạt tk?");
        JButton btn_SignIn = new JButton("SignIn");
        JButton btn_SignUp = new JButton("SignUp");
        JButton btn_KH = new JButton("Active");

        Username.setSize(200, 100);
        Username.setLocation(40, 10);
        Username.setFont(new Font("Arial", 1, 15));
        this.add(Username);

        Password.setSize(200, 100);
        Password.setLocation(40, 70);
        Password.setFont(new Font("Arial", 1, 15));
        this.add(Password);

        txt_Username.setSize(300, 40);
        txt_Username.setLocation(150, 40);
        this.add(txt_Username);

        txt_Password.setSize(300, 40);
        txt_Password.setLocation(150, 100);
        this.add(txt_Password);

        btn_SignIn.setSize(100, 40);
        btn_SignIn.setLocation(150, 150);
        btn_SignIn.setFont(new Font("Arial", 1, 15));
        this.add(btn_SignIn);

        btn_SignUp.setSize(100, 40);
        btn_SignUp.setLocation(250, 200);
        btn_SignUp.setFont(new Font("Arial", 1, 15));
        this.add(btn_SignUp);

        text.setSize(200, 40);
        text.setLocation(150, 200);
        text.setFont(new Font("Arial", 0, 15));
        this.add(text);

        KH.setSize(200, 40);
        KH.setLocation(150, 250);
        KH.setFont(new Font("Arial", 0, 15));
        this.add(KH);

        btn_KH.setSize(100, 40);
        btn_KH.setLocation(250, 250);
        btn_KH.setFont(new Font("Arial", 1, 15));
        this.add(btn_KH);

        btn_SignUp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SignUpView signUp = new SignUpView();
                signUp.setVisible(true);
                dispose();
            }
        });

        btn_SignIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = txt_Username.getText().trim();
                String password = txt_Password.getText().trim();

                if (username.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(rootPane, "Please enter both username and password.");
                    return;
                }

                String url = "jdbc:sqlserver://Tempest:1433;databasename=ASM2;trustServerCertificate=true;user=sa;password=123456";
                try (Connection con = DriverManager.getConnection(url)) {
                    try (PreparedStatement stmtUser = con.prepareStatement(checkUserQuery)) {
                        stmtUser.setString(1, username);
                        ResultSet rsUser = stmtUser.executeQuery();
                        if (!isAccountActive(username)) {
                            JOptionPane.showMessageDialog(rootPane, "Tài khoản đã bị khóa! Liên hệ quản trị viên để mở khóa.");
                        } else {
                            if (rsUser.next()) {
                                try (PreparedStatement stmtPassword = con.prepareStatement(checkPasswordQuery)) {
                                    stmtPassword.setString(1, username);
                                    stmtPassword.setString(2, password);
                                    ResultSet rsPassword = stmtPassword.executeQuery();

                                    if (rsPassword.next()) {

                                        if (username.equals("admin")) {
                                            JOptionPane.showMessageDialog(rootPane, "Admin Login successful!");
                                            ViewAdmin viewAdmin = new ViewAdmin();
                                            viewAdmin.setVisible(true);
                                            dispose();
                                        } else {
                                            JOptionPane.showMessageDialog(rootPane, "User Login successful!");
                                            UserView userView = new UserView(username);
                                            userView.setVisible(true);
                                            dispose();
                                        }
                                    } else {
                                        JOptionPane.showMessageDialog(rootPane, "Invalid password. Please try again.");
                                    }
                                }
                            } else {
                                JOptionPane.showMessageDialog(rootPane, "Username does not exist.");
                            }
                        }

                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(rootPane, "Database error: " + ex.getMessage());
                    ex.printStackTrace();
                }
            }
        });

        btn_KH.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TaiKichHoat kichHoat = new TaiKichHoat();
                kichHoat.setVisible(true);
                dispose();
            }
        });
    }

    private boolean isAccountActive(String username) {
        String url = "jdbc:sqlserver://Tempest:1433;databasename=ASM2;trustServerCertificate=true;user=sa;password=123456";
        String sql = "SELECT STATUS FROM tk WHERE USERNAME = ?";

        try (Connection connection = DriverManager.getConnection(url); PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int status = resultSet.getInt("STATUS");
                return status != -1; // Nếu STATUS = -1 thì không thể đăng nhập
            }
        } catch (SQLException e) {
            System.err.println("Error checking account status: " + e.getMessage());
        }
        return false;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 537, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 337, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(SignInView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SignInView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SignInView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SignInView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SignInView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
