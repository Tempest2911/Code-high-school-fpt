/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package asm2;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.sql.ResultSet;

/**
 *
 * @author drago
 */
public class SignUpView extends javax.swing.JFrame {

    /**
     * Creates new form SignUpView
     */
    public SignUpView() {
        initComponents();
        signUpView();
    }

    static String insertQuery = "Insert into tk values(?,?,?,?,?,?,?,?)";
    String avatarPath = null;

    public void signUpView() {
        JButton avatar = new JButton("Image here");
        avatar.setSize(150, 200);
        avatar.setLocation(15, 10);
        avatar.setForeground(Color.black);
        avatar.setOpaque(true);
        this.add(avatar);

        JLabel username = new JLabel("Username");
        username.setSize(150, 50);
        username.setLocation(200, 10);
        username.setFont(new Font("Arial", 1, 18));
        this.add(username);

        JLabel password = new JLabel("Password");
        password.setSize(150, 50);
        password.setLocation(200, 70);
        password.setFont(new Font("Arial", 1, 18));
        this.add(password);

        JLabel PassAgain = new JLabel("Re-password");
        PassAgain.setSize(150, 50);
        PassAgain.setLocation(200, 140);
        PassAgain.setFont(new Font("Arial", 1, 18));
        this.add(PassAgain);

        JLabel Name = new JLabel("Name");
        Name.setSize(150, 50);
        Name.setLocation(200, 210);
        Name.setFont(new Font("Arial", 1, 18));
        this.add(Name);

        JLabel Phone = new JLabel("Phone-num");
        Phone.setSize(150, 50);
        Phone.setLocation(200, 280);
        Phone.setFont(new Font("Arial", 1, 18));
        this.add(Phone);

        JLabel Address = new JLabel("Address");
        Address.setSize(150, 50);
        Address.setLocation(200, 350);
        Address.setFont(new Font("Arial", 1, 18));
        this.add(Address);

        JLabel Role = new JLabel("Role");
        Role.setSize(150, 50);
        Role.setLocation(200, 420);
        Role.setFont(new Font("Arial", 1, 18));
        this.add(Role);

        JLabel Status = new JLabel("Status");
        Status.setSize(150, 50);
        Status.setLocation(200, 490);
        Status.setFont(new Font("Arial", 1, 18));
        this.add(Status);

        JTextField txt_username = new JTextField();
        txt_username.setSize(250, 50);
        txt_username.setLocation(330, 10);
        txt_username.setFont(new Font("Arial", 0, 18));
        this.add(txt_username);

        JTextField txt_password = new JTextField();
        txt_password.setSize(250, 50);
        txt_password.setLocation(330, 70);
        txt_password.setFont(new Font("Arial", 0, 18));
        this.add(txt_password);

        JTextField txt_passAgain = new JTextField();
        txt_passAgain.setSize(250, 50);
        txt_passAgain.setLocation(330, 140);
        txt_passAgain.setFont(new Font("Arial", 0, 18));
        this.add(txt_passAgain);

        JTextField txt_Name = new JTextField();
        txt_Name.setSize(250, 50);
        txt_Name.setLocation(330, 210);
        txt_Name.setFont(new Font("Arial", 0, 18));
        this.add(txt_Name);

        JTextField txt_Phone = new JTextField();
        txt_Phone.setSize(250, 50);
        txt_Phone.setLocation(330, 280);
        txt_Phone.setFont(new Font("Arial", 0, 18));
        this.add(txt_Phone);

        JTextField txt_Address = new JTextField();
        txt_Address.setSize(250, 50);
        txt_Address.setLocation(330, 350);
        txt_Address.setFont(new Font("Arial", 0, 18));
        this.add(txt_Address);

        JTextField txt_Role = new JTextField();
        txt_Role.setText("User");
        txt_Role.setEditable(false);
        txt_Role.setSize(250, 50);
        txt_Role.setLocation(330, 420);
        txt_Role.setFont(new Font("Arial", 0, 18));
        this.add(txt_Role);

        JTextField txt_Status = new JTextField();
        txt_Status.setText("1");
        txt_Status.setEditable(false);
        txt_Status.setSize(250, 50);
        txt_Status.setLocation(330, 490);
        txt_Status.setFont(new Font("Arial", 0, 18));
        this.add(txt_Status);

        JButton SignUp = new JButton("SignUp");
        SignUp.setSize(100, 50);
        SignUp.setLocation(330, 560);
        this.add(SignUp);

        JLabel already = new JLabel("Already have an account?");
        already.setSize(150, 30);
        already.setLocation(330, 630);
        this.add(already);

        JButton SignIn = new JButton("SignIn");
        SignIn.setSize(100, 30);
        SignIn.setLocation(470, 630);
        this.add(SignIn);

        avatar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                chooser.setFileFilter(new FileNameExtensionFilter("Image", "jpg", "png", "gif"));
                int returnValue = chooser.showOpenDialog(rootPane);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File file = chooser.getSelectedFile();
                    avatarPath = file.getAbsolutePath();
                    ImageIcon icon = new ImageIcon(file.getAbsolutePath());
                    avatar.setIcon(icon);
                }

            }

        });

        SignIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SignInView signInView = new SignInView();
                signInView.setVisible(true);
                dispose();
            }
        });

        SignUp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = txt_username.getText().trim();
                String password = txt_password.getText().trim();
                String rePassword = txt_passAgain.getText().trim();
                String name = txt_Name.getText().trim();
                String phone = txt_Phone.getText().trim();
                String address = txt_Address.getText().trim();
                String role = txt_Role.getText().trim();
                String status = txt_Status.getText().trim();

                // Kiểm tra nhập liệu
                if (!validateInput(username, password, rePassword, name, phone, address, role, status, avatarPath)) {
                    return;
                }

                // Kiểm tra username trùng
                if (isUsernameTaken(username)) {
                    JOptionPane.showMessageDialog(rootPane, "Username already exists. Please choose another one.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Lưu dữ liệu vào cơ sở dữ liệu
                try (Connection con = DriverManager.getConnection("jdbc:sqlserver://Tempest:1433;databasename=ASM2;trustServerCertificate=true;user=sa;password=123456"); PreparedStatement stmt = con.prepareStatement(insertQuery)) {

                    stmt.setString(1, username);
                    stmt.setString(2, password);
                    stmt.setString(3, name);
                    stmt.setString(4, phone);
                    stmt.setString(5, address);
                    stmt.setString(6, avatarPath);
                    stmt.setString(7, role);
                    stmt.setString(8, status);

                    int result = stmt.executeUpdate();
                    if (result > 0) {
                        JOptionPane.showMessageDialog(rootPane, "Sign up successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(rootPane, "Sign up failed. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                    }

                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(rootPane, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
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

    private boolean validateInput(String username, String password, String rePassword, String name, String phone, String address, String role, String status, String avatarPath) {
        if (username.isEmpty() || password.isEmpty() || rePassword.isEmpty() || phone.isEmpty() || avatarPath == null || name == null || address == null || role == null || status == null) {
            JOptionPane.showMessageDialog(this, "Hãy điền đủ và avatar", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (!password.equals(rePassword)) {
            JOptionPane.showMessageDialog(this, "Passwords ko khớp", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (password.length() < 6) {
            JOptionPane.showMessageDialog(this, "Password ít nhất 6 kí tự", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (!phone.matches("\\d{10,12}")) { // Số điện thoại có từ 10-12 chữ số
            JOptionPane.showMessageDialog(this, "Phone không đúng định dạng", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (!name.matches("^[A-Za-zÀ-ỹ\\s]+$")) {
            JOptionPane.showMessageDialog(this, "Tên ko được chứa số", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 605, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 719, Short.MAX_VALUE)
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
            java.util.logging.Logger.getLogger(SignUpView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SignUpView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SignUpView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SignUpView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SignUpView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
