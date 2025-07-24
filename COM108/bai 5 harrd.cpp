#include <stdio.h>
int main(){
    // khai bao so nguyen n
    int n; 
    // khai bao sum
    float sum = 0; 
    // nhap du lieu gan vao bien n 
    printf("nhap n = ");
    scanf("%d", &n);
    //vong lap for bat dau tu i = 1 và ket thuc khi i <= n, moi lan lap i tang len 1
    for(int i = 1; i <= n; i++){
        // moi lan thuc hien vong lap bien sum se tan lên 1/i 
        sum += 1/i;//ép kieu float cho phép tinh 1/i vi la so nguyen chia so nguyen
    }
    // in ket qua ra man hinh    
    printf("S = %f", sum);
}
