#include <stdio.h>
#include <math.h>
int main(){
	int n;
	float sum1 =0;
	float sum2 =0;
	int i=1;
	printf("Nhap n: ");
	scanf("%d", &n);
	
	for(i; i<=n; i++){

	float	sum1 = (float)sum1 + i;
	float	sum2 = (float)sum2 + 1/sum1;
	}
	printf("Ket qua la: %f", sum2);
}
