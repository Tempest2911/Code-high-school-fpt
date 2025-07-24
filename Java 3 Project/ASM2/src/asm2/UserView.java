/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package asm2;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author drago
 */
public class UserView extends javax.swing.JFrame {

    /**
     * Creates new form UserView
     */
    public UserView(String UsernameTable) {
        this.UsernameTable = UsernameTable;
        initComponents(); // Khởi tạo giao diện
        user();           // Hiển thị các thành phần giao diện
    }

    public String UsernameTable;

    public UserView() {
    }

    static String updateQuery = "update account set password = ? where username = ?";
    String url = "jdbc:sqlserver://Tempest:1433;databasename=ASM2;trustServerCertificate=true;user=sa;password=123456";
    String avatarPath = null;

    public void user() {
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

        JTextField txt_SDT = new JTextField();
        txt_SDT.setSize(300, 40);
        txt_SDT.setLocation(100, 90);
        this.add(txt_SDT);

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

        JTextField txt_status = new JTextField();
        txt_status.setEditable(false);
        txt_status.setSize(300, 40);
        txt_status.setLocation(100, 240);
        this.add(txt_status);

        JLabel Username = new JLabel("Username");
        Username.setSize(150, 40);
        Username.setLocation(10, 290);
        Username.setFont(new Font("Arial", 1, 15));
        this.add(Username);

        JTextField txt_Username = new JTextField();
        txt_Username.setSize(300, 40);
        txt_Username.setLocation(100, 290);
        this.add(txt_Username);

        JLabel Password = new JLabel("Password");
        Password.setSize(150, 40);
        Password.setLocation(10, 340);
        Password.setFont(new Font("Arial", 1, 15));
        this.add(Password);

        JTextField txt_Password = new JTextField();
        txt_Password.setSize(300, 40);
        txt_Password.setLocation(100, 340);
        this.add(txt_Password);

        JButton btn_Edit = new JButton("Edit");
        btn_Edit.setSize(80, 30);
        btn_Edit.setLocation(30, 390);
        this.add(btn_Edit);

        JButton btn_Delete = new JButton("Delete");
        btn_Delete.setSize(80, 30);
        btn_Delete.setLocation(130, 390);
        this.add(btn_Delete);

        JButton btn_EditImg = new JButton("Image");
        btn_EditImg.setSize(80, 30);
        btn_EditImg.setLocation(230, 390);
        this.add(btn_EditImg);

        JButton btn_Lock = new JButton("Lock");
        btn_Lock.setSize(80, 30);
        btn_Lock.setLocation(330, 390);
        this.add(btn_Lock);

        JLabel avatar = new JLabel("Image here");
        avatar.setSize(350, 400);
        avatar.setLocation(450, 30);
        avatar.setHorizontalAlignment(SwingConstants.CENTER);
        avatar.setVerticalAlignment(SwingConstants.CENTER);
        this.add(avatar, BorderLayout.CENTER);

        btn_Delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txt_Address.setText("");
                txt_Name.setText("");
                txt_Password.setText("");
                txt_Role.setText("");
                txt_SDT.setText("");
                txt_Username.setText("");
                txt_status.setText("");
            }
        });

        btn_Edit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editUserData(txt_Username.getText(), txt_Password.getText(), txt_Name.getText(),
                        txt_SDT.getText(), txt_Address.getText(), avatarPath, txt_Role.getText(), txt_status.getText());
            }
        });

        btn_EditImg.addActionListener(new ActionListener() {
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

        JTable table = new JTable();
        DefaultTableModel model = new DefaultTableModel();
        // Thêm các cột
        model.addColumn("Username");
        model.addColumn("Password");
        model.addColumn("Name");
        model.addColumn("Phone");
        model.addColumn("Address");
        model.addColumn("Role");
        model.addColumn("Status"); // Thêm cột Avatar

        table.setModel(model);

        // Lấy dữ liệu từ cơ sở dữ liệu
        loadDataToTable(model);

        // Thêm JTable vào JScrollPane
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setSize(800, 200);
        scrollPane.setLocation(15, 450);
        this.add(scrollPane);

        table.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int selectedRow = table.getSelectedRow(); // Lấy hàng được chọn
                if (selectedRow != -1) {
                    // Lấy dữ liệu từ bảng
                    String username = model.getValueAt(selectedRow, 0).toString();
                    txt_Username.setText(model.getValueAt(selectedRow, 0).toString());
                    txt_Password.setText(model.getValueAt(selectedRow, 1).toString());
                    txt_Name.setText(model.getValueAt(selectedRow, 2).toString());
                    txt_SDT.setText(model.getValueAt(selectedRow, 3).toString());
                    txt_Address.setText(model.getValueAt(selectedRow, 4).toString());
                    txt_Role.setText(model.getValueAt(selectedRow, 5).toString());
                    txt_status.setText(model.getValueAt(selectedRow, 6).toString());

                    String avatarPath = getAvatarPathFromDatabase(username); // Truy vấn từ cơ sở dữ liệu
                    setAvatarImage(avatar, avatarPath); // Hiển thị ảnh lên JButton

                    avatar.setText("");
                }
            }
        });

        btn_Lock.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow(); // Lấy hàng được chọn
                if (selectedRow != -1) {
                    String username = model.getValueAt(selectedRow, 0).toString();

                    int confirm = JOptionPane.showConfirmDialog(
                            rootPane,
                            "Bạn có chắc muốn khóa tài khoản?",
                            "Xác nhận",
                            JOptionPane.YES_NO_OPTION
                    );

                    if (confirm == JOptionPane.YES_OPTION) {
                        updateUserStatus(username, -1); // Cập nhật trạng thái thành -1
                        model.setValueAt("-1", selectedRow, 5); // Cập nhật trên bảng
                        JOptionPane.showMessageDialog(rootPane, "Đã khóa tài khoản!");
                        SignInView signInView = new SignInView();
                        signInView.setVisible(true);
                        dispose();
                    }
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Vui lòng chọn một tài khoản để khóa!");
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

    private void editUserData(String username, String password, String name,
            String phone, String address, String avatar, String role, String status) {
        String url = "jdbc:sqlserver://Tempest:1433;databasename=ASM2;trustServerCertificate=true;user=sa;password=123456";

        // Câu lệnh SQL để cập nhật dữ liệu
        String sql = "UPDATE tk SET PASSWORD = ?, NAME = ?, PHONE = ?, ADDRESS = ?, AVATAR =?, ROLE = ?, STATUS = ? WHERE USERNAME = ?";

        try (Connection connection = DriverManager.getConnection(url); PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            // Gán giá trị cho các tham số
            preparedStatement.setString(1, password);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, phone);
            preparedStatement.setString(4, address);
            preparedStatement.setString(5, avatar);
            preparedStatement.setString(6, role);
            preparedStatement.setString(7, status);
            preparedStatement.setString(8, username);

            // Thực thi lệnh SQL
            int rowsUpdated = preparedStatement.executeUpdate();

            if (rowsUpdated > 0) {
                JOptionPane.showMessageDialog(this, "User updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "No user found with the given username.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error updating user: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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

    private void loadDataToTable(DefaultTableModel model) {
        String url = "jdbc:sqlserver://Tempest:1433;databasename=ASM2;trustServerCertificate=true;user=sa;password=123456";

        try (Connection connection = DriverManager.getConnection(url)) {
            String sql = "SELECT USERNAME, PASSWORD, NAME, PHONE, ADDRESS, ROLE, STATUS FROM tk where USERNAME = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, UsernameTable); 
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                String username = rs.getString("USERNAME");
                String password = rs.getString("PASSWORD");
                String name = rs.getString("NAME");
                String phone = rs.getString("PHONE");
                String address = rs.getString("ADDRESS");
                String role = rs.getString("ROLE");
                String status = rs.getString("STATUS");

                model.addRow(new Object[]{username, password, name, phone, address, role, status});
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error loading data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
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
            .addGap(0, 842, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 709, Short.MAX_VALUE)
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
            java.util.logging.Logger.getLogger(UserView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UserView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UserView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UserView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UserView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
