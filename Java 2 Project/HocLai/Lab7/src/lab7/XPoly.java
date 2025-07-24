/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab7;

/**
 *
 * @author PC
 */
public class XPoly {
    @Deprecated(since = "1.2", forRemoval = false)
    public static boolean isCardNumber(String number) {
        // Loại bỏ khoảng trắng và ký tự không phải số
        String cleaned = number.replaceAll("[^0-9]", "");
        
        int sum = 0;
        for (int i = 0; i < cleaned.length(); i++) {
            char c = cleaned.charAt(i);
            sum += Character.getNumericValue(c);
        }
        
        return sum % 2 == 0;
    }
}
