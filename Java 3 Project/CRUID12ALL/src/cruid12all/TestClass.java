/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cruid12all;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author drago
 */
public class TestClass {

    public static void main(String[] args) {

        String Quary1 = "select * from Sinhvien";
        String Quary2 = "delete from Sinhvien where ten like ?";
        String Quary3 = "Insert into SinhVien (MaSV, Ten, Tuoi, DiaChi) values (?, ?, ?, ?)";
        List<Object> params1 = new ArrayList<>();// ko co tham so
        List<Object> params2 = new ArrayList<>();
        List<Object> params3 = new ArrayList<>();
        params3.add("th04433");
        params3.add("PhongND");
        params3.add(22);
        params3.add("dong anh");

        CRUD_DAO.excutequery(Quary1, params1);
    }
}
