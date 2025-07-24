/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package baitaphoclai1;

import java.util.*;

/**
 *
 * @author drago
 */
public class BaiTapHocLai1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Integer> list = new ArrayList<>();

        // 1. Nhập danh sách số nguyên (số lượng tùy ý)
        System.out.println("Nhập danh sách số nguyên (cách nhau bởi dấu cách), nhấn Enter để hoàn tất:");

        String inputLine = scanner.nextLine();
        String[] numbers = inputLine.split("\\s+"); // Tách các số dựa trên dấu cách

        try {
            for (String num : numbers) {
                list.add(Integer.parseInt(num));
            }
        } catch (NumberFormatException e) {
            System.out.println("Lỗi: Vui lòng chỉ nhập số nguyên!");
            return;
        }

        if (list.isEmpty()) {
            System.out.println("Danh sách rỗng! Kết thúc chương trình.");
            return;
        }

        System.out.println("Danh sách ban đầu: " + list);

        Collections.sort(list);
        System.out.println("Danh sách sau khi sắp xếp tăng dần: " + list);

        Collections.reverse(list);
        System.out.println("Danh sách sau khi đảo ngược: " + list);

        Collections.shuffle(list);
        System.out.println("Danh sách sau khi trộn ngẫu nhiên: " + list);

        Collections.rotate(list, 2);
        System.out.println("Danh sách sau khi xoay phải 2 vị trí: " + list);

        Collections.sort(list, Collections.reverseOrder());
        System.out.println("Danh sách sau khi sắp xếp giảm dần: " + list);

        Collections.sort(list);
        System.out.println("Danh sách sau khi sắp xếp lại để tìm kiếm: " + list);

        System.out.print("Nhập số cần tìm: ");
        if (scanner.hasNextInt()) {
            int key = scanner.nextInt();
            int index = Collections.binarySearch(list, key);
            if (index >= 0) {
                System.out.println("Số " + key + " được tìm thấy ở vị trí " + index);
            } else {
                System.out.println("Số " + key + " không tồn tại trong danh sách.");
            }
        } else {
            System.out.println("Lỗi: Bạn phải nhập một số nguyên!");
        }

    }

}
