#include <stdio.h>

int main() {
    int n;
    printf("Nhap so n: ");
    scanf("%d", &n);
    int i;
    int sum = 0;
    for (i = 1; i <= n; i++) {  // �i?u ki?n c?a v�ng l?p ch?nh s?a th�nh i <= n
        if (i != 7 && i % 2 != 0) {  // Th�m di?u ki?n i != 7 d? lo?i tr? s? 7
            sum = sum + i;
        }
    }
    printf("Tong so le tu 1 den n tru so 7 la: %d", sum);
    return 0;
}

