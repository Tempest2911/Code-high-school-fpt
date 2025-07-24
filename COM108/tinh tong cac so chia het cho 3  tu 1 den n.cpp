#include <stdio.h>

int main() {
    int n;
    int sum = 0;
	int i=1;

    printf("Nhap vào so nguyen duong n: ");
    scanf("%d", &n);
    
    do{
    	printf("\nBan da nhap sai, vui long nhap lai\n");
    	printf("Nhap vao so nguyen duong n: ");
    	scanf("%d", &n);
	}while(n<=0);

	if(n>0){
    for ( i; i <= n; i++) {
        if (i % 3 == 0) {
            sum += i;
        }
    }
		printf("Tong cac so tu 1 den %d là: %d\n", n, sum);
	}
    return 0;
}

