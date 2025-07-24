/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.coachmeai;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

public class ThongBao extends JPanel {

    static String connectionUrl = "jdbc:sqlserver://TEMPEST:1433;databaseName=Coach_Me_AI;trustServerCertificate=true;user=sa;password=123456";

    private JPanel panelThongBao;
    private JScrollPane scrollPane;

    public ThongBao() {
        giaoDien_thongBao();
    }

    public void giaoDien_thongBao() {
        this.setLayout(null);

        JPanel whitePanel = new JPanel();
        whitePanel.setBackground(Color.WHITE);
        whitePanel.setBounds(0, 0, 710, 650);
        whitePanel.setLayout(null); // Cho phép đặt vị trí tùy chỉnh

        JLabel chuongLabel = new JLabel();
        ImageIcon chuongImage = new ImageIcon("C:\\Users\\Admin\\Documents\\Image_TuAnh\\chuong.png");

        // Kiểm tra ảnh có tồn tại không
        if (chuongImage.getIconWidth() > 0) {
            // Chỉnh kích thước ảnh
            Image img = chuongImage.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
            chuongLabel.setIcon(new ImageIcon(img));
        } else {
            chuongLabel.setText("Ảnh không tồn tại!");
        }

        chuongLabel.setBounds(20, 20, 50, 50); // Vị trí ảnh

        // Tiêu đề "THÔNG BÁO"
        JLabel titleLabel = new JLabel("THÔNG BÁO");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 32));
        titleLabel.setBounds(80, 20, 300, 50); // Vị trí tiêu đề

        //button xóa thông báo 
        // Nút "Xóa thông báo"
        JButton btn_xoaThongBao = new JButton("Xóa thông báo");
        btn_xoaThongBao.setBounds(500, 60, 150, 40);
        btn_xoaThongBao.setBackground(new Color(10, 58, 95));
        btn_xoaThongBao.setForeground(Color.WHITE);
        btn_xoaThongBao.setFont(new Font("Arial", Font.BOLD, 15));

        // Sự kiện khi nhấn nút "Xóa thông báo"
        btn_xoaThongBao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int result = JOptionPane.showConfirmDialog(null,
                        "Bạn có chắc chắn xóa tất cả thông báo không?",
                        "Xác nhận xóa",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE);

                if (result == JOptionPane.YES_OPTION) {
                    JOptionPane.showMessageDialog(null, "Đã xóa tất cả thông báo!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        panelThongBao = new JPanel();
        panelThongBao.setLayout(new BoxLayout(panelThongBao, BoxLayout.Y_AXIS));

        scrollPane = new JScrollPane(panelThongBao);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        panelThongBao.setBackground(Color.WHITE);

        scrollPane.setBounds(20, 120, 650, 500); // Định vị trí của ScrollPane
        whitePanel.add(scrollPane);
        // Thêm vào whitePanel
        whitePanel.add(chuongLabel);
        whitePanel.add(titleLabel);
        whitePanel.add(btn_xoaThongBao);

        // Thêm vào JFrame
        this.add(whitePanel);

        loadThongBao();
        this.setVisible(true);
    }

    private void loadThongBao() {
        panelThongBao.removeAll();
        List<String[]> thongBaoList = getThongBaoFromDatabase();

        for (String[] tb : thongBaoList) {
            int thongBaoID = Integer.parseInt(tb[2]);

            JPanel panel = new JPanel(new BorderLayout());
            panel.setBackground(new Color(220, 240, 255));
            panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

            JLabel lblTieuDe = new JLabel("📌 " + tb[0]);
            lblTieuDe.setFont(new Font("Arial", Font.BOLD, 14));

            JLabel lblNgay = new JLabel(tb[1]);
            lblNgay.setFont(new Font("Arial", Font.ITALIC, 12));

            // Nút "Xóa"
            JButton btnXoa = new JButton("🗑 Xóa");
            btnXoa.addActionListener((ActionEvent e) -> xoaThongBao(thongBaoID));

            // Panel chứa 2 nút
            JPanel panelButtons = new JPanel();
            //panelButtons.add(btnXemChiTiet);
            panelButtons.add(btnXoa);
            // Panel chứa 2 nút
            panelButtons.setOpaque(true);
            panelButtons.setBackground(new Color(220, 240, 255));
            panelButtons.add(btnXoa);

            panel.add(lblTieuDe, BorderLayout.CENTER);
            panel.add(lblNgay, BorderLayout.SOUTH);
            panel.add(panelButtons, BorderLayout.EAST);
            scrollPane.setBorder(BorderFactory.createEmptyBorder());

            panelThongBao.add(panel);
            panelThongBao.add(Box.createVerticalStrut(10));
        }

        panelThongBao.revalidate();
        panelThongBao.repaint();
    }

    private List<String[]> getThongBaoFromDatabase() {
        List<String[]> thongBaoList = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(connectionUrl); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery("SELECT tbdg.ID, dtb.Tieu_De, dtb.Noi_Dung, tbdg.Thoi_Gian_Gui "
                + "FROM Thong_Bao_Da_Gui tbdg "
                + "JOIN Data_Thong_Bao dtb ON tbdg.ID_Thong_Bao = dtb.ID "
                + "ORDER BY tbdg.Thoi_Gian_Gui DESC")) {

            while (rs.next()) {
                String tieuDe = rs.getString("Tieu_De");
                String noiDung = rs.getString("Noi_Dung");
                String ngayGui = rs.getTimestamp("Thoi_Gian_Gui").toString();
                String id = rs.getString("ID");
                thongBaoList.add(new String[]{tieuDe, ngayGui, id, noiDung});
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return thongBaoList;
    }

    private void xemChiTiet(String tieuDe, String noiDung) {
        JTextArea textArea = new JTextArea(noiDung);
        textArea.setWrapStyleWord(true);
        textArea.setLineWrap(true);
        textArea.setEditable(false);
        textArea.setOpaque(false);

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(300, 150));

        JOptionPane.showMessageDialog(this, scrollPane, "📜 " + tieuDe, JOptionPane.INFORMATION_MESSAGE);
    }

    private void xoaThongBao(int idThongBao) {
        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn xóa thông báo này?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            try (Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost;databaseName=YourDB;user=sa;password=yourpassword"); PreparedStatement pstmt = conn.prepareStatement("DELETE FROM Thong_Bao_Da_Gui WHERE ID = ?")) {
                pstmt.setInt(1, idThongBao);
                pstmt.executeUpdate();
                loadThongBao();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void xoaTatCaThongBao() {
        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn xóa tất cả thông báo?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            try (Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost;databaseName=YourDB;user=sa;password=yourpassword"); Statement stmt = conn.createStatement()) {
                stmt.executeUpdate("DELETE FROM Thong_Bao_Da_Gui");
                loadThongBao();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Thông báo");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(710, 650);
            frame.setResizable(false);
            ThongBao thongBaoPanel = new ThongBao();
            frame.add(thongBaoPanel);
            frame.setLocationRelativeTo(null); // Căn giữa màn hình
            frame.setVisible(true);
        });
    }
}
