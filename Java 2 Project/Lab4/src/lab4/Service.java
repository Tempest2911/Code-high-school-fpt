/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab4;

import java.util.ArrayList;

/**
 *
 * @author drago
 */
public class Service {
    
    ArrayList<Model> listNV = new ArrayList<>();
    
    Model modelNV = new Model();
    
    
    public void autoAdd(){
        listNV.add(new Model(123, "OLELE", true, "Trưởng phòng"));
        listNV.add(new Model(321, "SIGMA", false, "Nhân viên"));
    }
    
    public ArrayList<Model> getAll(){
        return listNV;
    }
    
    public void ThemNV(Model modelNV){
        listNV.add(modelNV);
    }
    
    public void xoaNV(int id){
        Model mXoa = null;
        
        for (Model model : listNV) {
            if (model.getId()==id) {
                mXoa= model;
            }
        }
        listNV.remove(mXoa);
    }
    
    public void suaNV(Model modelNV){
        for (int i = 0; i < listNV.size(); i++) {
            if (listNV.get(i).getId()== modelNV.getId()) {
                listNV.set(i, modelNV);
            }
        }
    }
}
