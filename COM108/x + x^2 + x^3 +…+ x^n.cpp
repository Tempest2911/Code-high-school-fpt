#include<stdio.h>
#include <math.h>
int main(){
	int x=0;
	int a=1;
	int b=0;
	double tong =0;
	 
	printf("Nhap x: ");
	scanf("%d", &x);
	printf("Nhap b: ");
	scanf("%d", &b);
	for(a; a<=b; a++){
		double c = pow(x,a);
		tong = tong + c;
		
	}
	printf("Ket qua la: %.1lf", tong);
}
