#include <stdio.h>

int main()
{
    int n;
    printf("Nhap vao so can kiem tra: ");
    scanf("%d", &n);
    if (n % 2 == 0)
        printf("%d la so chan.\n", n);
    else
        printf("%d la so le.\n", n);
    return 0;
}
