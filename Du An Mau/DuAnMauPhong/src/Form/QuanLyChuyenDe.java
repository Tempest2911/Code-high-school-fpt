/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Form;

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
import DAOClass.CRUD_DAO;
import java.util.ArrayList;
import java.text.DecimalFormat;

/**
 *
 * @author drago
 */
public class QuanLyChuyenDe extends javax.swing.JFrame {

    /**
     * Creates new form ViewAdmin
     */
    public QuanLyChuyenDe() {
        initComponents();
        admin();
    }
    String avatarPath = null;

    public void admin() {

        // Các thành phần giao diện khác
        JLabel ID = new JLabel("ID");
        ID.setSize(150, 40);
        ID.setLocation(10, 40);
        ID.setFont(new Font("Arial", 1, 15));
        this.add(ID);

        JTextField txt_ID = new JTextField();
        txt_ID.setSize(300, 40);
        txt_ID.setLocation(100, 40);
        this.add(txt_ID);

        JLabel Name = new JLabel("Name");
        Name.setSize(150, 40);
        Name.setLocation(10, 90);
        Name.setFont(new Font("Arial", 1, 15));
        this.add(Name);

        JTextField txt_Name = new JTextField();
        txt_Name.setSize(300, 40);
        txt_Name.setLocation(100, 90);
        this.add(txt_Name);

        JLabel Describe = new JLabel("Describe");
        Describe.setSize(150, 40);
        Describe.setLocation(10, 140);
        Describe.setFont(new Font("Arial", 1, 15));
        this.add(Describe);

        JTextField txt_Describe = new JTextField();
        txt_Describe.setSize(300, 40);
        txt_Describe.setLocation(100, 140);
        this.add(txt_Describe);

        JLabel Price = new JLabel("Price");
        Price.setSize(150, 40);
        Price.setLocation(10, 190);
        Price.setFont(new Font("Arial", 1, 15));
        this.add(Price);

        JTextField txt_Price = new JTextField();
        txt_Price.setSize(300, 40);
        txt_Price.setLocation(100, 190);
        this.add(txt_Price);

        JButton btn_Create = new JButton("Create");
        btn_Create.setSize(80, 30);
        btn_Create.setLocation(30, 290);
        this.add(btn_Create);

        JButton btn_Delete = new JButton("Delete");
        btn_Delete.setSize(80, 30);
        btn_Delete.setLocation(130, 290);
        this.add(btn_Delete);

        JButton btn_Edit = new JButton("Update");
        btn_Edit.setSize(80, 30);
        btn_Edit.setLocation(230, 290);
        this.add(btn_Edit);

        JButton btn_Image = new JButton("Image");
        btn_Image.setSize(80, 30);
        btn_Image.setLocation(330, 290);
        this.add(btn_Image);

        JLabel avatar = new JLabel("");
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
        );

        // Bổ sung JTable để hiển thị dữ liệu                                                                       
        JTable table = new JTable();
        DefaultTableModel model = new DefaultTableModel();

        // Thêm các cột
        model.addColumn(
                "ID");
        model.addColumn(
                "Name");
        model.addColumn(
                "Describe");
        model.addColumn(
                "Price");
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

