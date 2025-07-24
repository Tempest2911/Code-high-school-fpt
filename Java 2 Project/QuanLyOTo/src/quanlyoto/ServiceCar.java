/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quanlyoto;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Comparator;

/**
 *
 * @author drago
 */
public class ServiceCar {

    ArrayList<ModelCar> modelCar = new ArrayList<>();

    ModelCar model = new ModelCar();

    public ServiceCar() {
        modelCar.add(new ModelCar(1, "Bugatti Chiron", "Bugatti", 200000, false));
        modelCar.add(new ModelCar(1, "Ferrari LaFerrari", "Ferrari", 250000, true));
        modelCar.add(new ModelCar(1, "McLaren P1", "McLaren", 120000, false));
        modelCar.add(new ModelCar(1, "Porsche 911", "Porsche", 14000, true));

    }

    public ArrayList<ModelCar> getAll() {
        return modelCar;
    }

    public void AddCar(ModelCar model) {
        modelCar.add(model);
    }

    public void DeleteCar(int idXoa) {
        ModelCar model = null;

        for (ModelCar modelCar1 : modelCar) {
            if (modelCar1.getId() == idXoa) {
                model = modelCar1;
            }
        }

        modelCar.remove(model);
    }

    public void ChangeCar(ModelCar model) {
        for (int i = 0; i < modelCar.size(); i++) {
            if (modelCar.get(i).getId() == model.getId()) {
                modelCar.set(i, model);
            }
        }
    }

    public ArrayList<ModelCar> SearchName(String name) {
        ArrayList<ModelCar> List = new ArrayList<>();

        for (ModelCar modelCar : modelCar) {
            if (modelCar.getName().contains(name)) {
                List.add(modelCar);
            }
        }
        return List;
    }

    public void SoftByName() {
        modelCar.sort((o1, o2) -> {
            return o1.getName().compareTo(o2.getName());
        });
    }
    
    public void ghiFile(){
        File file = new File("Data.txt");
        try{
            if (!file.exists()) {
                file.createNewFile();
            }
            try(FileOutputStream fos = new FileOutputStream(file);
                ObjectOutputStream oos = new ObjectOutputStream(fos)) {
                    for (ModelCar modelCar1 : modelCar) {
                    oos.writeObject(modelCar1);
                }
                }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void docFile(){
         File file = new File("Data.txt");
         ArrayList<ModelCar> newList = new ArrayList<>();
        try{
            if (!file.exists()) {
                System.out.println("Khong tim thay file");
            }
            try(FileInputStream fis = new FileInputStream(file);
                ObjectInputStream ois = new ObjectInputStream(fis)) {
                   while(fis.available()>0){
                       modelCar.add((ModelCar) ois.readObject());
                }
                   modelCar.remove(newList);
                   newList.addAll(newList);
                   newList.sort(Comparator.comparing(ModelCar::getId));
                }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

}
