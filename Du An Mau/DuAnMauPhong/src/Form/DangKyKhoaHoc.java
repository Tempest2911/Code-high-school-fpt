/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Form;

import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JCheckBox;
import javax.swing.ListSelectionModel;
import java.sql.*;
import DAOClass.CRUD_DAO;
import java.awt.Checkbox;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.DecimalFormat;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author drago
 */
public class DangKyKhoaHoc extends javax.swing.JFrame {

    /**
     * Creates new form DangKyKhoaHoc
     */
    public DangKyKhoaHoc() {
        initComponents();
        GetAllCDDate();
        loadDataToTable();
        cboCD.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedCD = cboCD.getSelectedItem().toString();
                getKHFromCD(selectedCD);
                tinhTongTien(); // Reset tổng tiền khi đổi chuyên đề
            }
        });

        // Gọi danh sách khóa học ban đầu từ chuyên đề đầu tiên
        if (cboCD.getItemCount() > 0) {
            getKHFromCD(cboCD.getSelectedItem().toString());
        }
        txtMoney.setEditable(false);
    }

    private void loadDataToTable() {
        DefaultTableModel model = (DefaultTableModel) tbl_Table.getModel();
        model.setRowCount(0); // Xóa dữ liệu cũ trong bảng

        try (Connection connection = CRUD_DAO.getConnect()) {
            String sql = "SELECT * FROM HocVien";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                int id = resultSet.getInt("MaHV");
                String name = resultSet.getString("Name");
                String phone = resultSet.getString("SDT");
                String email = resultSet.getString("Email");
                // Sử dụng model.addRow() thay vì tbl_Table.add()
                model.addRow(new Object[]{id, name, phone, email});
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error loading data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void GetAllCDDate() {
        try (Connection con = CRUD_DAO.getConnect()) {
            String url = "Select tenChuyende from chuyende";
            PreparedStatement stmt = con.prepareStatement(url);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                cboCD.addItem(rs.getString("tenChuyende"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getKHFromCD(String tenCD) {
        try (Connection con = CRUD_DAO.getConnect()) {
            String sql = "SELECT IDKH, tenKhoahoc, giatien FROM Khoahoc WHERE idcd = (SELECT idcd FROM Chuyende WHERE tenChuyende = ?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setNString(1, tenCD);
            ResultSet rs = stmt.executeQuery();

            panelCourses.removeAll(); // Xóa các checkbox cũ
            panelCourses.setLayout(new GridLayout(0, 1));

            ArrayList<JCheckBox> checkBoxList = new ArrayList<>();

            while (rs.next()) {
                String khoaHoc = rs.getNString("tenKhoahoc");
                int gia = rs.getInt("giatien");
                int IDKH = rs.getInt("IDKH");

                JCheckBox checkBox = new JCheckBox(khoaHoc + " - " + gia + " VNĐ");
                checkBox.putClientProperty("idkh", IDKH);
                checkBox.putClientProperty("giatien", gia); // Lưu giá tiền vào checkbox

                // Thêm sự kiện tính tiền khi chọn checkbox
                checkBox.addItemListener(new ItemListener() {
                    @Override
                    public void itemStateChanged(ItemEvent e) {
                        tinhTongTien();
                    }
                });

                checkBoxList.add(checkBox);
            }

            for (JCheckBox checkBox : checkBoxList) {
                panelCourses.add(checkBox);
            }

            panelCourses.revalidate();
            panelCourses.repaint();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void tinhTongTien() {
        float tongTien = 0;

        for (Component c : panelCourses.getComponents()) {
            if (c instanceof JCheckBox checkBox) {
                if (checkBox.isSelected()) {
                    Object giaObj = checkBox.getClientProperty("giatien");

                    if (giaObj instanceof Integer) {
                        tongTien += ((Integer) giaObj).floatValue();
                    } else if (giaObj instanceof Float) {
                        tongTien += (Float) giaObj;
                    }
                }
            }
        }

        // Format số để hiển thị chuẩn (không có E)
        DecimalFormat df = new DecimalFormat("###.##");
        txtMoney.setText(df.format(tongTien));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        rdoCK = new javax.swing.JRadioButton();
        rdoTM = new javax.swing.JRadioButton();
        btn_Dangki = new javax.swing.JButton();
        txtName = new javax.swing.JTextField();
        txtPhone = new javax.swing.JTextField();
        txtMoney = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cboCD = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        panelCourses = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_Table = new javax.swing.JTable();
        jMenuBar3 = new javax.swing.JMenuBar();
        jMenu11 = new javax.swing.JMenu();
        jMenu6 = new javax.swing.JMenu();
        jMenu22 = new javax.swing.JMenu();
        LogOut2 = new javax.swing.JMenu();
        Exit2 = new javax.swing.JMenu();
        jMenu23 = new javax.swing.JMenu();
        quanLyCD2 = new javax.swing.JMenu();
        quanLyKH2 = new javax.swing.JMenu();
        quanLyTK2 = new javax.swing.JMenu();
        quanLyHV2 = new javax.swing.JMenu();
        jMenu24 = new javax.swing.JMenu();
        jMenu25 = new javax.swing.JMenu();
        jMenu26 = new javax.swing.JMenu();
        jMenu27 = new javax.swing.JMenu();
        jMenu28 = new javax.swing.JMenu();
        jMenu29 = new javax.swing.JMenu();
        jMenu30 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        buttonGroup1.add(rdoCK);
        rdoCK.setText("Chuyển khoản");
        rdoCK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoCKActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdoTM);
        rdoTM.setText("Tiền mặt");

        btn_Dangki.setText("Đăng kí");
        btn_Dangki.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_DangkiActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setText("Đăng Kí Khóa học");

        jLabel2.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabel2.setText("Các chuyên đề");

        jLabel3.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabel3.setText("SĐT");

        jLabel6.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabel6.setText("Hình thức thanh toán");

        jLabel7.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabel7.setText("Tên");

        jLabel8.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabel8.setText("Email");

        jLabel4.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabel4.setText("Các khóa học");

        cboCD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboCDActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabel9.setText("Tổng tiền (VNĐ)");

        javax.swing.GroupLayout panelCoursesLayout = new javax.swing.GroupLayout(panelCourses);
        panelCourses.setLayout(panelCoursesLayout);
        panelCoursesLayout.setHorizontalGroup(
            panelCoursesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panelCoursesLayout.setVerticalGroup(
            panelCoursesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 32, Short.MAX_VALUE)
        );

        tbl_Table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Name", "Phone", "Email"
            }
        ));
        tbl_Table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_TableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_Table);

        jMenu11.setText("Hệ thống");

        jMenu6.setText("Trang chủ");
        jMenu6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu6MouseClicked(evt);
            }
        });
        jMenu11.add(jMenu6);

        jMenu22.setText("Đổi mật khẩu");
        jMenu11.add(jMenu22);

        LogOut2.setText("Đăng xuất");
        LogOut2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                LogOut2MouseClicked(evt);
            }
        });
        jMenu11.add(LogOut2);

        Exit2.setText("Thoát");
        Exit2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Exit2MouseClicked(evt);
            }
        });
        jMenu11.add(Exit2);

        jMenuBar3.add(jMenu11);

        jMenu23.setText("Quản lý");

        quanLyCD2.setText("Chuyên đề");
        quanLyCD2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                quanLyCD2MouseClicked(evt);
            }
        });
        jMenu23.add(quanLyCD2);

        quanLyKH2.setText("Khóa học");
        quanLyKH2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                quanLyKH2MouseClicked(evt);
            }
        });
        jMenu23.add(quanLyKH2);

        quanLyTK2.setText("Tài khoản");
        quanLyTK2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                quanLyTK2MouseClicked(evt);
            }
        });
        jMenu23.add(quanLyTK2);

        quanLyHV2.setText("Học viên");
        quanLyHV2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                quanLyHV2MouseClicked(evt);
            }
        });
        jMenu23.add(quanLyHV2);

        jMenuBar3.add(jMenu23);

        jMenu24.setText("Thống kê");

        jMenu25.setText("Bảng điểm");
        jMenu24.add(jMenu25);

        jMenu26.setText("Doanh thu");
        jMenu24.add(jMenu26);

        jMenu27.setText("Lượng người học");
        jMenu24.add(jMenu27);

        jMenuBar3.add(jMenu24);

        jMenu28.setText("Trợ giúp");

        jMenu29.setText("Hướng dẫn sử dụng");
        jMenu28.add(jMenu29);

        jMenu30.setText("Giới thiệu về chúng tôi");
        jMenu28.add(jMenu30);

        jMenuBar3.add(jMenu28);

        setJMenuBar(jMenuBar3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel8)
                    .addComponent(jLabel7)
                    .addComponent(jLabel9)
                    .addComponent(jLabel6))
                .addGap(62, 62, 62)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelCourses, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMoney, javax.swing.GroupLayout.PREFERRED_SIZE, 434, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(rdoCK, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(51, 51, 51)
                                .addComponent(rdoTM, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(cboCD, javax.swing.GroupLayout.PREFERRED_SIZE, 431, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 431, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 431, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 431, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_Dangki, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 654, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(49, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cboCD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(panelCourses, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtMoney, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(rdoCK)
                    .addComponent(rdoTM))
                .addGap(18, 18, 18)
                .addComponent(btn_Dangki)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenu6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu6MouseClicked
        Home home = new Home();
        home.setVisible(true);
        dispose();
    }//GEN-LAST:event_jMenu6MouseClicked

    private void LogOut2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LogOut2MouseClicked
        // TODO add your handling code here:
        SignIn signIn = new SignIn();
        signIn.setVisible(true);
        dispose();
    }//GEN-LAST:event_LogOut2MouseClicked

    private void Exit2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Exit2MouseClicked
        dispose();
    }//GEN-LAST:event_Exit2MouseClicked

    private void quanLyCD2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_quanLyCD2MouseClicked
        // TODO add your handling code here:
        QuanLyChuyenDe cd = new QuanLyChuyenDe();
        cd.setVisible(true);
        dispose();
    }//GEN-LAST:event_quanLyCD2MouseClicked

    private void quanLyKH2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_quanLyKH2MouseClicked
        // TODO add your handling code here:
        QuanLyKhoaHoc kh = new QuanLyKhoaHoc();
        kh.setVisible(true);
        dispose();
    }//GEN-LAST:event_quanLyKH2MouseClicked

    private void quanLyTK2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_quanLyTK2MouseClicked
        // TODO add your handling code here:
        QuanLyAccount acc = new QuanLyAccount();
        acc.setVisible(true);
        dispose();
    }//GEN-LAST:event_quanLyTK2MouseClicked

    private void quanLyHV2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_quanLyHV2MouseClicked
        // TODO add your handling code here:
        QuanLyHocVien hv = new QuanLyHocVien();
        hv.setVisible(true);
        dispose();
    }//GEN-LAST:event_quanLyHV2MouseClicked

    private void cboCDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboCDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboCDActionPerformed

    private void btn_DangkiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_DangkiActionPerformed
        try (Connection con = CRUD_DAO.getConnect()) {
            String name = txtName.getText();
            String phone = txtPhone.getText();
            String email = txtEmail.getText();
            float total = Float.parseFloat(txtMoney.getText());
            String payment = rdoCK.isSelected() ? "Chuyển khoản" : "Tiền mặt";

            // 🔹 1. Kiểm tra xem học viên đã có trong DB chưa, nếu chưa thì thêm vào
            int maHV = -1;
            String sqlCheckHV = "SELECT maHV FROM HocVien WHERE name = ? AND SDT = ? AND Email = ?";
            try (PreparedStatement ps = con.prepareStatement(sqlCheckHV)) {
                ps.setString(1, name);
                ps.setString(2, phone);
                ps.setString(3, email);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        maHV = rs.getInt("maHV"); // Lấy ID nếu đã tồn tại
                    }
                }
            }

            // Nếu chưa có học viên, thêm vào DB
            if (maHV == -1) {
                String sqlInsertHV = "INSERT INTO HocVien(name, SDT, Email) VALUES (?, ?, ?)";
                try (PreparedStatement stmt = con.prepareStatement(sqlInsertHV, Statement.RETURN_GENERATED_KEYS)) {
                    stmt.setString(1, name);
                    stmt.setString(2, phone);
                    stmt.setString(3, email);
                    stmt.executeUpdate();
                    try (ResultSet rs = stmt.getGeneratedKeys()) {
                        if (rs.next()) {
                            maHV = rs.getInt(1); // Lấy ID mới tạo
                        }
                    }
                }
            }

            // 🔹 2. Lặp qua checkbox để lấy danh sách khóa học được chọn
            List<Integer> khoaHocIDs = new ArrayList<>();
            for (Component c : panelCourses.getComponents()) {
                if (c instanceof JCheckBox checkBox && checkBox.isSelected()) {
                    Object idkhObj = checkBox.getClientProperty("idkh");
                    if (idkhObj instanceof Integer) {
                        khoaHocIDs.add((Integer) idkhObj);
                    }
                }
            }

            // 🔹 3. Thêm thanh toán
            int maTT = -1;
            String sqlInsertThanhToan = "INSERT INTO ThanhToan(maHV, Total, Payment) VALUES (?, ?, ?)";
            try (PreparedStatement stmt = con.prepareStatement(sqlInsertThanhToan, Statement.RETURN_GENERATED_KEYS)) {
                stmt.setInt(1, maHV);
                stmt.setFloat(2, total);
                stmt.setString(3, payment);
                stmt.executeUpdate();
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        maTT = rs.getInt(1); // Lấy ID mới tạo
                    }
                }
            }

            // 🔹 4. Thêm dữ liệu vào bảng DangKyKH và ChiTietThanhToan
            System.out.println("maHV: " + maHV + ", maTT: " + maTT);

            String sqlInsertDangKyKH = "INSERT INTO DangKyKH(maHV, maKH) VALUES (?, ?)";
            String sqlInsertChiTietTT = "INSERT INTO ChiTietThanhToan(maTT, maKH) VALUES (?, ?)";

            System.out.println("Danh sách khóa học đã chọn: " + khoaHocIDs);

            for (int maKH : khoaHocIDs) {
                // Chi tiết thanh toán
                try (PreparedStatement stmt = con.prepareStatement(sqlInsertChiTietTT)) {
                    stmt.setInt(1, maTT);
                    stmt.setInt(2, maKH);
                    int rowsAffected = stmt.executeUpdate();
                    System.out.println("Số dòng ảnh hưởng: " + rowsAffected);

                }
                // Đăng ký khóa học
                try (PreparedStatement stmt = con.prepareStatement(sqlInsertDangKyKH)) {
                    stmt.setInt(1, maHV);
                    stmt.setInt(2, maKH);
                    int rowsAffected = stmt.executeUpdate();
                    System.out.println("Số dòng ảnh hưởng: " + rowsAffected);

                }

            }

            JOptionPane.showMessageDialog(this, "Đăng ký thành công!");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi SQL: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Lỗi không xác định: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btn_DangkiActionPerformed

    private void rdoCKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoCKActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdoCKActionPerformed

    private void tbl_TableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_TableMouseClicked
        int row = tbl_Table.getSelectedRow(); // Lấy hàng được chọn
        if (row == -1) {
            return; // Không có dòng nào được chọn
        }

        try {
            // Lấy dữ liệu từ bảng (kiểm tra null trước khi gọi toString)
            txtName.setText(tbl_Table.getValueAt(row, 1).toString());

            txtPhone.setText(tbl_Table.getValueAt(row, 2).toString());
            txtEmail.setText(tbl_Table.getValueAt(row, 3).toString());

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Lỗi chuyển đổi ID: " + e.getMessage());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Lỗi khi lấy dữ liệu: " + e.getMessage());
        }
    }//GEN-LAST:event_tbl_TableMouseClicked
    private boolean kiemTraTenTonTai(String name) {
        String sql = "SELECT COUNT(*) FROM HocVien WHERE name = ?";

        try (Connection con = CRUD_DAO.getConnect(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, name);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;  // Nếu > 0 thì tên đã tồn tại
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Lỗi hoặc không có dữ liệu
    }

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
            java.util.logging.Logger.getLogger(DangKyKhoaHoc.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DangKyKhoaHoc.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DangKyKhoaHoc.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DangKyKhoaHoc.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DangKyKhoaHoc().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu Exit2;
    private javax.swing.JMenu LogOut2;
    private javax.swing.JButton btn_Dangki;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cboCD;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu11;
    private javax.swing.JMenu jMenu22;
    private javax.swing.JMenu jMenu23;
    private javax.swing.JMenu jMenu24;
    private javax.swing.JMenu jMenu25;
    private javax.swing.JMenu jMenu26;
    private javax.swing.JMenu jMenu27;
    private javax.swing.JMenu jMenu28;
    private javax.swing.JMenu jMenu29;
    private javax.swing.JMenu jMenu30;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenuBar jMenuBar3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panelCourses;
    private javax.swing.JMenu quanLyCD2;
    private javax.swing.JMenu quanLyHV2;
    private javax.swing.JMenu quanLyKH2;
    private javax.swing.JMenu quanLyTK2;
    private javax.swing.JRadioButton rdoCK;
    private javax.swing.JRadioButton rdoTM;
    private javax.swing.JTable tbl_Table;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtMoney;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtPhone;
    // End of variables declaration//GEN-END:variables
}
