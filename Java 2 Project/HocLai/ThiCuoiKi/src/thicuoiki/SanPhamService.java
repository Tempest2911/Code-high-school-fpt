/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package thicuoiki;

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
public class SanPhamService {

    ArrayList<SanPham> listSP = new ArrayList<>();

    public ArrayList<SanPham> getAll() {
        return listSP;
    }

    public void autoAdd() {
        listSP.add(new SanPham("Ferrari LaFerrari", "Chiếc", 300000.0));
        listSP.add(new SanPham("Bút Shimpazini", "Cái", 10.0));
        listSP.add(new SanPham("Bugatti Chiron", "Chiếc", 50000.0));
        listSP.add(new SanPham("Ghế Tung Tung Sahur", "Cái", 50.0));
    }

    public void add(SanPham m) {
        listSP.add(m);
    }

    public void writeFile() {
        File file = new File("sanpham.txt");
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            try (FileOutputStream fos = new FileOutputStream(file); ObjectOutputStream oos = new ObjectOutputStream(fos)) {
                for (SanPham model : listSP) {
                    oos.writeObject(model);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void readFile() {
        File file = new File("sanpham.txt");
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            listSP.clear();
            try (FileInputStream fis = new FileInputStream(file); ObjectInputStream ois = new ObjectInputStream(fis)) {
                while (fis.available() > 0) {
                    listSP.add((SanPham) ois.readObject());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
