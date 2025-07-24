#include <stdio.h>
	int main(){
		int n=0;
		printf("Nhap n: ");
		scanf("%d", &n);
		int a[n];
		int i;
		int dem;
		for(i=0;i<n;i++){
			printf("a[%d]: ",i);
			scanf("%d",&a[i]);
		}
		
		int j; 	
		for(i=1;i<n;i++){
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
