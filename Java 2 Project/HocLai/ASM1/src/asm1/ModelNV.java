package asm1;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author drago
 */
public class ModelNV {

    private int id;
    private String hoTen;
    private int tuoi;
    private String email;
    private float luong;

    public ModelNV() {
    }

    public ModelNV(int id, String hoTen, int tuoi, String email, float luong) {
        this.id = id;
        this.hoTen = hoTen;
        this.tuoi = tuoi;
        this.email = email;
        this.luong = luong;
    }

    public int getId() {
        return id;
    }

    public void setMaSV(int maSV) {
        this.id = maSV;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public int getTuoi() {
        return tuoi;
    }

    public void setTuoi(int tuoi) {
        this.tuoi = tuoi;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public float getLuong() {
        return luong;
    }

    public void setLuong(float luong) {
        this.luong = luong;
    }

    @Override
    public String toString() {
        return id + " - " + hoTen + " - " + tuoi + " - " + email + " - " + luong;
    }

}
