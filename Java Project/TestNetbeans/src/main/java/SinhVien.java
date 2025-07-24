/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author drago
 */
public class SinhVien {
    String maSinhVien;

    String hoTen;

    int namSinh;

    String diaChi;

    public SinhVien() {
    }

    public SinhVien(String maSinhVien, String hoTen, int namSinh, String diaChi) {
        this.maSinhVien = maSinhVien;
        this.hoTen = hoTen;
        this.namSinh = namSinh;
        this.diaChi = diaChi;
    }

    @Override
    public String toString() {
        return "SinhVien{" + "maSinhVien=" + maSinhVien + ", hoTen=" + hoTen + ", namSinh=" + namSinh + ", diaChi=" + diaChi + '}';
    }

    
    public void inTuoi() {
        System.out.println(2024 - namSinh);
    }

    public void chay() {
        System.out.println("Sinh vien chay x km/h");
    }
}
