#include <stdio.h>
int main(){
	int n;
	int a;
	int b;
	int c;
	
	printf("nhap so n: ");
	scanf("%d",&n);
	switch(n){
		case 1:
			printf("Tinh tong a+b \n");
			printf("nhap a: ");
			scanf("%d",&a);
			
			printf("\nnhap b: ");
			scanf("%d",&b);
				
			printf("a+b= %f", (float) a+b);
		break;
		
		case 2:
			printf("Giai pt ax+b=c");
			
			printf("\nNhap gia tri a: ");
    		scanf("%d", &a);
    
			printf("\nNhap gia tri b: ");
			scanf("%d", &b);
	
    		printf("ax+b=0 \nx= %f", (float) -b/a);
		break;
		
		case 3:
			printf("Tinh hieu");
			
			printf("\nnhap a: ");
			scanf("%d",&a);
			
			printf("\nnhap b: ");
			scanf("%d",&b);
				
			printf("a-b= %f", (float) a-b);
			
		break;
		
		default:
			printf("not a sigma");
	}
	return 0;
}
