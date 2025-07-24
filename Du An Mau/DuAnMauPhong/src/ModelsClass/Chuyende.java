/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModelsClass;

import java.util.UUID;

/**
 *
 * @author Acer
 */
public class Chuyende {

    int id;
    String name;
    String mota;
    double hocphi;
    String imgURL;

    public Chuyende() {
    }

    public Chuyende(int id, String name, String mota, double hocphi, String imgURL) {
        this.id = id;
        this.name = name;
        this.mota = mota;
        this.hocphi = hocphi;
        this.imgURL = imgURL;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    public double getHocphi() {
        return hocphi;
    }

    public void setHocphi(double hocphi) {
        this.hocphi = hocphi;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }

    
}
