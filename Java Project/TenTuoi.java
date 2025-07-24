import java.util.Scanner;

public class TenTuoi {
	public static void main(String args[]) {
		String hoten;
		String diachi;
		int tuoi;

		Scanner scanner = new Scanner(System.in);

		System.out.print("Nhap ho ten: ");
		hoten = scanner.nextLine();

		System.out.print("Nhap tuoi: ");
		tuoi = Integer.parseInt(scanner.nextLine());

		System.out.print("Nhap dia chi: ");
		diachi = scanner.nextLine();

		System.out.println("" + hoten + " nam nay " + "" + tuoi + " tuoi");
		System.out.println("Dia chi la: " + diachi);
	}
}