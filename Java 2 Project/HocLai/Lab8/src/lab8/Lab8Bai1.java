/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab8;

import java.util.ArrayList;

/**
 *
 * @author PC
 */
public class Lab8Bai1 {
    public static void main(String[] args) {
        ArrayList<Object> list = new ArrayList<>();
        list.add(20);        
        list.add(3.14);
        list.add(true);      
        list.add("Highest in the room"); 
       
        System.out.println("Các giá trị trong ArrayList là:");
        System.out.println("1. Số nguyên: " + list.get(0));
        System.out.println("2. Số thực: " + list.get(1));
        System.out.println("3. Giá trị boolean: " + list.get(2));
        System.out.println("4. Xâu ký tự: " + list.get(3));
    }
}
