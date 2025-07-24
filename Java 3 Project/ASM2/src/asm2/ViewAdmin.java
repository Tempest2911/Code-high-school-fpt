/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package asm2;

import static asm2.SignUpView.insertQuery;
import java.awt.Color;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import java.sql.Statement;
import java.sql.ResultSet;
import javax.swing.*; // Để sử dụng JFrame, JTable, JScrollPane, v.v.
import javax.swing.table.DefaultTableModel; // Để tạo DefaultTableModel cho JTable
import java.awt.*; // Để sử dụng Color, Font
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.*; //
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author drago
 */
public class ViewAdmin extends javax.swing.JFrame {

    /**
     * Creates new form ViewAdmin
     */
    public ViewAdmin() {
        initComponents();
        admin();
    }
    String avatarPath = null;

    public void admin() {
        // Các thành phần giao diện khác
        JLabel Name = new JLabel("Name");
        Name.setSize(150, 40);
        Name.setLocation(10, 40);
        Name.setFont(new Font("Arial", 1, 15));
        this.add(Name);

        JTextField txt_Name = new JTextField();
        txt_Name.setSize(300, 40);
        txt_Name.setLocation(100, 40);
        this.add(txt_Name);

        JLabel Phone = new JLabel("Phone");
        Phone.setSize(150, 40);
        Phone.setLocation(10, 90);
        Phone.setFont(new Font("Arial", 1, 15));
        this.add(Phone);

        JTextField txt_Phone = new JTextField();
        txt_Phone.setSize(300, 40);
        txt_Phone.setLocation(100, 90);
        this.add(txt_Phone);

        JLabel Address = new JLabel("Address");
        Address.setSize(150, 40);
        Address.setLocation(10, 140);
        Address.setFont(new Font("Arial", 1, 15));
        this.add(Address);

        JTextField txt_Address = new JTextField();
        txt_Address.setSize(300, 40);
        txt_Address.setLocation(100, 140);
        this.add(txt_Address);

        JLabel Role = new JLabel("Role");
        Role.setSize(150, 40);
        Role.setLocation(10, 190);
        Role.setFont(new Font("Arial", 1, 15));
        this.add(Role);

        JTextField txt_Role = new JTextField();
        txt_Role.setEditable(false);
        txt_Role.setSize(300, 40);
        txt_Role.setLocation(100, 190);
        this.add(txt_Role);

        JLabel Status = new JLabel("Status");
        Status.setSize(150, 40);
        Status.setLocation(10, 240);
        Status.setFont(new Font("Arial", 1, 15));
        this.add(Status);

        JTextField txt_Status = new JTextField();
        txt_Status.setEditable(false);
        txt_Status.setSize(300, 40);
        txt_Status.setLocation(100, 240);
        this.add(txt_Status);

        JButton btn_Khoa = new JButton("Khoa");
        btn_Khoa.setSize(80, 30);
        btn_Khoa.setLocation(30, 290);
        this.add(btn_Khoa);

        JButton btn_MKhoa = new JButton("Mo khoa");
        btn_MKhoa.setSize(80, 30);
        btn_MKhoa.setLocation(130, 290);
        this.add(btn_MKhoa);

        JButton btn_Edit = new JButton("Edit");
        btn_Edit.setSize(80, 30);
        btn_Edit.setLocation(230, 290);
        this.add(btn_Edit);

        JButton btn_Image = new JButton("Image");
        btn_Image.setSize(80, 30);
        btn_Image.setLocation(330, 290);
        this.add(btn_Image);

        JLabel avatar = new JLabel("Image here");
        avatar.setSize(350, 300);
        avatar.setLocation(430, 20);
// Căn chỉnh chữ ở giữa
        avatar.setHorizontalAlignment(SwingConstants.CENTER); // Căn giữa theo chiều ngang
        avatar.setVerticalAlignment(SwingConstants.CENTER); // Căn giữa theo chiều dọc

// Thêm JLabel vào JFrame
        this.add(avatar, BorderLayout.CENTER);

        btn_Image.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (!txt_Role.getText().equals("Admin")) {
                    JOptionPane.showMessageDialog(rootPane, "Admin không được edit thông tin của người khác!!!!");
                } else {
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
            }
        }
        );

        // Bổ sung JTable để hiển thị dữ liệu                                                                       
        JTable table = new JTable();
        DefaultTableModel model = new DefaultTableModel();

        // Thêm các cột
        model.addColumn(
                "Username");
        model.addColumn(
                "Name");
        model.addColumn(
                "Phone");
        model.addColumn(
                "Address");
        model.addColumn(
                "Role");
        model.addColumn(
                "Status"); // Thêm cột Avatar

        table.setModel(model);

        // Lấy dữ liệu từ cơ sở dữ liệu
        loadDataToTable(model);

        // Thêm JTable vào JScrollPane
        JScrollPane scrollPane = new JScrollPane(table);

        scrollPane.setSize(
                800, 200);
        scrollPane.setLocation(
                10, 350);

        this.add(scrollPane);

        btn_Edit.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                if (!txt_Role.getText().equals("Admin")) {
                    JOptionPane.showMessageDialog(rootPane, "Admin không được edit thông tin của người khác!!!!");
                } else {

                    editUserData(txt_Name.getText(), txt_Phone.getText(), txt_Address.getText(), txt_Role.getText(), txt_Status.getText(), avatarPath);
                    model.setRowCount(0);
                    loadDataToTable(model);

                }
            }
        }
        );

        table.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt
            ) {
                int selectedRow = table.getSelectedRow(); // Lấy hàng được chọn
                if (selectedRow != -1) {
                    // Lấy dữ liệu từ bảng
                    String username = model.getValueAt(selectedRow, 0).toString(); // Cột Username là cột đầu tiên
                    txt_Name.setText(model.getValueAt(selectedRow, 1).toString());
                    txt_Phone.setText(model.getValueAt(selectedRow, 2).toString());
                    txt_Address.setText(model.getValueAt(selectedRow, 3).toString());
                    txt_Role.setText(model.getValueAt(selectedRow, 4).toString());
                    txt_Status.setText(model.getValueAt(selectedRow, 5).toString());

                    // Lấy đường dẫn avatar từ cơ sở dữ liệu
                    String avatarPath = getAvatarPathFromDatabase(username); // Truy vấn từ cơ sở dữ liệu
                    setAvatarImage(avatar, avatarPath); // Hiển thị ảnh lên JButton
                }
            }
        }
        );

        btn_Khoa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (txt_Role.getText().equals("Admin")) {
                    JOptionPane.showMessageDialog(rootPane, "Admin ko thể khóa chính mình");
                } else {
                    int selectedRow = table.getSelectedRow(); // Lấy hàng được chọn
                    if (selectedRow != -1) {
                        String username = model.getValueAt(selectedRow, 0).toString();

                        int confirm = JOptionPane.showConfirmDialog(
                                rootPane,
                                "Bạn có chắc muốn khóa tài khoản này?",
                                "Xác nhận",
                                JOptionPane.YES_NO_OPTION
                        );

                        if (confirm == JOptionPane.YES_OPTION) {
                            updateUserStatus(username, -1); // Cập nhật trạng thái thành -1
                            model.setValueAt("-1", selectedRow, 5); // Cập nhật trên bảng
                            JOptionPane.showMessageDialog(rootPane, "Đã khóa tài khoản!");
                        }
                    } else {
                        JOptionPane.showMessageDialog(rootPane, "Vui lòng chọn một tài khoản để khóa!");
                    }
                }

            }
        });

        btn_MKhoa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (txt_Role.getText().equals("Admin")) {
                    JOptionPane.showMessageDialog(rootPane, "Admin không được khóa thì mở khóa cũng không được");
                } else {
                    int selectedRow = table.getSelectedRow(); // Lấy hàng được chọn
                    if (selectedRow != 1) {
                        String username = model.getValueAt(selectedRow, 0).toString();

                        int confirm = JOptionPane.showConfirmDialog(
                                rootPane,
                                "Bạn có chắc muốn mở khóa tài khoản này?",
                                "Xác nhận",
                                JOptionPane.YES_NO_OPTION
                        );

                        if (confirm == JOptionPane.YES_OPTION) {
                            updateUserStatus(username, 1); // Cập nhật trạng thái thành -1
                            model.setValueAt("1", selectedRow, 5); // Cập nhật trên bảng
                            JOptionPane.showMessageDialog(rootPane, "Đã mở khóa tài khoản!");
                        }
                    } else {
                        JOptionPane.showMessageDialog(rootPane, "Vui lòng chọn một tài khoản để khóa!");
                    }
                }
            }
        }
        );

    }

    private void updateUserStatus(String username, int status) {
        String url = "jdbc:sqlserver://Tempest:1433;databasename=ASM2;trustServerCertificate=true;user=sa;password=123456";
        String sql = "UPDATE tk SET STATUS = ? WHERE USERNAME = ?";

        try (Connection connection = DriverManager.getConnection(url); PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, status);
            preparedStatement.setString(2, username);

            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Status updated successfully for user: " + username);
            } else {
                System.err.println("Failed to update status for user: " + username);
            }
        } catch (SQLException e) {
            System.err.println("Error updating user status: " + e.getMessage());
        }
    }

    private String getAvatarPathFromDatabase(String username) {
        String avatarPath = null;
        String url = "jdbc:sqlserver://Tempest:1433;databasename=ASM2;trustServerCertificate=true;user=sa;password=123456";

        try (Connection connection = DriverManager.getConnection(url)) {
            String sql = "SELECT AVATAR FROM tk WHERE USERNAME = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                avatarPath = resultSet.getString("AVATAR");
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving avatar path: " + e.getMessage());
        }

        return avatarPath;
    }

