#include <stdio.h>
int main(){
	int n=0;
	int sum=0;
	printf("Nhap n: ");
	scanf("%d",&n);
	
	int a[n];
	int i;
	for(i=0;i<n;i++){
		printf("Nhap a[%d]: ",i);
		scanf("%d",&a[i]);
	}
	int tong = 0;
    int dem = 0;

    for (int i = 0; i < n; i++) {
        if (i % 2 == 0) {
        	if(a[i] % 2 != 0){
            tong += a[i];
            dem++;
        }
        }
    }
    
     if (dem > 0) {
        float trungBinhCong = (float)tong / dem;
        printf("Trung binh cong cua cac so le o vi tri chan la: %.2f\n", trungBinhCong);
    } else {
        printf("Khong co so le o vi tri chan trong mang.\n");
    }

    return 0;
}
