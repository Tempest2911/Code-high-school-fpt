/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package thitest;

import java.io.Serializable;

/**
 *
 * @author drago
 */
public class DienThoai implements Serializable {

    String ten;
    String hang;
    Double gia;

    public DienThoai() {
    }

    public DienThoai(String ten, String hang, Double gia) {
        this.ten = ten;
        this.hang = hang;
        this.gia = gia;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getHang() {
        return hang;
    }

    public void setHang(String hang) {
        this.hang = hang;
    }

    public Double getGia() {
        return gia;
    }

    public void setGia(Double gia) {
        this.gia = gia;
    }

}
