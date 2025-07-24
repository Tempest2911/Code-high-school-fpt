/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quanlykhachhang;

import java.util.ArrayList;

/**
 *
 * @author drago
 */
public class Service {
    ArrayList<Model> listKH = new ArrayList<>();
    
    public void autoAdd(){
        listKH.add(new Model("123", "Phong", "Nữ", 2007, "VIP"));
        listKH.add(new Model("321", "Hưng", "Nam", 1970, "VVIP"));
        listKH.add(new Model("213", "Quyết", "Nữ", 2016, "Khách Thường"));
    }
    
    public void Sua(Model model){
        for (int i = 0; i < listKH.size(); i++) {
            if (listKH.get(i).getMaKH() == model.getMaKH() ) {
                listKH.set(i, model);
            }
        }
    }
    
    public void GhiFile(){
        
    }
    
   
}
