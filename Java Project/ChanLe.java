import java.util.Scanner;

public class ChanLe {
    public static void main(String[] args) {
        Integer a;

        Scanner scanner = new Scanner(System.in);

        System.out.println("Nhap so: ");
        a = scanner.nextInt();

        if (a%2==0) {
            System.out.println("Day la so chan");
        } else {
            System.out.println("Day la so le");
        }
    }
}
