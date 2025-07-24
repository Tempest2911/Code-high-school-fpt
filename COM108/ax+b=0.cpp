#include <stdio.h>

int main() {
    float a, b, x;
    
    printf("Nhap gia tri a: ");
    scanf("%f", &a);
    printf("\nSo a la: %.f\n",a);
    
	printf("\nNhap gia tri b: ");
	scanf("%f", &b);
	printf("\nSo b la: %.f\n",b);	
	
    x = -b / a;
    printf("\n%.fx + %.f = 0 \nx = %.1f", a, b, x);
    return 0;
}

