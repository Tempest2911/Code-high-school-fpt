/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package buoi2;

/**
 *
 * @author drago
 */
public class HocSinh extends Nguoi {

    private String ma;
    private double diem;

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

    public HocSinh() {
    }

    public HocSinh(String ma, double diem) {
        this.ma = ma;
        this.diem = diem;
    }

    public HocSinh(String ma, double diem, String ten, int tuoi) {
        super(ten, tuoi);
        this.ma = ma;
        this.diem = diem;
    }

    public void hienThiThongTin() {
        System.out.println("Họ tên: " + super.getTen());
        System.out.println("Tuổi: " + super.getTuoi());
        System.out.println("Ma: " + ma);
        System.out.println("Diem: "+diem);
    }
}
