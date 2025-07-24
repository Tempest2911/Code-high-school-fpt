#include <stdio.h>

int main() {
    int n;
    int sum = 0;

    printf("Nh?p vào s? nguyên duong n: ");
    scanf("%d", &n);

    // S? d?ng vòng l?p for d? tính t?ng các s? ch?n t? 1 d?n n
    for (int i = 1; i <= n; i++) {
        if (i % 2 == 0) {
            sum += i;
        }
    }

    printf("T?ng các s? ch?n t? 1 d?n %d là: %d\n", n, sum);

    return 0;
}

