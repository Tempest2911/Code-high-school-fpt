/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bt1;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author drago
 */
public class Main {

    public static void main(String[] args) {
        ArrayList<HocSinh> ListHS = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        
        int quantityHS=0;
        
        System.out.println("Nhap so luong SV: ");
        quantityHS = scanner.nextInt();
        scanner.nextLine();
        
        
        for (int i = 0; i < quantityHS; i++) {
            
        System.out.println("----------------------------------");
        System.out.println("Vui long nhap ten: ");
        String ten = scanner.nextLine();

        System.out.println("Vui long nhap tuoi: ");
        int tuoi = Integer.valueOf(scanner.nextLine());
        
        System.out.println("Vui long nhap ma: ");
        String ma = scanner.nextLine();
        
        System.out.println("Vui long nhap diem: ");
        double diem = Double.valueOf(scanner.nextLine());
        
        HocSinh hs = new HocSinh(ma, diem, ten, tuoi);
        ListHS.add(hs);
        
        }
        
        for (HocSinh hocSinh : ListHS) {
            System.out.println("-----------------------------------");
            hocSinh.hienThiThongTin();
        }  
    }
}
