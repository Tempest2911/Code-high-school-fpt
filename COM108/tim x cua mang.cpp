#include <stdio.h>
 int main(){
 	int n=0;
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
	int x=0;
	for(i=0;i<n;i++){
		if(a[i]==x){
			printf("\nDa tim thay x tai vi tri %d",i);
		}
	}
}
