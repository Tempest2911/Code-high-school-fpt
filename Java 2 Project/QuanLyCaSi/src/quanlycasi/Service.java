/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quanlycasi;

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

    ArrayList<Model> listCaSi = new ArrayList<>();

    public void autoAdd() {
        listCaSi.add(new Model("C123", "Eminem", "Nam", "Hang A", 40));
        listCaSi.add(new Model("C321", "Yoasobi", "Nu", "Hang B", 25));
        listCaSi.add(new Model("C213", "Hatsune Miku", "Nu", "Hang C", 20));
        listCaSi.add(new Model("C432", "Travis Scott", "Nam", "Hang A", 50));
        listCaSi.add(new Model("C324", "Linkin Park", "Nam", "Hang B", 80));
    }

    public ArrayList<Model> getAll() {
        return listCaSi;
    }

    public void add(Model listCS) {
        listCaSi.add(listCS);
    }

    public void change(Model listCS) {
        for (int i = 0; i < listCaSi.size(); i++) {
            if (listCaSi.get(i).getMa().equals(listCS.getMa())) {
                listCaSi.set(i, listCS);
            }
        }
    }

    public void delete(String idxoa) {
        Model mxoa = null;

        for (Model model : listCaSi) {
            if (model.getMa().equals(idxoa)) {
                mxoa = model;
            }
        }

        listCaSi.remove(mxoa);
    }

    public void ghiFile() {
        File file = new File("casi.txt");
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            try (FileOutputStream fos = new FileOutputStream(file); ObjectOutputStream oos = new ObjectOutputStream(fos)) {
                for (Model model : listCaSi) {
                    oos.writeObject(model);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void docFile() {
        File file = new File("casi.txt");
        try {
            if (!file.exists()) {
                System.out.println("File khong ton tai");
            }
            listCaSi.clear();
            try (FileInputStream fis = new FileInputStream(file); ObjectInputStream ois = new ObjectInputStream(fis)) {
                while (fis.available() > 0) {
                    listCaSi.add((Model) ois.readObject());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
