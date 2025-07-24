/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab1;

/**
 *
 * @author drago
 */
public class SinhVien {
    public String ma;
    public double diem;
    public int namSinh;

    public SinhVien() {
    }

    public SinhVien(String ma, double diem, int namSinh) {
        this.ma = ma;
        this.diem = diem;
        this.namSinh = namSinh;
    }

    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public double getDiem() {
        return diem;
    }

    public void setDiem(double diem) {
        this.diem = diem;
    }

    public int getNamSinh() {
        return namSinh;
    }

    public void setNamSinh(int namSinh) {
        this.namSinh = namSinh;
    }
    
    
    public void hienThiThongTin(){
        System.out.println("------------------------------");
        System.out.println("Ma cua hs la: "+ma);
        System.out.println("Diem cua hs la: "+diem);
        System.out.println("Nam sinh cua hs la: "+namSinh);
    }

    
    
    
    
    
}
