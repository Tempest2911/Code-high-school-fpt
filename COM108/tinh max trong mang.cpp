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
	
	int max = a[0];
    for(int i = 0; i<n;i++)
    {
        if(a[i] > max) {
		max = a[i];
		}
    }
    printf("\nPhan tu max la: %d", max);
}
