import java.util.Scanner;

public class MaxMin {
    public static void main(String[] args) {
        int a;
        int b;

        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhap so a: ");
        a = scanner.nextInt();
        System.out.println("Nhap so b: ");
        b = scanner.nextInt();

        int max = a > b ? a:b;
        int min = a <b ? a:b;

        System.out.println("\nMax la: " + max);
        System.out.println("\nMin la: "+min);
    }
}
