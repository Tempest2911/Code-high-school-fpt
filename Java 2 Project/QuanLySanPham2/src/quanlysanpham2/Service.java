/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quanlysanpham2;

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

    ArrayList<Model> lispSP = new ArrayList<>();

    public ArrayList<Model> getAll() {
        return lispSP;
    }

    public void autoAdd() {
        lispSP.add(new Model("SP1", "Lenovo", "Technology", "New", 123));
        lispSP.add(new Model("SP2", "Ferrari LaFerrari", "Car", "New", 3535));
        lispSP.add(new Model("SP3", "E-Dra EK387", "Technology", "Old", 123));
        lispSP.add(new Model("SP4", "Lenovo", "Computer", "Old", 8565));
    }

    public void add(Model m) {
        lispSP.add(m);
    }

    public void change(Model m) {
        for (int i = 0; i < lispSP.size(); i++) {
            if (lispSP.get(i).getId().equals(m.getId())) {
                lispSP.set(i, m);
            }
        }
    }

    public void delete(String id) {
        Model mxoa = null;

        for (Model model : lispSP) {
            if (model.getId().equals(id)) {
                mxoa = model;
            }
        }
        lispSP.remove(mxoa);
    }

    public void Sort() {
        lispSP.sort(Comparator.comparing(Model::getName));
    }

    public ArrayList<Model> Search(String name) {
        ArrayList<Model> listSearch = new ArrayList<>();
        for (Model model : lispSP) {
            if (model.getName().contains(name)) {
                listSearch.add(model);
            }
        }
        return listSearch;
    }

    public void GhiFile() {
        File file = new File("SP.txt");
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            try (FileOutputStream fos = new FileOutputStream(file); ObjectOutputStream oos = new ObjectOutputStream(fos)) {
                for (Model model : lispSP) {
                    oos.writeObject(model);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void DocFile() {
        File file = new File("SP.txt");

        try {
            if (!file.exists()) {
                System.out.println("Ko thay file");
            }
            lispSP.clear();
            try (FileInputStream fis = new FileInputStream(file); ObjectInputStream ois = new ObjectInputStream(fis)) {
                while (fis.available() > 0) {
                    lispSP.add((Model) ois.readObject());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
