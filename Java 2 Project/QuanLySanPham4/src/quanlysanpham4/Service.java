/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quanlysanpham4;

import java.util.ArrayList;

/**
 *
 * @author drago
 */
public class Service {

    ArrayList<Model> ListSP = new ArrayList<>();

    public ArrayList<Model> getAll() {
        return ListSP;
    }

    public void autoAdd() {
        ListSP.add(new Model("1", "Phong", 15, "Male", 8324));
        ListSP.add(new Model("2", "Khoa", 15, "Female", 83543));
        ListSP.add(new Model("3", "Phúc", 15, "Male", 95835));
    }

    public void add(Model m) {
        ListSP.add(m);
    }

}
