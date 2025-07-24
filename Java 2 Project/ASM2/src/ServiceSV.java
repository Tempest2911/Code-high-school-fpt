
import java.util.ArrayList;
import java.util.Scanner;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author drago
 */
public class ServiceSV {

    ArrayList<Model> modelSV = new ArrayList<>();

    Scanner s = new Scanner(System.in);

    public ArrayList<Model> getAll() {
        return modelSV;
    }

    public void autoAdd() {
        modelSV.add(new Model(123, "PHONG", 9, 1, 10, 6.6f));
        modelSV.add(new Model(321, "HUNG", 9, 8, 10, 9));
        modelSV.add(new Model(213, "QUYET", 1, 5, 8, 4.6f));
    }

    public void add(Model m) {
        modelSV.add(m);
    }

    public void delete(int id) {
        Model mXoa = null;

        for (Model model : modelSV) {
            if (model.getMaSV() == id) {
                mXoa = model;
            }
        }
        modelSV.remove(mXoa);
    }

    public void update(Model m) {
        for (int i = 0; i < modelSV.size(); i++) {
            if (modelSV.get(i).getMaSV() == m.getMaSV()) {
                modelSV.set(i, m);
            }
        }
    }

}
