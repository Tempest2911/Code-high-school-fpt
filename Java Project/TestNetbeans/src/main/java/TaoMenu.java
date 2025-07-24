/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.util.Scanner;

/**
 *
 * @author drago
 */
public class TaoMenu {
    static void bai1() {
        int a;
        int b;
        int c;

        Scanner scanner = new Scanner(System.in);

        System.out.print("Nhap so a: ");
        a = scanner.nextInt();

        System.out.print("Nhap so b: ");
        b = scanner.nextInt();

        c = a + b;

        System.out.printf("Tong cua a+b la: %d\n", c);
    }

    static void bai2() {
        int x;
        int y;

        Scanner scanner = new Scanner(System.in);

        System.out.print("Nhap so a: ");
        x = scanner.nextInt();

        System.out.print("Nhap so b: ");
        y = scanner.nextInt();

        int z = x * y;

        System.out.printf("Tich cua a*b la: %d\n", z);
    }

    static void bai3() {
        String hoten;
        String lop;
        int tuoi;
        String masv;

        Scanner scanner = new Scanner(System.in);

        System.out.print("Nhap ho ten cua hs: ");
        hoten = scanner.nextLine();

        System.out.print("Nhap tuoi cua hs: ");
        tuoi = Integer.parseInt(scanner.nextLine());

        System.out.print("Nhap ma sv cua hs: ");
        masv = scanner.nextLine();

        System.out.print("Nhap lop hoc cua hs: ");
        lop = scanner.nextLine();


        String thongTin = "\nTen cua hs la: " + hoten + "\nTuoi cua hs la: " + tuoi + "\nMa sv cua hs la: " + masv + "\nLop hoc cua hs la: " + lop;
        System.out.println(thongTin);
    }

    public static void main(String[] args) {
        int x = 0;
        Scanner scanner = new Scanner(System.in);

        do {
            
            System.out.println("\n1) Nhap a,b roi tinh tong\n");
            System.out.println("2) Nhap a,b roi tinh nhan\n");
            System.out.println("3) Nhap thong tin cua hoc sinh\n");
            System.out.println("4) Thoat chuong trinh\n");
            System.out.println("Moi chon so: ");
            x = scanner.nextInt();
            switch (x) {
                case 1:
                    bai1();
                    break;

                case 2:
                    bai2();
                    break;

                case 3:
                    bai3();
                    break;

                case 4:
                    System.out.println("Thoat chuong trinh");
                    break;

                default:
                    System.out.println("Ban da nhap sai, vui long nhap lai\n");
                    break;
            }
        } while (x != 4);
    }
}
