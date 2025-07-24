/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import java.util.Scanner;

/**
 *
 * @author drago
 */
public class Bai1_TinhChuViDienTichHCN {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        float chieuDai = 0;
        float chieuRong = 0;
        double dienTich;
        double chuVi;
        
        System.out.println("Nhap chieu dai: ");
        chieuDai = scanner.nextFloat();
        
        System.out.println("Nhap chieu rong: ");
        chieuRong = scanner.nextFloat();
        
        dienTich = chieuDai * chieuRong;
        chuVi = (chieuDai+chieuRong)*2;
        
        System.out.println("Dien Tich HCN la: " +dienTich);
        System.out.println("Chu Vi HCN la: " +chuVi);
        
        
    }
    
}