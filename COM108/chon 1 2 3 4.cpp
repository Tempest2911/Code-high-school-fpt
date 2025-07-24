#include <stdio.h>
#include <math.h>
int main(){
	int l;
	printf("Nhap vao chuong trinh: ");
	scanf("%d",&l);
	switch(l){
		case 1:
			printf("Ban da thoat khoi chuong trinh");
		break;
		
		case 2:
			float a, b, x;
			printf("Nhap gia tri a: ");
    		scanf("%f", &a);
    
			printf("\nNhap gia tri b: ");
			scanf("%f", &b);
	
    		x = -b / a;
    		printf("\n%.fx + %.f = 0 \nx = %.1f", a, b, x);
		break;
		
		case 3:
			double q, w, e, delta, x1, x2;

		    printf("Nhap he so a: ");
		    scanf("%lf", &q);
		
		    printf("Nhap he so b: ");
		    scanf("%lf", &w);
		
		    printf("Nhap he so c: ");
		    scanf("%lf", &e);
		
		    delta = w * w - 4 * q * e;
		
		    if (delta > 0) {

		        x1 = (-w + sqrt(delta)) / (2 * q);
		        x2 = (-w - sqrt(delta)) / (2 * q);
		        printf("Phuong trinh co 2 nghiem thuc: x1 = %lf, x2 = %lf\n", x1, x2);
		    } else if (delta == 0) {

		        x1 = -w / (2 * q);
		        printf("Phuong trinh co 1 nghiem kep: x = %lf\n", x1);
		    } else {

		        printf("Phuong trinh vo nghiem thuc\n");
		    }
		break;
		
		case 4:
			float d;
			printf("nhap so kWh ban da dung trong thang : ");
			scanf("%f", &d);
			if(d <= 50){ 
				float a = d+1678;
				printf("so tien dien ban dung la: %.1f", a);  
			}else if( d <= 100 ){
				float a = d+1678+(d-50)*1734;
				printf("so tien dien ban dung la: %.1f", a);
			}else if( d <= 200 ){
				float a = d+1678+d+50+1734+(d-100)*2014;
				printf("so tien dien ban dung la: %.1f", a);
			}else if( d <=300 ){
				float a = d+1678+d+50+1734+d+100+2014+(d-200)*2536;
				printf("so tien dien ban dung la: %.1f", a);
			}else if( d <= 400){
				float a = d+1678+d+50+1734+d+100+2014+d+200+2536+(d-300)*2834;
				printf("so tien dien ban dung la: %.1f", a);
			}else if(d >= 401){
				float a = d+1678+d+50+1734+d+100+2014+d+200+2536+d+300+2834+(d-400)*2927;
				printf("so tien dien ban dung la: %.1f", a);
			}
			break;
		
		case 5:
			float v;
			printf("Nhap vao 1 diem cua hoc sinh: ");
			scanf("%f", &v);
			
			if(v>10){
				printf("Ban da nhap sai");
			} else if(v>=9){
				printf("Hoc luc xuat sac");
			}else if(v>=8){
				printf("Hoc luc gioi");
			}else if(v>=7){
				printf("Hoc luc kha");
			}else if(v>=5){
				printf("Hoc luc TB");
			}else if(v>=3.5){
				printf("Hoc luc yeu");
			}else{
				printf("Hoc luc kem");
			}
		break;
	}
}

