#include <stdio.h>
void bai1(){
			char ten[20];
			int tuoi;
			char diachi[20];
			char kyhoc[20];
			char nganh[20];
			
			printf("Nhap ten: ");
			scanf("%s",&ten);
			
			printf("Nhap tuoi: ");
			scanf("%d",&tuoi);
			fflush(stdin);
			
			printf("Nhap dia chi: ");
			scanf("%s",&diachi);
			
			printf("Nhap ky hoc: ");
			scanf("%s",&kyhoc);
			
			printf("Nhap nganh: ");
			scanf("%s",&nganh);
			
			printf("Ten: %s",ten);
			printf("\nTuoi: %d",tuoi);
			printf("\nDia chi: %s",diachi);
			printf("\nKy hoc: %s",kyhoc);
			printf("\nChuyen nganh: %s",nganh);	
}
void bai2(){
	int tong;
	int n=0;
	printf("Nhap so n: ");
	scanf("%d",&n);
	int i;

	for(i=1;i<=n;i++){
		tong = tong+i;
		
	}
	printf("Tong cac so tu 1 den n la: %d",tong);
}
void bai3(){
	int n;
	printf("Nhap tong lop: ");
	scanf("%d",&n);
	int a[n];
	int i;
	int dem;
	int sum=0;
	for(i=0;i<n;i++){
		printf("Lop %d co so sinh vien la: ",i);
		scanf("%d",&a[i]);
		sum=sum+a[i];
	}
		printf("\nSo luong cac lop la: %d",sum);
		
		printf("\nVi tri lop hoc co so luong sinh vien nho hon 30 la: ");
		for(i=0;i<n;i++){
			if(a[i]<30){
				printf("\t%d",i);	
			}
}
		int min = a[0];
	    for(int i = 0; i<n;i++){
	        if(a[i] < min) {
			min = a[i];
			}
	    }
	    printf("\nLop co so sv nho nhat la: %d", min);
		
}
int main(){
	int n;
	do{
	
	printf("\n1. Nhap thong tin ca nhan");
	printf("\n2. Tinh tong ");
	printf("\n3. Thong tin sinh vien cac lop");
	printf("\n0.Thoat");
	printf("\nNhap so menu: ");
	scanf("%d",&n);
	
	switch(n){
		case 0:
			printf("Thoat chuong trinh");
		break;
		case 1:
			bai1();
		break;
		case 2:
			bai2();
		break;
		case 3:
			bai3();
		break;
		
		default:
			printf("\nBan da nhap sai so");
		break;
	}
}while(n!=0);
}