        table.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int selectedRow = table.getSelectedRow(); // Lấy hàng được chọn
                if (selectedRow != -1) {
                    try {
                        // Lấy dữ liệu từ bảng
                        int id = Integer.parseInt(model.getValueAt(selectedRow, 0).toString()); // Ép kiểu về int nếu cần
                        txt_ID.setText(String.valueOf(id));
                        txt_Name.setText(model.getValueAt(selectedRow, 1).toString());
                        txt_Describe.setText(model.getValueAt(selectedRow, 2).toString());
                        txt_Price.setText(model.getValueAt(selectedRow, 3).toString());

                        // Lấy đường dẫn avatar từ cơ sở dữ liệu
                        String avatarPath = getAvatarPathFromDatabase(id); // Truy vấn từ database

                        // Kiểm tra nếu đường dẫn không rỗng hoặc null mới cập nhật ảnh
                        if (avatarPath != null && !avatarPath.isEmpty()) {
                            setAvatarImage(avatar, avatarPath); // Hiển thị ảnh lên JButton
                        } else {
                            System.out.println("Avatar không tồn tại hoặc đường dẫn rỗng.");
                        }
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, "Lỗi chuyển đổi ID: " + e.getMessage());
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "Lỗi khi lấy dữ liệu: " + e.getMessage());
                    }
                }
            }
        });

        btn_Create.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CreatetUserData(txt_Name.getText(), txt_Describe.getText(), txt_Price.getText());
                model.setRowCount(0);
                loadDataToTable(model);
            }
        });

        btn_Delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DeleteUserData(Integer.parseInt(txt_ID.getText()));
                model.setRowCount(0);
                loadDataToTable(model);
            }
        }
        );

        btn_Edit.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //   Kiểm tra nhập liệu
                if (!validateInput(txt_ID.getText(), txt_Name.getText(), txt_Describe.getText(), txt_Price.getText())) {
                    return;
                }
                editUserData(Integer.parseInt(txt_ID.getText()), txt_Name.getText(), txt_Describe.getText(), txt_Price.getText());
                model.setRowCount(0);
                loadDataToTable(model);
            }
        }
        );

    }

    private boolean validateInput(String id, String name, String describe, String price) {
        // Kiểm tra ID không được để trống và chỉ chứa số
        if (id.isEmpty() || !id.matches("\\d+")) {
            JOptionPane.showMessageDialog(this, "ID không được để trống và chỉ chứa số!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // Kiểm tra tên và mô tả không được rỗng
        if (name.isEmpty() || describe.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Hãy điền đầy đủ thông tin", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // Kiểm tra giá tiền không được để trống và không chứa chữ
        if (price.isEmpty() || !price.matches("\\d+(\\.\\d+)?")) {
            JOptionPane.showMessageDialog(this, "Giá tiền không được chứa chữ và phải là số!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }

    private String getAvatarPathFromDatabase(int ID) {
        String avatarPath = null;

        try (Connection connection = CRUD_DAO.getConnect()) {
            String sql = "SELECT HinhAnh FROM chuyende WHERE IDCD = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, ID);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                avatarPath = resultSet.getString("HinhAnh");
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving avatar path: " + e.getMessage());
        }

        return avatarPath;
    }

    private void loadDataToTable(DefaultTableModel model) {
        try (Connection connection = CRUD_DAO.getConnect()) {
            String sql = "SELECT * FROM ChuyenDe";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                String ID = resultSet.getString("IDCD");
                String name = resultSet.getString("TenChuyenDe");
                String mota = resultSet.getString("MoTa");
                double price = resultSet.getDouble("GiaTien"); // Lấy giá tiền dưới dạng số
                String image = resultSet.getString("HinhAnh");

                // Chuyển double thành String nhưng KHÔNG có dấu phẩy
                String formattedPrice = String.format("%.2f", price);

                model.addRow(new Object[]{ID, name, mota, formattedPrice, image});
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

    private void editUserData(int id, String name, String describe, String price) {
        String sql = "UPDATE ChuyenDe SET TenChuyenDe = ?, MoTa = ?, GiaTien = ?, HinhAnh = ? where Idcd = ?";

        try (Connection conn = CRUD_DAO.getConnect(); PreparedStatement preparedStatement = conn.prepareStatement(sql)) {

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, describe);
            preparedStatement.setString(3, price);
            preparedStatement.setString(4, avatarPath);
            preparedStatement.setInt(5, id);
            // Thực thi lệnh SQL
            int rowsUpdated = preparedStatement.executeUpdate();

            if (rowsUpdated > 0) {
                JOptionPane.showMessageDialog(null, "Updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Update error: No record found!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error updating user: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void CreatetUserData(String name, String describe, String price) {
        String sql = "Insert into ChuyenDe values(?,?,?,?)";

        try (Connection conn = CRUD_DAO.getConnect(); PreparedStatement preparedStatement = conn.prepareStatement(sql)) {

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, describe);
            preparedStatement.setString(3, price);
            preparedStatement.setString(4, avatarPath);
            // Thực thi lệnh SQL
            int rowsUpdated = preparedStatement.executeUpdate();

            if (rowsUpdated > 0) {
                JOptionPane.showMessageDialog(null, "Created successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Create error: No record found!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error creating ChuyenDe: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void DeleteUserData(int id) {
        String sql = "Delete ChuyenDe where Idcd =?";

        try (Connection conn = CRUD_DAO.getConnect(); PreparedStatement preparedStatement = conn.prepareStatement(sql)) {

            preparedStatement.setInt(1, id);

            int rowsUpdated = preparedStatement.executeUpdate();

            if (rowsUpdated > 0) {
                JOptionPane.showMessageDialog(null, "Deleted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Delete error: No record found!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error deleting ChuyenDe: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu6 = new javax.swing.JMenu();
        jMenu5 = new javax.swing.JMenu();
        LogOut = new javax.swing.JMenu();
        Exit = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        quanLyCD = new javax.swing.JMenu();
        quanLyKH = new javax.swing.JMenu();
        quanLyTK = new javax.swing.JMenu();
        quanLyHV = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        jMenu14 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        jMenu16 = new javax.swing.JMenu();
        jMenu17 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jMenu1.setText("Hệ thống");

        jMenu6.setText("Trang chủ");
        jMenu6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu6MouseClicked(evt);
            }
        });
        jMenu1.add(jMenu6);

        jMenu5.setText("Đổi mật khẩu");
        jMenu1.add(jMenu5);

        LogOut.setText("Đăng xuất");
        LogOut.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                LogOutMouseClicked(evt);
            }
        });
        jMenu1.add(LogOut);

        Exit.setText("Thoát");
        Exit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ExitMouseClicked(evt);
            }
        });
        jMenu1.add(Exit);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Quản lý");

        quanLyCD.setText("Chuyên đề");
        quanLyCD.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                quanLyCDMouseClicked(evt);
            }
        });
        jMenu2.add(quanLyCD);

        quanLyKH.setText("Khóa học");
        quanLyKH.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                quanLyKHMouseClicked(evt);
            }
        });
        jMenu2.add(quanLyKH);

        quanLyTK.setText("Tài khoản");
        quanLyTK.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                quanLyTKMouseClicked(evt);
            }
        });
        jMenu2.add(quanLyTK);

        quanLyHV.setText("Học viên");
        quanLyHV.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                quanLyHVMouseClicked(evt);
            }
        });
        jMenu2.add(quanLyHV);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Thống kê");

        jMenu14.setText("Thống kê tổng hợp");
        jMenu14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu14MouseClicked(evt);
            }
        });
        jMenu3.add(jMenu14);

        jMenuBar1.add(jMenu3);

        jMenu4.setText("Trợ giúp");

        jMenu16.setText("Hướng dẫn sử dụng");
        jMenu4.add(jMenu16);

        jMenu17.setText("Giới thiệu về chúng tôi");
        jMenu4.add(jMenu17);

        jMenuBar1.add(jMenu4);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 841, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 623, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenu6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu6MouseClicked
        Home home = new Home();
        home.setVisible(true);
        dispose();
    }//GEN-LAST:event_jMenu6MouseClicked

    private void LogOutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LogOutMouseClicked
        // TODO add your handling code here:
        SignIn signIn = new SignIn();
        signIn.setVisible(true);
        dispose();
    }//GEN-LAST:event_LogOutMouseClicked

    private void ExitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ExitMouseClicked
        dispose();
    }//GEN-LAST:event_ExitMouseClicked

    private void quanLyCDMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_quanLyCDMouseClicked
        // TODO add your handling code here:
        QuanLyChuyenDe cd = new QuanLyChuyenDe();
        cd.setVisible(true);
        dispose();
    }//GEN-LAST:event_quanLyCDMouseClicked

    private void quanLyKHMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_quanLyKHMouseClicked
        // TODO add your handling code here:
        QuanLyKhoaHoc kh = new QuanLyKhoaHoc();
        kh.setVisible(true);
        dispose();
    }//GEN-LAST:event_quanLyKHMouseClicked

    private void quanLyTKMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_quanLyTKMouseClicked
        // TODO add your handling code here:
        QuanLyAccount acc = new QuanLyAccount();
        acc.setVisible(true);
        dispose();
    }//GEN-LAST:event_quanLyTKMouseClicked

    private void quanLyHVMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_quanLyHVMouseClicked
        // TODO add your handling code here:
        QuanLyHocVien hv = new QuanLyHocVien();
        hv.setVisible(true);
        dispose();
    }//GEN-LAST:event_quanLyHVMouseClicked

    private void jMenu14MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu14MouseClicked
        ThongKe tk = new ThongKe();
        tk.setVisible(true);
        dispose();

    }//GEN-LAST:event_jMenu14MouseClicked

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
            java.util.logging.Logger.getLogger(QuanLyChuyenDe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QuanLyChuyenDe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QuanLyChuyenDe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QuanLyChuyenDe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new QuanLyChuyenDe().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu Exit;
    private javax.swing.JMenu LogOut;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu14;
    private javax.swing.JMenu jMenu16;
    private javax.swing.JMenu jMenu17;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenu quanLyCD;
    private javax.swing.JMenu quanLyHV;
    private javax.swing.JMenu quanLyKH;
    private javax.swing.JMenu quanLyTK;
    // End of variables declaration//GEN-END:variables
}
