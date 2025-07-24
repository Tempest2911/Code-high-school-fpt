/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package onthideco;

import java.io.Serializable;

/**
 *
 * @author drago
 */
public class Model implements Serializable{

    private String TenSP;
    private String Hang;
    private float gia;
    private String TrangThai;

    public Model() {
    }

    public Model(String TenSP, String Hang, float gia, String TrangThai) {
        this.TenSP = TenSP;
        this.Hang = Hang;
        this.gia = gia;
        this.TrangThai = TrangThai;
    }

    public String getTenSP() {
        return TenSP;
    }

    public void setTenSP(String TenSP) {
        this.TenSP = TenSP;
    }

    public String getHang() {
        return Hang;
    }

    public void setHang(String Hang) {
        this.Hang = Hang;
    }

    public float getGia() {
        return gia;
    }

    public void setGia(float gia) {
        this.gia = gia;
    }

    public String getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(String TrangThai) {
        this.TrangThai = TrangThai;
    }

}
