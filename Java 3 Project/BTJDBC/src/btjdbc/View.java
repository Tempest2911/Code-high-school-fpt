/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package btjdbc;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author drago
 */
public class View extends javax.swing.JFrame {

    ArrayList<String> allAccount = new ArrayList<>();

    /**
     * Creates new form View
     */
    public View() {
        initComponents();
        Login();
    }

    public void Login() {
        JTextField txt_Username = new JTextField();
        JPasswordField txt_Password = new JPasswordField();
        JLabel Username = new JLabel("Username");
        JLabel Password = new JLabel("Password");
        JButton btn_Login = new JButton("Login");

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

        btn_Login.setSize(100, 40);
        btn_Login.setLocation(150, 150);
        btn_Login.setFont(new Font("Arial", 1, 15));
        this.add(btn_Login);

        String[] columnNames = {"Username", "Password"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(40, 40, 600, 300);
        this.add(scrollPane);
        scrollPane.setVisible(false);

        btn_Login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = txt_Username.getText();
                String password = txt_Password.getText();
                String url = "jdbc:sqlserver://Tempest:1433;databasename=Account;trustServerCertificate=true;User=sa;Password=123456";
                try (Connection con = DriverManager.getConnection(url);) {
                    String SQL = "SELECT * FROM Account WHERE Username = ? AND Password = ?";
                    PreparedStatement loginStmt = con.prepareStatement(SQL);
                    loginStmt.setString(1, username);
                    loginStmt.setString(2, password);
                    ResultSet loginRs = loginStmt.executeQuery();

                    if (loginRs.next()) {
                        JOptionPane.showMessageDialog(rootPane, "Login successfully");
                        Username.setVisible(false);
                        Password.setVisible(false);
                        txt_Username.setVisible(false);
                        txt_Password.setVisible(false);
                        btn_Login.setVisible(false);
                        scrollPane.setVisible(true);
                        model.setRowCount(0);

                    } else {
                        JOptionPane.showMessageDialog(rootPane, "Incorrect Username or Password");
                    }

                    String SQL1 = "SELECT * FROM Account";
                    try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(SQL1)) {

                        while (rs.next()) {
                            String user = rs.getString("Username");
                            String pass = rs.getString("Password");
                            model.addRow(new Object[]{user, pass});
                        }
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }
        });

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 670, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 551, Short.MAX_VALUE)
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
            java.util.logging.Logger.getLogger(View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new View().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
