#include <stdio.h>

int main() {
	int i;
	int n;
	int sum=0;
	int dem=0;
	printf("Nhap so n: ");
	scanf("%d", &n);
  for (i = 1; i <= n; i++) {
  	if(i%2 == 0){
    printf("%d\n", i);
    dem++;
    sum += i;
	}
}
printf("dem = %d\n", dem);
    printf("T?ng các s? ch?n t? 1 d?n %d là: %d\n", n, sum);
  printf("Tong cac so chan la: %f",(float) sum/dem);	
  return 0;
}
