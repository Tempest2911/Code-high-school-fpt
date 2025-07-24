import java.util.Scanner;

public class TinhThueThuNhap {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Nhap so thu nhap cua ban: ");
        double thuNhap = scanner.nextDouble();

        double thue = 0;

        if (thuNhap < 9000000) {
            thue = 0;
        } else if (thuNhap >= 9000000 && thuNhap <= 15000000) {
            thue = thuNhap * 10/100;
        } else if (thuNhap > 15000000 && thuNhap <= 30000000) {
            thue = thuNhap * 15/100;
        } else {
            thue = thuNhap * 20/100;
        }

        System.out.println("Thue thu nhap cua ban la: " + thue);

    }
}
