#include <stdio.h>
#include <math.h>
int main(){
	int a=0;
	printf("Nhap so a: ");
	scanf("%d",&a);
	int b=0;
	printf("Nhap so b: ");
	scanf("%d",&b);
	int c=0;
	printf("Nhap so c: ");
	scanf("%d",&c); 
	int delta=(pow(b,2)-4*a*c);
	if (a==0){
		if (b==0){
			if (c==0){
				printf("phuong trinh vo so nghiem");
				}esle{
			    printf("phuong trinh vo nghiem");
			} 
		}esle{
		printf("x= %f",(float)-c/b);
		}
	}else{
	if(delta==0){
		printf("phuong trinh co 1 nghiem kep:%f",(float)-b/(2*a));
	}else{
		if(delta>0){
			printf("\nphuong trinh co 2 nghiem phan biet:");
			printf("\nx1:%lf",(-(double)b+sqrt(delta))/(2*a));
			printf("\nx2:%lf",(-(double)b-sqrt(delta))/(2*a));
		}else{
			if(delta<0){
				printf("phuong trinh ko co nghiem"); 
			} 
		} 
	} 
}
	
}
