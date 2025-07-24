/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModelFolder;

import java.sql.Timestamp;

/**
 *
 * @author drago
 */
public class BaiViet {

    private String id;
    private String tieuDe;
    private String moTa;
    private String noiDung;
    private Timestamp ngayDang;
    private String trangThai;

    public BaiViet() {
    }

    public BaiViet(String id, String tieuDe, String moTa, String noiDung, Timestamp ngayDang, String trangThai) {
        this.id = id;
        this.tieuDe = tieuDe;
        this.moTa = moTa;
        this.noiDung = noiDung;
        this.ngayDang = ngayDang;
        this.trangThai = trangThai;
    }

    public String getId() {
        return id;
    }

    public String getTieuDe() {
        return tieuDe;
    }

    public String getMoTa() {
        return moTa;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public Timestamp getNgayDang() {
        return ngayDang;
    }

    public String getTrangThai() {
        return trangThai;
    }
}
