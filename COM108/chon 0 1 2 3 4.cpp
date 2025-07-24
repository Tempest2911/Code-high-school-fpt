#include <stdio.h>
#include <math.h>
int main(){
	int so;
	printf("Nhap so ban muon: ");
	scanf("%d", &so);
	
	switch(so){
		case 0:
			printf("Ban da thoat khoi chuong trinh");
		break;
		
		case 1:
			int a;
			int b;
			int tong;
			
			printf("Nhap a: ",a);
			scanf("%d",&a);
			printf("Nhap b: ",b);
			scanf("%d",&b);
			printf("%d + %d = %.1f", a,b,(float) a+b);
		break;
		
		case 2:
			{
			
			int n;
			int sum =0;
			int i;
			printf("Nhap n: ");
			scanf("%d", &n);
			
			for(i=1;i<=n;i++){
				if(i%2 == 0){
					sum += i;
				}
			}
			printf("tong cac so tu 1 den n la: %d", sum);
		}
		break;
		
		case 3:
			{
			int elec;
			printf("Nhap vao so dien: ",elec);
			scanf("%d", &elec);
			int a =elec*1000;
			int b= a+(elec-100)*1900;
			int c= b+(elec-300)*2500;
			
			if(elec<100){
				printf("so tien dien la: %.1d", a);
			}else if(elec>100){
				printf("So tien dien la: %d",b);
			}else if(elec>300){
				printf("So tien dien la: %d",c);
			}
		}
		break;
	}
}
