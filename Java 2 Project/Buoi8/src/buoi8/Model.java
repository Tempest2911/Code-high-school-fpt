/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package buoi8;

/**
 *
 * @author drago
 */
public class Model {
    private int id;
    private String ten;
    private float gia;
    private String mauSac;

    public Model() {
    }

    public Model(int id, String ten, float gia, String mauSac) {
        this.id = id;
        this.ten = ten;
        this.gia = gia;
        this.mauSac = mauSac;
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

    public String getMauSac() {
        return mauSac;
    }

    public void setMauSac(String mauSac) {
        this.mauSac = mauSac;
    }
    
}
