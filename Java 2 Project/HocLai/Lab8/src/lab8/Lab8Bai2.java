/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab8;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author PC
 */
public class Lab8Bai2 {
    public static void main(String[] args) {
        ArrayList<Integer> myarrr = new ArrayList<Integer>();
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Nhập 10 số nguyên:");
        for (int i = 0; i < 10; i++) {
            System.out.print("Nhập số thứ " + (i + 1) + ": ");
            int number = scanner.nextInt();
            myarrr.add(number);
        }
        System.out.println("\nCác số đã nhập là:");
        for (int number : myarrr) {
            System.out.print(number + " ");
        }
        
        scanner.close();
    }
}
