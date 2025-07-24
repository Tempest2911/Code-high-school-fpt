#include <stdio.h>
int main(){
	double a,b;
	
	printf("Nhap chieu dai: ");
	scanf("%lf",&a);
	printf("Nhap chieu rong: ");
	scanf("%lf",&b);
	
	printf("Chu vi hinh chu nhat la: %f",(float)2*(a+b));
	printf("\nDien tich hinh chu nhat la: %f",(float)a*b);
}
