/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package buoi8;

import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author drago
 */
public class Service {

    ArrayList<Model> list = new ArrayList<>();
    Scanner s = new Scanner(System.in);

    public ArrayList<Model> getAll() {
        return list;
    }
    
    

    public void Add(Model m) {
        list.add(m);
    }
    
    public void Sua(Model m) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId() == m.getId()) {
                list.set(i, m);
            }
        }
    }

    public void xoa(int id) {
        Model mCanXoa = null;

        for (Model model : list) {
            if (model.getId() == id) {
                mCanXoa = model;
            }
        }
        list.remove(mCanXoa);
    }

}
