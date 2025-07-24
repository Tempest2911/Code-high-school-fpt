/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CodeMain;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.image.BufferedImage;
import javax.swing.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.table.DefaultTableModel;

public class trang_QuanLyBaiViet extends JPanel{

    private JPanel tong_2_panel;
    private CardLayout cardLayout;

    public trang_QuanLyBaiViet() {
        giaoDien_QuanLyBaiViet();
    }

    public void giaoDien_QuanLyBaiViet() {
        this.setSize(850, 650);
        this.setLayout(null);

        JPanel bluePanel = new JPanel();
        bluePanel.setBackground(new Color(0, 102, 153)); // Màu xanh
        bluePanel.setBounds(0, 0, 160, 650);
        bluePanel.setLayout(null);

        JPanel whitePanel = new JPanel();
        whitePanel.setBackground(Color.WHITE);
        whitePanel.setBounds(160, 0, 690, 650);
        whitePanel.setLayout(null);

        JLabel lbl_qlbv = new JLabel("QUẢN LÝ BÀI VIẾT");
        lbl_qlbv.setBounds(20, 5, 250, 30);
        lbl_qlbv.setFont(new Font("Arial", Font.BOLD, 20));
        whitePanel.add(lbl_qlbv);

        cardLayout = new CardLayout();
        tong_2_panel = new JPanel(cardLayout);
        tong_2_panel.setBounds(0, 35, 690, 650);
        whitePanel.add(tong_2_panel);

        // Tạo và thêm giao diện blue_2_Panel
        JPanel blue_2_Panel = danhSachBaiViet();
        tong_2_panel.add(blue_2_Panel, "BLUE_2");

        // Tạo và thêm giao diện blue_3_Panel
        JPanel blue_3_Panel = taoBaiVietMoi();
        tong_2_panel.add(blue_3_Panel, "BLUE_3");

        JPanel blue_4_Panel = suaBaiViet();
        tong_2_panel.add(blue_4_Panel, "BLUE_4");

        cardLayout.show(tong_2_panel, "BLUE_2");
        this.add(bluePanel);
        this.add(whitePanel);
        this.setVisible(true);
    }


