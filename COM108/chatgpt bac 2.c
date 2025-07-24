#include <stdio.h>
#include <math.h>

int main() {
    // Khai b�o c�c bi?n
    double a, b, c, delta, x1, x2;

    // Nh?p c�c h? s? a, b, c t? ngu?i d�ng
    printf("Nhap he so a: ");
    scanf("%lf", &a);

    printf("Nhap he so b: ");
    scanf("%lf", &b);

    printf("Nhap he so c: ");
    scanf("%lf", &c);

    // T�nh n
    delta = b * b - 4 * a * c;

    // Ki?m tra gi� tr? c?a delta d? x�c d?nh s? nghi?m
    if (delta > 0) {
        // C� 2 nghi?m th?c
        x1 = (-b + sqrt(delta)) / (2 * a);
        x2 = (-b - sqrt(delta)) / (2 * a);
        printf("Phuong trinh co 2 nghiem thuc: x1 = %lf, x2 = %lf\n", x1, x2);
    } else if (delta == 0) {
        // C� 1 nghi?m th?c (nghi?m k�p)
        x1 = -b / (2 * a);
        printf("Phuong trinh co 1 nghiem kep: x = %lf\n", x1);
    } else {
        // Kh�ng c� nghi?m th?c
        printf("Phuong trinh vo nghiem thuc\n");
    }

    return 0;
}

