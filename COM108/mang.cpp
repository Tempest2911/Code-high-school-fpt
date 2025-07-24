#include <stdio.h>
 int main(){
 	int n=0;
 	int sum=0;
 	int dem=0;
 	printf("Nhap so cua mang: ");
 	scanf("%d",&n);
 	int a[n];
 	int i;
 	for(i=0;i<n;i++){
 		printf("Nhap a[%d]: ", i);
 		scanf("%d",&a[i]);
	}
	printf("Mang vua nhap la: ");
	for(i=0;i<n;i++){
		printf("%d\t",a[i]);
	}
	 
	printf("\nCac so chan la: ");
	for(i=0; i<n; i++){
		if(a[i]%2==0){
			sum=sum+a[i];
			dem++;
			printf("\t%d",a[i]);
		}
	}
	printf("\nTong cac so chan la: %d",sum);
	printf("\nTB cong cac so chan la: %f",(float)sum/dem);
	
	printf("\nVi tri cua cac so chan trong mang la: ");
	for(i=0; i<n; i++){
		if(a[i]%2==0){
			printf("\t%d",i);
		}
	}
	
	int max = a[0];
    for(int i = 0; i<n;i++){
        if(a[i] > max) {
		max = a[i];
		}
    }
    printf("\nPhan tu max la: %d", max);
	
	int min = a[0];
    for(int i = 0; i<n;i++)
    {
        if(a[i] < min) {
		min = a[i];
		}
    }
    printf("\nPhan tu min la: %d", min);


	int j;
	printf("\nSo nguyen to trong mang la: ");
	for(i=0; i<n; i++){
		int dem=0;
		for(j=1;j<a[i];j++){
			if(a[i]%j==0){
				dem++;
			}
		}
		
		if(dem==1){
		printf("%d\t",a[i]);
	}
	}
}
