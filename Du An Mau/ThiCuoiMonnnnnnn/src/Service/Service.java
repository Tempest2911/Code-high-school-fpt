/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import DaoClass.CRUD_DAO;
import java.sql.*;
import java.util.ArrayList;
import Model.Model;

/**
 *
 * @author drago
 */
public class Service {

    ArrayList<Model> listch = new ArrayList<>();

    public ArrayList<Model> getAll() {
        return listch;
    }

    public void LoadDataTAble() {
        listch.clear();
        String select = "SELECT ch.idch, ch.tenCH,ch.mota,ch.loaihang,ch.ngaydangky,tt.tenTT,tt.diachitt FROM dbo.CUAHANG ch\n"
                + "JOIN dbo.TRUNGTAM tt ON tt.idTT = ch.idTT";
        try (Connection con = CRUD_DAO.getConnect()) {
            PreparedStatement stmt = con.prepareStatement(select);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Model ch = new Model(
                        rs.getInt("idch"),
                        rs.getString("tench"),
                        rs.getString("mota"),
                        rs.getString("loaihang"),
                        rs.getString("ngaydangky"),
                        rs.getString("tenTT"),
                        rs.getString("diachitt")
                );

                listch.add(ch);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void add(Model m) {

        String add = "INSERT INTO Cuahang(tenCH, mota, loaihang, idTT) "
                + "VALUES(?, ?, ?, (SELECT idTT FROM TrungTam WHERE tenTT = ?))";
        try (Connection con = CRUD_DAO.getConnect()) {
            PreparedStatement stmt = con.prepareStatement(add);
            stmt.setString(1, m.tench);
            stmt.setString(2, m.mota);
            stmt.setString(3, m.loaihang);
            stmt.setString(4, m.tentt);
            int rs = stmt.executeUpdate();

            if (rs > 0) {
                System.out.println("Thêm thành công");
            } else {
                System.out.println("Thêm thất bại");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void edit(Model m) {

        String edit = "UPDATE cuahang\n"
                + "SET tench = ?,\n"
                + "    mota = ?,\n"
                + "    loaihang = ?,\n"
                + "    ngaydangky = ?,\n"
                + "    idTT = (SELECT idTT FROM TrungTam WHERE tenTT = ?)\n"
                + "WHERE idch = ?";

//        String editTT = "Update TrungTam set diachiTT = ? where tenTT = ?";
        try (Connection con = CRUD_DAO.getConnect()) {
            PreparedStatement stmt = con.prepareStatement(edit);
            stmt.setString(1, m.tench);
            stmt.setString(2, m.mota);
            stmt.setString(3, m.loaihang);
            stmt.setString(4, m.ngaydangky);
            stmt.setString(5, m.tentt);
            stmt.setInt(6, m.idch);

            int rs = stmt.executeUpdate();

//            try (Connection conn = CRUD_DAO.getConnect()) {
//                PreparedStatement stmtt = conn.prepareStatement(editTT);
//                stmtt.setString(1, m.diachi);
//                stmtt.setString(2, m.tentt);
//                int rss = stmtt.executeUpdate();
//
//                if (rss > 0) {
//                    System.out.println("Sửa thành công");
//                } else {
//                    System.out.println("Sửa thất bại");
//                }
//            }
            if (rs > 0) {
                System.out.println("Sửa thành công");
            } else {
                System.out.println("Sửa thất bại");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(Model m) {
        String delete = "Delete from Cuahang where idch = ?";

        try (Connection con = CRUD_DAO.getConnect()) {
            PreparedStatement stmt = con.prepareStatement(delete);
            stmt.setInt(1, m.idch);
            int rs = stmt.executeUpdate();

            if (rs > 0) {
                System.out.println("Xóa thành công");
            } else {
                System.out.println("Xóa thất bại");
            }

        } catch (Exception e) {
        }
    }

    public ArrayList<Model> timKiem1(String keyword, String loaiTim) {
        ArrayList<Model> listResult = new ArrayList<>();
        String sql = "SELECT ch.idCH, ch.tenCH, ch.mota, ch.loaihang, ch.ngaydangky, tt.tenTT, tt.Diachitt FROM CuaHang ch JOIN dbo.TRUNGTAM tt ON tt.idTT = ch.idTT WHERE ch.tenCH LIKE ?";

        try (Connection conn = CRUD_DAO.getConnect(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "%" + keyword + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Model ch = new Model(
                        rs.getInt("idCH"),
                        rs.getString("tenCH"),
                        rs.getString("mota"),
                        rs.getString("loaihang"),
                        rs.getString("ngaydangky"),
                        rs.getString("tenTT"),
                        rs.getString("diachitt")
                );
                listResult.add(ch);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listResult;
    }

    public ArrayList<Model> timKiem2(String keyword, String loaiTim) {
        ArrayList<Model> listResult = new ArrayList<>();
        // Tùy thuộc vào cột bạn muốn tìm
        String sql = "SELECT ch.idCH, ch.tenCH, ch.mota, ch.loaihang, ch.ngaydangky, tt.tenTT, tt.diachitt FROM CuaHang ch JOIN dbo.TRUNGTAM tt ON tt.idTT = ch.idTT WHERE ch.mota LIKE ?";

        try (Connection conn = CRUD_DAO.getConnect(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "%" + keyword + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Model ch = new Model(
                        rs.getInt("idCH"),
                        rs.getString("tenCH"),
                        rs.getString("mota"),
                        rs.getString("loaihang"),
                        rs.getString("ngaydangky"),
                        rs.getString("tenTT"),
                        rs.getString("diachitt")
                );
                listResult.add(ch);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listResult;
    }

    public ArrayList<Model> timKiem3(String keyword, String loaiTim) {
        ArrayList<Model> listResult = new ArrayList<>();
        //    public void loadDataToCBO() {
//        String url = "Select tenTT from TrungTam";
//        try (Connection con = CRUD_DAO.getConnect()) {
//            PreparedStatement stmt = con.prepareStatement(url);
//            ResultSet rs = stmt.executeQuery();
//            while (rs.next()) {
//                cboNameTT.addItem(rs.getString("TenTT"));
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
        String sql = "SELECT ch.idCH, ch.tenCH, ch.mota, ch.loaihang, ch.ngaydangky, tt.tenTT, tt.diachitt FROM CuaHang ch JOIN dbo.TRUNGTAM tt ON tt.idTT = ch.idTT WHERE tt.Diachitt LIKE ?";

        try (Connection conn = CRUD_DAO.getConnect(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "%" + keyword + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Model ch = new Model(
                        rs.getInt("idCH"),
                        rs.getString("tenCH"),
                        rs.getString("mota"),
                        rs.getString("loaihang"),
                        rs.getString("ngaydangky"),
                        rs.getString("tenTT"),
                        rs.getString("diachitt")
                );
                listResult.add(ch);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listResult;
    }

}
