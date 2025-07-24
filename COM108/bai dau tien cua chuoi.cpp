#include <stdio.h>
int main(){
	
	char hoten[20];
	printf("Nhap ten hoc sinh: ");
	gets(hoten);	
	
	char diachi[20];
	printf("Nhap dia chi: ");
	gets(diachi);	
	
	int diem=0;
	printf("Nhap diem: ");
	scanf("%d",&diem);	
	fflush(stdin); //xoa dau enter
	
	char lophoc[20];
	printf("Nhap lop hoc: ");
	gets(lophoc);	
	
	char monhoc[20];
	printf("Nhap mon hoc: ");
	gets(monhoc);
		
	printf("Ho va ten: %s\n",hoten);
	printf("Dia chi: %s\n",diachi);
	printf("Diem: %d\n",diem);
	printf("Lop hoc: %s\n",lophoc);
	printf("Mon hoc: %s\n",monhoc);
}
