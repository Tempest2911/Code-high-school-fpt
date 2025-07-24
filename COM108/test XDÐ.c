#include <stdio.h>
#include <math.h>
int main(){
	double a, b, c, delta, x1, x2;
	
	printf(" Nhap so a: ");
	scanf("%lf", &a);
	
	printf(" Nhap so b: ");
	scanf("%lf", &b);
	
	printf(" Nhap so c: ");
	scanf("%lf", &c);
	
	delta = b * b - 4 * a * c;
	
	if(delta > 0){
		x1 = (-b + sqrt(delta) / 2 * a);
		x2 = (-b - sqrt(delta) / 2 * a);
		printf("Phuong trinh co 2 nghiem phan biet la: x1= %lf, x2= %lf", x1, x2);
	}else if(delta == 0){
		x1 = -b / 2 * a;
		printf("phuong trinh co nghiem kep la: %lf", x1);
	}else{
		printf("Phuong trinh vo nghiem");
	}
	return 0;
}
