package com.mycompany.DAO;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

// Make sure JDBC Driver is loaded

public class ThongKePHDAO {
    private static final String URL = "jdbc:sqlserver://Tempest:1433;databasename=Coach_Me_AI;trustServerCertificate=true;charset=UTF-8";
    private static final String USER = "sa";
    private static final String PASSWORD = "123456";

    public static int getSoGioHoc(int idTaiKhoan, int thang, int nam) {
        String query = "SELECT SUM(DATEDIFF(HOUR, Thoi_Gian_Bat_Dau, Thoi_Gian_Ket_Thuc)) AS TongGioHoc FROM Nhiem_Vu " +
                "WHERE ID_Tai_Khoan = ? AND MONTH(Thoi_Gian_Bat_Dau) = ? AND YEAR(Thoi_Gian_Bat_Dau) = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, idTaiKhoan);
            ps.setInt(2, thang);
            ps.setInt(3, nam);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("TongGioHoc");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static int getNhiemVuHoanThanh(int idTaiKhoan, int thang, int nam) {
        String query = "SELECT COUNT(*) AS SoNhiemVu FROM Nhiem_Vu " +
                "WHERE ID_Tai_Khoan = ? AND Trang_Thai = N'Hoàn thành' " +
                "AND MONTH(Thoi_Gian_Bat_Dau) = ? AND YEAR(Thoi_Gian_Bat_Dau) = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, idTaiKhoan);
            ps.setInt(2, thang);
            ps.setInt(3, nam);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("SoNhiemVu");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static int getNhiemVuChuaHoanThanh(int idTaiKhoan, int thang, int nam) {
        String query = "SELECT COUNT(*) AS SoNhiemVu FROM Nhiem_Vu " +
                "WHERE ID_Tai_Khoan = ? AND Trang_Thai = N'Chưa hoàn thành' " +
                "AND MONTH(Thoi_Gian_Bat_Dau) = ? AND YEAR(Thoi_Gian_Bat_Dau) = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, idTaiKhoan);
            ps.setInt(2, thang);
            ps.setInt(3, nam);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("SoNhiemVu");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static Map<String, Float> getDiemTrungBinh(int idTaiKhoan, int thang, int nam) {
        Map<String, Float> diemTBMap = new HashMap<>();

        String sql = "SELECT mh.Ten AS Mon_Hoc, COALESCE(AVG(kq.Diem), 0) AS Diem_Trung_Binh " +
                "FROM Mon_Hoc mh " +
                "LEFT JOIN Nhiem_Vu nv ON mh.ID_Mon_Hoc = nv.ID_Mon_Hoc AND nv.ID_Tai_Khoan = ? " +
                "LEFT JOIN Bai_Test bt ON bt.ID_Nhiem_Vu = nv.ID_Nhiem_Vu " +
                "AND MONTH(nv.Thoi_Gian_Bat_Dau) = ? " +
                "AND YEAR(nv.Thoi_Gian_Bat_Dau) = ? " +
                "GROUP BY mh.Ten " +
                "ORDER BY mh.Ten";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idTaiKhoan);
            stmt.setInt(2, thang);
            stmt.setInt(3, nam);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String monHoc = rs.getString("Mon_Hoc");
                float diemTB = rs.getFloat("Diem_Trung_Binh");
                diemTBMap.put(monHoc, diemTB);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return diemTBMap;
    }


}

