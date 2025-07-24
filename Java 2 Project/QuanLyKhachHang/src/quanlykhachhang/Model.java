/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quanlykhachhang;

/**
 *
 * @author drago
 */
public class Model {
    private String maKH;
    private String tenKH;
    private String gioiTinh;
    private Integer namSinh;
    private String loatKH;

    public Model() {
    }
    
    
    

    public Model(String maKH, String tenKH, String gioiTinh, Integer namSinh, String loatKH) {
        this.maKH = maKH;
        this.tenKH = tenKH;
        this.gioiTinh = gioiTinh;
        this.namSinh = namSinh;
        this.loatKH = loatKH;
    }

    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    public String getTenKH() {
        return tenKH;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public Integer getNamSinh() {
        return namSinh;
    }

    public void setNamSinh(Integer namSinh) {
        this.namSinh = namSinh;
    }

    public String getLoatKH() {
        return loatKH;
    }

    public void setLoatKH(String loatKH) {
        this.loatKH = loatKH;
    }
    
    
}
