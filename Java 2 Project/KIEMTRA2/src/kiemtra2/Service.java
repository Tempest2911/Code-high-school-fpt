/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kiemtra2;

import java.util.ArrayList;

/**
 *
 * @author drago
 */
public class Service {

    ArrayList<Model> listDH = new ArrayList<>();

    public ArrayList<Model> getall() {
        return listDH;
    }

    public void autoAdd() {
        listDH.add(new Model(123, 100, "Rolex", true, "Rolex mmb"));
        listDH.add(new Model(321, 999, "Bugatti", true, "Bugatti Chiron"));

    }

    public void addDH(Model model) {
        listDH.add(model);
    }

    public void deleteDH(int idXoa) {
        Model mXoa = null;

        for (Model model : listDH) {
            if (model.getId() == idXoa) {
                mXoa = model;
            }
        }
        listDH.remove(mXoa);
    }

}
