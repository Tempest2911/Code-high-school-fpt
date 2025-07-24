/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package QuanLy;

import java.util.ArrayList;
import java.util.Comparator;

/**
 *
 * @author drago
 */
public class Service {
    
    ArrayList<Model> ListDT = new ArrayList<>();
    
    public ArrayList<Model> getAll() {
        return ListDT;
    }
    
    public void autoAdd() {
        ListDT.add(new Model(123, "K60 Ultra", true, "Xiaomi", 999));
        ListDT.add(new Model(321, "S69 Ultra", true, "Samsung", 214));
        ListDT.add(new Model(213, "Bedrock", false, "Nokia", 69));
    }
    
    public void addDT(Model model) {
        ListDT.add(model);
    }
    
    public void changeDT(Model model) {
        for (int i = 0; i < ListDT.size(); i++) {
            if (ListDT.get(i).getId() == model.getId()) {
                ListDT.set(i, model);
            }
        }
    }
    
    public void deleteDT(int idXoa) {
        Model mXoa = null;
        
        for (Model model : ListDT) {
            if (model.getId() == idXoa) {
                mXoa = model;
            }
        }
        ListDT.remove(mXoa);
    }
    
    public ArrayList<Model> SearchName(String ten) {
        ArrayList<Model> listSearch = new ArrayList<>();
        for (Model model : ListDT) {
            if (model.getTen().contains(ten)) {
                listSearch.add(model);
            }
        }
        return listSearch;
    };
    
    public void softName() {
        ListDT.sort(Comparator.comparing(Model::getTen));
    }
}
