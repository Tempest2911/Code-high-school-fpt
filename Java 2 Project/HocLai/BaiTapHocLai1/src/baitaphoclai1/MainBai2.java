/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package baitaphoclai1;

import java.util.Scanner;

/**
 *
 * @author drago
 */
public class MainBai2 {

    public static void main(String[] args) {
        EmployeeBai2 em = new EmployeeBai2();
        Scanner s = new Scanner(System.in);
        System.out.print("Nhập số nhân viên: ");
        int quantity = s.nextInt();
        s.nextLine();

        EmployeeBai2[] employee = new EmployeeBai2[quantity];

        for (int i = 0; i < quantity; i++) {
            System.out.println("==================================================");
            System.out.println("Nhập thông tin nhân viên " + (i + 1) + " :");
            System.out.print("Nhập ID: ");
            int id = s.nextInt();
            s.nextLine();

            System.out.print("Nhập tên nhân viên: ");
            String name = s.nextLine();

            System.out.print("Nhập địa chỉ: ");
            String address = s.nextLine();

            System.out.print("Nhập lương: ");
            float price = s.nextFloat();

            employee[i] = new EmployeeBai2(id, name, address, price);
        }
        System.out.println("================================================");
        System.out.println("Danh sách sản phẩm:");
        for (EmployeeBai2 e : employee) {
            System.out.println("-----------------------");
            System.out.println("ID: " + e.getId());
            System.out.println("Name: " + e.getName());
            System.out.println("Address: " + e.getAddress());
            System.out.println("Salary: " + e.getSalary());

        }

        em.sortEmployees(employee);
        System.out.println("================================================");
        System.out.println("\nDanh sách nhân viên sau khi sắp xếp theo lương tăng dần:");
        System.out.println("-----------------------------------");
        for (EmployeeBai2 e : employee) {
            System.out.println("ID: " + e.getId());
            System.out.println("Name: " + e.getName());
            System.out.println("Address: " + e.getAddress());
            System.out.println("Salary: " + e.getSalary());
            System.out.println("-----------------------");
        }
    }
}
