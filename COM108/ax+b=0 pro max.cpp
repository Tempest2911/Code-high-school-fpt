#include<stdio.h>
int main(){
	float a,b,x;
	
	printf("Nhap so a: ");
	scanf("%f",&a);
	
	printf("Nhap so b: ");
	scanf("%f",&b);
	
	if(a!=0){
		x=-b/a;
		printf("PT co nghiem la: %f",x);
	}else if(a==0 && b==0){
		printf("PT co vo so nghiem");
	}else if(a==0 && b!=0){
		printf("PT vo nghiem");
	}
	return 0;
}
