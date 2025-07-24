/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import DaoClass.CRUD_DAO;
import java.sql.*;
import java.util.ArrayList;
import Model.Model;
import Model.ModelPH;

/**
 *
 * @author drago
 */
public class Service {

    ArrayList<Model> listsv = new ArrayList<>();
    ArrayList<ModelPH> listph = new ArrayList<>();

    public ArrayList<Model> getAll() {
        return listsv;
    }

    public ArrayList<ModelPH> getAllPH() {
        return listph;
    }

    public void LoadDataTAble() {
        listsv.clear();
        String select = "SELECT sv.idSV, sv.tenSV, sv.email, sv.sdt, sv.diachi, ph.tenPH, ph.nghenghiep FROM dbo.SINHVIEN sv\n"
                + "JOIN dbo.PHUHUYNH ph ON ph.idPH = sv.idPH";
        try (Connection con = CRUD_DAO.getConnect()) {
            PreparedStatement stmt = con.prepareStatement(select);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Model sv = new Model(
                        rs.getInt("idSV"),
                        rs.getString("tenSV"),
                        rs.getString("email"),
                        rs.getString("sdt"),
                        rs.getString("diachi"),
                        rs.getString("tenPH"),
                        rs.getString("nghenghiep")
                );

                listsv.add(sv);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void LoadDataTablePH() {
        listph.clear();
        String select = "SELECT ph.idPH, ph.tenPH, ph.nghenghiep FROM dbo.PHUHUYNH ph";
        try (Connection con = CRUD_DAO.getConnect()) {
            PreparedStatement stmt = con.prepareStatement(select);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                ModelPH ph = new ModelPH(rs.getInt("idPH"), rs.getString("TenPH"), rs.getString("Nghenghiep"));

                listph.add(ph);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void add(Model m) {

        String add = "INSERT INTO SinhVien(tenPH, email, sdt, diachi, idPH) "
                + "VALUES(?, ?, ?, ?, (SELECT IDPH FROM Phuhuynh WHERE TenPH = ?))";

        try (Connection con = CRUD_DAO.getConnect()) {
            PreparedStatement stmt = con.prepareStatement(add);
            stmt.setString(1, m.tensv);
            stmt.setString(2, m.email);
            stmt.setString(3, m.phone);
            stmt.setString(4, m.address);
            stmt.setString(5, m.tenph);
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

        String edit = "UPDATE SinhVien\n"
                + "SET tensv = ?,\n"
                + "    email = ?,\n"
                + "    sdt = ?,\n"
                + "    diachi = ?,\n"
                + "    idPH = (SELECT idPH FROM PHUHUYNH WHERE tenPH = ?)\n"
                + "WHERE idsv = ?";
        try (Connection con = CRUD_DAO.getConnect()) {
            PreparedStatement stmt = con.prepareStatement(edit);
            stmt.setString(1, m.tensv);
            stmt.setString(2, m.email);
            stmt.setString(3, m.phone);
            stmt.setString(4, m.address);
            stmt.setString(5, m.tenph);
            stmt.setInt(6, m.idsv);

            int rs = stmt.executeUpdate();

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
        String delete = "Delete from SinhVien where idsv = ?";

        try (Connection con = CRUD_DAO.getConnect()) {
            PreparedStatement stmt = con.prepareStatement(delete);
            stmt.setInt(1, m.idsv);
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
        String sql = "SELECT sv.idSV, sv.tenSV, sv.email, sv.sdt, sv.diachi, ph.tenPH, ph.nghenghiep FROM dbo.SINHVIEN sv JOIN dbo.PHUHUYNH ph ON ph.idPH = sv.idPH WHERE sv.sdt LIKE ?";

        try (Connection conn = CRUD_DAO.getConnect(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "%" + keyword + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Model ch = new Model(
                        rs.getInt("idSV"),
                        rs.getString("tenSV"),
                        rs.getString("email"),
                        rs.getString("sdt"),
                        rs.getString("diachi"),
                        rs.getString("tenPH"),
                        rs.getString("nghenghiep")
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
        String sql = "SELECT sv.idSV, sv.tenSV, sv.email, sv.sdt, sv.diachi, ph.tenPH, ph.nghenghiep FROM dbo.SINHVIEN sv JOIN dbo.PHUHUYNH ph ON ph.idPH = sv.idPH WHERE sv.diachi LIKE ?";

        try (Connection conn = CRUD_DAO.getConnect(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "%" + keyword + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Model ch = new Model(
                        rs.getInt("idSV"),
                        rs.getString("tenSV"),
                        rs.getString("email"),
                        rs.getString("sdt"),
                        rs.getString("diachi"),
                        rs.getString("tenPH"),
                        rs.getString("nghenghiep")
                );
                listResult.add(ch);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listResult;
    }

    public ArrayList<Model> timKiem4(String keyword, String loaiTim) {
        ArrayList<Model> listResult = new ArrayList<>();
        String sql = "SELECT sv.idSV, sv.tenSV, sv.email, sv.sdt, sv.diachi, ph.tenPH, ph.nghenghiep FROM dbo.SINHVIEN sv JOIN dbo.PHUHUYNH ph ON ph.idPH = sv.idPH WHERE sv.tenSV LIKE ?";

        try (Connection conn = CRUD_DAO.getConnect(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "%" + keyword + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Model ch = new Model(
                        rs.getInt("idSV"),
                        rs.getString("tenSV"),
                        rs.getString("email"),
                        rs.getString("sdt"),
                        rs.getString("diachi"),
                        rs.getString("tenPH"),
                        rs.getString("nghenghiep")
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
        String sql = "SELECT sv.idSV, sv.tenSV, sv.email, sv.sdt, sv.diachi, ph.tenPH, ph.nghenghiep FROM dbo.SINHVIEN sv JOIN dbo.PHUHUYNH ph ON ph.idPH = sv.idPH WHERE ph.tenPH LIKE ?";

        try (Connection conn = CRUD_DAO.getConnect(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "%" + keyword + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Model ch = new Model(
                        rs.getInt("idSV"),
                        rs.getString("tenSV"),
                        rs.getString("email"),
                        rs.getString("sdt"),
                        rs.getString("diachi"),
                        rs.getString("tenPH"),
                        rs.getString("nghenghiep")
                );
                listResult.add(ch);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listResult;
    }
}
