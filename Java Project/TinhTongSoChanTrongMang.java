import java.util.Arrays;
import java.util.Scanner;

public class TinhTongSoChanTrongMang {
    public static void main(String[] args) {
        int n[] = { 3, 6, 8, 5, 9, 12, 53, 56, 78 };
        int sum = 0;

        Scanner scanner = new Scanner(System.in);

        for (int i = 0; i < n.length; i++) {
            if (n[i] % 2 == 0) {
                sum = sum + n[i];
            }
        }
        System.out.println("Tong cac so chan la: " + sum);

        int max = n[0];
        for (int i = 0; i < n.length; i++) {
            if (max < n[i]) {
                max = n[i];
            }
        }
        System.out.println("Max trong mang la: " + max);

        int min = n[0];
        for (int i = 0; i < n.length; i++) {
            if (min > n[i]) {
                min = n[i];
            }
        }
        System.out.println("Max trong mang la: " + min);

        System.out.println("Mang goc" + Arrays.toString(n));
    }
}
