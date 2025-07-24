/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package QuanLy;

/**
 *
 * @author drago
 */
public class Model {

    private int id;
    private String ten;
    private boolean kheSim;
    private String hang;
    private float gia;

    public Model() {
    }

    public Model(int id, String ten, boolean kheSim, String hang, float gia) {
        this.id = id;
        this.ten = ten;
        this.kheSim = kheSim;
        this.hang = hang;
        this.gia = gia;
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

    public boolean isKheSim() {
        return kheSim;
    }

    public void setKheSim(boolean kheSim) {
        this.kheSim = kheSim;
    }

    public String getHang() {
        return hang;
    }

    public void setHang(String hang) {
        this.hang = hang;
    }

    public float getGia() {
        return gia;
    }

    public void setGia(float gia) {
        this.gia = gia;
    }
    
    
}
