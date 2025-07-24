#include <stdio.h>

int is_prime(int num) {
    if (num < 2) return 0;  // False
    for (int i = 2; i * i <= num; i++)
        if (num % i == 0) return 0;  // False
    return 1;  // True
}

void find_primes(int arr[], int size) {
    printf("Cac so NT trong mang: ");
    for (int i = 0; i < size; i++)
        if (is_prime(arr[i])) printf("%d ", arr[i]);
    printf("\n");
}

int main() {
    int n;
    printf("Nhap kich thuoc mang: ");
    scanf("%d", &n);

    int a[n];
    printf("Nhap phan tu mang:\n");
    for (int i = 0; i < n; i++) {
        printf("Nhap phan tu %d: ", i);
        scanf("%d", &a[i]);
    }

    find_primes(a, n);

    return 0;
}

