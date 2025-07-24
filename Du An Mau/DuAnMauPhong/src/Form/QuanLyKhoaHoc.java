/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Form;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JButton;

import javax.swing.JOptionPane;
import java.sql.Statement;
import java.sql.ResultSet;
import javax.swing.*; // Để sử dụng JFrame, JTable, JScrollPane, v.v.
import javax.swing.table.DefaultTableModel; // Để tạo DefaultTableModel cho JTable
import java.awt.*; // Để sử dụng Color, Font

import java.io.File;
import javax.swing.filechooser.FileNameExtensionFilter;
import DAOClass.CRUD_DAO;
import ModelsClass.KhoaHoc;
import java.util.Arrays;
import java.util.ArrayList;

/**
 *
 * @author drago
 */
public class QuanLyKhoaHoc extends javax.swing.JFrame {

    ArrayList<KhoaHoc> listkh = new ArrayList<>();

    /**
     * Creates new form ViewAdmin
     */
    public QuanLyKhoaHoc() {
        initComponents();
        loadDataToTable();
        loadChuyenDeToComboBox();
    }
    String avatarPath = null;

    public void FillTableData(ArrayList<KhoaHoc> m) {
        DefaultTableModel model = (DefaultTableModel) tblTable.getModel();
        model.setRowCount(0);
        for (KhoaHoc hv : m) {
            model.addRow(new Object[]{
                hv.getId(),
                hv.getName(),
                hv.getDescribe(),
                hv.getPrice(),
                hv.getImgURL(),});
        }
    }

    public KhoaHoc readForm() {
        return new KhoaHoc(
                Integer.parseInt(txtID.getText()),
                txtName.getText(),
                txtDescribe.getText(),
                Double.parseDouble(txtPrice.getText()),
                avatarPath
        );
    }

