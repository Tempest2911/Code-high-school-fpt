#include <stdio.h>
#include <math.h>
int main(){
	double a, b, c, delta, x1, x2;
	
	printf("Nhap so a: ");
	scanf("%lf", &a);
	
	printf("Nhap so b: ");
	scanf("%lf", &b);
	
	printf("Nhap so c: ");
	scanf("%lf", &c);
	
	if(a == 0){
		if(b == 0){
			if(c == 0){
				printf("pt VSN");
			}else{
			
				printf("PT VN");
			}
		}else{
			printf("PT co nghiem: x= %.3f", (float) -c/b);
		}
	}else{
	
	delta = b * b - 4 * a * c;
	 
	if(delta > 0){
		x1 = (-b + sqrt(delta)) / (2 * a);
        x2 = (-b - sqrt(delta)) / (2 * a);
		printf("Phuong trinh co 2 nghiem thuc: x1 = %lf, x2 = %lf\n", x1, x2);
	}else if(delta == 0){
			x1 = -b/2*a;
		printf("Phuong trinh co nghiem kep la: %lf\n", x1);
	}else{
		printf("Phuong trinh vo nghiem");
	}
}
	return 0;
}
