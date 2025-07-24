/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package thicuoiki;

import java.io.Serializable;

/**
 *
 * @author drago
 */
public class SanPham implements Serializable {

    public String name;
    public String donVi;
    public Double donGia;

    public SanPham() {
    }

    public SanPham(String name, String donVi, Double donGia) {
        this.name = name;
        this.donVi = donVi;
        this.donGia = donGia;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDonVi() {
        return donVi;
    }

    public void setDonVi(String donVi) {
        this.donVi = donVi;
    }

    public Double getDonGia() {
        return donGia;
    }

    public void setDonGia(Double donGia) {
        this.donGia = donGia;
    }

}
