/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package onthideco;

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
    
    ArrayList<Model> listSP = new ArrayList<>();
    
    public void autoadd() {
        listSP.add(new Model("S24 Ultra", "SamSung", 123234, "Tốt"));
        listSP.add(new Model("Fia", "Nokia", 1234, "Bình thường"));
        listSP.add(new Model("Ranni", "SamSung", 10244, "Tốt"));
        listSP.add(new Model("Malenia", "Nokia", 2424, "Bình thường"));
    }
    
    public ArrayList<Model> getall() {
        return listSP;
    }
    
    public void Add(Model model) {
        listSP.add(model);
    }
    
    public void Ghi() {
        File file = new File("SP.txt");
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
    
    public void Doc() {
        File file = new File("SP.txt");
        ArrayList<Model> newList = new ArrayList<>();
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
