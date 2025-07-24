#include <stdio.h>
#include <math.h>
void nhap1(){
	int a,b;
	
	printf("Nhap a: ");
	scanf("%d", &a);
	printf("Nhap b: ");
	scanf("%d", &b);
	
	(float) a+b;
	printf("%d + %d = %.2f",a,b,(float)a+b);
}

void nhap2(){
	
}
int main(){
	nhap1();
}
