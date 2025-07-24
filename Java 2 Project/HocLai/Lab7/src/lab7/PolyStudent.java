/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab7;

import java.util.ArrayList;

/**
 *
 * @author PC
 */
public class PolyStudent {

    ArrayList<bai1> b1 = new ArrayList<>();
    public String fullname;
    public bai1.Career career;

    public void print() {
        System.out.println("FullName: " + this.fullname);
        switch (this.career) {
            case UDPM:
                System.out.println("Career: Ứng dụng phần mềm");
                break;
            case TKTW:
                System.out.println("Career: Thiết kế trang web");
                break;
            case LTDĐ:
                System.out.println("Career: Lập trình di động");
                break;
            case TKĐH:
                System.out.println("Career: Thiết kế đồ họa");
                break;
        }
    }

    public static void main(String[] args) {
        PolyStudent sv = new PolyStudent();
        sv.fullname = "Nguyen Duy Phong";
        sv.career = bai1.Career.valueOf("UDPM");
        sv.print();
    }
}
