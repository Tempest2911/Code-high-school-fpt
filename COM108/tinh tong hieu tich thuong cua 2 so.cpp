#include <stdio.h>
int main(){
	
	double a,b;
	
	printf("Nhap so thu nhat: ");
	scanf("%lf",&a);
	printf("Nhap so thu hai: ");
	scanf("%lf",&b);
		
	printf("Tong cua 2 so la: %f",(float)a+b);
	printf("\nHieu cua 2 so la: %f",(float)a-b);
	printf("\nTich cua 2 so la: %f",(float)a*b);
	printf("\nThuong cua 2 so la: %f",(float)a/b);
	
}
