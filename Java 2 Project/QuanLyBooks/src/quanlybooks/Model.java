/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quanlybooks;

import java.io.Serializable;

/**
 *
 * @author drago
 */
public class Model implements Serializable {

    private String id;
    private String name;
    private String genre;
    private String status;
    private int Quantity;

    public Model() {
    }

    public Model(String id, String name, String genre, String status, int Quantity) {
        this.id = id;
        this.name = name;
        this.genre = genre;
        this.status = status;
        this.Quantity = Quantity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int Quantity) {
        this.Quantity = Quantity;
    }

}
