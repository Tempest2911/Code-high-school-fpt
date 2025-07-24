#include <stdio.h>
#include <math.h>
void nhap1(){
	int a,b;
	
	printf("Nhap a: ");
	scanf("%d", &a);
	printf("Nhap b: ");
	scanf("%d", &b);
	
	(float) a+b;
	printf("%d",a+b);
}

void nhap2(){
	float a, b, c, delta, x1, x2;
	
	printf("Nhap a: ");
	scanf("%f", &a);
	
	printf("Nhap b: ");
	scanf("%f", &b);
	
	printf("Nhap c: ");
	scanf("%f", &c);
	
	delta = b*b-4*a*c;
	
	if(delta>0){
		x1 = (-b + sqrt(delta)) / (2*a);
		x2 = (-b - sqrt(delta)) / (2*a);
		
		printf("PT co 2 nghiem phan biet: x1 = %f, x2 = %f", x1, x2);
	}else if(delta == 0){
		x1= -b/(2*a);
		printf("PT co nghiem kep la: %f", x1);
	}else{
		printf("Pt vo nghiem");
	}
}

void nhap3(){
	int x=0;
	int i=1;
	int tong =0;
	printf("Nhap x: ",x);
	scanf("%d",&x);
	for(i; i<=x; i++){
		if(i%2 == 0){
			tong +=i;
	
		}
	}
	printf("Tong cac so chia het cho 2 tu 1 den %d la: %d", x, tong);
}


int main(){
	
	
	int so;
	printf("1. Nhap a,b roi tinh tong\n");
	printf("2. Nhap a, b, c roi tinh pt bac 2\n");
	printf("3. Nhap x roi tinh tong cac so tu 1 den x\n");
	printf("Nhap so ban muon: ");
	scanf("%d", &so);
	
	switch(so){
		case 0:
			printf("Thoat chuong trinh");
		break;
		case 1: 
			nhap1();
		break;
		
		case 2:
			nhap2();
		break;
		
		case 3:
			nhap3();
		break;
			
		}
}
