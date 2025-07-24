#include <stdio.h>
int main(){
	int n;
	
	printf("Nhap 1 so: ");
	scanf("%d",&n);
	
	if(n>0){
		printf("Day la so nguyen duong");
	}else if(n==0){
		printf("0 ko phai so nguyen duong ko phai so nguyen am");
	}else{
		printf("Day la so nguyen am");
	}

}
