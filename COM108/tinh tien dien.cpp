#include <stdio.h>

int main(){
	int a;
	printf("So Kwh su dung la: ");
	scanf("%d",&a);
	
	int b = a*1678;
	int c = b+(a-50)*1734;
	int d = c+(a-100)*2014;
	int x = d+(a-200)*2536;
	int y = x+(a-300)*2834;
	int z = y+(a-400)*2927; 
	if(a<=50){
		printf("Tien dien cua ban la: %d",b);
	}else if(a<=100){
		printf("Tien dien cua ban la: %d",c);
	}else if(a<=200){
		printf("Tien dien cua ban la: %d",d);
	}else if(a<=300){
		printf("Tien dien cua ban la: %d",x);
	}else if(a<=400){
		printf("Tien dien cua ban la: %d",y);
	}else if(a>400){
		printf("Tien dien cua ban la: %d",z);
	}
	
}
