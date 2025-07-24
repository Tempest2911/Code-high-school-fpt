#include<stdio.h>
#include<math.h>
void bai1(char monhoc[20],char mamon[20],int tinchi=0){
	printf("\nMon hoc: %s",monhoc);
	printf("\nMa mon: %s",mamon);
	
	float hocphi= tinchi*500;
	printf("\nHoc phi la: %f",hocphi);
}

void bai2(int n){
	int i;
    int tong = 0;
    for(i = 1; i <= n; i++) {
        if(i % 3 == 0) {
            tong = tong + i;
        }
    }
    printf("Tong cac so tu 1 den n la: %d\n", tong);
    
    if(tong % 2 == 0) {
        printf("Day la so chan\n");
    } else {
        printf("Day la so le\n");
    }

}

void bai3(int n){
	int i;
	
	int a[n];
	int sum=0;
	int dem=0;
	int demdiem=0;
	for(i=0;i<n;i++){
		printf("Diem cua hoc sinh %d: ",i);
		scanf("%d",&a[i]);
		sum=sum+a[i];
		dem++;
	}
	float TB=sum/dem;
	printf("\nDiem TB cua ca hs la: %f",TB);
	
	int max=a[0];
		for(i=0;i<n;i++){
			if(a[i]>max){
				max=a[i];
			}
		}
	printf("\nDiem cao nhat la: %d",max);
	
	for(i=0;i<n;i++){
		if(a[i]>5){
			demdiem++;
			
		}
	}
	printf("\nSo sinh vien ko co diem duoi 5 la: %d",demdiem);
}

int main(){
	int n;
	do{
		printf("\n+--------------------------Menu------------------------+");
		printf("\n\t\t1. Nh?p thông tin mon hoc");
		printf("\n\t\t2. Tính tong so le");
		printf("\n\t\t3. Thong tin diem lab");
		printf("\n\t\t0.Thoát");
		printf("\n+--------------------------------------------------------+");
	
	printf("\nMoi nhap so menu: ");
	scanf("%d",&n);
	
	switch(n){
		case 1:
 				char monhoc[20];
                char mamon[20];
                int tinchi;
				printf("Nhap mon hoc: ");
				scanf("%s",&monhoc);
				
				printf("\nNhap ma mon: ");
				scanf("%s",&mamon);
				
				printf("\nNhap so tin chi: ");
				scanf("%d",&tinchi);
				bai1(monhoc,mamon,tinchi);
		break;
		case 2:
			int n;
			printf("Nhap so n: ");
			scanf("%d",&n);
			bai2(n);
		break;
		
		case 3:
			
			int n;
			printf("Nhap so sinh vien: ");
			scanf("%d",&n);
			bai3(n);
		
		break;
		case 0:
			printf("Thoat chuong trinh");
		break;
		default:
			printf("\nVui long nhap lai, ban da nhap sai");
			
	}
}while(n!=0);
}

