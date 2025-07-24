#include <stdio.h>
#include <conio.h>
int main(){
	float d;
	printf("Nhap vao 1 diem cua hoc sinh: ");
	scanf("%f", &d);
	
	if(d>10){
		printf("Ban da nhap sai");
	} else if(d>=9){
		printf("Hoc luc xuat sac");
	}else if(d>=8){
		printf("Hoc luc gioi");
	}else if(d>=7){
		printf("Hoc luc kha");
	}else if(d>=5){
		printf("Hoc luc TB");
	}else if(d>=3.5){
		printf("Hoc luc yeu");
	}else{
		printf("Hoc luc kem");
	}
	
}

