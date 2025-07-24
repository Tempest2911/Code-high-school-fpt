/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.coachmeai;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Label;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

/**
 *
 * @author Admin
 */
public class ThongTinCaNhan extends JPanel {

    private JPanel blueCardPanel; // JPanel chứa blue_2_Panel và blue_3_Panel
    private CardLayout cardLayout;

    public ThongTinCaNhan() {
        giaoDien_ThongTinCaNhan();
    }

    public void giaoDien_ThongTinCaNhan() {
        this.setLayout(null);

        JPanel whitePanel = new JPanel();
        whitePanel.setBackground(Color.WHITE);
        whitePanel.setBounds(0, 0, 710, 650);
        whitePanel.setLayout(null);

        CircleLabel lbl_white_anhTron_ta = new CircleLabel(100);
        lbl_white_anhTron_ta.setBounds(50, 30, 100, 100);
        whitePanel.add(lbl_white_anhTron_ta);

        JLabel txt_white_ten_ta = new JLabel("Tên tài khoản");
        txt_white_ten_ta.setBounds(170, 30, 200, 30);
        txt_white_ten_ta.setFont(new Font("Arial", Font.BOLD, 20));
        whitePanel.add(txt_white_ten_ta);

        Label lbl_white_vaiTro_ta = new Label("Vai trò:");
        lbl_white_vaiTro_ta.setBounds(170, 60, 50, 30);
        lbl_white_vaiTro_ta.setFont(new Font("Arial", Font.PLAIN, 15));
        whitePanel.add(lbl_white_vaiTro_ta);

        JLabel lbl_white_trangThai_ta = new JLabel("Liên kết:");
        lbl_white_trangThai_ta.setBounds(170, 90, 70, 30);
        lbl_white_trangThai_ta.setFont(new Font("Arial", Font.PLAIN, 15));
        whitePanel.add(lbl_white_trangThai_ta);

        // Tạo JButton
        JButton btn_white_ttcn_ta = new JButton("Thông tin cá nhân");
        btn_white_ttcn_ta.setBounds(480, 30, 180, 30);
        btn_white_ttcn_ta.setFont(new Font("Arial", Font.PLAIN, 15));
        btn_white_ttcn_ta.setBackground(Color.WHITE);
        btn_white_ttcn_ta.setForeground(Color.BLACK);
        // Resize icon để vừa với button (kích thước 20x20)
        ImageIcon ttcn_icon_1 = new ImageIcon("C:\\Users\\Admin\\Documents\\Coach_Me_AI\\src\\Image_TuAnh\\avatar.png");
        Image ttcb_icon_2 = ttcn_icon_1.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        ImageIcon ttcb_icon = new ImageIcon(ttcb_icon_2);
        btn_white_ttcn_ta.setIcon(ttcb_icon);
        btn_white_ttcn_ta.setHorizontalTextPosition(SwingConstants.RIGHT); // Chữ bên phải icon
        btn_white_ttcn_ta.setIconTextGap(10); // Khoảng cách giữa icon và chữ
        // Thêm button vào panel
        whitePanel.add(btn_white_ttcn_ta);

        JButton btn_white_dmk_ta = new JButton("Đổi mật khẩu");
        btn_white_dmk_ta.setBounds(480, 60, 180, 30);
        btn_white_dmk_ta.setFont(new Font("Arial", Font.PLAIN, 15));
        btn_white_dmk_ta.setBackground(Color.WHITE);
        ImageIcon dmk_icon_1 = new ImageIcon("C:\\Users\\Admin\\Documents\\Coach_Me_AI\\src\\Image_TuAnh\\doiMatKhau.png");
        Image dmk_icon_2 = dmk_icon_1.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        ImageIcon dmk_icon = new ImageIcon(dmk_icon_2);
        btn_white_dmk_ta.setIcon(dmk_icon);
        btn_white_dmk_ta.setHorizontalTextPosition(SwingConstants.RIGHT); // Chữ bên phải icon
        btn_white_dmk_ta.setIconTextGap(10);
        whitePanel.add(btn_white_dmk_ta);

        JButton btn_white_dx_ta = new JButton("Đăng xuất");
        btn_white_dx_ta.setBounds(480, 90, 180, 30);
        btn_white_dx_ta.setFont(new Font("Arial", Font.PLAIN, 15));
        btn_white_dx_ta.setBackground(Color.WHITE);
        ImageIcon dx_icon_1 = new ImageIcon("C:\\Users\\Admin\\Documents\\Coach_Me_AI\\src\\Image_TuAnh\\dangXuat.png");
        Image dx_icon_2 = dx_icon_1.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        ImageIcon dx_icon = new ImageIcon(dx_icon_2);
        btn_white_dx_ta.setIcon(dx_icon);
        btn_white_dx_ta.setHorizontalTextPosition(SwingConstants.RIGHT); // Chữ bên phải icon
        btn_white_dx_ta.setIconTextGap(10);
        whitePanel.add(btn_white_dx_ta);

        btn_white_ttcn_ta.addActionListener(e -> cardLayout.show(blueCardPanel, "BLUE_2"));

        btn_white_ttcn_ta.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btn_white_ttcn_ta.setBackground(Color.decode("#064469")); // Chuyển sang màu xanh đậm khi hover
                btn_white_ttcn_ta.setForeground(Color.WHITE); // Chữ trắng khi hover
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btn_white_ttcn_ta.setBackground(Color.WHITE); // Trở lại màu trắng khi rời chuột
                btn_white_ttcn_ta.setForeground(Color.BLACK); // Chữ quay về màu đen
            }
        });

        btn_white_dmk_ta.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btn_white_dmk_ta.setBackground(Color.decode("#064469")); // Chuyển sang màu xanh đậm khi hover
                btn_white_dmk_ta.setForeground(Color.WHITE); // Chữ trắng khi hover
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btn_white_dmk_ta.setBackground(Color.WHITE); // Trở lại màu trắng khi rời chuột
                btn_white_dmk_ta.setForeground(Color.BLACK); // Chữ quay về màu đen
            }
        });
        btn_white_dx_ta.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btn_white_dx_ta.setBackground(Color.decode("#064469")); // Chuyển sang màu xanh đậm khi hover
                btn_white_dx_ta.setForeground(Color.WHITE); // Chữ trắng khi hover
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btn_white_dx_ta.setBackground(Color.WHITE); // Trở lại màu trắng khi rời chuột
                btn_white_dx_ta.setForeground(Color.BLACK); // Chữ quay về màu đen
            }
        });

        // Khởi tạo CardLayout để quản lý các giao diện trong blueCardPanel
        cardLayout = new CardLayout();
        blueCardPanel = new JPanel(cardLayout);
        blueCardPanel.setBounds(20, 150, 635, 450);
        whitePanel.add(blueCardPanel);

        JPanel blue_tkht_Panel = giaoDien_0_1_ThongKeHocTap();
        blueCardPanel.add(blue_tkht_Panel, "BLUE_tkht");

        JPanel blue_tkbd_Panel = giaoDien_0_2_ThongKeBangDiem();
        blueCardPanel.add(blue_tkbd_Panel, "BLUE_tkbd");

        JPanel blue_2_Panel = giaoDien_1_ThongTinCaNhan();
        blueCardPanel.add(blue_2_Panel, "BLUE_2");

        JPanel blue_3_Panel = giaoDien_2_ChinhSuaThongTinCaNhan();
        blueCardPanel.add(blue_3_Panel, "BLUE_3");

        JPanel blue_4_Panel = giaoDien_3_DoiMatKhau();
        blueCardPanel.add(blue_4_Panel, "BLUE_4");

        cardLayout.show(blueCardPanel, "BLUE_1");

        btn_white_ttcn_ta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(blueCardPanel, "BLUE_2"); // Chuyển sang giao diện giaoDien_1
            }
        });

        btn_white_dmk_ta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(blueCardPanel, "BLUE_4"); // Chuyển sang giao diện giaoDien_1
            }
        });

        JFrame frame = new JFrame("Đăng Xuất");
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        btn_white_dx_ta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int result = JOptionPane.showConfirmDialog(frame, "Bạn có chắc chắn muốn đăng xuất?",
                        "Xác nhận đăng xuất", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    JOptionPane.showMessageDialog(frame, "Bạn đã đăng xuất thành công!");
                    System.exit(0); // Thoát ứng dụng
                }
            }
        });
        this.add(whitePanel);
        this.setVisible(true);
    }

    public JPanel giaoDien_0_1_ThongKeHocTap() {
        JPanel panel = new JPanel();
        panel.setBackground(Color.decode("#D0EBF8"));
        panel.setBounds(20, 150, 635, 450);
        panel.setLayout(null); // Bỏ layout để tự set vị trí

        JLabel lbl_tkht = new JLabel("THỐNG KÊ HỌC TẬP");
        lbl_tkht.setBounds(20, 20, 250, 30);
        lbl_tkht.setFont(new Font("Arial", Font.BOLD, 20));
        panel.add(lbl_tkht);

        JComboBox<String> cbo_hv_thang = new JComboBox<>(new String[]{
            "Tháng", "Tháng 1", "Tháng 2", "Tháng 3", "Tháng 4", "Tháng 5",
            "Tháng 6", "Tháng 7", "Tháng 8", "Tháng 9", "Tháng 10", "Tháng 11", "Tháng 12"});
        cbo_hv_thang.setBounds(307, 20, 150, 30);
        cbo_hv_thang.setBackground(Color.decode("#064469"));
        cbo_hv_thang.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 12));
        cbo_hv_thang.setBorder(null);
        cbo_hv_thang.setForeground(Color.white);
        panel.add(cbo_hv_thang);

        JComboBox<String> cbo_hv_nam = new JComboBox<>(new String[]{
            "Năm", "Năm 2023", "Năm 2024", "Năm 2025"
        });
        cbo_hv_nam.setBounds(467, 20, 150, 30);
        cbo_hv_nam.setBackground(Color.decode("#064469"));
        cbo_hv_nam.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 12));
        cbo_hv_nam.setBorder(null);
        cbo_hv_nam.setForeground(Color.white);
        panel.add(cbo_hv_nam);

        JPanel panel_soGioHoc = new JPanel();
        panel_soGioHoc.setBackground(Color.WHITE);// Nền trắng 
        panel_soGioHoc.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1)); // Viền đen dày 2px
        panel_soGioHoc.setBounds(20, 80, 190, 100);
        panel_soGioHoc.setLayout(null);
        panel.add(panel_soGioHoc);

        JPanel panel_nhiemVuDaHoanThanh = new JPanel();
        panel_nhiemVuDaHoanThanh.setBackground(Color.WHITE); // Nền trắng
        panel_nhiemVuDaHoanThanh.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1)); // Viền đen dày 2px
        panel_nhiemVuDaHoanThanh.setBounds(225, 80, 190, 100);
        panel_nhiemVuDaHoanThanh.setLayout(null);
        panel.add(panel_nhiemVuDaHoanThanh);

        JPanel panel_nhiemVuChuaHoanThanh = new JPanel();
        panel_nhiemVuChuaHoanThanh.setBackground(Color.WHITE); // Nền trắng
        panel_nhiemVuChuaHoanThanh.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1)); // Viền đen dày 2px
        panel_nhiemVuChuaHoanThanh.setBounds(430, 80, 190, 100);
        panel_nhiemVuChuaHoanThanh.setLayout(null);
        panel.add(panel_nhiemVuChuaHoanThanh);

        JLabel lbl_sgh_1 = new JLabel("Số giờ học");
        lbl_sgh_1.setBounds(10, 10, 150, 20);
        lbl_sgh_1.setFont(new Font("Arial", Font.BOLD, 12));
        panel_soGioHoc.add(lbl_sgh_1);

        JLabel lbl_sgh_2 = new JLabel("...");
        lbl_sgh_2.setBounds(10, 35, 100, 30);
        lbl_sgh_2.setFont(new Font("Arial", Font.BOLD, 27));
        panel_soGioHoc.add(lbl_sgh_2);

        JLabel lbl_sgh_3 = new JLabel("Trong 1 tháng");
        lbl_sgh_3.setBounds(10, 70, 200, 20);
        lbl_sgh_3.setForeground(Color.GRAY);
        panel_soGioHoc.add(lbl_sgh_3);

        JLabel lbl_nvdht_1 = new JLabel("Nhiệm vụ đã hoàn thành");
        lbl_nvdht_1.setBounds(10, 10, 150, 20);
        lbl_nvdht_1.setFont(new Font("Arial", Font.BOLD, 12));
        panel_nhiemVuDaHoanThanh.add(lbl_nvdht_1);

        JLabel lbl_nvdht_2 = new JLabel("...");
        lbl_nvdht_2.setBounds(10, 35, 100, 30);
        lbl_nvdht_2.setFont(new Font("Arial", Font.BOLD, 27));
        panel_nhiemVuDaHoanThanh.add(lbl_nvdht_2);

        JLabel lbl_nvdht_3 = new JLabel("Trong 1 tháng");
        lbl_nvdht_3.setBounds(10, 70, 200, 20);
        lbl_nvdht_3.setForeground(Color.GRAY);
        panel_nhiemVuDaHoanThanh.add(lbl_nvdht_3);

        JLabel lbl_nvcht_1 = new JLabel("Nhiệm vụ chưa hoàn thành");
        lbl_nvcht_1.setBounds(10, 10, 155, 20);
        lbl_nvcht_1.setFont(new Font("Arial", Font.BOLD, 12));
        panel_nhiemVuChuaHoanThanh.add(lbl_nvcht_1);

        JLabel lbl_nvcht_2 = new JLabel("...");
        lbl_nvcht_2.setBounds(10, 35, 100, 30);
        lbl_nvcht_2.setFont(new Font("Arial", Font.BOLD, 27));
        panel_nhiemVuChuaHoanThanh.add(lbl_nvcht_2);

        JLabel lbl_nvcht_3 = new JLabel("Trong 1 tháng");
        lbl_nvcht_3.setBounds(10, 70, 200, 20);
        lbl_nvcht_3.setForeground(Color.GRAY);
        panel_nhiemVuChuaHoanThanh.add(lbl_nvcht_3);

        JLabel lbl_cauHoi = new JLabel("CÂU HỎI");
        lbl_cauHoi.setBounds(20, 200, 100, 20);
        lbl_cauHoi.setForeground(Color.GRAY);
        lbl_cauHoi.setFont(new Font("Arial", Font.BOLD, 18));
        panel.add(lbl_cauHoi);

        // Mục 1: Hỏi Coach Me AI
        JLabel coachMeAI = createMenuItem("Hỏi Coach Me AI", "C:\\Users\\Admin\\Documents\\Image_TuAnh\\1.PNG");
        coachMeAI.setBounds(20, 240, 250, 30);

        // Mục 2: Liên hệ với chúng tôi
        JLabel lienHe = createMenuItem("Liên hệ với chúng tôi", "C:\\Users\\Admin\\Documents\\Image_TuAnh\\2.PNG");
        lienHe.setBounds(20, 280, 250, 30);

        // Tiêu đề "Công cụ"
        JLabel labelCongCu = new JLabel("CÔNG CỤ");
        labelCongCu.setFont(new Font("Arial", Font.BOLD, 18));
        labelCongCu.setForeground(Color.GRAY);
        labelCongCu.setBounds(20, 320, 200, 20);

        // Mục 3: Tạo lịch học
        JLabel taoLichHoc = createMenuItem("Tạo lịch học", "C:\\Users\\Admin\\Documents\\Image_TuAnh\\3.PNG");
        taoLichHoc.setBounds(20, 360, 250, 30);

        // Mục 4: Bộ đếm giờ học
        JLabel boDemGio = createMenuItem("Bộ đếm giờ học", "C:\\Users\\Admin\\Documents\\Image_TuAnh\\4.PNG");
        boDemGio.setBounds(20, 400, 250, 30);

        JButton btn_ThongKeBangDiem = new JButton("Thống kê bảng điểm");
        btn_ThongKeBangDiem.setBounds(470, 400, 150, 30);
        btn_ThongKeBangDiem.setBackground(Color.decode("#064469")); // Màu nền
        btn_ThongKeBangDiem.setForeground(Color.WHITE); // Màu chữ thành trắng
        btn_ThongKeBangDiem.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 12));
        panel.add(btn_ThongKeBangDiem);

        // Thêm các thành phần vào panel
        panel.add(coachMeAI);
        panel.add(lienHe);
        panel.add(labelCongCu);
        panel.add(taoLichHoc);
        panel.add(boDemGio);
        btn_ThongKeBangDiem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(blueCardPanel, "BLUE_tkbd"); // Chuyển sang giao diện giaoDien_1
            }
        });
        return panel;
    }

    public JPanel giaoDien_0_2_ThongKeBangDiem() {
        JPanel panel = new JPanel();
        panel.setBackground(Color.decode("#D0EBF8"));
        panel.setBounds(20, 150, 635, 450);
        panel.setLayout(null); // Bỏ layout để tự set vị trí

        JLabel lbl_tkht = new JLabel("THỐNG KÊ BẢNG ĐIỂM");
        lbl_tkht.setBounds(20, 20, 250, 30);
        lbl_tkht.setFont(new Font("Arial", Font.BOLD, 20));
        panel.add(lbl_tkht);

        JComboBox<String> cbo_hv_thang = new JComboBox<>(new String[]{
            "Tháng", "Tháng 1", "Tháng 2", "Tháng 3", "Tháng 4", "Tháng 5",
            "Tháng 6", "Tháng 7", "Tháng 8", "Tháng 9", "Tháng 10", "Tháng 11", "Tháng 12"});
        cbo_hv_thang.setBounds(307, 20, 150, 30);
        cbo_hv_thang.setBackground(Color.decode("#064469"));
        cbo_hv_thang.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 12));
        cbo_hv_thang.setBorder(null);
        cbo_hv_thang.setForeground(Color.white);
        panel.add(cbo_hv_thang);

        JComboBox<String> cbo_hv_nam = new JComboBox<>(new String[]{
            "Năm", "Năm 2023", "Năm 2024", "Năm 2025"
        });
        cbo_hv_nam.setBounds(467, 20, 150, 30);
        cbo_hv_nam.setBackground(Color.decode("#064469"));
        cbo_hv_nam.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 12));
        cbo_hv_nam.setBorder(null);
        cbo_hv_nam.setForeground(Color.white);
        panel.add(cbo_hv_nam);

        JPanel panel_soGioHoc = new JPanel();
        panel_soGioHoc.setBackground(Color.WHITE);// Nền trắng 
        panel_soGioHoc.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1)); // Viền đen dày 2px
        panel_soGioHoc.setBounds(20, 80, 190, 100);
        panel_soGioHoc.setLayout(null);
        panel.add(panel_soGioHoc);

        JPanel panel_nhiemVuDaHoanThanh = new JPanel();
        panel_nhiemVuDaHoanThanh.setBackground(Color.WHITE); // Nền trắng
        panel_nhiemVuDaHoanThanh.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1)); // Viền đen dày 2px
        panel_nhiemVuDaHoanThanh.setBounds(225, 80, 190, 100);
        panel_nhiemVuDaHoanThanh.setLayout(null);
        panel.add(panel_nhiemVuDaHoanThanh);

        JPanel panel_nhiemVuChuaHoanThanh = new JPanel();
        panel_nhiemVuChuaHoanThanh.setBackground(Color.WHITE); // Nền trắng
        panel_nhiemVuChuaHoanThanh.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1)); // Viền đen dày 2px
        panel_nhiemVuChuaHoanThanh.setBounds(430, 80, 190, 100);
        panel_nhiemVuChuaHoanThanh.setLayout(null);
        panel.add(panel_nhiemVuChuaHoanThanh);

        JLabel lbl_sgh_1 = new JLabel("Điểm TB môn toán");
        lbl_sgh_1.setBounds(10, 10, 150, 20);
        lbl_sgh_1.setFont(new Font("Arial", Font.BOLD, 12));
        panel_soGioHoc.add(lbl_sgh_1);

        JLabel lbl_sgh_2 = new JLabel("...");
        lbl_sgh_2.setBounds(10, 35, 100, 30);
        lbl_sgh_2.setFont(new Font("Arial", Font.BOLD, 27));
        panel_soGioHoc.add(lbl_sgh_2);

        JLabel lbl_sgh_3 = new JLabel("Trong 1 tháng");
        lbl_sgh_3.setBounds(10, 70, 200, 20);
        lbl_sgh_3.setForeground(Color.GRAY);
        panel_soGioHoc.add(lbl_sgh_3);

        JLabel lbl_nvdht_1 = new JLabel("Điểm TB môn văn");
        lbl_nvdht_1.setBounds(10, 10, 150, 20);
        lbl_nvdht_1.setFont(new Font("Arial", Font.BOLD, 12));
        panel_nhiemVuDaHoanThanh.add(lbl_nvdht_1);

        JLabel lbl_nvdht_2 = new JLabel("...");
        lbl_nvdht_2.setBounds(10, 35, 100, 30);
        lbl_nvdht_2.setFont(new Font("Arial", Font.BOLD, 27));
        panel_nhiemVuDaHoanThanh.add(lbl_nvdht_2);

        JLabel lbl_nvdht_3 = new JLabel("Trong 1 tháng");
        lbl_nvdht_3.setBounds(10, 70, 200, 20);
        lbl_nvdht_3.setForeground(Color.GRAY);
        panel_nhiemVuDaHoanThanh.add(lbl_nvdht_3);

        JLabel lbl_nvcht_1 = new JLabel("Điểm TB môn anh");
        lbl_nvcht_1.setBounds(10, 10, 155, 20);
        lbl_nvcht_1.setFont(new Font("Arial", Font.BOLD, 12));
        panel_nhiemVuChuaHoanThanh.add(lbl_nvcht_1);

        JLabel lbl_nvcht_2 = new JLabel("...");
        lbl_nvcht_2.setBounds(10, 35, 100, 30);
        lbl_nvcht_2.setFont(new Font("Arial", Font.BOLD, 27));
        panel_nhiemVuChuaHoanThanh.add(lbl_nvcht_2);

        JLabel lbl_nvcht_3 = new JLabel("Trong 1 tháng");
        lbl_nvcht_3.setBounds(10, 70, 200, 20);
        lbl_nvcht_3.setForeground(Color.GRAY);
        panel_nhiemVuChuaHoanThanh.add(lbl_nvcht_3);

        JLabel lbl_cauHoi = new JLabel("CÂU HỎI");
        lbl_cauHoi.setBounds(20, 200, 100, 20);
        lbl_cauHoi.setForeground(Color.GRAY);
        lbl_cauHoi.setFont(new Font("Arial", Font.BOLD, 18));
        panel.add(lbl_cauHoi);

        // Mục 1: Hỏi Coach Me AI
        JLabel coachMeAI = createMenuItem("Hỏi Coach Me AI", "C:\\Users\\Admin\\Documents\\Image_TuAnh\\1.PNG");
        coachMeAI.setBounds(20, 240, 250, 30);

        // Mục 2: Liên hệ với chúng tôi
        JLabel lienHe = createMenuItem("Liên hệ với chúng tôi", "C:\\Users\\Admin\\Documents\\Image_TuAnh\\2.PNG");
        lienHe.setBounds(20, 280, 250, 30);

        // Tiêu đề "Công cụ"
        JLabel labelCongCu = new JLabel("CÔNG CỤ");
        labelCongCu.setFont(new Font("Arial", Font.BOLD, 18));
        labelCongCu.setForeground(Color.GRAY);
        labelCongCu.setBounds(20, 320, 200, 20);

        // Mục 3: Tạo lịch học
        JLabel taoLichHoc = createMenuItem("Tạo lịch học", "C:\\Users\\Admin\\Documents\\Image_TuAnh\\3.PNG");
        taoLichHoc.setBounds(20, 360, 250, 30);

        // Mục 4: Bộ đếm giờ học
        JLabel boDemGio = createMenuItem("Bộ đếm giờ học", "C:\\Users\\Admin\\Documents\\Image_TuAnh\\4.PNG");
        boDemGio.setBounds(20, 400, 250, 30);

        JButton btn_ThongKeBangDiem = new JButton("Thống kê học tập");
        btn_ThongKeBangDiem.setBounds(470, 400, 150, 30);
        btn_ThongKeBangDiem.setBackground(Color.decode("#064469")); // Màu nền
        btn_ThongKeBangDiem.setForeground(Color.WHITE); // Màu chữ thành trắng
        btn_ThongKeBangDiem.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 12));
        panel.add(btn_ThongKeBangDiem);

        // Thêm các thành phần vào panel
        panel.add(coachMeAI);
        panel.add(lienHe);
        panel.add(labelCongCu);
        panel.add(taoLichHoc);
        panel.add(boDemGio);

        btn_ThongKeBangDiem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(blueCardPanel, "BLUE_tkht"); // Chuyển sang giao diện giaoDien_1
            }
        });

        return panel;
    }

    public JPanel giaoDien_1_ThongTinCaNhan() {
        // Màn hình xanh ngọc
        JPanel blue_2_Panel = new JPanel();
        blue_2_Panel.setBackground(Color.decode("#D0EBF8"));
        blue_2_Panel.setBounds(20, 150, 635, 450);
        blue_2_Panel.setLayout(null); // Bỏ layout để tự set vị trí

        JLabel blue_2_tieuDe_ta = new JLabel("THÔNG TIN CÁ NHÂN", JLabel.CENTER);
        blue_2_tieuDe_ta.setFont(new Font("Arial", Font.BOLD, 25));
        blue_2_tieuDe_ta.setBounds(100, 15, 400, 40);

        JLabel lbl_MaHV_ta = new JLabel("Mã học viên:");
        lbl_MaHV_ta.setFont(new Font("Arial", Font.PLAIN, 15));
        lbl_MaHV_ta.setBounds(20, 70, 100, 30);

        JTextField txt_MaHV_ta = new JTextField();
        txt_MaHV_ta.setFont(new Font("Arial", Font.PLAIN, 15));
        txt_MaHV_ta.setBounds(120, 70, 300, 30);

        JLabel lbl_HoVaTen_ta = new JLabel("Họ và tên:");
        lbl_HoVaTen_ta.setFont(new Font("Arial", Font.PLAIN, 15));
        lbl_HoVaTen_ta.setBounds(20, 120, 100, 30);

        JTextField txt_HoVaTen_ta = new JTextField();
        txt_HoVaTen_ta.setFont(new Font("Arial", Font.PLAIN, 15));
        txt_HoVaTen_ta.setBounds(120, 120, 300, 30);

        JLabel lbl_TenTaiKhoan_ta = new JLabel("Tên tài khoản:");
        lbl_TenTaiKhoan_ta.setFont(new Font("Arial", Font.PLAIN, 15));
        lbl_TenTaiKhoan_ta.setBounds(20, 170, 100, 30);

        JTextField txt_TenTaiKhoan_ta = new JTextField();
        txt_TenTaiKhoan_ta.setFont(new Font("Arial", Font.PLAIN, 15));
        txt_TenTaiKhoan_ta.setBounds(120, 170, 300, 30);

        JLabel lbl_MatKhau_ta = new JLabel("Mật khẩu:");
        lbl_MatKhau_ta.setFont(new Font("Arial", Font.PLAIN, 15));
        lbl_MatKhau_ta.setBounds(20, 220, 100, 30);

        JPasswordField pf_MatKhau_ta = new JPasswordField();
        pf_MatKhau_ta.setFont(new Font("Arial", Font.PLAIN, 15));
        pf_MatKhau_ta.setBounds(120, 220, 300, 30);

        JLabel lbl_Email_ta = new JLabel("Email:");
        lbl_Email_ta.setFont(new Font("Arial", Font.PLAIN, 15));
        lbl_Email_ta.setBounds(20, 270, 100, 30);

        JTextField txt_Email_ta = new JTextField();
        txt_Email_ta.setFont(new Font("Arial", Font.PLAIN, 15));
        txt_Email_ta.setBounds(120, 270, 300, 30);

        JLabel lbl_SoDienThoai_ta = new JLabel("Số điện thoại:");
        lbl_SoDienThoai_ta.setFont(new Font("Arial", Font.PLAIN, 15));
        lbl_SoDienThoai_ta.setBounds(20, 320, 100, 30);

        JTextField txt_SoDienThoai_ta = new JTextField();
        txt_SoDienThoai_ta.setFont(new Font("Arial", Font.PLAIN, 15));
        txt_SoDienThoai_ta.setBounds(120, 320, 300, 30);

        JLabel lbl_lienKet_ta = new JLabel("Liên kết:");
        lbl_lienKet_ta.setFont(new Font("Arial", Font.PLAIN, 15));
        lbl_lienKet_ta.setBounds(20, 370, 100, 30);

        JRadioButton rdo_DaLienKet_ta = new JRadioButton("Đã liên kết");
        JRadioButton rdo_ChuaLienKet_ta = new JRadioButton("Chưa liên kết");
        ButtonGroup group_TrangThai_ta = new ButtonGroup();
        rdo_ChuaLienKet_ta.setBackground(Color.decode("#D0EBF8"));
        rdo_DaLienKet_ta.setBackground(Color.decode("#D0EBF8"));
        group_TrangThai_ta.add(rdo_DaLienKet_ta);
        group_TrangThai_ta.add(rdo_ChuaLienKet_ta);
        rdo_DaLienKet_ta.setBounds(120, 370, 100, 30);
        rdo_ChuaLienKet_ta.setBounds(280, 370, 100, 30);

        JLabel lbl_Anh_ta = new JLabel();
        lbl_Anh_ta.setBounds(450, 80, 150, 227);
        lbl_Anh_ta.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        lbl_Anh_ta.setHorizontalAlignment(JLabel.CENTER);

        JButton btn_ChinhSua_ta = new JButton("Chỉnh Sửa");
        btn_ChinhSua_ta.setBounds(230, 410, 200, 30);
        btn_ChinhSua_ta.setFont(new Font("Arial", Font.BOLD, 14));
        btn_ChinhSua_ta.setForeground(Color.BLACK);
        btn_ChinhSua_ta.setBackground(Color.WHITE);

        JFrame frame = new JFrame("Chỉnh sửa");
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        // Bắt sự kiện khi nhấn nút "Chỉnh sửa thông tin"
        btn_ChinhSua_ta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int result = JOptionPane.showConfirmDialog(frame, "Bạn có chắc chắn muốn chỉnh sửa thông tin cá nhân?",
                        "Xác nhận", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    // Kiểm tra xem giaoDien_2_ChinhSuaThongTinCaNhan đã được thêm vào chưa
                    if (blueCardPanel.getComponentCount() == 0) {
                        JPanel blue_3_Panel = giaoDien_2_ChinhSuaThongTinCaNhan();
                        blueCardPanel.add(blue_3_Panel, "BLUE_3");
                    }

                    // Chuyển sang panel chỉnh sửa thông tin
                    cardLayout.show(blueCardPanel, "BLUE_3");
                }
            }
        });

        btn_ChinhSua_ta.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btn_ChinhSua_ta.setBackground(Color.decode("#064469")); // Chuyển sang màu xanh đậm khi hover
                btn_ChinhSua_ta.setForeground(Color.WHITE); // Chữ trắng khi hover
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btn_ChinhSua_ta.setBackground(Color.WHITE); // Trở lại màu trắng khi rời chuột
                btn_ChinhSua_ta.setForeground(Color.BLACK); // Chữ quay về màu đen
            }
        });

        txt_MaHV_ta.setBackground(Color.decode("#D5E9F2"));
        txt_HoVaTen_ta.setBackground(Color.decode("#D5E9F2"));
        txt_TenTaiKhoan_ta.setBackground(Color.decode("#D5E9F2"));
        pf_MatKhau_ta.setBackground(Color.decode("#D5E9F2"));
        txt_Email_ta.setBackground(Color.decode("#D5E9F2"));
        txt_SoDienThoai_ta.setBackground(Color.decode("#D5E9F2"));

        blue_2_Panel.add(blue_2_tieuDe_ta);
        blue_2_Panel.add(lbl_MaHV_ta);
        blue_2_Panel.add(lbl_HoVaTen_ta);
        blue_2_Panel.add(lbl_TenTaiKhoan_ta);
        blue_2_Panel.add(lbl_MatKhau_ta);
        blue_2_Panel.add(lbl_Email_ta);
        blue_2_Panel.add(lbl_SoDienThoai_ta);
        blue_2_Panel.add(lbl_lienKet_ta);
        blue_2_Panel.add(txt_MaHV_ta);
        blue_2_Panel.add(txt_HoVaTen_ta);
        blue_2_Panel.add(txt_TenTaiKhoan_ta);
        blue_2_Panel.add(pf_MatKhau_ta);
        blue_2_Panel.add(txt_Email_ta);
        blue_2_Panel.add(txt_SoDienThoai_ta);
        blue_2_Panel.add(rdo_ChuaLienKet_ta);
        blue_2_Panel.add(rdo_DaLienKet_ta);
        blue_2_Panel.add(lbl_Anh_ta);
        blue_2_Panel.add(btn_ChinhSua_ta);
        return blue_2_Panel;
    }

    public JPanel giaoDien_2_ChinhSuaThongTinCaNhan() {
        // Màn hình xanh ngọc
        JPanel blue_2_Panel = new JPanel();
        blue_2_Panel.setBackground(Color.decode("#D0EBF8"));
        blue_2_Panel.setBounds(20, 150, 635, 450);
        blue_2_Panel.setLayout(null); // Bỏ layout để tự set vị trí

        JLabel blue_2_tieuDe_ta = new JLabel("THÔNG TIN CÁ NHÂN", JLabel.CENTER);
        blue_2_tieuDe_ta.setFont(new Font("Arial", Font.BOLD, 25));
        blue_2_tieuDe_ta.setBounds(100, 15, 400, 40);

        JLabel lbl_MaHV_ta = new JLabel("Mã học viên:");
        lbl_MaHV_ta.setFont(new Font("Arial", Font.PLAIN, 15));
        lbl_MaHV_ta.setBounds(20, 70, 100, 30);

        JTextField txt_MaHV_ta = new JTextField();
        txt_MaHV_ta.setFont(new Font("Arial", Font.PLAIN, 15));
        txt_MaHV_ta.setBounds(120, 70, 300, 30);

        JLabel lbl_HoVaTen_ta = new JLabel("Họ và tên:");
        lbl_HoVaTen_ta.setFont(new Font("Arial", Font.PLAIN, 15));
        lbl_HoVaTen_ta.setBounds(20, 120, 100, 30);

        JTextField txt_HoVaTen_ta = new JTextField();
        txt_HoVaTen_ta.setFont(new Font("Arial", Font.PLAIN, 15));
        txt_HoVaTen_ta.setBounds(120, 120, 300, 30);

        JLabel lbl_TenTaiKhoan_ta = new JLabel("Tên tài khoản:");
        lbl_TenTaiKhoan_ta.setFont(new Font("Arial", Font.PLAIN, 15));
        lbl_TenTaiKhoan_ta.setBounds(20, 170, 100, 30);

        JTextField txt_TenTaiKhoan_ta = new JTextField();
        txt_TenTaiKhoan_ta.setFont(new Font("Arial", Font.PLAIN, 15));
        txt_TenTaiKhoan_ta.setBounds(120, 170, 300, 30);

        JLabel lbl_MatKhau_ta = new JLabel("Mật khẩu:");
        lbl_MatKhau_ta.setFont(new Font("Arial", Font.PLAIN, 15));
        lbl_MatKhau_ta.setBounds(20, 220, 100, 30);

        JPasswordField pf_MatKhau_ta = new JPasswordField();
        pf_MatKhau_ta.setFont(new Font("Arial", Font.PLAIN, 15));
        pf_MatKhau_ta.setBounds(120, 220, 300, 30);

        JLabel lbl_Email_ta = new JLabel("Email:");
        lbl_Email_ta.setFont(new Font("Arial", Font.PLAIN, 15));
        lbl_Email_ta.setBounds(20, 270, 100, 30);

        JTextField txt_Email_ta = new JTextField();
        txt_Email_ta.setFont(new Font("Arial", Font.PLAIN, 15));
        txt_Email_ta.setBounds(120, 270, 300, 30);

        JLabel lbl_SoDienThoai_ta = new JLabel("Số điện thoại:");
        lbl_SoDienThoai_ta.setFont(new Font("Arial", Font.PLAIN, 15));
        lbl_SoDienThoai_ta.setBounds(20, 320, 100, 30);

        JTextField txt_SoDienThoai_ta = new JTextField();
        txt_SoDienThoai_ta.setFont(new Font("Arial", Font.PLAIN, 15));
        txt_SoDienThoai_ta.setBounds(120, 320, 300, 30);

        JLabel lbl_lienKet_ta = new JLabel("Liên kết:");
        lbl_lienKet_ta.setFont(new Font("Arial", Font.PLAIN, 15));
        lbl_lienKet_ta.setBounds(20, 370, 100, 30);

        JRadioButton rdo_DaLienKet_ta = new JRadioButton("Đã liên kết");
        JRadioButton rdo_ChuaLienKet_ta = new JRadioButton("Chưa liên kết");
        rdo_ChuaLienKet_ta.setBackground(Color.decode("#D0EBF8"));
        rdo_DaLienKet_ta.setBackground(Color.decode("#D0EBF8"));
        ButtonGroup group_TrangThai_ta = new ButtonGroup();
        group_TrangThai_ta.add(rdo_DaLienKet_ta);
        group_TrangThai_ta.add(rdo_ChuaLienKet_ta);
        rdo_DaLienKet_ta.setBounds(120, 370, 100, 30);
        rdo_ChuaLienKet_ta.setBounds(280, 370, 100, 30);

        JLabel lbl_Anh_ta = new JLabel();
        lbl_Anh_ta.setBounds(450, 80, 150, 227);
        lbl_Anh_ta.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        lbl_Anh_ta.setHorizontalAlignment(JLabel.CENTER);

        JButton btn_ChonAnh_ta = new JButton("Chọn Ảnh");
        btn_ChonAnh_ta.setBounds(475, 320, 100, 30);
        btn_ChonAnh_ta.setFont(new Font("Arial", Font.BOLD, 14));
        btn_ChonAnh_ta.setForeground(Color.BLACK);
        btn_ChonAnh_ta.setBackground(Color.WHITE);

        JButton btn_CapNhat_ta = new JButton("Cập Nhật");
        btn_CapNhat_ta.setBounds(230, 410, 200, 30);
        btn_CapNhat_ta.setFont(new Font("Arial", Font.BOLD, 14));
        btn_CapNhat_ta.setForeground(Color.BLACK);
        btn_CapNhat_ta.setBackground(Color.WHITE);

        btn_ChonAnh_ta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    String imagePath = fileChooser.getSelectedFile().getAbsolutePath();

                    // Đặt ảnh vào lbl_Anh_ta (hình chữ nhật)
                    ImageIcon icon = new ImageIcon(new ImageIcon(imagePath).getImage().getScaledInstance(120, 150, Image.SCALE_SMOOTH));
                    lbl_Anh_ta.setIcon(icon);

                }
            }
        });

        // Tạo hiệu ứng hover (chuyển màu khi di chuột vào)
        btn_ChonAnh_ta.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btn_ChonAnh_ta.setBackground(Color.decode("#064469")); // Chuyển sang màu xanh đậm khi hover
                btn_ChonAnh_ta.setForeground(Color.WHITE); // Chữ trắng khi hover
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btn_ChonAnh_ta.setBackground(Color.WHITE); // Trở lại màu trắng khi rời chuột
                btn_ChonAnh_ta.setForeground(Color.BLACK); // Chữ quay về màu đen
            }
        });

        btn_CapNhat_ta.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btn_CapNhat_ta.setBackground(Color.decode("#064469")); // Chuyển sang màu xanh đậm khi hover
                btn_CapNhat_ta.setForeground(Color.WHITE); // Chữ trắng khi hover
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btn_CapNhat_ta.setBackground(Color.WHITE); // Trở lại màu trắng khi rời chuột
                btn_CapNhat_ta.setForeground(Color.BLACK); // Chữ quay về màu đen
            }
        });

        btn_CapNhat_ta.addActionListener(e -> {
            JOptionPane.showMessageDialog(
                    null,
                    "Cập nhật thông tin thành công!",
                    "Thông báo",
                    JOptionPane.INFORMATION_MESSAGE
            );
        });

        txt_MaHV_ta.setBackground(Color.decode("#D5E9F2"));
        txt_HoVaTen_ta.setBackground(Color.decode("#D5E9F2"));
        txt_TenTaiKhoan_ta.setBackground(Color.decode("#D5E9F2"));
        pf_MatKhau_ta.setBackground(Color.decode("#D5E9F2"));
        txt_Email_ta.setBackground(Color.decode("#D5E9F2"));
        txt_SoDienThoai_ta.setBackground(Color.decode("#D5E9F2"));

        blue_2_Panel.add(blue_2_tieuDe_ta);
        blue_2_Panel.add(lbl_MaHV_ta);
        blue_2_Panel.add(lbl_HoVaTen_ta);
        blue_2_Panel.add(lbl_TenTaiKhoan_ta);
        blue_2_Panel.add(lbl_MatKhau_ta);
        blue_2_Panel.add(lbl_Email_ta);
        blue_2_Panel.add(lbl_SoDienThoai_ta);
        blue_2_Panel.add(lbl_lienKet_ta);
        blue_2_Panel.add(txt_MaHV_ta);
        blue_2_Panel.add(txt_HoVaTen_ta);
        blue_2_Panel.add(txt_TenTaiKhoan_ta);
        blue_2_Panel.add(pf_MatKhau_ta);
        blue_2_Panel.add(txt_Email_ta);
        blue_2_Panel.add(txt_SoDienThoai_ta);
        blue_2_Panel.add(rdo_ChuaLienKet_ta);
        blue_2_Panel.add(rdo_DaLienKet_ta);
        blue_2_Panel.add(lbl_Anh_ta);
        blue_2_Panel.add(btn_ChonAnh_ta);
        blue_2_Panel.add(btn_CapNhat_ta);
        return blue_2_Panel;
    }

    public JPanel giaoDien_3_DoiMatKhau() {
        JPanel panel = new JPanel();
        panel.setBackground(Color.decode("#D0EBF8"));
        panel.setBounds(20, 150, 635, 450);
        panel.setLayout(null); // Bỏ layout để tự set vị trí

        JLabel lbl_matKhauCu = new JLabel("Mật khẩu cũ");
        lbl_matKhauCu.setBounds(30, 30, 100, 30);
        lbl_matKhauCu.setFont(new Font("Arial", Font.PLAIN, 16));
        panel.add(lbl_matKhauCu);

        JPasswordField txt_matKhauCu = new JPasswordField();
        txt_matKhauCu.setBounds(200, 30, 380, 30);
        panel.add(txt_matKhauCu);

        JLabel lbl_matKhauMoi = new JLabel("Mật khẩu mới");
        lbl_matKhauMoi.setBounds(30, 80, 100, 30);
        lbl_matKhauMoi.setFont(new Font("Arial", Font.PLAIN, 16));
        panel.add(lbl_matKhauMoi);

        JPasswordField txt_matKhauMoi = new JPasswordField();
        txt_matKhauMoi.setBounds(200, 80, 380, 30);
        panel.add(txt_matKhauMoi);

        JLabel lbl_xacNhanMatKhau = new JLabel("Xác nhận mật khẩu");
        lbl_xacNhanMatKhau.setBounds(30, 130, 150, 30);
        lbl_xacNhanMatKhau.setFont(new Font("Arial", Font.PLAIN, 16));
        panel.add(lbl_xacNhanMatKhau);

        JPasswordField txt_xacNhanMatKhau = new JPasswordField();
        txt_xacNhanMatKhau.setBounds(200, 130, 380, 30);
        panel.add(txt_xacNhanMatKhau);

        JButton btn_XacNhan = new JButton("Xác Nhận");
        btn_XacNhan.setBounds(200, 185, 100, 30);
        btn_XacNhan.setBackground(new Color(10, 38, 74));
        btn_XacNhan.setForeground(Color.WHITE);
        btn_XacNhan.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 12));
        panel.add(btn_XacNhan);

        JFrame frame = new JFrame("Đổi mật khẩu");
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        btn_XacNhan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int result = JOptionPane.showConfirmDialog(frame, "Bạn có chắc chắn muốn thay đổi mật khẩu?",
                        "Xác nhận", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    // Kiểm tra xem giaoDien_2_ChinhSuaThongTinCaNhan đã được thêm vào chưa
                    if (blueCardPanel.getComponentCount() == 0) {
                        JPanel blue_4_Panel = giaoDien_3_DoiMatKhau();
                        blueCardPanel.add(blue_4_Panel, "BLUE_4");
                    }

                    // Chuyển sang panel chỉnh sửa thông tin
                    cardLayout.show(blueCardPanel, "BLUE_4");
                }
            }
        });

        JButton btn_Huy = new JButton("Hủy");

        btn_Huy.setBounds(
                320, 185, 100, 30);
        btn_Huy.setBackground(
                new Color(10, 38, 74));
        btn_Huy.setForeground(Color.WHITE);

        btn_Huy.setFont(
                new java.awt.Font("Arial", java.awt.Font.BOLD, 12));
        panel.add(btn_Huy);

        return panel;
    }

    private ImageIcon createCircularImageIcon(String imagePath, int diameter) {
        try {
            // Đọc ảnh gốc
            BufferedImage originalImage = ImageIO.read(new File(imagePath));

            // Resize ảnh về kích thước hình tròn mong muốn
            Image resizedImage = originalImage.getScaledInstance(diameter, diameter, Image.SCALE_SMOOTH);
            BufferedImage circleBuffer = new BufferedImage(diameter, diameter, BufferedImage.TYPE_INT_ARGB);

            Graphics2D g2 = circleBuffer.createGraphics();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // Cắt ảnh thành hình tròn
            Ellipse2D.Double clip = new Ellipse2D.Double(0, 0, diameter, diameter);
            g2.setClip(clip);
            g2.drawImage(resizedImage, 0, 0, null);
            g2.dispose();

            return new ImageIcon(circleBuffer);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private JLabel createMenuItem(String text, String iconPath) {
        ImageIcon icon = new ImageIcon(iconPath);
        Image img = icon.getImage().getScaledInstance(40, 30, Image.SCALE_SMOOTH); // Chỉnh ảnh nhỏ lại
        icon = new ImageIcon(img);

        JLabel label = new JLabel(text, icon, JLabel.LEFT);
        label.setFont(new Font("Arial", Font.PLAIN, 14));
        return label;
    }

    class CircleLabel extends JLabel {

        private int diameter;
        private BufferedImage image; // Lưu trữ ảnh

        public CircleLabel(int diameter) {
            this.diameter = diameter;
            setPreferredSize(new Dimension(diameter, diameter));
        }

        public void setImage(BufferedImage img) {
            this.image = img;
            repaint(); // Cập nhật giao diện khi có ảnh mới
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // Vẽ hình tròn màu xám nếu chưa có ảnh
            if (image == null) {
                g2d.setColor(Color.GRAY);
                g2d.fillOval(0, 0, diameter, diameter);
            } else {
                // Vẽ ảnh trong hình tròn
                BufferedImage circularImage = createCircularImage(image, diameter);
                g2d.drawImage(circularImage, 0, 0, diameter, diameter, null);
            }
        }

        private BufferedImage createCircularImage(BufferedImage img, int size) {
            // Resize ảnh về kích thước mong muốn
            Image scaledImage = img.getScaledInstance(size, size, Image.SCALE_SMOOTH);
            BufferedImage output = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);

            Graphics2D g2 = output.createGraphics();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // Cắt ảnh thành hình tròn
            Ellipse2D.Double clip = new Ellipse2D.Double(0, 0, size, size);
            g2.setClip(clip);
            g2.drawImage(scaledImage, 0, 0, null);
            g2.dispose();

            return output;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Thông tin cá nhân");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(710, 650);
            frame.setResizable(false);
            ThongTinCaNhan ttcnPanel = new ThongTinCaNhan();
            frame.add(ttcnPanel);
            frame.setLocationRelativeTo(null); // Căn giữa màn hình
            frame.setVisible(true);
        });
    }
}
