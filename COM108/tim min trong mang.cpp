#include <stdio.h>
int main(){
	int n=0;
	
	printf("Nhap n: ");
	scanf("%d",&n);
	
	int a[n];
	int i=0;
	for(i=0;i<n;i++){
		printf("Nhap a[%d]:", i);
		scanf("%d",&a[i]);
	}
	
	int min=a[0];
	for(i=0;i<n;i++){
		if(a[i]<min){
			min = a[i];
		}
	}
	printf("So min torng mang la: %d",min);
}
