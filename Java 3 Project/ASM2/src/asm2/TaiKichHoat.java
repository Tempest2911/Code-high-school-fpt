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
import javax.swing.JTextField;

/**
 *
 * @author drago
 */
public class TaiKichHoat extends javax.swing.JFrame {

    /**
     * Creates new form TaiKichHoat
     */
    public TaiKichHoat() {
        initComponents();
        TaiTK();
    }

    public void TaiTK() {
        JLabel username = new JLabel("Username");
        username.setSize(150, 50);
        username.setLocation(50, 30);
        username.setFont(new Font("Arial", 1, 18));
        this.add(username);

        JLabel password = new JLabel("Password");
        password.setSize(150, 50);
        password.setLocation(50, 80);
        password.setFont(new Font("Arial", 1, 18));
        this.add(password);

        JLabel Phone = new JLabel("Phone-num");
        Phone.setSize(150, 50);
        Phone.setLocation(50, 130);
        Phone.setFont(new Font("Arial", 1, 18));
        this.add(Phone);

        JTextField txt_username = new JTextField();
        txt_username.setSize(250, 40);
        txt_username.setLocation(170, 30);
        txt_username.setFont(new Font("Arial", 0, 18));
        this.add(txt_username);

        JTextField txt_password = new JTextField();
        txt_password.setSize(250, 40);
        txt_password.setLocation(170, 80);
        txt_password.setFont(new Font("Arial", 0, 18));
        this.add(txt_password);

        JTextField txt_Phone = new JTextField();
        txt_Phone.setSize(250, 40);
        txt_Phone.setLocation(170, 130);
        txt_Phone.setFont(new Font("Arial", 0, 18));
        this.add(txt_Phone);

        JButton btn_KH = new JButton("Kích hoạt tài khoản");
        btn_KH.setSize(250, 50);
        btn_KH.setLocation(170, 200);
        btn_KH.setFont(new Font("Arial", 0, 18));
        this.add(btn_KH);

        btn_KH.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = txt_username.getText().trim();
                String password = txt_password.getText().trim();
                String phone = txt_Phone.getText().trim();

                if (!validateInput(username, password, phone)) {
                    return;
                }

                if (!isUsernameTaken(username)) {
                    JOptionPane.showMessageDialog(rootPane, "Username không tồn tại. Vui lòng thử lại.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (!isPhoneAndPasswordValid(username, phone, password)) {
                    JOptionPane.showMessageDialog(rootPane, "Phone hoặc Password không đúng. Vui lòng thử lại.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (!isAccountActive(username)) {
                    updateUserStatus(username, 1); // Cập nhật trạng thái thành 1
                    JOptionPane.showMessageDialog(rootPane, "Đã mở khóa tài khoản!");
                    SignInView signInView = new SignInView();
                    signInView.setVisible(true);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Tài khoản này chưa bị khóa.");
                }
            }
        });

    }

    private void updateUserStatus(String username, int status) {
        String url = "jdbc:sqlserver://Tempest:1433;databasename=ASM2;trustServerCertificate=true;user=sa;password=123456";
        String sql = "UPDATE tk SET STATUS = ? WHERE USERNAME = ?";

        try (Connection connection = DriverManager.getConnection(url); PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, status);
            preparedStatement.setString(2, username);


        } catch (SQLException e) {
            System.err.println("Error updating user status: " + e.getMessage());
        }
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

    private boolean isUsernameTaken(String username) {
        String checkQuery = "SELECT COUNT(*) FROM tk WHERE USERNAME = ?";
        try (Connection con = DriverManager.getConnection("jdbc:sqlserver://Tempest:1433;databasename=ASM2;trustServerCertificate=true;user=sa;password=123456"); PreparedStatement stmt = con.prepareStatement(checkQuery)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private boolean isPhoneAndPasswordValid(String username, String phone, String password) {
        String query = "SELECT COUNT(*) FROM tk WHERE USERNAME = ? AND PHONE = ? AND PASSWORD = ?";
        try (Connection con = DriverManager.getConnection("jdbc:sqlserver://Tempest:1433;databasename=ASM2;trustServerCertificate=true;user=sa;password=123456"); PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1, username);
            stmt.setString(2, phone);
            stmt.setString(3, password);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0; // Nếu COUNT > 0, thông tin hợp lệ
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Nếu có lỗi hoặc thông tin không hợp lệ
    }

    private boolean validateInput(String username, String password, String phone) {
        if (username.isEmpty() || password.isEmpty() || phone.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Hãy điền đủ", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 571, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 388, Short.MAX_VALUE)
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
            java.util.logging.Logger.getLogger(TaiKichHoat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TaiKichHoat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TaiKichHoat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TaiKichHoat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TaiKichHoat().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
