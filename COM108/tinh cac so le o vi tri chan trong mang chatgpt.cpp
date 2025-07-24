#include <stdio.h>
#include <conio.h>
#include <stdlib.h>
#define MAX 100

void nhapmang(int a[], int &n);
void xuatmang(int a[], int n);
double TBChan(int a[], int n);

void main() {
    int a[MAX], n;
    nhapmang(a, n);
    printf("\nNoi dung cua mang");
    xuatmang(a, n);
    double kq = TBChan(a, n);
    printf("\nTrung binh cac phan tu chan: %8.2f", kq);
    printf("\nChuc cac ban hoc tot");
    getch();
}

void nhapmang(int a[], int &n) {
    do {
        printf("\nSo phan tu trong mang: ");
        scanf("%d", &n);
    } while (n <= 0 || n > 100);
    for (int i = 0; i < n; i++) {
        printf("\nSo phan tu a[%d] la: ", i);
        scanf("%d", &a[i]);
    }
}

void xuatmang(int a[], int n) {
    for (int i = 0; i < n; i++)
        printf("%4d", a[i]);
}

double TBChan(int a[], int n) {
    double avg, sum = 0;
    int dem = 0;
    for (int i = 0; i < n; i++) {
        if (a[i] % 2 == 0) {
            sum += a[i];
            dem++;
        }
    }
    avg = double(sum / dem);
    return avg;
}

