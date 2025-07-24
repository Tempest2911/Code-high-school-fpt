#include<stdio.h>
int main(){
	int a = 10;
	int b = 9;
	printf("So cua a la: %d",a);
	printf("\nSo cua b la: %d",b);
	printf("\n%d", a/b*(a+b));
	printf("\n%d", a%b+a-b*(a/b-a+a*b)+a*a*a*a*a*a*a*a);
	printf("\n%d", a%b*a-b+a*b/a);
	printf("\n%d", a+a*b);
	printf("\n%d", (a+a)*b);
	float x =(float) a/b; // phep chia a cho b phai ep kieu ve float
	double y =(double) (a+b)/a;
	float z =(float) (a+b*a)/(a+b);
	printf("\na/b= %f",x);
	printf("\n%f", y);
	printf("\n%f", z);
	int tuoi=0;
	printf("\nMoi ban nhap tuoi: ");
	scanf("\n%d", &tuoi);
	printf("\nTuoi vua nhap la: %d", tuoi);
	
	float diem=0;
	printf("\n Moi ban nhap diem: ");
	scanf("%f", &diem);
	printf("\n Diem vua nhap la: %.f",diem);
	
	float a=0;
	printf("\n Nhap so a: ");
	scanf("%f", &a);
	printf("\n So a la: %f",a);
	
	float b=0;
	printf("\n Nhap so b: ");
	scanf("%f", &b);
	printf("\n So b la: %f",b);

	return 0;
}











