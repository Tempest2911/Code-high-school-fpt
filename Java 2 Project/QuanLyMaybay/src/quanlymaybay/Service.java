/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quanlymaybay;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 *
 * @author drago
 */
public class Service {

    ArrayList<Model> listMayBay = new ArrayList<>();

    public ArrayList<Model> getall() {
        return listMayBay;
    }

    public void autoadd() {
        listMayBay.add(new Model("P123", "F22 Raptor", "Hoat dong", "Lookhead", 1970));
        listMayBay.add(new Model("P321", "A-10 Thunderbolt", "Hoat dong", "Lookhead", 1960));
        listMayBay.add(new Model("P213", "Boeing 747", "Khong hoat dong", "Boeing", 2007));
        listMayBay.add(new Model("P312", "Airbus A320", "Hoat dong", "Airbus", 2000));
        listMayBay.add(new Model("P234", "Su-57", "Khong Hoat dong", "Sukhoi", 2010));
    }

    public void add(Model model) {
        listMayBay.add(model);
    }

    public void change(Model model) {
        for (int i = 0; i < listMayBay.size(); i++) {
            if (listMayBay.get(i).getId().equals(model.getId())) {
                listMayBay.set(i, model);
            }
        }
    }

    public void delete(String idxoa) {
        Model mxoa = null;

        for (Model model : listMayBay) {
            if (model.getId().equals(idxoa)) {
                mxoa = model;
            }
        }
        listMayBay.remove(mxoa);
    }

    public void ghiFile() {
        File file = new File("maybay.txt");
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            try (FileOutputStream fos = new FileOutputStream(file); ObjectOutputStream oos = new ObjectOutputStream(fos)) {
                for (Model model : listMayBay) {
                    oos.writeObject(model);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void docFile() {
        File file = new File("maybay.txt");
        try {
            if (!file.exists()) {
                System.out.println("File khong ton tai");
            }
            listMayBay.clear();
            try (FileInputStream fis = new FileInputStream(file); ObjectInputStream ois = new ObjectInputStream(fis)) {
                while (fis.available() > 0) {
                    listMayBay.add((Model) ois.readObject());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
