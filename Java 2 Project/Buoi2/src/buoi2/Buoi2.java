/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package buoi2;

import java.util.Scanner;

/**
 *
 * @author drago
 */
public class Buoi2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        QuanLyHocSinh quanLy = new QuanLyHocSinh();

        System.out.println("--------------------------------------");
        System.out.println("1.Nhap thong tin hoc sinh");
        System.out.println("2.In tat ca thong tin hoc sinh");
        System.out.println("3.xoa hoc sinh theo ten");
        System.out.println("4.sua hoc sinh theo ten");
        System.out.println("5.sap xep hoc sinh theo ten tang dan");
        System.out.println("6.sap xep hoc sinh theo diem giam dan");
        System.out.println("7.Thoat");
        System.out.println("-------------------------------------------------------");
        int n = 0;
        
        
        do {
            System.out.println("----------------------------");
            System.out.println("Nhap so: ");
            n = Integer.valueOf(scanner.nextLine());
            
            switch (n) {
            case 1:
                quanLy.nhapThongTin();
                break;
            case 2:
            quanLy.inThongTin();
            break;
            
            case 3:
                quanLy.xoaThongTin();
                break;
            case 4:
                quanLy.suaThongTin();
                break;
            case 7:
                System.exit(0);
        }
        } while (n !=7);
        
        
    }

}
