#include <stdio.h>
#include <math.h>
int main() {
float a, b, c, delta, x1, x2;

printf("Nhap he so a: ");
scanf("%f", &a);

printf("Nhap he so b: ");
scanf("%f", &b);

printf("Nhap he so c: ");
scanf("%f", &c);

// Tinh delta	
 double z = pow(b,2);
delta = z - 4*a*c;

// Xac dinh loai nghiem
if (delta < 0) {
printf("Phuong trinh vo nghiem.");
} else if (delta == 0) {
x1 = -b / (2*a);
printf("Phuong trinh co nghiem kep x = %f", x1);
} else {
x1 = (-b + sqrt(delta)) / (2*a);
x2 = (-b - sqrt(delta)) / (2*a);
printf("Phuong trinh co hai nghiem phan biet x1 = %f, x2 = %f", x1, x2);
}

return 0;
}
