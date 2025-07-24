/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author drago
 */
public class ModelPH {

    public int idPH;
    public String tenPH;
    public String nghenghiep;

    public ModelPH() {
    }

    public ModelPH(int idPH, String tenPH, String nghenghiep) {
        this.idPH = idPH;
        this.tenPH = tenPH;
        this.nghenghiep = nghenghiep;
    }

    public int getIdPH() {
        return idPH;
    }

    public void setIdPH(int idPH) {
        this.idPH = idPH;
    }

    public String getTenPH() {
        return tenPH;
    }

    public void setTenPH(String tenPH) {
        this.tenPH = tenPH;
    }

    public String getNghenghiep() {
        return nghenghiep;
    }

    public void setNghenghiep(String nghenghiep) {
        this.nghenghiep = nghenghiep;
    }

}
