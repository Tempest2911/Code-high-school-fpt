#include <stdio.h>
int main(){

	int a=0;
    int b=0;
		printf("\nNhap gia tri a: ");
		scanf("%f", &a);
		
    	printf("\nNhap gia tri b: ");
		scanf("%f", &b);

	if(a==0){
		if(b==0){
			printf("vo so nghiem");
		}else{
		printf("vo nghiem");	
	}

	}else{
		printf("PT co nghiem la: %.2f", (float)-b/a);
	return 0;
	}
	
}
