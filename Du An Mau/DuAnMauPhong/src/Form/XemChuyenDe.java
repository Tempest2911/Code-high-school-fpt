/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Form;

import DAOClass.CRUD_DAO;
import ModelsClass.Chuyende;
import ModelsClass.KhoaHoc;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import ModelsClass.KhoaHoc;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.sql.*;

/**
 *
 * @author Acer
 */
public class XemChuyenDe extends javax.swing.JFrame {

    private List<Chuyende> chuyendeList; // Danh sách các chuyên đề lấy ra từ db
    private JPanel contentPanel;
    private JLabel pageLabel;
    private JButton nextButton, backButton;
    private int currentPage = 1;
    private int itemsPerPage = 4;

    public XemChuyenDe() {
        initComponents();

        setTitle("Danh sách Chuyên đề");
        setSize(1000, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        loadChuyendeFromDB();
        initUI();

    }

    private void loadChuyendeFromDB() {
        chuyendeList = new ArrayList<>();
        String query = "SELECT * FROM Chuyende";
        try {
            ResultSet rs = CRUD_DAO.executeQuery(query);

            while (rs.next()) {
                chuyendeList.add(new Chuyende(
                        rs.getInt("IDCD"), // ID
                        rs.getString("TenChuyenDe"), // Tên chuyên đề
                        rs.getString("MoTa"), // Mô tả
                        rs.getDouble("GiaTien"), // Học phí (ép kiểu nếu cần)
                        rs.getString("HinhAnh") // Ảnh (nếu đúng kiểu)
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initUI() {
        contentPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        add(contentPanel, BorderLayout.CENTER);

        JPanel paginationPanel = new JPanel();
        backButton = new JButton("Back");
        nextButton = new JButton("Next");
        pageLabel = new JLabel("Page: 1");

        backButton.addActionListener(this::prevPage);
        nextButton.addActionListener(this::nextPage);

        paginationPanel.add(backButton);
        paginationPanel.add(pageLabel);
        paginationPanel.add(nextButton);
        add(paginationPanel, BorderLayout.SOUTH);

        loadPage();
        setVisible(true);
    }

    private JPanel createChuyendePanel(Chuyende cd) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        panel.setBackground(Color.LIGHT_GRAY);

        ImageIcon icon = new ImageIcon(new ImageIcon(cd.getImgURL()).getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH));
        panel.add(new JLabel(icon), BorderLayout.NORTH);

        JPanel infoPanel = new JPanel(new GridLayout(3, 1));
        infoPanel.add(new JLabel("Tên: " + cd.getName()));
        infoPanel.add(new JLabel("Mô tả: " + cd.getMota()));
        infoPanel.add(new JLabel("Học phí: " + cd.getHocphi() + " VND"));
        panel.add(infoPanel, BorderLayout.CENTER);

        JButton button = new JButton("Xem khóa học");
        button.addActionListener(e -> showKhoaHocDialog(cd.getId()));
        panel.add(button, BorderLayout.SOUTH);

        return panel;
    }

    private void showKhoaHocDialog(int chuyenDeId) {
        List<KhoaHoc> khoaHocList = getKhoaHocByChuyenDe(chuyenDeId);

        if (khoaHocList.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Không có khóa học nào thuộc chuyên đề này.");
            return;
        }

        JDialog dialog = new JDialog(this, "Danh sách khóa học", true);
        dialog.setSize(700, 500);
        dialog.setLocationRelativeTo(this);

        JPanel mainPanel = new JPanel(new GridLayout(0, 2, 15, 15));
        mainPanel.setBackground(Color.WHITE);

        for (KhoaHoc khoaHoc : khoaHocList) {
            mainPanel.add(createKhoaHocPanel(khoaHoc));
        }

        dialog.add(new JScrollPane(mainPanel));
        dialog.setVisible(true);

    }

    private JPanel createKhoaHocPanel(KhoaHoc khoaHoc) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
        panel.setBackground(Color.WHITE);
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                panel.setBackground(Color.LIGHT_GRAY); // Màu khi hover vào
            }

            @Override
            public void mouseExited(MouseEvent e) {
                panel.setBackground(Color.WHITE); // Trở về màu cũ khi rời chuột
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                // Xử lý khi panel được click
                DangKyKhoaHoc dk = new DangKyKhoaHoc();
                dk.setVisible(true);
                dispose();
            }

        });

        panel.setPreferredSize(new Dimension(300, 200));

        ImageIcon icon = new ImageIcon(new ImageIcon(khoaHoc.getImgURL()).getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH));
        JLabel imgLabel = new JLabel(icon, JLabel.CENTER);
        panel.add(imgLabel, BorderLayout.NORTH);

        JLabel nameLabel = new JLabel(khoaHoc.getName(), JLabel.CENTER);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 14));
        nameLabel.setForeground(Color.DARK_GRAY);
        panel.add(nameLabel, BorderLayout.CENTER);

        return panel;
    }

    private List<KhoaHoc> getKhoaHocByChuyenDe(int chuyenDeId) {
        List<KhoaHoc> khoaHocList = new ArrayList<>();
        String query = "SELECT * FROM KhoaHoc WHERE IDCD = ?";

        try (Connection conn = CRUD_DAO.getConnect(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, chuyenDeId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    khoaHocList.add(new KhoaHoc(
                            rs.getInt("IDKH"),
                            rs.getString("TenKhoaHoc"),
                            rs.getString("MoTa"),
                            rs.getFloat("GiaTien"),
                            rs.getString("HinhAnh")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return khoaHocList;
    }

    private void loadPage() {
        contentPanel.removeAll();
        int start = (currentPage - 1) * itemsPerPage;
        int end = Math.min(start + itemsPerPage, chuyendeList.size());
        for (int i = start; i < end; i++) {
            contentPanel.add(createChuyendePanel(chuyendeList.get(i)));
        }
        pageLabel.setText("Page: " + currentPage);
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    private void prevPage(ActionEvent e) {
        if (currentPage > 1) {
            currentPage--;
            loadPage();
        }
    }

    private void nextPage(ActionEvent e) {
        int totalPages = (int) Math.ceil((double) chuyendeList.size() / itemsPerPage);
        if (currentPage < totalPages) {
            currentPage++;
            loadPage();
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
            .addGap(0, 985, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 536, Short.MAX_VALUE)
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
            java.util.logging.Logger.getLogger(XemChuyenDe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(XemChuyenDe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(XemChuyenDe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(XemChuyenDe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new XemChuyenDe().setVisible(true);
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
