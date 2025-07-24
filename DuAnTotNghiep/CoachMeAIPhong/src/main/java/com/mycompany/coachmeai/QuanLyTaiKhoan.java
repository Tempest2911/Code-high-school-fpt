/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.coachmeai;

import javax.swing.JPanel;
import com.mycompany.coachmeai.ChatTool;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.swing.*;
import java.util.Vector;
import javax.imageio.ImageIO;
import javax.swing.table.DefaultTableModel;

public class QuanLyTaiKhoan extends JPanel {

    public QuanLyTaiKhoan() {
        giaoDien_QuanLyTaiKhoan();
    }

    public void giaoDien_QuanLyTaiKhoan() {
        this.setLayout(null);

        JPanel whitePanel = new JPanel();
        whitePanel.setBackground(Color.WHITE);
        whitePanel.setBounds(0, 0, 710, 650);
        whitePanel.setLayout(null);

        JLabel lbl_qltk = new JLabel("QUẢN LÝ TÀI KHOẢN");
        lbl_qltk.setBounds(20, 5, 250, 30);
        lbl_qltk.setFont(new Font("Arial", Font.BOLD, 20));
        whitePanel.add(lbl_qltk);

        JPanel panel_tong_so_hv = new JPanel();
        panel_tong_so_hv.setBackground(Color.WHITE); // Nền trắng
        panel_tong_so_hv.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1)); // Viền đen dày 2px
        panel_tong_so_hv.setBounds(20, 40, 245, 120);
        panel_tong_so_hv.setLayout(null);

        whitePanel.add(panel_tong_so_hv);

        JLabel lbl_tongSo_hv = new JLabel("Tổng số học viên");
        lbl_tongSo_hv.setBounds(20, 10, 150, 20);
        lbl_tongSo_hv.setFont(new Font("Arial", Font.BOLD, 15));
        panel_tong_so_hv.add(lbl_tongSo_hv);

        JLabel lbl_tshv_so_luong = new JLabel("...");
        lbl_tshv_so_luong.setBounds(20, 35, 100, 30);
        lbl_tshv_so_luong.setFont(new Font("Arial", Font.BOLD, 27));
        panel_tong_so_hv.add(lbl_tshv_so_luong);

        JLabel lbl_tshv_tang_truong = new JLabel("...");
        lbl_tshv_tang_truong.setBounds(20, 70, 200, 20);
        lbl_tshv_tang_truong.setForeground(Color.GRAY);
        panel_tong_so_hv.add(lbl_tshv_tang_truong);

        JLabel lbl_tongSo_hv_2 = new JLabel("so với tháng trước");
        lbl_tongSo_hv_2.setBounds(50, 70, 200, 20);
        lbl_tongSo_hv_2.setForeground(Color.GRAY);
        panel_tong_so_hv.add(lbl_tongSo_hv_2);

        JLabel lbl_tongSo_hv_3 = new JLabel("Trong 1 tháng");
        lbl_tongSo_hv_3.setBounds(20, 90, 200, 20);
        lbl_tongSo_hv_3.setForeground(Color.GRAY);
        panel_tong_so_hv.add(lbl_tongSo_hv_3);

        JPanel panel_tong_so_ph = new JPanel();
        panel_tong_so_ph.setBackground(Color.WHITE); // Nền trắng
        panel_tong_so_ph.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1)); // Viền đen dày 2px
        panel_tong_so_ph.setBounds(290, 40, 245, 120);
        panel_tong_so_ph.setLayout(null);
        whitePanel.add(panel_tong_so_ph);

        JLabel lbl_tongSo_ph = new JLabel("Tổng số phụ huynh");
        lbl_tongSo_ph.setBounds(20, 10, 150, 20);
        lbl_tongSo_ph.setFont(new Font("Arial", Font.BOLD, 15));
        panel_tong_so_ph.add(lbl_tongSo_ph);

        JLabel lbl_tsph_so_luong = new JLabel("...");
        lbl_tsph_so_luong.setBounds(20, 35, 100, 30);
        lbl_tsph_so_luong.setFont(new Font("Arial", Font.BOLD, 27));
        panel_tong_so_ph.add(lbl_tsph_so_luong);

        JLabel lbl_tsph_tang_truong = new JLabel("...");
        lbl_tsph_tang_truong.setBounds(20, 70, 200, 20);
        lbl_tsph_tang_truong.setForeground(Color.GRAY);
        panel_tong_so_ph.add(lbl_tsph_tang_truong);

        JLabel lbl_tongSo_ph_2 = new JLabel("so với tháng trước");
        lbl_tongSo_ph_2.setBounds(50, 70, 200, 20);
        lbl_tongSo_ph_2.setForeground(Color.GRAY);
        panel_tong_so_ph.add(lbl_tongSo_ph_2);

        JLabel lbl_tongSo_ph_3 = new JLabel("Trong 1 tháng");
        lbl_tongSo_ph_3.setBounds(20, 90, 200, 20);
        lbl_tongSo_ph_3.setForeground(Color.GRAY);
        panel_tong_so_ph.add(lbl_tongSo_ph_3);

        JComboBox<String> cbo_thang = new JComboBox<>(new String[]{
            "Tháng", "Tháng 1", "Tháng 2", "Tháng 3", "Tháng 4", "Tháng 5",
            "Tháng 6", "Tháng 7", "Tháng 8", "Tháng 9", "Tháng 10", "Tháng 11", "Tháng 12"
        });
        cbo_thang.setBounds(550, 40, 100, 30);
        cbo_thang.setBackground(Color.decode("#064469"));
        cbo_thang.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 12));
        cbo_thang.setBorder(null);
        cbo_thang.setForeground(Color.white);
        whitePanel.add(cbo_thang);

        JComboBox cbo_nam = new JComboBox<>(new String[]{"Năm", "Năm 2023", "Năm 2024", "Năm 2025"});
        cbo_nam.setBounds(550, 80, 100, 30);
        cbo_nam.setBackground(Color.decode("#064469"));
        cbo_nam.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 12));
        cbo_nam.setBorder(null);
        cbo_nam.setForeground(Color.white);
        whitePanel.add(cbo_nam);

        JTabbedPane tabbedPanel_qltk_hv_ph = new JTabbedPane();
        JPanel panel_qltk_hv = qltk_hv("Học Viên");
        JPanel panel_qltk_ph = qltk_ph("Phụ Huynh");

        tabbedPanel_qltk_hv_ph.addTab("Học Viên", panel_qltk_hv);
        tabbedPanel_qltk_hv_ph.addTab("Phụ Huynh", panel_qltk_ph);
        tabbedPanel_qltk_hv_ph.setBounds(20, 170, 630, 430); // Đặt kích thước và vị trí cho tabbedPane
        whitePanel.add(tabbedPanel_qltk_hv_ph);

        this.add(whitePanel);
        this.setVisible(true);
    }

    public JPanel qltk_hv(String title) {
        JPanel panel = new JPanel(null);
        panel.setBackground(Color.decode("#D0EBF8"));

        String placeholder = "  Mời nhập ID hoặc tên học viên cần tìm";
        JTextField txt_hv_timKiem = new JTextField(placeholder);
        txt_hv_timKiem.setBounds(10, 10, 500, 30);
        txt_hv_timKiem.setBackground(Color.white);
        txt_hv_timKiem.setForeground(Color.GRAY);
        // Cách 1: Xóa viền (nếu muốn ô nhập không có viền)
        //txt_hv_timKiem.setBorder(new EmptyBorder(0, 5, 0, 5)); 
        // Cách 2: Đặt viền tùy chỉnh (nếu muốn viền mỏng màu xám)
        txt_hv_timKiem.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
        txt_hv_timKiem.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (txt_hv_timKiem.getText().equals(placeholder)) {
                    txt_hv_timKiem.setText(""); // Xóa nội dung khi nhấn vào
                    txt_hv_timKiem.setForeground(Color.BLACK); // Đổi lại màu chữ bình thường
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (txt_hv_timKiem.getText().trim().isEmpty()) {
                    txt_hv_timKiem.setText(placeholder); // Đặt lại placeholder nếu ô trống
                    txt_hv_timKiem.setForeground(Color.GRAY); // Đổi màu chữ về xám
                }
            }
        });
        panel.add(txt_hv_timKiem);

        JButton btn_hv_timKiem = new JButton("Tìm Kiếm");
        btn_hv_timKiem.setBounds(520, 10, 100, 30);
        btn_hv_timKiem.setBackground(new Color(10, 38, 74));
        btn_hv_timKiem.setForeground(Color.WHITE);
        btn_hv_timKiem.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 12));
        panel.add(btn_hv_timKiem);

        JComboBox<String> cbo_hv_trangThai = new JComboBox<>(new String[]{
            "Trạng thái", "Hoạt Động", "Không hoạt động"
        });
        cbo_hv_trangThai.setBounds(10, 60, 150, 30);
        cbo_hv_trangThai.setBackground(Color.decode("#064469"));
        cbo_hv_trangThai.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 12));
        cbo_hv_trangThai.setBorder(null);
