#include <stdio.h>

int main(){
	int n = 0;
	int a;
	int b;
	printf("Nhap so KM: ");
	scanf("%d", &n);
	
	if(n<=1){
		printf("So tien can thanh toan la: 10000");
	}else if(n<=30){
		a=10000+(n-1)*13600;
		printf("So tien can thanh toan la: %d",a);
	}else if(n>=31){
		b=10000+29*13600+(n-30)*11000;
		printf("So tien can thanh toan la: %d",b);
	}
	return 0;
}
