/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Bai3;

import Bai1.Product;
import java.text.DecimalFormat;
import java.util.Scanner;

/**
 *
 * @author drago
 */
public class NoTaxProduct extends Product {

    public NoTaxProduct() {
    }

    public NoTaxProduct(String name, float price) {
        super(name, price);
    }

    @Override
    public double getImportTax() {
        return 0;
    }

    public static void main(String[] args) {
        Product product = new Product();
        Product product1 = new Product("F-35 Lightning II", 143000000);
        Product product2 = new Product("F-22 Raptor", 177000000);
        NoTaxProduct product3 = new NoTaxProduct("Ferrari LaFerrari", 35000000);

        System.out.println("Thông tin sản phẩm:");
        printInfo(product1);
        printInfo(product2);
        printInfo(product3);

        System.out.println("\nThao tác CSDL:");
        product.insert();
        product.update();
        product.delete();
        product.select();
    }

    public static void printInfo(Product product) {
        System.out.printf("Tên: %s, Giá: %.0f, Thuế nhập khẩu: %.0f%n", product.getName(), product.getPrice(), product.getImportTax());
    }
}
