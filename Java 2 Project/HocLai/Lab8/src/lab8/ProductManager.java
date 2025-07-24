/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab8;

/**
 *
 * @author PC
 */
public class ProductManager {

    public static void main(String[] args) {
        Product p1 = new Product("iPhoneSuck69", 1234.0);
        Product p2 = new Product("Samsung Sigma Skibidi", 6969.0);
        ProductDAO dao = new ProductDAO();
        dao.add(p1);
        dao.add(p2);
        dao.store("prod.dat");
        ProductDAO dao2 = new ProductDAO();
        dao2.load("prod.dat");
        Product p = dao2.find("");
        if (p != null) {
            System.out.println(">Name: " + p.name);
            System.out.println(">Price: " + p.price);
        } else {
            System.out.println("Không tìm thấy sản phẩm!");
        }
    }
}
