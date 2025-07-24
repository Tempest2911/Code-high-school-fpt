#include<stdio.h>
int main(){
  int i, n;
  long S = 0, P = 1;
  do{
    printf("\nNhap vao so n: ");
    scanf("%d", &n);
  }while(n < 1);
  	for(int i = 1; i <= n; i ++){
    P = P * i;
	S= S+P;
  	}
  
  printf("\ntong cua bieu thuc: %ld",S);
 

}
