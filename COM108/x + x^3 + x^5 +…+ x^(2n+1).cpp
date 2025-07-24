#include <stdio.h>
#include <math.h>
int main(){
	int x=0;
	int a=0;
	int b=0;
	double tong=0;
	printf("Nhap x: ");
	scanf("%d", &x);
	printf("Nhap b: ");
	scanf("%d", &b);


	for(a = 0; a<=b; a++){
		double c = pow(x,(2*a+1));
		tong = tong +c;

}
	printf("Ket qua la: %.1lf", tong);
}
