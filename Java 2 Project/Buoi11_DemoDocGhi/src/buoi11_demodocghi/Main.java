/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package buoi11_demodocghi;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author drago
 *
 *
 *
 */
//  -Ghi - Output, Doc - Input - FileInputStream/FileOutputStream - Doc ghi file
//  - DataInputStream/DataOutputStream - Doc ghi du lieu nguyen thuy -
//  ObjectInpuStream/ObjectOutputStream - Doc ghi object
public class Main {

    public static void main(String[] args) {
        Main main = new Main();
        main.ghifile();
        main.docFile();
    }

    public void ghifile() {
        File file = new File("test.txt");

        ArrayList<DongVat> danhSach = new ArrayList<>();

        danhSach.add(new DongVat(1, "Lion", true, 100f));
        danhSach.add(new DongVat(2, "Pizza", false, 69f));
        danhSach.add(new DongVat(3, "Chicken", true, 20f));

        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            // Print the absolute file path
            System.out.println("File path: " + file.getAbsolutePath());

            try (FileOutputStream fos = new FileOutputStream(file); ObjectOutputStream oos = new ObjectOutputStream(fos)) {

                for (DongVat dv : danhSach) {
                    oos.writeObject(dv);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void docFile() {
        File file = new File("test.txt");

        try {
            ArrayList<DongVat> danhSach = new ArrayList<>();
            DongVat dv = null;
            if (!file.exists()) {
                System.out.println("Khong thay file");
            }
            
            try(FileInputStream fis = new FileInputStream(file); ObjectInputStream ois = new ObjectInputStream(fis)){
                while (fis.available() > 0 ) {                    
                    dv = (DongVat) ois.readObject();
                    danhSach.add(dv);
                }
            } 
            danhSach.forEach(dongVat -> System.out.println(dongVat.getTen() + " " + dongVat.getCanNang()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
