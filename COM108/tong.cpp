#include <stdio.h>
int main(){
	int n;
	do{
	printf("\n1.Tinh tong\n");
	printf("2.Tinh hieu\n");
	printf("0.End game\n");
	printf("Nhap so n: ");
	scanf("%d",&n);
	int a=0;
	int b=0;
	
	switch(n){
		case 1:
			printf("Nhap so a: ");
			scanf("%d",&a);
			printf("Nhap so b: ");
			scanf("%d",&b);
			printf("Tong la: %d\n", a+b);
			break;
		case 2:
			printf("Nhap so a: ");
			scanf("%d",&a);
			printf("Nhap so b: ");
			scanf("%d",&b);
			printf("Hieu la: %d\n", a-b);
			break;
		case 0:
			printf("ket thuc chuong trinh");
			break;
		default:
		printf("ban da nhap sai");
	}
}while(n!=0);
return 0;
	
}
