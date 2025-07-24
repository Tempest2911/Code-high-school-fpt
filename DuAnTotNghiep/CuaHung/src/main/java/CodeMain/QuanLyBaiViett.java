/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package CodeMain;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.*;
import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author drago
 */
public class QuanLyBaiViett extends javax.swing.JFrame {

    /**
     * Creates new form QuanLyBaiViett
     */
    private JPanel tong_2_panel;
    private CardLayout cardLayout;
    private static final String URL = "jdbc:sqlserver://TEMPEST:1433;databaseName=Coach_Me_AI;trustServerCertificate=true;user=sa;password=123456";
    private JTable tbl_data;

    private int idBaiVietDangSua = -1; // -1 = Chưa chọn bài viết nào
    private String selectedImagePath = null;
    private JTextField txt_tieuDe;
    private JTextField txt_moTa;
    private JTextArea txt_NoiDungBV;
    private JLabel lbl_HinhAnhBV;
    DefaultTableModel tbl_qlbv;
    JTable tb_data;
    String trangThai;
    String thang;
    String nam; // phan chinh sua bai viet

    public QuanLyBaiViett() {
        initComponents();
        giaoDien_QuanLyBaiViet();
        danhSachBaiViet();
        taoBaiVietMoi();
        loadData();
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

        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("ID");
        tableModel.addColumn("Tiêu đề bài viết");
        tableModel.addColumn("Mô tả ngắn gọn");
        tableModel.addColumn("Ngày đăng");
        tableModel.addColumn("Trạng Thái");
        tableModel.addColumn("Chức năng");

        tbl_data = new JTable(); // Gán giá trị cho biến toàn cục
        tbl_data.setRowHeight(30);
        tbl_data.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        tbl_data.setModel(tableModel);

        // Đặt bảng vào JScrollPane
        JScrollPane scrollPane = new JScrollPane(tbl_data);
        scrollPane.setBounds(0, 120, 630, 400);
        panel.add(scrollPane, BorderLayout.CENTER);
        loadData();
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

    public void loadData() {
        tbl_qlbv = (DefaultTableModel) tb_data.getModel();

        String query = "SELECT ID, Tieu_De, Mo_Ta, Ngay_Dang, Trang_Thai FROM Bai_Viet WHERE 1=1";

        if (trangThai != null && !"Trạng thái".equals(trangThai)) {
            query += " AND Trang_Thai = ?";
        }
        if (thang != null && !"Tháng".equals(thang)) {
            query += " AND MONTH(Ngay_Dang) = ?";
        }
        if (nam != null && !"Năm".equals(nam)) {
            query += " AND YEAR(Ngay_Dang) = ?";
        }

        try (Connection conn = DriverManager.getConnection(URL); PreparedStatement stmt = conn.prepareStatement(query)) {

            int paramIndex = 1;

            if (trangThai != null && !"Trạng thái".equals(trangThai)) {
                stmt.setString(paramIndex++, trangThai);
            }
            if (thang != null && !"Tháng".equals(thang)) {
                stmt.setInt(paramIndex++, Integer.parseInt(thang.replace("Tháng ", "")));
            }
            if (nam != null && !"Năm".equals(nam)) {
                stmt.setInt(paramIndex++, Integer.parseInt(nam.replace("Năm ", "")));
            }

            ResultSet rs = stmt.executeQuery();

            // Kiểm tra tbl_qlbv trước khi gọi setRowCount
            if (tbl_qlbv != null) {
                tbl_qlbv.setRowCount(0);
            } else {
                System.out.println("tbl_qlbv chưa được khởi tạo!");
                return; // Dừng function để tránh lỗi
            }

            while (rs.next()) {
                int id = rs.getInt("ID");
                String tieuDe = rs.getString("Tieu_De");
                String moTa = rs.getString("Mo_Ta");
                String ngayDang = rs.getString("Ngay_Dang");
                String trangThaiDB = rs.getString("Trang_Thai");

                tbl_qlbv.addRow(new Object[]{id, tieuDe, moTa, ngayDang, trangThaiDB, "Chức Năng"});
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void deleteBaiViet(int id, DefaultTableModel tbl_qlbv, int row) {
        int confirm = JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn xóa bài viết này?", "Xác nhận",
                JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            String deleteQuery = "DELETE FROM Bai_Viet WHERE ID = ?";

            try (Connection conn = DriverManager.getConnection(URL); PreparedStatement stmt = conn.prepareStatement(deleteQuery)) {

                stmt.setInt(1, id);
                int affectedRows = stmt.executeUpdate(); // Kiểm tra số dòng bị ảnh hưởng

                if (affectedRows > 0) {
                    tbl_qlbv.removeRow(row); // Chỉ xóa trên bảng nếu xóa trong DB thành công
                    JOptionPane.showMessageDialog(null, "Xóa thành công!");
                } else {
                    JOptionPane.showMessageDialog(null, "Không tìm thấy bài viết để xóa!");
                }

            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Lỗi khi xóa dữ liệu!");
            }
        }
    }

    private void editBaiViet(int id, JTable tb_data, int row) {
        idBaiVietDangSua = id; // Lưu ID bài viết cần chỉnh sửa

        // Lấy dữ liệu từ bảng trước
        String oldTitle = (String) tb_data.getValueAt(row, 1);
        String oldDescription = (String) tb_data.getValueAt(row, 2);

        // Gọi DB lấy `Noi_Dung` & `Hinh_Anh`
        String[] dataFromDB = getContentAndImageFromDB(id);
        String oldContent = dataFromDB[0]; // Lấy nội dung từ DB
        String imagePath = dataFromDB[1]; // Lấy đường dẫn ảnh từ DB

        selectedImagePath = imagePath; // Lưu đường dẫn ảnh

        // Gán dữ liệu lên form
        txt_tieuDe.setText(oldTitle);
        txt_moTa.setText(oldDescription);
        txt_NoiDungBV.setText(oldContent);

        // Kiểm tra & load ảnh
        if (imagePath != null && !imagePath.trim().isEmpty()) {
            File file = new File(imagePath);
            if (file.exists()) {
                ImageIcon icon = new ImageIcon(imagePath);
                Image img = icon.getImage().getScaledInstance(630, 200, Image.SCALE_SMOOTH);
                lbl_HinhAnhBV.setIcon(new ImageIcon(img));
            } else {
                System.out.println("Ảnh không tồn tại: " + imagePath);
                lbl_HinhAnhBV.setIcon(null);
            }
        } else {
            System.out.println("Không tìm thấy ảnh trong DB.");
            lbl_HinhAnhBV.setIcon(null);
        }

        // Chuyển sang trang sửa bài viết
        cardLayout.show(tong_2_panel, "BLUE_4");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    private String[] getContentAndImageFromDB(int id) {
        String noiDung = "";
        String imagePath = "";

        String sql = "SELECT Noi_Dung, Hinh_Anh FROM Bai_Viet WHERE ID = ?";

        try (Connection conn = DriverManager.getConnection(URL); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                noiDung = rs.getString("Noi_Dung");
                imagePath = rs.getString("Hinh_Anh");
                System.out.println("Dữ liệu từ DB - Nội dung: " + noiDung + ", Ảnh: " + imagePath);
            } else {
                System.out.println("Không tìm thấy bài viết với ID: " + id);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return new String[]{noiDung, imagePath}; // Trả về mảng dữ liệu
    }

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        // <editor-fold defaultstate="collapsed" desc=" Look and feel setting code
        // (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the default
         * look and feel.
         * For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(QuanLyBaiViett.class.getName()).log(java.util.logging.Level.SEVERE, null,
                    ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QuanLyBaiViett.class.getName()).log(java.util.logging.Level.SEVERE, null,
                    ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QuanLyBaiViett.class.getName()).log(java.util.logging.Level.SEVERE, null,
                    ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QuanLyBaiViett.class.getName()).log(java.util.logging.Level.SEVERE, null,
                    ex);
        }
        // </editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new QuanLyBaiViett().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
