/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quanlysanpham;

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
        listSP.add(new Model("SP1", "LOLOLO", "Điện tử", 12313, 13144, "Mới"));
        listSP.add(new Model("SP2", "Pizza Lion Chicken", "Thực phẩm", 35, 535, "Cũ"));
        listSP.add(new Model("SP3", "Augar Crush", "Quần áo", 4355, 757, "Mới"));
    }

    public void add(Model m) {
        listSP.add(m);
    }

    public void change(Model m) {
        for (int i = 0; i < listSP.size(); i++) {
            if (listSP.get(i).getMaSanPham().equals(m.getMaSanPham())) {
                listSP.set(i, m);
            }
        }
    }

    public void delete(String idXoa) {
        Model mxoa = null;

        for (Model model : listSP) {
            if (model.getMaSanPham().equals(idXoa)) {
                mxoa = model;
            }
        }
        listSP.remove(mxoa);
    }

    public void sapXep() {
        listSP.sort(Comparator.comparing(Model::getTenSanPham));
    }

    public ArrayList<Model> search(String ten) {
        ArrayList<Model> listSearch = new ArrayList<>();
        for (Model model : listSP) {
            if (model.getTenSanPham().contains(ten)) {
                listSearch.add(model);
            }
        }
        return listSearch;
    }

    public void ghiFile() {
        File file = new File("SanPha.txt");

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
        File file = new File("SanPha.txt");

        try {
            if (!file.exists()) {
                System.out.println("Khong tim thay file");
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
