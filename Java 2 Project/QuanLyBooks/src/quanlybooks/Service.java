/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quanlybooks;

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

    ArrayList<Model> listSP = new ArrayList<>();

    public ArrayList<Model> getAll() {
        return listSP;
    }

    public void autoAdd() {
        listSP.add(new Model("B1", "Arya", "Romcom", "New", 999));
        listSP.add(new Model("B2", "Berserk", "Fantasy", "Old", 6969));
        listSP.add(new Model("B3", "Frieren", "Fantasy", "New", 1233));
        listSP.add(new Model("B4", "Vagabond", "Sword", "Old", 2222));
        listSP.add(new Model("B5", "Atri", "Romcom", "New", 1111));
    }

    public void add(Model m) {
        listSP.add(m);
    }

    public void change(Model m) {
        for (int i = 0; i < listSP.size(); i++) {
            if (listSP.get(i).getId().equals(m.getId())) {
                listSP.set(i, m);
            }
        }
    }

    public void delete(String idxoa) {
        Model mxoa = null;

        for (Model model : listSP) {
            if (model.getId().equals(idxoa)) {
                mxoa = model;
            }
        }

        listSP.remove(mxoa);
    }

    public void sapXep() {
        listSP.sort(Comparator.comparing(Model::getName));
    }

    public ArrayList<Model> Search(String name) {
        ArrayList<Model> listSearch = new ArrayList<>();

        for (Model model : listSP) {
            if (model.getName().contains(name)) {
                listSearch.add(model);
            }
        }
        return listSearch;
    }

    public void ghiFile() {
        File file = new File("Book.txt");

        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            try (FileOutputStream fos = new FileOutputStream(file); ObjectOutputStream oos = new ObjectOutputStream(fos)) {
                for (Model model : listSP) {
                    oos.writeObject(model);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void docFile() {
        File file = new File("Book.txt");

        try {
            if (!file.exists()) {
                System.out.println("Ko thay file");
            }
            listSP.clear();
            try (FileInputStream fis = new FileInputStream(file); ObjectInputStream ois = new ObjectInputStream(fis)) {
                while (fis.available() > 0) {
                    listSP.add((Model) ois.readObject());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
