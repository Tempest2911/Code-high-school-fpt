/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quanlymaybay;

import java.io.Serializable;

/**
 *
 * @author drago
 */
public class Model implements Serializable{
    private String id;
    private String ten;
    private String trangthai;
    private String hang;
    private int namSanXuat;

    public Model() {
    }

    public Model(String id, String ten, String trangthai, String hang, int namSanXuat) {
        this.id = id;
        this.ten = ten;
        this.trangthai = trangthai;
        this.hang = hang;
        this.namSanXuat = namSanXuat;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(String trangthai) {
        this.trangthai = trangthai;
    }

    public String getHang() {
        return hang;
    }

    public void setHang(String hang) {
        this.hang = hang;
    }

    public int getNamSanXuat() {
        return namSanXuat;
    }

    public void setNamSanXuat(int namSanXuat) {
        this.namSanXuat = namSanXuat;
    }


    
            
}
