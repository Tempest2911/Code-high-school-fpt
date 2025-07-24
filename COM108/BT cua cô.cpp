#include <stdio.h>
int main(){
	int a;
	int tuoi;
	printf("Nhap so: ");
	scanf("%d",&a);
	printf("\nTa co so: %d",a);
	
	printf("\nNhap tuoi: ");
	scanf("%d",&tuoi);
	printf("\nSo tuoi la: %d",tuoi);
	
//	int toan=0;
//	int ly=0;
//	int hoa=0;
	double toan,ly,hoa;
	printf("\nNhap diem toan: ");
	scanf("%lf",&toan);
	printf("Nhap diem ly: ");
	scanf("%lf",&ly);
	printf("Nhap hoa: ");
	scanf("%lf",&hoa);
	
	printf("\nDiem TB 3 mon la: %f",(float)(toan+ly+hoa)/3);
	
}
