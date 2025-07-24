#include <stdio.h>
void yeucau1(){
	for(int i=1;i<=10;i++){
		printf("%d\t",i);
	}
}
int main(){
	
	int n;
	do{
		printf("Nhap so: ");
		scanf("%d",&n);
	}while(n>6);
	
	switch(n){
		case 1:{
			yeucau1();
			break;
		}
	}
}
