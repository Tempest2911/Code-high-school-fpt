#include <stdio.h>

// Hàm ki?m tra nam nhu?n
int kTraNamNhuan(int nam) {
  return ((nam % 4 == 0 && nam % 100 != 0) || nam % 400 == 0);
}

// Hàm ki?m tra s? ngày c?a m?t tháng
int check(int thang, int nam) {
  switch (thang) {
    case 1:
    case 3:
    case 5:
    case 7:
    case 8:
    case 10:
    case 12:
      return 31;
    case 4:
    case 6:
    case 9:
    case 11:
      return 30;
    case 2:
      if (kTraNamNhuan(nam))
        return 29;
      else
        return 28;
    default:
      printf("S? tháng nh?p vào không h?p l?\n");
      break;
  }
}

int main() {
  int thang, nam;
  do {
    printf("\nNh?p tháng b?n c?n ki?m tra: ");
    scanf("%d", &thang);
    printf("Nh?p nam b?n c?n ki?m tra: ");
    scanf("%d", &nam);
  } while (nam < 0 || thang < 1 || thang > 12);
  printf("S? ngày trong tháng là: %d \n", check(thang, nam));
}

