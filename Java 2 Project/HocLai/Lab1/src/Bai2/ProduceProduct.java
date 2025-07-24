/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Bai2;

import Bai1.Product;
import java.util.Scanner;

/**
 *
 * @author drago
 */
public class ProduceProduct {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        System.out.print("Nhập số sản phẩm: ");
        int quantity = s.nextInt();
        s.nextLine();

        Product[] products = new Product[quantity];

        for (int i = 0; i < quantity; i++) {
            System.out.println("==================================================");
            System.out.println("Nhập thông tin sản phẩm " + (i + 1) + " :");
            System.out.print("Nhập tên: ");
            String name = s.nextLine();

            System.out.print("Nhập giá: ");
            float price = s.nextFloat();
            s.nextLine();

            products[i] = new Product(name, price);
        }
        System.out.println("================================================");
        System.out.println("Danh sách sản phẩm:");
        for (Product p : products) {
            System.out.println("Tên: " + p.getName());
            System.out.println("Giá: " + p.getPrice());
            System.out.println("Thuế nhập khẩu: " + p.getImportTax());
            System.out.println("-----------------------");
        }
    }
}
