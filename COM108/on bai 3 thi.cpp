#include<stdio.h>
void bai1(){
	char ten[20];
	int nam;
	int can;
	int tuoi;
	
	printf("\nNhap ho va ten: ");
	scanf("%s",&ten);
	printf("\nNhap nam sinh: ");
	scanf("%s",&nam);
	printf("\nNhap can nang: ");
	scanf("%d",&can);
	printf("\nNhap tuoi: ");
	scanf("%d",&tuoi);

	printf("\nHo va ten la: %s",ten);
	printf("\nNam sinh la: %s",nam);
	printf("\nCan nang la: %d",can);
	printf("\nTuoi la: %d",tuoi);
}

void bai2(){
	int n;
	printf("\nNhap so n: ");
	scanf("%d",&n);
	int i;
	float sum=0;
	for(i=1;i<=n;i++){
		if(i%2!=0){
			sum=sum+i;
		}
	}
	printf("\nTong cac so le la: %f",sum);
	
	int max;
	for(i=1;i<=n;i++){
		
		if(i>max && i%2==0){
			max=i;
		}
	}
	printf("\nSo chan lon nhat la: %d",max);
}


void bai3(){
	int i;
	int n;
	printf("\nNhap so luong vacine: ");
	scanf("%d",&n);
	int a[n];
	for(i=0;i<n;i++){
		printf("\nThoi gian hieu qua cua vacine %d la: ",i);
		scanf("%d",&a[i]);
	}
		printf("\nSo luong vacine la: %d",n);
		
		int min=a[0];
		for(i=0;i<n;i++){
			if(a[i]<min){
				min=a[i];
			}
		}
		printf("\nThoi gian hieu qua vacine nho nhat la: %d",min);
	
		int max=a[0];
		for(i=0;i<n;i++){
			if(a[i]>max){
				max=a[i];
			}
		}
		printf("\nThoi gian hieu qua vacine cao nhat la: %d",max);
}

int main(){
	int n;
	do{

	printf("\n1.Thong tin nguoi yeu cu");
	printf("\n2.Ting tong so le tu 1 den n");
	printf("\n3.Thong tin vacine");
	printf("\n0.Thoat");
	
	printf("\nNhap so menu: ");
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
			printf("\nThoat chuong trinh");
		break;
		default:{
			printf("\nVui long nhap lai");
			break;
		}
	}
}while(n!=0);
}
