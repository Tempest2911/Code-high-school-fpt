/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Bai1;

/**
 *
 * @author drago
 */
interface DAO {

    void insert();

    void update();

    void delete();

    void select();
}

public class Product implements DAO {

    private String name;
    private float price;

    public Product() {
    }

    public Product(String name, float price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public double getImportTax() {
        return price * 0.1;
    }

    @Override
    public void insert() {
        System.out.println("Thêm mới thành công!");
    }

    @Override
    public void update() {
        System.out.println("Cập nhật thành công!");
    }

    @Override
    public void delete() {
        System.out.println("Xóa thành công!");
    }

    @Override
    public void select() {
        System.out.println("Lấy dữ liệu thành công!");
    }
}