cbo_hv_trangThai.setForeground(Color.white);
        panel.add(cbo_hv_trangThai);

        JComboBox<String> cbo_hv_thang = new JComboBox<>(new String[]{
            "Tháng", "Tháng 1", "Tháng 2", "Tháng 3", "Tháng 4", "Tháng 5",
            "Tháng 6", "Tháng 7", "Tháng 8", "Tháng 9", "Tháng 10", "Tháng 11", "Tháng 12"});
        cbo_hv_thang.setBounds(180, 60, 150, 30);
        cbo_hv_thang.setBackground(Color.decode("#064469"));
        cbo_hv_thang.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 12));
        cbo_hv_thang.setBorder(null);
        cbo_hv_thang.setForeground(Color.white);
        panel.add(cbo_hv_thang);

        JComboBox<String> cbo_hv_nam = new JComboBox<>(new String[]{
            "Năm", "Năm 2023", "Năm 2024", "Năm 2025"
        });
        cbo_hv_nam.setBounds(350, 60, 150, 30);
        cbo_hv_nam.setBackground(Color.decode("#064469"));
        cbo_hv_nam.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 12));
        cbo_hv_nam.setBorder(null);
        cbo_hv_nam.setForeground(Color.white);
        panel.add(cbo_hv_nam);

        DefaultTableModel tbl_qltk = new DefaultTableModel();
        tbl_qltk.addColumn("ID");
        tbl_qltk.addColumn("Tên tài khoản");
        tbl_qltk.addColumn("Họ và tên");
        tbl_qltk.addColumn("Email");
        tbl_qltk.addColumn("Ngày đăng ký");
        tbl_qltk.addColumn("Vai trò");
        tbl_qltk.addColumn("Trạng thái");
        tbl_qltk.addColumn("Chức năng");

        JTable tb_data = new JTable();
        tb_data.setRowHeight(30);
        tb_data.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        tb_data.setModel(tbl_qltk);

        // Đặt bảng vào JScrollPane
        JScrollPane scrollPane = new JScrollPane(tb_data);
        scrollPane.setBounds(0, 100, 630, 300);
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    public JPanel qltk_ph(String title) {
        JPanel panel = new JPanel(null);
        panel.setBackground(Color.decode("#D0EBF8"));

        String placeholder = "  Mời nhập ID hoặc tên phụ huynh cần tìm";
        JTextField txt_hv_timKiem = new JTextField(placeholder);
        txt_hv_timKiem.setBounds(10, 10, 500, 30);
        txt_hv_timKiem.setBackground(Color.white);
        txt_hv_timKiem.setForeground(Color.GRAY);
        // Cách 1: Xóa viền (nếu muốn ô nhập không có viền)
        //txt_hv_timKiem.setBorder(new EmptyBorder(0, 5, 0, 5)); 
        // Cách 2: Đặt viền tùy chỉnh (nếu muốn viền mỏng màu xám)
        txt_hv_timKiem.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
        txt_hv_timKiem.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (txt_hv_timKiem.getText().equals(placeholder)) {
                    txt_hv_timKiem.setText(""); // Xóa nội dung khi nhấn vào
                    txt_hv_timKiem.setForeground(Color.BLACK); // Đổi lại màu chữ bình thường
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (txt_hv_timKiem.getText().trim().isEmpty()) {
                    txt_hv_timKiem.setText(placeholder); // Đặt lại placeholder nếu ô trống
                    txt_hv_timKiem.setForeground(Color.GRAY); // Đổi màu chữ về xám
                }
            }
        });
        panel.add(txt_hv_timKiem);

        JButton btn_hv_timKiem = new JButton("Tìm Kiếm");
        btn_hv_timKiem.setBounds(520, 10, 100, 30);
        btn_hv_timKiem.setBackground(new Color(10, 38, 74));
        btn_hv_timKiem.setForeground(Color.WHITE);
        btn_hv_timKiem.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 12));
        panel.add(btn_hv_timKiem);

        JComboBox<String> cbo_hv_trangThai = new JComboBox<>(new String[]{
            "Trạng thái", "Hoạt Động", "Không hoạt động"
        });
        cbo_hv_trangThai.setBounds(10, 60, 150, 30);
        cbo_hv_trangThai.setBackground(Color.decode("#064469"));
        cbo_hv_trangThai.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 12));
        cbo_hv_trangThai.setBorder(null);
        cbo_hv_trangThai.setForeground(Color.white);
        panel.add(cbo_hv_trangThai);

        JComboBox<String> cbo_hv_thang = new JComboBox<>(new String[]{
            "Tháng", "Tháng 1", "Tháng 2", "Tháng 3", "Tháng 4", "Tháng 5",
            "Tháng 6", "Tháng 7", "Tháng 8", "Tháng 9", "Tháng 10", "Tháng 11", "Tháng 12"});
        cbo_hv_thang.setBounds(180, 60, 150, 30);
        cbo_hv_thang.setBackground(Color.decode("#064469"));
        cbo_hv_thang.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 12));
        cbo_hv_thang.setBorder(null);
        cbo_hv_thang.setForeground(Color.white);
        panel.add(cbo_hv_thang);

        JComboBox<String> cbo_hv_nam = new JComboBox<>(new String[]{
            "Năm", "Năm 2023", "Năm 2024", "Năm 2025"
        });
        cbo_hv_nam.setBounds(350, 60, 150, 30);
        cbo_hv_nam.setBackground(Color.decode("#064469"));
        cbo_hv_nam.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 12));
        cbo_hv_nam.setBorder(null);
        cbo_hv_nam.setForeground(Color.white);
        panel.add(cbo_hv_nam);

        DefaultTableModel tbl_qltk = new DefaultTableModel();
        tbl_qltk.addColumn("ID");
        tbl_qltk.addColumn("Tên tài khoản");
        tbl_qltk.addColumn("Họ và tên");
        tbl_qltk.addColumn("Email");
        tbl_qltk.addColumn("Ngày đăng ký");
        tbl_qltk.addColumn("Vai trò");
        tbl_qltk.addColumn("Trạng thái");
        tbl_qltk.addColumn("Chức năng");

        JTable tb_data = new JTable();
        tb_data.setRowHeight(30);
        tb_data.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        tb_data.setModel(tbl_qltk);

        // Đặt bảng vào JScrollPane
        JScrollPane scrollPane = new JScrollPane(tb_data);
        scrollPane.setBounds(0, 100, 630, 300);
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Feedback");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(710, 650);
            frame.setResizable(false);

            QuanLyTaiKhoan quanLyTaiKhoanPanel = new QuanLyTaiKhoan();
            frame.add(quanLyTaiKhoanPanel);

            frame.setLocationRelativeTo(null); // Căn giữa màn hình
            frame.setVisible(true);
        });
    }
}

