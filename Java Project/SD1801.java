import java.util.Scanner;

public class SD1801 {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
            
               String hoten;
               int tuoi;
               String diachi;

               System.out.print("Moi ban nhap ten :");
               hoten = scanner.nextLine();
              
               System.out.print("Moi ban nhap tuoi: ");

               tuoi = Integer.parseInt(scanner.nextLine());
               
               System.out.print("Moi ban nhap dia chi: ");
               diachi = scanner.nextLine();
               
               System.out.println("Ten vua nhap la: "+hoten);
               System.out.println("Tuoi cua ban la: "+tuoi);
               System.out.println("Dia chi cua ban la: "+diachi);
               
            
    
    }
}