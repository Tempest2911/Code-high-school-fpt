#include <stdio.h>

int main() {
    int n;
    int sum = 0;

    printf("Nh?p v�o s? nguy�n duong n: ");
    scanf("%d", &n);

    // S? d?ng v�ng l?p for d? t�nh t?ng c�c s? ch?n t? 1 d?n n
    for (int i = 1; i <= n; i++) {
        if (i % 2 == 0) {
            sum += i;
        }
    }

    printf("T?ng c�c s? ch?n t? 1 d?n %d l�: %d\n", n, sum);

    return 0;
}

