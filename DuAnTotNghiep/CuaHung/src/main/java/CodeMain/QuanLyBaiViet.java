/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CodeMain;

import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.event.TableModelEvent;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import ModelFolder.BaiViet;
import java.sql.*;
import java.util.ArrayList;

public class QuanLyBaiViet extends JPanel {

    // Mình sưa thành khai báo biến toàn cục nhá để lấy ra cho dễ !
    private JPanel tong_2_panel;
    private CardLayout cardLayout;
    private JComboBox<String> cbo_bv_trangThai;
    private JComboBox<String> cbo_bv_thang;
    private JComboBox<String> cbo_bv_nam;

    private String selectedImagePath = null;

    private int idBaiVietDangSua = -1; // -1 = Chưa chọn bài viết nào

    private DefaultTableModel tbl_qlbv;
    private JTable tb_data;
    private Connection conn;

    private JTextField txt_tieuDe;
    private JTextField txt_moTa;
    private JTextArea txt_NoiDungBV;
    private JLabel lbl_HinhAnhBV;

    private static final String URL = "jdbc:sqlserver://TEMPEST:1433;databaseName=Coach_Me_AI;trustServerCertificate=true;user=sa;password=123456";
    ArrayList<BaiViet> danhSachBaiViet = new ArrayList<>();

    public void khoiTaoBang() {
        String[] columnNames = {"ID", "Tiêu Đề", "Mô Tả", "Nội Dung", "Ngày Đăng", "Trạng Thái", "Hình Ảnh",
            "Chức Năng"};

        if (tbl_qlbv == null) {
            tbl_qlbv = new DefaultTableModel(columnNames, 0) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return column == 7;
                }
            };
            tb_data = new JTable(tbl_qlbv);
            tb_data.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            tb_data.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

