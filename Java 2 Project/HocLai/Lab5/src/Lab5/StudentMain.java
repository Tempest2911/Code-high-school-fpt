/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Lab5;

/**
 *
 * @author drago
 */
public class StudentMain {
     public static void main(String[] args) {
        XFile manager = new XFile();
        manager.listHS.add(new Student("GRRRRRRR", 5, "CNTT"));
        manager.listHS.add(new Student("LALAL", 7.5, "TKTW"));
        manager.listHS.add(new Student("Bình", 8.5, "CNTT"));

        manager.ghiFile(); // Ghi file
        manager.docFile(); // Đọc file

        // Kiểm tra danh sách sau khi đọc
        for (Student s : manager.listHS) {
            System.out.println(s);
        }
    }
}
