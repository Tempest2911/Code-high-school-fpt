/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package thitest;

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
public class DienthoaiService {

    ArrayList<DienThoai> listDT = new ArrayList<>();

    public ArrayList<DienThoai> getAll() {
        return listDT;
    }

    public void autoAdd() {
        listDT.add(new DienThoai("Iphone suck 16", "Apple", 16.000000));
        listDT.add(new DienThoai("Oppo skibidi", "Oppo", 34.000000));
        listDT.add(new DienThoai("Iphone cccc", "Apple", 12.000000));
        listDT.add(new DienThoai("Oppo grrrr", "Oppo", 444.000000));
        listDT.add(new DienThoai("Samsung sigma ultra", "Samsung", 1.000000));
    }

    public void add(DienThoai dt) {
        listDT.add(dt);
    }

    public void ghiFile() {
        File file = new File("dienthoai.txt");

        try (FileOutputStream fos = new FileOutputStream(file); ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            for (DienThoai dienThoai : listDT) {
                oos.writeObject(dienThoai);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void docFile() {
        File file = new File("dienthoai.txt");

        try {
            if (!file.exists()) {
                System.out.println("Cann't find file");
            }
            listDT.clear();
            try (FileInputStream fis = new FileInputStream(file); ObjectInputStream ois = new ObjectInputStream(fis)) {
                while (fis.available() > 0) {
                    listDT.add((DienThoai) ois.readObject());

                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
