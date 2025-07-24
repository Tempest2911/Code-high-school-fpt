/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Form;

import DAOClass.CRUD_DAO;
import ModelsClass.KhoaHoc;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author drago
 */
public class ThongKe extends javax.swing.JFrame {

    /**
     * Creates new form ThongKe
     */
    public ThongKe() {
        initComponents();
        loadDataToTableDiem();
        loadDataToTableDoanhThu();
        loadDataToTableNguoiHoc();
    }

    private void loadDataToTableDiem() {
        DefaultTableModel model = (DefaultTableModel) tblDiem.getModel();
        model.setRowCount(0); // Xóa dữ liệu cũ trong bảng

        try (Connection connection = CRUD_DAO.getConnect()) {
            String sql = "SELECT hv.MaHV, hv.Name, hv.Score FROM dbo.HocVien hv";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                int id = resultSet.getInt("MaHV");
                String name = resultSet.getString("Name");
                float score = resultSet.getFloat("Score");
                String rank = "";
                if (score >= 8) {
                    rank = "Giỏi";
                } else if (score >= 7) {
                    rank = "Khá";
                } else if (score >= 5) {
                    rank = "Trung bình";
                } else if (score < 5) {
                    rank = "Kém";
                }

                // Sử dụng model.addRow() thay vì tbl_Table.add()
                model.addRow(new Object[]{id, name, score, rank});
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error loading data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loadDataToTableDoanhThu() {
        DefaultTableModel model = (DefaultTableModel) tblDoanhThu.getModel();
        model.setRowCount(0); // Xóa dữ liệu cũ trong bảng

        try (Connection connection = CRUD_DAO.getConnect()) {
            String sql = "SELECT \n"
                    + "    CD.TenChuyenDe AS [Chuyên đề], \n"
                    + "    COUNT(DISTINCT KH.IDKH) AS [Số khóa học], \n"
                    + "    COUNT(DISTINCT DK.maHV) AS [Số học viên], \n"
                    + "    SUM(TT.Total) AS [Doanh thu], \n"
                    + "    MAX(TT.Total) AS [Cao nhất], \n"
                    + "    MIN(TT.Total) AS [Thấp nhất], \n"
                    + "    AVG(TT.Total) AS [Trung bình]\n"
                    + "FROM ChuyenDe CD\n"
                    + "LEFT JOIN KhoaHoc KH ON CD.IDCD = KH.IDCD\n"
                    + "LEFT JOIN DangKyKH DK ON KH.IDKH = DK.IDDKKH\n"
                    + "LEFT JOIN ThanhToan TT ON DK.maHV = TT.maHV\n"
                    + "GROUP BY CD.TenChuyenDe;";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                Object[] row = {
                    rs.getString(1), // Chuyên đề
                    rs.getInt(2), // Số khóa học
                    rs.getInt(3), // Số học viên
                    rs.getFloat(4), // Doanh thu
                    rs.getFloat(5), // Cao nhất
                    rs.getFloat(6), // Thấp nhất
                    rs.getFloat(7) // Trung bình
                };
                model.addRow(row);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error loading data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loadDataToTableNguoiHoc() {
        DefaultTableModel model = (DefaultTableModel) tblNguoihoc.getModel();
        model.setRowCount(0); // Xóa dữ liệu cũ trong bảng

        try (Connection connection = CRUD_DAO.getConnect()) {
            String sql = "SELECT YEAR(tk.NgayTao), COUNT(hv.MaHV), MIN(tk.NgayTao), MAX(tk.ngaytao) FROM dbo.HocVien hv\n"
                    + "JOIN dbo.TaiKhoan tk ON tk.MaHV = hv.MaHV\n"
                    + "GROUP BY YEAR(tk.NgayTao)";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                Object[] row = {
                    rs.getString(1), // Chuyên đề
                    rs.getInt(2), // Số khóa học
                    rs.getString(3), // Số học viên
                    rs.getString(4) // Doanh thu
                };
                model.addRow(row);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error loading data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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

        jLabel1 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDiem = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblDoanhThu = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblNguoihoc = new javax.swing.JTable();
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

        jLabel1.setFont(new java.awt.Font("sansserif", 1, 20)); // NOI18N
        jLabel1.setText("THỐNG KÊ BROOO");

        tblDiem.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Họ và tên", "Điểm", "Xếp loại"
            }
        ));
        jScrollPane1.setViewportView(tblDiem);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 771, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(13, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Điểm", jPanel1);

        tblDoanhThu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Chuyên đề", "Số khóa học", "số học viên", "Doanhthu", "Cao nhất", "Thấp nhất", "Trung bình"
            }
        ));
        jScrollPane2.setViewportView(tblDoanhThu);
        if (tblDoanhThu.getColumnModel().getColumnCount() > 0) {
            tblDoanhThu.getColumnModel().getColumn(3).setHeaderValue("Doanhthu");
            tblDoanhThu.getColumnModel().getColumn(5).setHeaderValue("Thấp nhất");
            tblDoanhThu.getColumnModel().getColumn(6).setHeaderValue("Trung bình");
        }

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(13, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Doanh thu", jPanel2);

        tblNguoihoc.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Năm", "Số người học", "Đăng kí muộn nhất", "Đăng kí cao nhất"
            }
        ));
        jScrollPane3.setViewportView(tblNguoihoc);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(13, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Người học", jPanel3);

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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 399, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
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
            java.util.logging.Logger.getLogger(ThongKe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ThongKe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ThongKe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ThongKe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ThongKe().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu Exit;
    private javax.swing.JMenu LogOut;
    private javax.swing.JLabel jLabel1;
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
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JMenu quanLyCD;
    private javax.swing.JMenu quanLyHV;
    private javax.swing.JMenu quanLyKH;
    private javax.swing.JMenu quanLyTK;
    private javax.swing.JTable tblDiem;
    private javax.swing.JTable tblDoanhThu;
    private javax.swing.JTable tblNguoihoc;
    // End of variables declaration//GEN-END:variables
}