            // ✅ Đảm bảo bảng được thêm vào JScrollPane trước khi hiển thị
            JScrollPane scrollPane = new JScrollPane(tb_data);
            tong_2_panel.add(scrollPane);
        } else {
            tbl_qlbv.setRowCount(0); // ✅ Xóa dữ liệu cũ, tránh trùng lặp
        }
        fillTable();
    }

    public void fillTable() {
        String query = "SELECT ID, Tieu_De, Mo_Ta, Noi_Dung, Ngay_Dang, Trang_Thai, Hinh_Anh FROM Bai_Viet";

        try (Connection conn = DriverManager.getConnection(URL); PreparedStatement stmt = conn.prepareStatement(query); ResultSet rs = stmt.executeQuery()) {

            DefaultTableModel model = (DefaultTableModel) tb_data.getModel();
            model.setRowCount(0); // Xóa dữ liệu cũ

            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getInt("ID"),
                    rs.getString("Tieu_De"),
                    rs.getString("Mo_Ta"),
                    rs.getString("Noi_Dung"),
                    rs.getTimestamp("Ngay_Dang"),
                    rs.getString("Trang_Thai"),
                    rs.getString("Hinh_Anh"),
                    "Chỉnh Sửa"
                });
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi khi tải dữ liệu: " + e.getMessage());
        }
    }

    public ArrayList<BaiViet> Search(String nameSearch) {
        String query = "SELECT ID, Tieu_De, Mo_Ta, Noi_Dung, Ngay_Dang, Trang_Thai FROM Bai_Viet WHERE Tieu_De LIKE ? ORDER BY Ngay_Dang DESC;";
        ArrayList<BaiViet> listSearch = new ArrayList<>();
        try {
            Connection con = DriverManager.getConnection(URL);
            PreparedStatement stm = con.prepareStatement(query);
            stm.setString(1, "%" + nameSearch + "%");

            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                String id = rs.getString("ID");
                String tieuDe = rs.getString("Tieu_De");
                String moTa = rs.getString("Mo_Ta");
                String noiDung = rs.getString("Noi_Dung");
                Timestamp ngayDang = rs.getTimestamp("Ngay_Dang");
                String trangThai = rs.getString("Trang_Thai");

                listSearch.add(new BaiViet(id, tieuDe, moTa, noiDung, ngayDang, trangThai));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listSearch;
    }

    // tai du lieu len bang
    public void loadData(DefaultTableModel tbl_qlbv, JTable tb_data, String trangThai, String thang, String nam) {
        danhSachBaiViet.clear();
        khoiTaoBang();
        String query = "SELECT ID, Tieu_De, Mo_Ta, Ngay_Dang, Trang_Thai FROM Bai_Viet WHERE 1=1";

        if (trangThai.equals("Đã Xuất Bản")) {
            trangThai = "Xuất bản";
        } else if (trangThai.equals("Bản Nháp")) {
            trangThai = "Bản nháp";
        }

        boolean hasCondition = false;

        if (!"Trạng thái".equals(trangThai)) {
            query += " AND Trang_Thai = ?";
            hasCondition = true;
        }
        if (!"Tháng".equals(thang)) {
            query += " AND MONTH(Ngay_Dang) = ?";
        }
        if (!"Năm".equals(nam)) {
            query += " AND YEAR(Ngay_Dang) = ?";
        }

        try (Connection conn = DriverManager.getConnection(URL); PreparedStatement stmt = conn.prepareStatement(query)) {

            int paramIndex = 1;
            if (!"Trạng thái".equals(trangThai)) {
                stmt.setString(paramIndex++, trangThai);
            }
            if (!"Tháng".equals(thang)) {
                stmt.setInt(paramIndex++, Integer.parseInt(thang.replace("Tháng ", "")));
            }
            if (!"Năm".equals(nam)) {
                stmt.setInt(paramIndex++, Integer.parseInt(nam.replace("Năm ", "")));
            }

            ResultSet rs = stmt.executeQuery();
            tbl_qlbv.setRowCount(0);

            while (rs.next()) {
                int id = rs.getInt("ID");
                String tieuDe = rs.getString("Tieu_De");
                String moTa = rs.getString("Mo_Ta");
                String ngayDang = rs.getString("Ngay_Dang");
                String trangThaiDB = rs.getString("Trang_Thai");

                tbl_qlbv.addRow(new Object[]{id, tieuDe, moTa, ngayDang, trangThaiDB, "Chức Năng"});
            }

            // Cài đặt ComboBox cho cột "Chức Năng"
            String[] options = {"Chức Năng", "Sửa", "Xóa"};
            tb_data.getColumnModel().getColumn(5).setCellEditor(new DefaultCellEditor(new JComboBox<>(options)));

            tb_data.getColumnModel().getColumn(5).setCellRenderer(new DefaultTableCellRenderer() {
                @Override
                public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                        boolean hasFocus, int row, int column) {
                    return new JComboBox<>(options); // Hiển thị dropdown đúng trên từng dòng
                }
            });

            tb_data.getModel().addTableModelListener(e -> {
                if (e.getType() == TableModelEvent.UPDATE && e.getColumn() == 5) {
                    int row = e.getFirstRow();
                    String selectedAction = (String) tb_data.getValueAt(row, 5);
                    int selectedID = (int) tb_data.getValueAt(row, 0);

                    if ("Sửa".equals(selectedAction)) {
                        editBaiViet(selectedID, tb_data, row);
                    } else if ("Xóa".equals(selectedAction)) {
                        deleteBaiViet(selectedID, tbl_qlbv, row);
                    }
                }
            });

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // xoa bai viet
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

    // phan chinh sua bai viet
    private void editBaiViet(int id, JTable tb_data, int row) {
        idBaiVietDangSua = id; // Lưu ID bài viết cần chỉnh sửa

        // 🔹 Lấy dữ liệu từ bảng trước
        String oldTitle = (String) tb_data.getValueAt(row, 1);
        String oldDescription = (String) tb_data.getValueAt(row, 2);

        // 🔹 Gọi DB lấy `Noi_Dung` & `Hinh_Anh`
        String[] dataFromDB = getContentAndImageFromDB(id);
        String oldContent = dataFromDB[0]; // Lấy nội dung từ DB
        String imagePath = dataFromDB[1]; // Lấy đường dẫn ảnh từ DB

        selectedImagePath = imagePath; // Lưu đường dẫn ảnh

        // 🔹 Gán dữ liệu lên form
        txt_tieuDe.setText(oldTitle);
        txt_moTa.setText(oldDescription);
        txt_NoiDungBV.setText(oldContent);

        // 🔹 Kiểm tra & load ảnh
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

        // 🔹 Chuyển sang trang sửa bài viết
        cardLayout.show(tong_2_panel, "BLUE_4");
    }

    // lay du lieu tu bang de loat lem chinh sua
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

    // lay anh ra tu db
    private String getImagePathFromDB(int idBaiViet) {
        String imagePath = null;
        String sql = "SELECT Hinh_Anh FROM Bai_Viet WHERE ID = ?";

        try (Connection conn = DriverManager.getConnection(URL); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idBaiViet);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                imagePath = rs.getString("Hinh_Anh");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return (imagePath != null && !imagePath.trim().isEmpty()) ? imagePath : "default.jpg";
    }

    private void luuBaiViet(int idTaiKhoanAdmin, boolean isPublish) {
        if (idBaiVietDangSua == -1) { // 🔹 Nếu không có ID bài viết đang sửa
            taoBaiViet(idTaiKhoanAdmin, isPublish);
        } else { // 🔹 Nếu đang sửa bài viết
            capNhatBaiViet(isPublish);
        }
    }

    // 🔵 **Cập nhật bài viết**
    private void capNhatBaiViet(boolean isPublish) {
        String tieuDe = txt_tieuDe.getText().trim();
        String moTa = txt_moTa.getText().trim();
        String noiDung = txt_NoiDungBV.getText().trim();

        if (tieuDe.isEmpty() || noiDung.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Tiêu đề và nội dung không được để trống!");
            return;
        }

        String trangThai = isPublish ? "Xuất bản" : "Bản nháp";

        String sql = "UPDATE Bai_Viet SET Tieu_De = ?, Mo_Ta = ?, Noi_Dung = ?, Trang_Thai = ?, Hinh_Anh = ? WHERE ID = ?";

        try (Connection conn = DriverManager.getConnection(URL); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, tieuDe);
            stmt.setString(2, moTa.isEmpty() ? null : moTa);
            stmt.setString(3, noiDung);
            stmt.setString(4, trangThai);
            stmt.setString(5, selectedImagePath != null && !selectedImagePath.trim().isEmpty() ? selectedImagePath
                    : getImagePathFromDB(idBaiVietDangSua));
            stmt.setInt(6, idBaiVietDangSua);

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                JOptionPane.showMessageDialog(null,
                        isPublish ? "Xuất bản thành công!" : "Cập nhật bản nháp thành công!");
//                fillTable(); // Gọi lại để làm mới bảng
            } else {
                JOptionPane.showMessageDialog(null, isPublish ? "Xuất bản thất bại!" : "Cập nhật bản nháp thất bại!");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Lỗi khi cập nhật bài viết: " + ex.getMessage(), "Lỗi SQL",
                    JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

    // kiem tra tk co ton tai hay ko de tao bai viet
    private boolean kiemTraTaiKhoanTonTai(int idTaiKhoanAdmin) {
        String query = "SELECT COUNT(*) FROM Tai_Khoan WHERE ID_Tai_Khoan = ?";
        try (Connection conn = DriverManager.getConnection(URL); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, idTaiKhoanAdmin);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0; // Trả về true nếu ID tồn tại
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private void taoBaiViet(int idTaiKhoanAdmin, boolean isPublish) {
        if (!kiemTraTaiKhoanTonTai(idTaiKhoanAdmin)) {
            JOptionPane.showMessageDialog(null, "Lỗi: Tài khoản không tồn tại! Hãy kiểm tra lại ID tài khoản.");
            return;
        }

        String tieuDe = txt_tieuDe.getText().trim();
        String moTa = txt_moTa.getText().trim();
        String noiDung = txt_NoiDungBV.getText().trim();
        if (tieuDe.isEmpty() || noiDung.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Tiêu đề và nội dung không được để trống!");
            return;
        }

        String trangThai = isPublish ? "Xuất bản" : "Bản nháp";
        String sql = "INSERT INTO Bai_Viet (ID_TAI_KHOAN_ADMIN, Tieu_De, Mo_Ta, Noi_Dung, Ngay_Dang, Trang_Thai, Hinh_Anh) "
                + "VALUES (?, ?, ?, ?, GETDATE(), ?, ?)";

        try (Connection conn = DriverManager.getConnection(URL); PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, idTaiKhoanAdmin);
            stmt.setString(2, tieuDe);
            stmt.setString(3, moTa.isEmpty() ? null : moTa);
            stmt.setString(4, noiDung);
            stmt.setString(5, trangThai);
            stmt.setString(6, selectedImagePath != null && !selectedImagePath.trim().isEmpty() ? selectedImagePath
                    : "default.jpg");

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    idBaiVietDangSua = rs.getInt(1);
                }
                JOptionPane.showMessageDialog(null, isPublish ? "Xuất bản thành công!" : "Lưu bản nháp thành công!");
            } else {
                JOptionPane.showMessageDialog(null, isPublish ? "Xuất bản thất bại!" : "Lưu bản nháp thất bại!");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Lỗi khi thêm bài viết: " + ex.getMessage(), "Lỗi SQL",
                    JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

    public QuanLyBaiViet() {
        giaoDien_QuanLyBaiViet();
    }

    public void giaoDien_QuanLyBaiViet() {
        this.setLayout(null);

        JPanel whitePanel = new JPanel();
        whitePanel.setBackground(Color.WHITE);
        whitePanel.setBounds(0, 0, 710, 650);
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
        // JPanel blue_3_Panel = suaBaiViet();
        // tong_2_panel.add(blue_3_Panel, "BLUE_4");
        JPanel blue_4_Panel = suaBaiViet();
        tong_2_panel.add(blue_4_Panel, "BLUE_4");

        cardLayout.show(tong_2_panel, "BLUE_2");
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

        btn_taoBaiViet.addActionListener(e -> {
            // Xóa dữ liệu cũ trước khi mở trang tạo bài viết
            txt_tieuDe.setText("");
            txt_moTa.setText("");
            txt_NoiDungBV.setText("");
            lbl_HinhAnhBV.setIcon(null);
            selectedImagePath = null; // Reset đường dẫn ảnh

            // Đặt lại ID bài viết đang sửa để tránh nhầm lẫn với chỉnh sửa
            idBaiVietDangSua = -1;

            // Chuyển sang giao diện tạo bài viết
            cardLayout.show(tong_2_panel, "BLUE_4");
        });

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
        // tim kiem

        btn_bv_timKiem.addActionListener(e -> {
            String keyword = txt_bv_timKiem.getText();
            System.out.println("Nút tìm kiếm được nhấn với từ khóa: " + keyword); // Thêm thông báo ghi nhớ
            Search(keyword);
            // loat data
            loadData(
                    tbl_qlbv,
                    tb_data,
                    cbo_bv_trangThai.getSelectedItem().toString(),
                    cbo_bv_thang.getSelectedItem().toString(),
                    cbo_bv_nam.getSelectedItem().toString());
        });

        cbo_bv_trangThai = new JComboBox<>(new String[]{"Trạng thái", "Đã Xuất Bản", "Bản Nháp"});
        cbo_bv_trangThai.setBounds(10, 60, 150, 30);
        cbo_bv_trangThai.setBackground(Color.decode("#064469"));
        cbo_bv_trangThai.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 12));
        cbo_bv_trangThai.setBorder(null);
        cbo_bv_trangThai.setForeground(Color.white);
        panel.add(cbo_bv_trangThai);

        // cai nay sua theo bien toan cuc nhe
        cbo_bv_thang = new JComboBox<>(new String[]{
            "Tháng", "Tháng 1", "Tháng 2", "Tháng 3", "Tháng 4", "Tháng 5",
            "Tháng 6", "Tháng 7", "Tháng 8", "Tháng 9", "Tháng 10", "Tháng 11", "Tháng 12"});
        cbo_bv_thang.setBounds(180, 60, 150, 30);
        cbo_bv_thang.setBackground(Color.decode("#064469"));
        cbo_bv_thang.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 12));
        cbo_bv_thang.setBorder(null);
        cbo_bv_thang.setForeground(Color.white);
        panel.add(cbo_bv_thang);

        // sua theo bien toan cuc
        cbo_bv_nam = new JComboBox<>(new String[]{
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

        // goi den bang
        tb_data = new JTable(tbl_qlbv);
        tb_data.setRowHeight(30);
        tb_data.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        tb_data.setModel(tbl_qlbv);

        // Đặt bảng vào JScrollPane
        JScrollPane scrollPane = new JScrollPane(tb_data);
        scrollPane.setBounds(0, 120, 630, 400);
        panel.add(scrollPane, BorderLayout.CENTER);

        cbo_bv_trangThai.addActionListener(e -> loadData(tbl_qlbv, tb_data,
                cbo_bv_trangThai.getSelectedItem().toString(),
                cbo_bv_thang.getSelectedItem().toString(),
                cbo_bv_nam.getSelectedItem().toString()));

        cbo_bv_thang.addActionListener(e -> loadData(tbl_qlbv, tb_data,
                cbo_bv_trangThai.getSelectedItem().toString(),
                cbo_bv_thang.getSelectedItem().toString(),
                cbo_bv_nam.getSelectedItem().toString()));

        // de chay cbo
        cbo_bv_nam.addActionListener(e -> loadData(tbl_qlbv, tb_data,
                cbo_bv_trangThai.getSelectedItem().toString(),
                cbo_bv_thang.getSelectedItem().toString(),
                cbo_bv_nam.getSelectedItem().toString()));

        // loat data
        loadData(
                tbl_qlbv,
                tb_data,
                cbo_bv_trangThai.getSelectedItem().toString(),
                cbo_bv_thang.getSelectedItem().toString(),
                cbo_bv_nam.getSelectedItem().toString());

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

        txt_tieuDe = new JTextField();
        txt_tieuDe.setBounds(100, 10, 400, 30);
        panel_tong.add(txt_tieuDe);

        JLabel lbl_moTa = new JLabel("Mô tả:");
        lbl_moTa.setBounds(25, 40, 100, 30);
        lbl_moTa.setBackground(Color.red);
        lbl_moTa.setFont(new Font("Arial", Font.PLAIN, 16));
        panel_tong.add(lbl_moTa);

        txt_moTa = new JTextField();
        txt_moTa.setBounds(100, 40, 400, 30);
        panel_tong.add(txt_moTa);

        JButton btn_luuBanNhap = new JButton("Lưu bản nháp");
        btn_luuBanNhap.setBounds(510, 10, 150, 30);
        btn_luuBanNhap.setForeground(Color.WHITE); // Chữ trắng
        btn_luuBanNhap.setFont(new Font("Arial", Font.BOLD, 15));
        btn_luuBanNhap.setBackground(new Color(10, 38, 74));
        panel_tong.add(btn_luuBanNhap);
        // luu ban nhap
        btn_luuBanNhap.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                luuBaiViet(idBaiVietDangSua, false); // Gọi hàm lưu bản nháp
            }
        });

        JButton btn_xuatBan = new JButton("Xuất bản");
        btn_xuatBan.setBounds(510, 40, 150, 30);
        btn_xuatBan.setForeground(Color.WHITE); // Chữ trắng
        btn_xuatBan.setFont(new Font("Arial", Font.BOLD, 15));
        btn_xuatBan.setBackground(new Color(10, 38, 74));
        panel_tong.add(btn_xuatBan);

        // xuat ban bai viet
        btn_xuatBan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                luuBaiViet(idBaiVietDangSua, true); // Gọi hàm lưu bản nháp
                fillTable(); // Cập nhật bảng sau khi xuất bản
            }
        });

        JLabel lbl_baiVietCuaBan = new JLabel("Bài viết của bạn:");
        lbl_baiVietCuaBan.setBounds(25, 70, 200, 30);
        lbl_baiVietCuaBan.setFont(new Font("Arial", Font.PLAIN, 16));
        panel_tong.add(lbl_baiVietCuaBan);

        txt_NoiDungBV = new JTextArea();
        txt_NoiDungBV.setLineWrap(true);
        txt_NoiDungBV.setWrapStyleWord(true);
        JScrollPane scrollContent_1 = new JScrollPane(txt_NoiDungBV);
        scrollContent_1.setBounds(25, 100, 630, 200);
        panel_tong.add(scrollContent_1);

        JLabel lbl_hinhAnh = new JLabel("Hình ảnh:");
        lbl_hinhAnh.setBounds(25, 300, 100, 30);
        lbl_hinhAnh.setFont(new Font("Arial", Font.PLAIN, 16));
        panel_tong.add(lbl_hinhAnh);

        lbl_HinhAnhBV = new JLabel();
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

        btn_quayLai.addActionListener(e -> {
            cardLayout.show(tong_2_panel, "BLUE_2");
            fillTable();
        });

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
                fileChooser.setFileFilter(new FileNameExtensionFilter("Hình ảnh", "jpg", "png", "jpeg", "gif"));

                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    selectedImagePath = selectedFile.getAbsolutePath(); // ✅ Lưu đường dẫn ảnh

                    // ✅ Hiển thị ảnh đúng trên form hiện tại
                    if (idBaiVietDangSua == -1) {
                        System.out.println("🟢 Chọn ảnh cho Tạo bài viết mới");
                    } else {
                        System.out.println("🔵 Chọn ảnh cho Sửa bài viết ID: " + idBaiVietDangSua);
                    }

                    // ✅ Cập nhật ảnh trên giao diện
                    ImageIcon icon = new ImageIcon(selectedImagePath);
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
            frame.setSize(710, 650);
            frame.setResizable(false);

            QuanLyBaiViet quanLyBaiVietPanel = new QuanLyBaiViet();
            frame.add(quanLyBaiVietPanel);

            frame.setLocationRelativeTo(null); // Căn giữa màn hình
            frame.setVisible(true);
        });
    }
}
