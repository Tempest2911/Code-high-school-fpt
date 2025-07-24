#include<stdio.h>
void bai1(){
	char ten[20];
	char sosv[10];
	int diem;
	
	printf("Nhap ten: ");
	scanf("%s",&ten);
	
	printf("Nhap ma so sinh vien: ");
	scanf("%s",&sosv);
	fflush(stdin);
	
	printf("Nhap diem: ");
	scanf("%d",&diem);
	fflush(stdin);
	
	printf("\nTen la: %s",ten);
	printf("\nMa so sinh vienla: TH%s",sosv);
	printf("\nDiem cua hs la: %d",diem);
}

void bai2(){
	int n;
	printf("Nhap so n: ");
	scanf("%d",&n);
	int i;
	int tong=0;
	for(i=1;i<=n;i++){
		tong= tong +i;
	}
	printf("Tong cac so tu 1 den n la: %d",tong);
	
	if(tong/2==0){
		printf("\nDay la so chan");
	}else{
		printf("\nDay la so le");
	}
}

void bai3(){
	int n;
	printf("Nhap so luong tivi: ");
	scanf("%d",&n);
	int a[n];
	int i;
	int dem=0;
	int sum=0;
	for(i=0;i<n;i++){
		printf("Tivi %d co kich co la: ",i);
		scanf("%d",&a[i]);
		sum += a[i];
		dem++;
	}	
	printf("\nSo luong cac tivi: %d",dem);
	double diemTB = (sum) / dem;
	printf("\nKich co TB tivi la: %lf",diemTB);
	
	int max = a[0];
	    for(int i = 0; i<n;i++){
	        if(a[i] > max) {
			max = a[i];
			}
	    }
	    printf("\nKich co Tivi lon nhat la: %d", max);
	
	printf("\nVi tri tivi lon nhat la: ");
	for(i=0;i<n;i++){
		if(a[i]==max){
			printf("%d",i);
		}
	}
	
}
int main(){
	int n;
	do{
	
	printf("\n+--------------------------Menu------------------------+");
	printf("\n\t\t1. Nh?p thông tin cá nhân");
	printf("\n\t\t2. Tính t?ng tu 1 den n");
	printf("\n\t\t3. Thong tin tivi");
	printf("\n\t\t0.Thoát");
	printf("\n+--------------------------------------------------------+");
	
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
			printf("Thoat chuong trinh");
		break;
	}
		
}while(n!=0);
	
}
