/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package buoi2;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author drago
 */
public class QuanLyHocSinh {

    ArrayList<HocSinh> ListHS = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);

    public void nhapThongTin() {

        int quantityHS = 0;

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
    }

    public void inThongTin() {
        for (HocSinh hocSinh : ListHS) {
            System.out.println("-----------------------------------");
            hocSinh.hienThiThongTin();
        }
    }

    //method: xoa hoc sinh theo ten
    public void xoaThongTin() {
        System.out.println("Nhap ten hoc sinh de xoa: ");
        String ten = scanner.nextLine();

        ArrayList<HocSinh> danhSachCanXoa = new ArrayList<>();

        //cach xoa 1
        ListHS.forEach(hs -> {
            if (hs.getTen().equals(ten)) {
                danhSachCanXoa.add(hs);
            }
        });

        //cach xoa 2
//        for (int i = 0; i < ListHS.size(); i++) {
//            if (ListHS.get(i).getTen().equalsIgnoreCase(ten)) {
//                danhSachCanXoa.add(ListHS.get(i));
//            }
//        }
        ListHS.removeAll(danhSachCanXoa);
    }
    //method: sua hoc sinh theo ten

    public void suaThongTin() {

        System.out.println("Nhap ten hs can sua: ");
        String tenDeSua = scanner.nextLine();
        for (HocSinh hocSinh : ListHS) {
            if (hocSinh.getTen().equalsIgnoreCase(tenDeSua)) {
                System.out.println("Nhap ten moi: ");
                String tenMoi = scanner.nextLine();
                System.out.println("Nhap tuoi moi: ");
                int tuoimoi = Integer.valueOf(scanner.nextLine());

                System.out.println("Nhap ma moi: ");
                String maMoi = scanner.nextLine();

                System.out.println("Nhap diem moi: ");
                double diemMoi = Double.valueOf(scanner.nextLine());
                
                hocSinh.setTen(tenMoi);
                hocSinh.setTuoi(tuoimoi);
                hocSinh.setMa(maMoi);
                hocSinh.setDiem(diemMoi);
            }
        }

    }

    //method: sap xep hoc sinh theo ten tang dan
    
    public void sapXepTangDan(){
        
    }
    //method sap xep hoc sinh theo giam dan
}
