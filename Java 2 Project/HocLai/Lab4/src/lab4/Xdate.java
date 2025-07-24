/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab4;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Lenovo
 */
public class Xdate {

    public static Date parse(String text, String pattern) throws RuntimeException {
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        formatter.setLenient(false); // Ngăn chặn các giá trị ngày không hợp lệ

        try {
            return formatter.parse(text);
        } catch (ParseException e) {
            throw new RuntimeException("Lỗi: Định dạng ngày không hợp lệ! Yêu cầu theo mẫu " + pattern);
        }
    }

    public static Date parse(String text) throws RuntimeException {
        return parse(text, "dd-MM-yyyy");
    }
}