// Phương thức load dữ liệu từ cơ sở dữ liệu vào bảng
    private void loadDataToTable(DefaultTableModel model) {
        String url = "jdbc:sqlserver://Tempest:1433;databasename=ASM2;trustServerCertificate=true;user=sa;password=123456";

        try (Connection connection = DriverManager.getConnection(url)) {
            String sql = "SELECT USERNAME, NAME, PHONE, ADDRESS, ROLE, STATUS, AVATAR FROM tk";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                String username = resultSet.getString("USERNAME");
                String name = resultSet.getString("NAME");
                String phone = resultSet.getString("PHONE");
                String address = resultSet.getString("ADDRESS");
                String role = resultSet.getString("ROLE");
                String status = resultSet.getString("STATUS");
                String avatar = resultSet.getString("AVATAR");  // Lấy đường dẫn avatar

                model.addRow(new Object[]{username, name, phone, address, role, status, avatar});
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error loading data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

// Phương thức hiển thị ảnh trên JButton
    private void setAvatarImage(JLabel avatarButton, String avatarPath) {
        try {
            if (avatarPath != null && !avatarPath.isEmpty()) {
                // Kiểm tra xem tệp ảnh có tồn tại không
                File imageFile = new File(avatarPath);
                if (imageFile.exists()) {
                    // Tạo đối tượng ImageIcon từ file ảnh
                    ImageIcon imageIcon = new ImageIcon(imageFile.getAbsolutePath());

                    // Lấy kích thước JButton
                    int buttonWidth = avatarButton.getWidth();
                    int buttonHeight = avatarButton.getHeight();

                    // Co giãn ảnh sao cho vừa với JButton
                    Image image = imageIcon.getImage().getScaledInstance(buttonWidth, buttonHeight, Image.SCALE_SMOOTH);

                    // Cập nhật icon cho JButton
                    avatarButton.setIcon(new ImageIcon(image));
                } else {
                    avatarButton.setText("No Image");
                }
            } else {
                avatarButton.setText("No Image");
            }
        } catch (Exception e) {
            avatarButton.setText("Error");
            System.err.println("Error loading image: " + e.getMessage());
        }
    }

    private void editUserData(String name,
            String phone, String address, String role, String status, String avatar) {
        String url = "jdbc:sqlserver://Tempest:1433;databasename=ASM2;trustServerCertificate=true;user=sa;password=123456";

        // Câu lệnh SQL để cập nhật dữ liệu
        String sql = "UPDATE tk SET NAME = ?, PHONE = ?, ADDRESS = ?, AVATAR =?, ROLE = ?, STATUS = ? WHERE NAME = ?";

        try (Connection connection = DriverManager.getConnection(url); PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            // Gán giá trị cho các tham số
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, phone);
            preparedStatement.setString(3, address);
            preparedStatement.setString(4, avatar);
            preparedStatement.setString(5, role);
            preparedStatement.setString(6, status);
            preparedStatement.setString(7, name);

            // Thực thi lệnh SQL
            int rowsUpdated = preparedStatement.executeUpdate();

            if (rowsUpdated > 0) {
                JOptionPane.showMessageDialog(this, "Admin updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Update eror", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error updating user: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 841, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 708, Short.MAX_VALUE)
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
            java.util.logging.Logger.getLogger(ViewAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ViewAdmin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
