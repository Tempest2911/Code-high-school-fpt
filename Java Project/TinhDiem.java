import java.util.Scanner;

public class TinhDiem {
    public static void main(String[] args) {
        int diem;

        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhap diem cua hoc sinh: ");
        diem = scanner.nextInt();

        if (diem >= 9) {
            System.out.print("Hoc luc cua hoc sinh la Gioi");
        } else if (diem >= 7) {
            System.out.println("Hoc luc cua hoc sinh la Kha");
        } else if (diem >= 5) {
            System.out.println("Hoc luc cua hoc sinh la TB");
        }
    }
}
