#include<stdio.h>

void bai1(){
	char ma[20];
	char ten[20];
	int tuoi=0;
	float gia;
	
	printf("\nNhap ma thu cung: ");
	scanf("%s",&ma);
	
	printf("\nNhap ten thu cung: ");
	scanf("%s",&ten);
	
	printf("\nTuoi cua thu cung la: ");
	scanf("%d",&tuoi);
	
	printf("\nMa thu cung la: %s",ma);
	printf("\nTen thu cung la: %s",ten);
	printf("\nTuoi thu cung la: %d",tuoi);
	gia=tuoi*500;
	printf("\nGia tien cua thu cung: %.1f",gia);
}

void bai2(){
	int n;
	int i;
	float sum=0;
	int dem=0;
	int demchiaba=0;
	int demsochan=0;
	printf("\nNhap n: ");
	scanf("%d",&n);
	
	for(i=1;i<=n;i++){
		if(i%3==0){
		sum=sum+i;
	}
	}
	printf("\nTong cac so le tu 1 den n la: %.1f",sum);
	
	if(n%5==0){
		printf("\nN chia het cho 5");
	}else{
		printf("\nN ko chia het cho 5");
	}
	for(i=1;i<=n;i++){
	if(i%3==0){
		demchiaba++;
	}
}
	printf("\nSo luong nhung so chia het cho 3 la: %d",demchiaba);
	
	for(i=1;i<=n;i++){
	if(i%2==0){
		demsochan++;
		
	}
}
	printf("\nSo luong nhung so chan la: %d",demsochan);
}

void bai3(){
	int n;
	int i;
	int a[n];
	float dem=0;
	float tong=0;
	printf("\nNhap so luong thu cung: ");
	scanf("%d",&n);
	
	for(i=0;i<n;i++){
		printf("\nCan nang cua thu cung %d la: ",i);
		scanf("%d",&a[i]);
		tong=tong+a[i];
		dem++;
	}
	float TB= tong/dem;
	printf("\nCan nang TB la: %.1f",TB);
	
	int min=a[0];
	for(int i=0;i<n;i++){
		if(a[i]<min){
			min=a[i];
		}
	}
	printf("\nCan nang thap nhat la: %d",min);
	
	int demduoiTB=0;
	for(i=0;i<n;i++){
		if(a[i]<TB){
			demduoiTB++;
		}	
	}
	printf("\nSo thu cung co can nang duoi TB la: %d",demduoiTB);
	
	int demlonnhat=0;
	int max=a[0];
	for(int i=0;i<n;i++){
		if(a[i]>max){
			max=a[i];
			demlonnhat++;
		}else if(a[i]==max){
			demlonnhat++;
		}
		
		
	}
	printf("\nCan nang lon nhat la: %d",max);
	printf("\nSo luong thu cung co can nang lon nhat la: %d",demlonnhat);
}

int main(){
	int n;
	do{
	
		printf("\n+--------------------------Menu------------------------+");
		printf("\n\t\t1. Thong tin thu cung");
		printf("\n\t\t2. Tính tong");
		printf("\n\t\t3. Thong tin cua hang");
		printf("\n\t\t0.Thoát");
		printf("\n+--------------------------------------------------------+");
		
		printf("\nNhap n: ");
		scanf("%d",&n);
	
	switch(n){
		case 1:
			bai1();
		break;
		
		case 2:
			bai2();
		break;
		
		case 3:
			bai3();
		break;
		
		case 0:
			printf("Thoat chuong trinh");
		break;
		
		default:{
			printf("Ban da nhap sai, vui long nhap lai");
			break;
		}
	}
}while(n!=0);
}
