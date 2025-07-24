/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quanlycasi;

import java.io.Serializable;

/**
 *
 * @author drago
 */
public class Model implements Serializable{

    private String ma;
    private String ten;
    private String gioiTinh ;
    private String hang;
    private int namHoatDong;

    public Model() {
    }

    public Model(String ma, String ten, String gioiTinh, String hang, int namHoatDong) {
        this.ma = ma;
        this.ten = ten;
        this.gioiTinh = gioiTinh;
        this.hang = hang;
        this.namHoatDong = namHoatDong;
    }

    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getHang() {
        return hang;
    }

    public void setHang(String hang) {
        this.hang = hang;
    }

    public int getNamHoatDong() {
        return namHoatDong;
    }

    public void setNamHoatDong(int namHoatDong) {
        this.namHoatDong = namHoatDong;
    }
    
    
}
