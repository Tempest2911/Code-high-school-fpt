/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package lab3;

/**
 *
 * @author drago
 */
public class Lab3 {

   private int id;
   private String ten;
   private float gia;
   private String voBanh;

    public Lab3() {
    }

    public Lab3(int id, String ten, float gia, String voBanh) {
        this.id = id;
        this.ten = ten;
        this.gia = gia;
        this.voBanh = voBanh;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public float getGia() {
        return gia;
    }

    public void setGia(float gia) {
        this.gia = gia;
    }

    public String getVoBanh() {
        return voBanh;
    }

    public void setVoBanh(String voBanh) {
        this.voBanh = voBanh;
    }
   
   
    
}
