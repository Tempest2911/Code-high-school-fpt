/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab1;

import java.util.Scanner;

/**
 *
 * @author drago
 */
public class Main {

    public static void main(String[] args) {
        QuanLySinhVien quanLySV = new QuanLySinhVien();
        Scanner s = new Scanner(System.in);
        int n;
        do {
            System.out.println("---------------------------------------------------------");
            System.out.println("1.Them sinh vien:");
            System.out.println("2.In thong tin sinh vien:");
            System.out.println("3.xoa sinh vien theo ma:");
            System.out.println("4.Sua thong tin theo ma sinh vien:");
            System.out.println("5.Sap xep sinh vien nam tang dan theo nam sinh:");
            System.out.println("0.Thoat");
            System.out.println("Moi ban chon so :");
            n = s.nextInt();
            s.nextLine();

            switch (n) {
                case 1:
                    System.out.println("------------------------------------------");
                    quanLySV.themSV();
                    break;
                case 2:
                    System.out.println("------------------------------------------");
                    quanLySV.inRa();
                    break;
                case 3:
                    System.out.println("------------------------------------------");
                    quanLySV.xoaTheoMa();
                    break;
                case 4:
                    System.out.println("------------------------------------------");
                    quanLySV.suaThongTin();
                    break;
                case 5:
                    System.out.println("------------------------------------------");
                    quanLySV.sapXepTangDan();
                    break;
                case 0:
                    System.out.println("Thoat Chuong Trinh");
                    System.exit(n);

                   
            }
        } while (n != 0);

    }
}
