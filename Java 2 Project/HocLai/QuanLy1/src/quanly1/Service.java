/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quanly1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Comparator;

/**
 *
 * @author drago
 */
public class Service {

    ArrayList<Model> list = new ArrayList<>();

    public ArrayList<Model> getAll() {
        return list;
    }

    public void autoAdd() {
        list.add(new Model(1, "Ferrari", "Car", 124324.0, "Còn hàng", 123));
        list.add(new Model(2, "TUNG TUNG TUNG SAHUR", "Meme", 124324.0, "Còn hàng", 123));
        list.add(new Model(3, "Math", "Subject", 124324.0, "Còn hàng", 123));
    }

    public void add(Model m) {
        list.add(m);
    }

    public void edit(Model m) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId() == m.id) {
                list.set(i, m);
            }
        }
    }

    public void delete(Model m) {
        Model mxoa = null;
        for (Model model : list) {
            if (model.getId() == m.id) {
                mxoa = model;
            }
        }
        list.remove(mxoa);
    }

    public ArrayList<Model> Search(String name) {
        ArrayList<Model> listSearch = new ArrayList<>();
        for (Model model : list) {
            if (model.getName().contains(name)) {
                listSearch.add(model);
            }
        }
        return listSearch;
    }

    public void sort() {
        list.sort(Comparator.comparing(Model::getName));
    }

    public void writeFile() {
        File file = new File("skibidi.txt");
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            try (FileOutputStream fos = new FileOutputStream(file); ObjectOutputStream oos = new ObjectOutputStream(fos)) {
                for (Model model : list) {
                    oos.writeObject(model);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void readFile() {
        File file = new File("skibidi.txt");
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            list.clear();
            try (FileInputStream fis = new FileInputStream(file); ObjectInputStream ois = new ObjectInputStream(fis)) {
                while (fis.available() > 0) {
                    list.add((Model) ois.readObject());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
