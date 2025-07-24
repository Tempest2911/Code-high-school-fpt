import java.util.Scanner;

public class LuyThua {
    public static void main(String[] args) {
        float a,b;

        Scanner scanner = new Scanner(System.in);

        System.out.print("Nhap so a: ");
        a = scanner.nextFloat();

        System.out.print("Nhap so b: ");
        b = scanner.nextFloat();

        double x=Math.pow(a, b);
        float min=Math.min(a, b);
        float max=Math.max(a, b);

        System.out.println("total: " + x);
        System.out.println("Min: " + min);
        System.out.println("Max: " + max);
    }
}
