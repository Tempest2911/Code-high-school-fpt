/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quanlysanpham3;

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
        listSP.add(new Model("SP1", "Adidas Fly", "Adidas", "New", 112));
        listSP.add(new Model("SP2", "Nike Nigga", "Nike", "Old", 1343));
        listSP.add(new Model("SP3", "Sigma Gyat", "Sigma", "New", 2424));
         listSP.add(new Model("SP4", "oeiwrhjfuierhui Gyat", "Sigma", "New", 2424));
    }

    public void add(Model m) {
        listSP.add(m);
    }

    public void edit(Model m) {
        for (int i = 0; i < listSP.size(); i++) {
            if (listSP.get(i).getId().equals(m.getId())) {
                listSP.set(i, m);
            }
        }
    }

//    public void delete(String idXoa) {
//        Model mxoa = null;
//        for (Model model : listSP) {
//            if (model.getId().equals(idXoa)) {
//                mxoa = model;
//            }
//        }
//        listSP.remove(mxoa);
//    }

    public void xoa(Model m) {
        Model mxoa = null;
        for (Model model : listSP) {
            if (model.getId().equals(m.getId())) {
                mxoa = model;
            }
        }
        listSP.remove(mxoa);
    }

    public ArrayList<Model> Search(String name) {
        ArrayList<Model> listSearch = new ArrayList();
        for (Model model : listSP) {
            if (model.getName().contains(name)) {
                listSearch.add(model);
            }
        }

        return listSearch;
    }

    public void sapxep() {
        listSP.sort(Comparator.comparing(Model::getName));
    }

    public void writeFile() {
        File file = new File("Sp.txt");
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

    public void readFile() {
        File file = new File("Sp.txt");
        try {
            if (!file.exists()) {
                System.out.println("File ????");
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