    private String getAvatarPathFromDatabase(int ID) {
        String avatarPath = null;
        try (Connection connection = CRUD_DAO.getConnect()) {
            String sql = "SELECT HinhAnh FROM KhoaHoc WHERE IDKH = ?";
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

    public ArrayList<KhoaHoc> getAll() {
        return listkh;
    }

    public void AddTableDate() {
        listkh.clear();
        try (Connection con = CRUD_DAO.getConnect()) {
            String sql = "SELECT * FROM KhoaHoc";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                KhoaHoc hv = new KhoaHoc(
                        rs.getInt("IDKH"),
                        rs.getString("TenKhoaHoc"),
                        rs.getString("MoTa"),
                        rs.getDouble("GiaTien"),
                        rs.getString("HinhAnh")
                );
                listkh.add(hv);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

// Phương thức load dữ liệu từ cơ sở dữ liệu vào bảng
    private void loadDataToTable() {
        DefaultTableModel model = (DefaultTableModel) tblTable.getModel();
        model.setRowCount(0); // Xóa dữ liệu cũ trong bảng

        try (Connection connection = CRUD_DAO.getConnect()) {
            String sql = "SELECT * FROM KhoaHoc";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                int id = resultSet.getInt("IDKH");
                String name = resultSet.getString("TenKhoaHoc");
                String describe = resultSet.getString("MoTa");
                String price = resultSet.getString("GiaTien");
                String image = resultSet.getString("HinhAnh");
                // Sử dụng model.addRow() thay vì tbl_Table.add()
                model.addRow(new Object[]{id, name, describe, price, image});
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error loading data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

// Phương thức hiển thị ảnh trên JButton
    private void setAvatarImage(JButton avatarButton, String avatarPath) {
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

    private void loadChuyenDeToComboBox() {
        try (Connection connection = CRUD_DAO.getConnect()) {
            // Kết nối tới database
            String sql = "SELECT TenChuyenDe FROM ChuyenDe";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            // Xóa dữ liệu cũ trong ComboBox
            cboCD.removeAllItems();

            // Duyệt kết quả và thêm vào JComboBox
            while (rs.next()) {
                cboCD.addItem(rs.getString("TenChuyenDe"));
            }

            // Đóng kết nối
            rs.close();
            ps.close();
            connection.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi tải danh sách chuyên đề: " + e.getMessage());
        }
    }

    private boolean validateInput(String id, String name, String describe, String price) {
        // Kiểm tra ID không được để trống và chỉ chứa số
        if (id.isEmpty() || !id.matches("\\d+")) {
            JOptionPane.showMessageDialog(this, "ID không được để trống và chỉ chứa số!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // Kiểm tra tên và mô tả không được rỗng
        if (name.isEmpty() || describe.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Hãy điền đầy đủ thông tin!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // Kiểm tra giá tiền không được để trống và không chứa chữ
        if (price.isEmpty() || !price.matches("\\d+(\\.\\d+)?")) {
            JOptionPane.showMessageDialog(this, "Giá tiền chỉ được chứa số!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        txtPrice = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtID = new javax.swing.JTextField();
        btn_Image = new javax.swing.JButton();
        btn_Create = new javax.swing.JButton();
        btn_Update = new javax.swing.JButton();
        btn_Delete = new javax.swing.JButton();
        btn_AddImage = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblTable = new javax.swing.JTable();
        txtName = new javax.swing.JTextField();
        txtDescribe = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        cboCD = new javax.swing.JComboBox<>();
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

        jLabel1.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jLabel1.setText("ID");

        txtPrice.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N

        jLabel8.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jLabel8.setText("Name");

        jLabel9.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jLabel9.setText("Describe");

        jLabel10.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jLabel10.setText("Price");

        txtID.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N

        btn_Image.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ImageActionPerformed(evt);
            }
        });

        btn_Create.setText("Create");
        btn_Create.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_CreateActionPerformed(evt);
            }
        });

        btn_Update.setText("Update");
        btn_Update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_UpdateActionPerformed(evt);
            }
        });

        btn_Delete.setText("Delete");
        btn_Delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_DeleteActionPerformed(evt);
            }
        });

        btn_AddImage.setText("Image");
        btn_AddImage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_AddImageActionPerformed(evt);
            }
        });

        tblTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Name", "Describe", "Price"
            }
        ));
        tblTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblTable);

        txtName.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N

        txtDescribe.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N

        jLabel11.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jLabel11.setText("Chuyên Đề");

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
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(94, 94, 94)
                        .addComponent(txtPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(42, 42, 42)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(txtID, javax.swing.GroupLayout.DEFAULT_SIZE, 317, Short.MAX_VALUE)
                                .addComponent(txtName, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtDescribe, javax.swing.GroupLayout.Alignment.LEADING))
                            .addComponent(cboCD, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(72, 72, 72)
                        .addComponent(btn_Create, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_Update, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_Delete, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_AddImage, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
                .addComponent(btn_Image, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btn_Image, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(41, 41, 41))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtDescribe, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(cboCD))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(txtPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(22, 22, 22)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_Create)
                            .addComponent(btn_Update)
                            .addComponent(btn_Delete)
                            .addComponent(btn_AddImage))
                        .addGap(31, 31, 31)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_ImageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ImageActionPerformed
        JFileChooser chooser = new JFileChooser();
        chooser.setFileFilter(new FileNameExtensionFilter("Image", "jpg", "png", "gif"));
        int returnValue = chooser.showOpenDialog(rootPane);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            avatarPath = file.getAbsolutePath();
            ImageIcon icon = new ImageIcon(file.getAbsolutePath());
            btn_Image.setIcon(icon);
        }
    }//GEN-LAST:event_btn_ImageActionPerformed

    private void btn_AddImageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_AddImageActionPerformed
        JFileChooser chooser = new JFileChooser();
        chooser.setFileFilter(new FileNameExtensionFilter("Image", "jpg", "png", "gif"));
        int returnValue = chooser.showOpenDialog(rootPane);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            avatarPath = file.getAbsolutePath();
            ImageIcon icon = new ImageIcon(file.getAbsolutePath());
            btn_Image.setIcon(icon);
        }
    }//GEN-LAST:event_btn_AddImageActionPerformed

    private void btn_UpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_UpdateActionPerformed
        if (!validateInput(txtID.getText(), txtName.getText(), txtDescribe.getText(), txtPrice.getText())) {
            return; // Nếu có lỗi thì dừng lại
        }

        try (Connection conn = CRUD_DAO.getConnect()) {
            // 1. Lấy thông tin từ form
            int khoaHocId = Integer.parseInt(txtID.getText());
            String name = txtName.getText();
            String describe = txtDescribe.getText();
            double price = Double.parseDouble(txtPrice.getText());
            String tenChuyenDe = (String) cboCD.getSelectedItem(); // Lấy chuyên đề từ ComboBox

            // 2. Lấy ID chuyên đề từ tên chuyên đề
            int chuyenDeId = -1;
            String sqlChuyenDe = "SELECT IDCD FROM ChuyenDe WHERE TenChuyenDe = ?";
            try (PreparedStatement psCD = conn.prepareStatement(sqlChuyenDe)) {
                psCD.setString(1, tenChuyenDe);
                try (ResultSet rsCD = psCD.executeQuery()) {
                    if (rsCD.next()) {
                        chuyenDeId = rsCD.getInt("IDCD");
                    } else {
                        JOptionPane.showMessageDialog(this, "Không tìm thấy chuyên đề!");
                        return;
                    }
                }
            }

            if (chuyenDeId <= 0) {
                JOptionPane.showMessageDialog(this, "ID Chuyên đề không hợp lệ!");
                return;
            }

            // 3. Cập nhật thông tin khóa học
            String sqlUpdateKH;
            if (avatarPath != null && !avatarPath.isEmpty()) {
                // Nếu có ảnh mới, cập nhật luôn ảnh
                sqlUpdateKH = "UPDATE KhoaHoc SET IDCD = ?, TenKhoaHoc = ?, MoTa = ?, GiaTien = ?, HinhAnh = ? WHERE IDKH = ?";
            } else {
                // Nếu không có ảnh mới, không cập nhật ảnh
                sqlUpdateKH = "UPDATE KhoaHoc SET IDCD = ?, TenKhoaHoc = ?, MoTa = ?, GiaTien = ? WHERE IDKH = ?";
            }

            try (PreparedStatement preparedStatement = conn.prepareStatement(sqlUpdateKH)) {
                preparedStatement.setInt(1, chuyenDeId);
                preparedStatement.setString(2, name);
                preparedStatement.setString(3, describe);
                preparedStatement.setDouble(4, price);

                if (avatarPath != null && !avatarPath.isEmpty()) {
                    preparedStatement.setString(5, avatarPath);
                    preparedStatement.setInt(6, khoaHocId);
                } else {
                    preparedStatement.setInt(5, khoaHocId);
                }

                int rowsUpdated = preparedStatement.executeUpdate();
                if (rowsUpdated > 0) {
                    JOptionPane.showMessageDialog(null, "Cập nhật khóa học thành công!", "Thành công", JOptionPane.INFORMATION_MESSAGE);
                    loadDataToTable();
                } else {
                    JOptionPane.showMessageDialog(null, "Không tìm thấy khóa học để cập nhật!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi SQL: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Lỗi không xác định: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btn_UpdateActionPerformed

    private void btn_CreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_CreateActionPerformed
        if (!validateInput(txtID.getText(), txtName.getText(), txtDescribe.getText(), txtPrice.getText())) {
            return; // Nếu có lỗi thì dừng lại
        }
        if (Double.parseDouble(txtPrice.getText()) > 999999999.99) {
            JOptionPane.showMessageDialog(this, "Giá tiền quá lớn! Vui lòng nhập lại.");
            return;
        }

        try (Connection conn = CRUD_DAO.getConnect()) {
            // 1. Lấy thông tin từ form
            String name = txtName.getText();
            String describe = txtDescribe.getText();
            double price = Double.parseDouble(txtPrice.getText());
            String tenChuyenDe = (String) cboCD.getSelectedItem();

            // 2. Lấy ID của chuyên đề
            int chuyenDeId = -1;
            String sqlChuyenDe = "SELECT IDCD FROM ChuyenDe WHERE TenChuyenDe = ?";
            try (PreparedStatement psCD = conn.prepareStatement(sqlChuyenDe)) {
                psCD.setString(1, tenChuyenDe);
                try (ResultSet rsCD = psCD.executeQuery()) {
                    if (rsCD.next()) {
                        chuyenDeId = rsCD.getInt("IDCD");
                    } else {
                        JOptionPane.showMessageDialog(this, "Không tìm thấy chuyên đề!");
                        return;
                    }
                }
            }

            if (chuyenDeId <= 0) {
                JOptionPane.showMessageDialog(this, "ID Chuyên đề không hợp lệ!");
                return;
            }

            // 3. Thêm khóa học vào database
            String sqlInsertKH = "INSERT INTO KhoaHoc (IDCD, TenKhoaHoc, MoTa, GiaTien) VALUES (?, ?, ?, ?)";
            try (PreparedStatement psKH = conn.prepareStatement(sqlInsertKH, Statement.RETURN_GENERATED_KEYS)) {
                psKH.setInt(1, chuyenDeId);
                psKH.setString(2, name);
                psKH.setString(3, describe);
                psKH.setDouble(4, price);

                int affectedRows = psKH.executeUpdate();
                if (affectedRows == 0) {
                    JOptionPane.showMessageDialog(this, "Lỗi khi thêm khóa học!");
                    return;
                }

                // Lấy ID khóa học mới vừa tạo
                try (ResultSet generatedKeys = psKH.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int khoaHocId = generatedKeys.getInt(1);
                        JOptionPane.showMessageDialog(this, "Thêm khóa học thành công! ID: " + khoaHocId);
                        loadDataToTable();
                    } else {
                        JOptionPane.showMessageDialog(this, "Không lấy được ID khóa học!");
                        return;
                    }
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Lỗi SQL: " + e.getMessage());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi không xác định: " + e.getMessage());
        }
    }//GEN-LAST:event_btn_CreateActionPerformed

    private void btn_DeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_DeleteActionPerformed
        String sql = "Delete KhoaHoc where IDKH =?";

        try (Connection conn = CRUD_DAO.getConnect(); PreparedStatement preparedStatement = conn.prepareStatement(sql)) {

            preparedStatement.setInt(1, Integer.parseInt(txtID.getText()));

            int rowsUpdated = preparedStatement.executeUpdate();

            if (rowsUpdated > 0) {
                JOptionPane.showMessageDialog(null, "Deleted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                loadDataToTable();
            } else {
                JOptionPane.showMessageDialog(null, "Delete error: No record found!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error deleting ChuyenDe: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btn_DeleteActionPerformed

    private void tblTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTableMouseClicked
        int selectedRow = tblTable.getSelectedRow(); // Lấy hàng được chọn 
        if (selectedRow != -1) {
            try {
                // Lấy dữ liệu từ bảng
                int id = Integer.parseInt(tblTable.getValueAt(selectedRow, 0).toString()); // Ép kiểu về int
                txtID.setText(String.valueOf(id));
                txtName.setText(tblTable.getValueAt(selectedRow, 1).toString());
                txtDescribe.setText(tblTable.getValueAt(selectedRow, 2).toString());
                txtPrice.setText(tblTable.getValueAt(selectedRow, 3).toString());

                // Lấy đường dẫn ảnh từ cơ sở dữ liệu
                String avatarPath = getAvatarPathFromDatabase(id);
                if (avatarPath != null && !avatarPath.isEmpty()) {
                    setAvatarImage(btn_Image, avatarPath);
                } else {
                    System.out.println("Avatar không tồn tại hoặc đường dẫn rỗng.");
                }

                // Lấy ID của chuyên đề từ bảng KhoaHoc
                int chuyenDeId = -1;
                String sqlCD = "SELECT IDCD FROM KhoaHoc WHERE IDKH = ?";
                try (Connection conn = CRUD_DAO.getConnect(); PreparedStatement ps = conn.prepareStatement(sqlCD)) {
                    ps.setInt(1, id);
                    try (ResultSet rs = ps.executeQuery()) {
                        if (rs.next()) {
                            chuyenDeId = rs.getInt("IDCD");
                        }
                    }
                }

                if (chuyenDeId != -1) {
                    // Lấy tên chuyên đề từ bảng ChuyenDe
                    String tenChuyenDe = "";
                    String sqlTenCD = "SELECT TenChuyenDe FROM ChuyenDe WHERE IDCD = ?";
                    try (Connection conn = CRUD_DAO.getConnect(); PreparedStatement ps = conn.prepareStatement(sqlTenCD)) {
                        ps.setInt(1, chuyenDeId);
                        try (ResultSet rs = ps.executeQuery()) {
                            if (rs.next()) {
                                tenChuyenDe = rs.getString("TenChuyenDe");
                            }
                        }
                    }

                    // Set tên chuyên đề vào ComboBox
                    cboCD.setSelectedItem(tenChuyenDe);
                } else {
                    cboCD.setSelectedIndex(-1); // Không tìm thấy chuyên đề
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Lỗi chuyển đổi ID: " + e.getMessage());
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Lỗi truy vấn SQL: " + e.getMessage());
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Lỗi không xác định: " + e.getMessage());
            }
        }

    }//GEN-LAST:event_tblTableMouseClicked

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
            java.util.logging.Logger.getLogger(QuanLyKhoaHoc.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QuanLyKhoaHoc.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QuanLyKhoaHoc.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QuanLyKhoaHoc.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new QuanLyKhoaHoc().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu Exit;
    private javax.swing.JMenu LogOut;
    private javax.swing.JButton btn_AddImage;
    private javax.swing.JButton btn_Create;
    private javax.swing.JButton btn_Delete;
    private javax.swing.JButton btn_Image;
    private javax.swing.JButton btn_Update;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cboCD;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JMenu quanLyCD;
    private javax.swing.JMenu quanLyHV;
    private javax.swing.JMenu quanLyKH;
    private javax.swing.JMenu quanLyTK;
    private javax.swing.JTable tblTable;
    private javax.swing.JTextField txtDescribe;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtPrice;
    // End of variables declaration//GEN-END:variables
}
