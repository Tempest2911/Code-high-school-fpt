import java.util.Scanner;

public class TenTuoiPro{
	public static void main(String args[]){
		String hoten;
		int tuoi;
		
		Scanner scanner = new Scanner(System.in);
		 
		System.out.print("Nhap ho ten: ");
		hoten = scanner.nextLine();

		System.out.print("Nhap tuoi: ");
		tuoi = scanner.nextInt();

		System.out.printf("%s nam nay %d tuoi ", hoten, tuoi);
		System.out.println("" + hoten + " nam nay " + tuoi + "tuoi");
		System.out.print("" + hoten + " nam nay " + tuoi + "tuoi");
	}
}