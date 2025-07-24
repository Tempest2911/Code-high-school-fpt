/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quanlyoto;

import java.io.Serializable;

/**
 *
 * @author drago
 */
public class ModelCar implements Serializable{
    
    private int id;
    private String name;
    private String Company;
    private float Price;
    private boolean Roof;

    public ModelCar() {
    }

    public ModelCar(int id, String name, String Company, float Price, boolean Roof) {
        this.id = id;
        this.name = name;
        this.Company = Company;
        this.Price = Price;
        this.Roof = Roof;
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

    public String getCompany() {
        return Company;
    }

    public void setCompany(String Company) {
        this.Company = Company;
    }

    public float getPrice() {
        return Price;
    }

    public void setPrice(float Price) {
        this.Price = Price;
    }

    public boolean isRoof() {
        return Roof;
    }

    public void setRoof(boolean Roof) {
        this.Roof = Roof;
    }
    
    
    
}
