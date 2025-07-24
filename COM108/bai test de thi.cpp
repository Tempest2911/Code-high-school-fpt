#include <stdio.h>
void yeucau1(){
	char hoten[50];
	char diachi[50];
	int namsinh;
	char gioitinh[50];
	
	printf("Nhap ho va ten: ");
	scanf("%s",&hoten);
	
	printf("Nhap dia chi: ");
	scanf("%s",&diachi);
	
	printf("Nhap nam sinh: ");
	scanf("%d",&namsinh);
	
	printf("Nhap gioi tinh: ");
	scanf("%s",&gioitinh);
	
	printf("Ho va ten: %s",hoten);
	printf("Dia chi: %s",diachi);
	float tuoi = 2024-namsinh;
	printf("Tuoi: %f",tuoi);
	printf("Gioi tinh: %s",gioitinh);
}
void yeucau2(){
	double toan,van,anh;
	
	printf("Nhap diem toan: ");
	scanf("%lf",&toan);
	printf("Nhap diem van: ");
	scanf("%lf",&van);
	printf("Nhap diem anh: ");
	scanf("%lf",&anh);
	
	float tb = (toan+anh+van)/3;
	
	printf("Diem TB cua sinh vien la: %f",tb);
	
	if(8<tb && tb>=10){
		printf("\nHoc luc gioi");
	}else if(5<tb && tb<=8){
		printf("\nHoc luc kha");
	}else if(0<tb && tb<=5){
		printf("\nHoc luc trung binh");
	}
}
void yeucau3(){
	int n;
	printf("Nhap so nhan vien duoc thuong: ");
	scanf("%d",&n);
	int a[n];
	int i;
	for(i=0;i<n;i++){
		printf("Nhan vien %d: ",i);
		scanf("%d",&a[i]);
	}
	for(i=0;i<n;i++){
			float thuong = a[i]*15/100;
			printf("\nThuong cua nhan vien %d: %.0f", i,thuong);
	}
	
	float max=a[0];
	for(i=0;i<n;i++){
		if(a[i]>max){
			max=a[i];
		}
	}
		printf("\nNhan vien co luong cao nhat la: %f",max);
		printf("\nVi tri cua nhan vien co luong cao nhat: ");
	for(i=0;i<n;i++){
		if(a[i]==max){
			printf("%d",i);
		}
	}
	int dem=0;
	for(i=0;i<n;i++){
		if(a[i]>5000000){
			dem++;
		}
	}
	printf("\nSo nhan vien co muc luong tren 5.000.000 la: %d",dem);
	
	
	
	

}


int main(){

	int n;
	do{
	printf("Nhap so ban muon chon: ");
	scanf("%d",&n);
	switch(n){
		
		case 1:
			yeucau1();
		break;
		case 2:
			yeucau2();
		break;
		case 3:
			yeucau3();
		break;
		default{
			printf("Ban da nhap sai");
			break;
		}
	}	
	while(n>4);
}
}
