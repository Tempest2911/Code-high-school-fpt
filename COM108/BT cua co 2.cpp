#include <stdio.h>
int main(){
	double a,b;
	
	printf("Nhap chieu dai: ");
	scanf("%lf",&a);
	printf("Nhap chieu rong: ");
	scanf("%lf",&b);
	
	if(a==b){
		printf("%.1lf*%.1lf= %.1f",a,b,(float)a*b);
	}else if(a!=b){
		printf("Dien tich hinh chu nhat la: %.1lf*%.1lf= %.1f",a,b,(float)a*b);
	}
}
