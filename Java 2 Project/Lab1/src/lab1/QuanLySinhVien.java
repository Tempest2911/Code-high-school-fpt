/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab1;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Comparator;

/**
 *
 * @author drago
 */
public class QuanLySinhVien {

    ArrayList<SinhVien> listSV = new ArrayList<>();
    Scanner s = new Scanner(System.in);

    public void themSV() {

        int quantityHS = 0;

        System.out.println("Nhap so luong SV: ");
        quantityHS = s.nextInt();
        s.nextLine();

        for (int i = 0; i < quantityHS; i++) {
            System.out.println("------------------------------------");
            System.out.println("Nhap ma cua SV: ");
            String ma = s.nextLine();
            System.out.println("Nhap diem cua SV: ");
            double diem = s.nextDouble();
            s.nextLine();
            System.out.println("Nhap nam sinh cua SV: ");
            int namSinh = s.nextInt();
            s.nextLine();

            SinhVien sv = new SinhVien(ma, diem, namSinh);

            listSV.add(sv);
        }
    }

    public void inRa() {
        for (SinhVien sinhVien : listSV) {
            sinhVien.hienThiThongTin();
        }
    }

    public void xoaTheoMa() {
        System.out.println("------------------------------------");
        System.out.println("Nhap ma SV de xoa: ");
        String maXoa = s.nextLine();

        ArrayList<SinhVien> xoaSV = new ArrayList<>();

        listSV.forEach(sv -> {
            if (sv.getMa().equalsIgnoreCase(maXoa)) {
                xoaSV.add(sv);
            }
        });

        listSV.removeAll(xoaSV);
    }

    public void sapXepTangDan() {
        listSV.sort(Comparator.comparingInt(SinhVien::getNamSinh));
    }

    public void suaThongTin() {
        String maSua;
        boolean maTonTai = false;
        System.out.println("-------------------------------------------");
        do {
            System.out.println("Nhap ma SV can sua: ");
            maSua = s.nextLine();

            for (SinhVien sinhVien : listSV) {
                if (sinhVien.getMa().equalsIgnoreCase(maSua)) {
                    System.out.println("-----------------------------------------------");
                    System.out.println("Nhap ma cua SV moi: ");
                    String maMoi = s.nextLine();
                    System.out.println("Nhap diem cua SV moi: ");
                    double diemMoi = s.nextDouble();
                    s.nextLine();
                    System.out.println("Nhap nam sinh cua SV moi: ");
                    int namSinhMoi = s.nextInt();
                    s.nextLine();

                    sinhVien.setMa(maMoi);
                    sinhVien.setDiem(diemMoi);
                    sinhVien.setNamSinh(namSinhMoi);

                }
            }
            if (!maTonTai) {
                System.out.println("Ma SV khong ton tai. Vui long nhap lai.");
            }
        } while (!maTonTai);
    }

}
