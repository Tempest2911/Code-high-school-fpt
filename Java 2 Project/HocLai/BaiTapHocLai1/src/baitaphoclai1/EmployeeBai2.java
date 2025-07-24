/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package baitaphoclai1;

import java.util.Arrays;
import java.util.Comparator;

/**
 *
 * @author drago
 */
public class EmployeeBai2 {

    private int id;
    private String name;
    private String address;
    private double salary;

    public EmployeeBai2() {
    }

    public EmployeeBai2(int id, String name, String address, double salary) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public static void sortEmployees(EmployeeBai2[] employees) {
        Arrays.sort(employees, Comparator.comparingDouble(EmployeeBai2::getSalary));
    }
}
