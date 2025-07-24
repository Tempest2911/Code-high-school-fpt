import java.util.Scanner;

public class TaoMenu2 {
    public static void main(String[] args) {
        class SinhVien {
            String ma, hoTen, diaChi, gioiTinh;
            int namSinh;
        
            public String toString() {
                return "SinhVien{" +
                        "ma='" + ma + '\'' +
                        ", hoTen='" + hoTen + '\'' +
                        ", diaChi='" + diaChi + '\'' +
                        ", gioiTinh='" + gioiTinh + '\'' +
                        ", namSinh=" + namSinh +
                        '}';
            }
        }
        
        class NhanVien {
            String ma, hoTen, diaChi, gioiTinh;
            int namSinh;
        
            public String toString() {
                return "NhanVien{" +
                        "ma='" + ma + '\'' +
                        ", hoTen='" + hoTen + '\'' +
                        ", diaChi='" + diaChi + '\'' +
                        ", gioiTinh='" + gioiTinh + '\'' +
                        ", namSinh=" + namSinh +
                        '}';
            }
        }
        
        class DongVat {
            String ma, ten, loai, gioiTinh;
            double canNang;
        
            public String toString() {
                return "DongVat{" +
                        "ma='" + ma + '\'' +
                        ", canNang=" + canNang +
                        ", ten='" + ten + '\'' +
                        ", loai='" + loai + '\'' +
                        ", gioiTinh='" + gioiTinh + '\'' +
                        '}';
            }
        }
        
        class KhachHang {
            String ma, ten, diaChi, gioiTinh, sdt;
        
            public String toString() {
                return "KhachHang{" +
                        "ma='" + ma + '\'' +
                        ", ten='" + ten + '\'' +
                        ", diaChi='" + diaChi + '\'' +
                        ", gioiTinh='" + gioiTinh + '\'' +
                        ", sdt='" + sdt + '\'' +
                        '}';
            }
        }
}
}