    public JPanel danhSachBaiViet() {
        JPanel panel_tong = new JPanel();
        panel_tong.setBackground(Color.white);
        panel_tong.setLayout(null);
        JButton btn_taoBaiViet = new JButton("TẠO BÀI VIẾT MỚI NGAY");
        btn_taoBaiViet.setBounds(20, 0, 630, 50);
        btn_taoBaiViet.setFont(new Font("Arial", Font.BOLD, 25));
        btn_taoBaiViet.setForeground(Color.WHITE); // Chữ trắng
        btn_taoBaiViet.setBackground(Color.decode("#064469")); // Nền xanh
        btn_taoBaiViet.setBorderPainted(false); // Tắt viền
        btn_taoBaiViet.setFocusPainted(true); // Tắt hiệu ứng khi click
        btn_taoBaiViet.setContentAreaFilled(true); // Hiển thị màu nền
        JPanel panel = new JPanel();
        panel.setBackground(Color.decode("#D0EBF8"));
        panel.setBounds(20, 60, 630, 500); // Đặt kích thước để nó hiển thị đúng

        btn_taoBaiViet.addActionListener(e -> cardLayout.show(tong_2_panel, "BLUE_3"));

        panel_tong.add(btn_taoBaiViet);
        panel_tong.add(panel);
        panel.setLayout(null);
        String placeholder = "  Mời nhập tiêu đề hoặc danh mục bài viết cần tìm";
        JTextField txt_bv_timKiem = new JTextField(placeholder);
        txt_bv_timKiem.setBounds(10, 10, 500, 30);
        txt_bv_timKiem.setBackground(Color.white);
        txt_bv_timKiem.setForeground(Color.GRAY);
        txt_bv_timKiem.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
        txt_bv_timKiem.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (txt_bv_timKiem.getText().equals(placeholder)) {
                    txt_bv_timKiem.setText(""); // Xóa nội dung khi nhấn vào
                    txt_bv_timKiem.setForeground(Color.BLACK); // Đổi lại màu chữ bình thường
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (txt_bv_timKiem.getText().trim().isEmpty()) {
                    txt_bv_timKiem.setText(placeholder); // Đặt lại placeholder nếu ô trống
                    txt_bv_timKiem.setForeground(Color.GRAY); // Đổi màu chữ về xám
                }
            }
        });
        panel.add(txt_bv_timKiem);

        JButton btn_bv_timKiem = new JButton("Tìm Kiếm");
        btn_bv_timKiem.setBounds(520, 10, 100, 30);
        btn_bv_timKiem.setBackground(new Color(10, 38, 74));
        btn_bv_timKiem.setForeground(Color.WHITE);
        btn_bv_timKiem.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 12));
        panel.add(btn_bv_timKiem);

        JComboBox<String> cbo_bv_trangThai = new JComboBox<>(new String[]{
            "Trạng thái", "Đã xuất bản", "Bản nháp"
        });
        cbo_bv_trangThai.setBounds(10, 60, 150, 30);
        cbo_bv_trangThai.setBackground(Color.decode("#064469"));
        cbo_bv_trangThai.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 12));
        cbo_bv_trangThai.setBorder(null);
        cbo_bv_trangThai.setForeground(Color.white);
        panel.add(cbo_bv_trangThai);

        JComboBox<String> cbo_bv_thang = new JComboBox<>(new String[]{
            "Tháng", "Tháng 1", "Tháng 2", "Tháng 3", "Tháng 4", "Tháng 5",
            "Tháng 6", "Tháng 7", "Tháng 8", "Tháng 9", "Tháng 10", "Tháng 11", "Tháng 12"});
        cbo_bv_thang.setBounds(180, 60, 150, 30);
        cbo_bv_thang.setBackground(Color.decode("#064469"));
        cbo_bv_thang.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 12));
        cbo_bv_thang.setBorder(null);
        cbo_bv_thang.setForeground(Color.white);
        panel.add(cbo_bv_thang);

        JComboBox<String> cbo_bv_nam = new JComboBox<>(new String[]{
            "Năm", "Năm 2023", "Năm 2024", "Năm 2025"
        });
        cbo_bv_nam.setBounds(350, 60, 150, 30);
        cbo_bv_nam.setBackground(Color.decode("#064469"));
        cbo_bv_nam.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 12));
        cbo_bv_nam.setBorder(null);
        cbo_bv_nam.setForeground(Color.white);
        panel.add(cbo_bv_nam);

        DefaultTableModel tbl_qlbv = new DefaultTableModel();
        tbl_qlbv.addColumn("ID");
        tbl_qlbv.addColumn("Tiêu đề bài viết");
        tbl_qlbv.addColumn("Mô tả ngắn gọn");
        tbl_qlbv.addColumn("Ngày đăng");
        tbl_qlbv.addColumn("Trạng Thái");
        tbl_qlbv.addColumn("Chức năng");

        JTable tb_data = new JTable();
        tb_data.setRowHeight(30);
        tb_data.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        tb_data.setModel(tbl_qlbv);

        // Đặt bảng vào JScrollPane
        JScrollPane scrollPane = new JScrollPane(tb_data);
        scrollPane.setBounds(0, 120, 630, 400);
        panel.add(scrollPane, BorderLayout.CENTER);
        return panel_tong;
    }

    public JPanel taoBaiVietMoi() {
        JPanel panel_tong = new JPanel();
        panel_tong.setBackground(Color.white);
        panel_tong.setLayout(null);

        JLabel lbl_tieuDe = new JLabel("Tiêu đề:");
        lbl_tieuDe.setBounds(25, 10, 100, 30);
        lbl_tieuDe.setBackground(Color.red);
        lbl_tieuDe.setFont(new Font("Arial", Font.PLAIN, 16));
        panel_tong.add(lbl_tieuDe);

        JTextField txt_tieuDe = new JTextField();
        txt_tieuDe.setBounds(100, 10, 400, 30);
        panel_tong.add(txt_tieuDe);

        JLabel lbl_moTa = new JLabel("Mô tả:");
        lbl_moTa.setBounds(25, 40, 100, 30);
        lbl_moTa.setBackground(Color.red);
        lbl_moTa.setFont(new Font("Arial", Font.PLAIN, 16));
        panel_tong.add(lbl_moTa);

        JTextField txt_moTa = new JTextField();
        txt_moTa.setBounds(100, 40, 400, 30);
        panel_tong.add(txt_moTa);

        JButton btn_luuBanNhap = new JButton("Lưu bản nháp");
        btn_luuBanNhap.setBounds(510, 10, 150, 30);
        btn_luuBanNhap.setForeground(Color.WHITE); // Chữ trắng
        btn_luuBanNhap.setFont(new Font("Arial", Font.BOLD, 15));
        btn_luuBanNhap.setBackground(new Color(10, 38, 74));
        panel_tong.add(btn_luuBanNhap);

        JButton btn_xuatBan = new JButton("Xuất bản");
        btn_xuatBan.setBounds(510, 40, 150, 30);
        btn_xuatBan.setForeground(Color.WHITE); // Chữ trắng
        btn_xuatBan.setFont(new Font("Arial", Font.BOLD, 15));
        btn_xuatBan.setBackground(new Color(10, 38, 74));
        panel_tong.add(btn_xuatBan);

        JLabel lbl_baiVietCuaBan = new JLabel("Bài viết của bạn:");
        lbl_baiVietCuaBan.setBounds(25, 70, 200, 30);
        lbl_baiVietCuaBan.setFont(new Font("Arial", Font.PLAIN, 16));
        panel_tong.add(lbl_baiVietCuaBan);

        JTextArea txt_NoiDungBV = new JTextArea();
        txt_NoiDungBV.setLineWrap(true);
        txt_NoiDungBV.setWrapStyleWord(true);
        JScrollPane scrollContent_1 = new JScrollPane(txt_NoiDungBV);
        scrollContent_1.setBounds(25, 100, 630, 200);
        scrollContent_1.setPreferredSize(new Dimension(630, 200));

        panel_tong.add(scrollContent_1);

        JLabel lbl_hinhAnh = new JLabel("Hình ảnh:");
        lbl_hinhAnh.setBounds(25, 300, 100, 30);
        lbl_hinhAnh.setFont(new Font("Arial", Font.PLAIN, 16));
        panel_tong.add(lbl_hinhAnh);

        JLabel lbl_HinhAnhBV = new JLabel();
        lbl_HinhAnhBV.setBounds(25, 330, 630, 200);
        lbl_HinhAnhBV.setBorder(BorderFactory.createLineBorder(Color.GRAY)); // Viền
        lbl_HinhAnhBV.setHorizontalAlignment(JLabel.CENTER);
        panel_tong.add(lbl_HinhAnhBV);

        JButton btn_quayLai = new JButton("Quay lại");
        btn_quayLai.setBounds(25, 540, 100, 30);
        btn_quayLai.setForeground(Color.WHITE); // Chữ trắng
        btn_quayLai.setFont(new Font("Arial", Font.BOLD, 15));
        btn_quayLai.setBackground(new Color(10, 38, 74));
        panel_tong.add(btn_quayLai);

        btn_quayLai.addActionListener(e -> cardLayout.show(tong_2_panel, "BLUE_2"));

        JButton btn_chonAnh = new JButton("Chọn Ảnh");
        btn_chonAnh.setBounds(500, 540, 150, 30);
        btn_chonAnh.setForeground(Color.WHITE); // Chữ trắng
        btn_chonAnh.setFont(new Font("Arial", Font.BOLD, 15));
        btn_chonAnh.setBackground(new Color(10, 38, 74));
        panel_tong.add(btn_chonAnh);
        btn_chonAnh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Chọn một hình ảnh");
                fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    ImageIcon icon = new ImageIcon(selectedFile.getAbsolutePath());

                    // Resize hình ảnh cho vừa JLabel
                    Image img = icon.getImage().getScaledInstance(630, 200, Image.SCALE_SMOOTH);
                    lbl_HinhAnhBV.setIcon(new ImageIcon(img));
                }
            }
        });
        return panel_tong;
    }

    public JPanel suaBaiViet() {
        JPanel panel_tong = new JPanel();
        panel_tong.setBackground(Color.white);
        panel_tong.setLayout(null);

        JLabel lbl_tieuDe = new JLabel("Tiêu đề:");
        lbl_tieuDe.setBounds(25, 10, 100, 30);
        lbl_tieuDe.setBackground(Color.red);
        lbl_tieuDe.setFont(new Font("Arial", Font.PLAIN, 16));
        panel_tong.add(lbl_tieuDe);

        JTextField txt_tieuDe = new JTextField();
        txt_tieuDe.setBounds(100, 10, 400, 30);
        panel_tong.add(txt_tieuDe);

        JLabel lbl_moTa = new JLabel("Mô tả:");
        lbl_moTa.setBounds(25, 40, 100, 30);
        lbl_moTa.setBackground(Color.red);
        lbl_moTa.setFont(new Font("Arial", Font.PLAIN, 16));
        panel_tong.add(lbl_moTa);

        JTextField txt_moTa = new JTextField();
        txt_moTa.setBounds(100, 40, 400, 30);
        panel_tong.add(txt_moTa);

        JButton btn_luuBanNhap = new JButton("Lưu bản nháp");
        btn_luuBanNhap.setBounds(510, 10, 150, 30);
        btn_luuBanNhap.setForeground(Color.WHITE); // Chữ trắng
        btn_luuBanNhap.setFont(new Font("Arial", Font.BOLD, 15));
        btn_luuBanNhap.setBackground(new Color(10, 38, 74));
        panel_tong.add(btn_luuBanNhap);

        JButton btn_xuatBan = new JButton("Xuất bản");
        btn_xuatBan.setBounds(510, 40, 150, 30);
        btn_xuatBan.setForeground(Color.WHITE); // Chữ trắng
        btn_xuatBan.setFont(new Font("Arial", Font.BOLD, 15));
        btn_xuatBan.setBackground(new Color(10, 38, 74));
        panel_tong.add(btn_xuatBan);

        JLabel lbl_baiVietCuaBan = new JLabel("Bài viết của bạn:");
        lbl_baiVietCuaBan.setBounds(25, 70, 200, 30);
        lbl_baiVietCuaBan.setFont(new Font("Arial", Font.PLAIN, 16));
        panel_tong.add(lbl_baiVietCuaBan);

        JTextArea txt_NoiDungBV = new JTextArea();
        txt_NoiDungBV.setLineWrap(true);
        txt_NoiDungBV.setWrapStyleWord(true);
        JScrollPane scrollContent_1 = new JScrollPane(txt_NoiDungBV);
        scrollContent_1.setBounds(25, 100, 630, 200);
        panel_tong.add(scrollContent_1);

        JLabel lbl_hinhAnh = new JLabel("Hình ảnh:");
        lbl_hinhAnh.setBounds(25, 300, 100, 30);
        lbl_hinhAnh.setFont(new Font("Arial", Font.PLAIN, 16));
        panel_tong.add(lbl_hinhAnh);

        JLabel lbl_HinhAnhBV = new JLabel();
        lbl_HinhAnhBV.setBounds(25, 330, 630, 200);
        lbl_HinhAnhBV.setBorder(BorderFactory.createLineBorder(Color.GRAY)); // Viền
        lbl_HinhAnhBV.setHorizontalAlignment(JLabel.CENTER);
        panel_tong.add(lbl_HinhAnhBV);

        JButton btn_quayLai = new JButton("Quay lại");
        btn_quayLai.setBounds(25, 540, 100, 30);
        btn_quayLai.setForeground(Color.WHITE); // Chữ trắng
        btn_quayLai.setFont(new Font("Arial", Font.BOLD, 15));
        btn_quayLai.setBackground(new Color(10, 38, 74));
        panel_tong.add(btn_quayLai);

        btn_quayLai.addActionListener(e -> cardLayout.show(tong_2_panel, "BLUE_3"));

        JButton btn_chonAnh = new JButton("Chọn Ảnh");
        btn_chonAnh.setBounds(500, 540, 150, 30);
        btn_chonAnh.setForeground(Color.WHITE); // Chữ trắng
        btn_chonAnh.setFont(new Font("Arial", Font.BOLD, 15));
        btn_chonAnh.setBackground(new Color(10, 38, 74));
        panel_tong.add(btn_chonAnh);
        btn_chonAnh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Chọn một hình ảnh");
                fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    ImageIcon icon = new ImageIcon(selectedFile.getAbsolutePath());

                    // Resize hình ảnh cho vừa JLabel
                    Image img = icon.getImage().getScaledInstance(630, 200, Image.SCALE_SMOOTH);
                    lbl_HinhAnhBV.setIcon(new ImageIcon(img));
                }
            }
        });
        return panel_tong;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Feedback");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(850, 650);
            frame.setResizable(false);

            trang_QuanLyBaiViet quanLyBaiVietPanel = new trang_QuanLyBaiViet();
            frame.add(quanLyBaiVietPanel);

            frame.setLocationRelativeTo(null); // Căn giữa màn hình
            frame.setVisible(true);
        });
    }
}
