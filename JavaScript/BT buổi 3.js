
let n = parseInt(prompt(`Nhap so: 
                    1.Bảng cửu chương
                    2.Thông tin
                    3.Thoát`));


switch (n) {
    case 1:
        let so = prompt(`Nhap so muon hien: `);
        let tinh;
        for (let i = 0; i <= 10; i++) {
            tinh = i * so;
            console.log(`${so} * ${i} = ${tinh}`);
        }
        break;

    case 2:
        let obj = {};

        obj.name = prompt(`.Nhập họ tên: `);
        obj.DOB = prompt(`.Nhập ngày sinh: `);
        obj.que = prompt(`.Nhập quê quán: `);
        obj.phong = prompt(`.Nhập phòng ban: `);
        obj.luong = prompt(`.Nhập Lương: `);

        console.log(obj);

        if (obj.luong > 5000) {
            console.log(`Lương cao`);
        } else if (obj.luong > 3000) {
            console.log(`Lương bình thường`);
        } else if (obj.luong < 3000) {
            console.log(`Lương thấp`);
        }

        break;

    case 3:
        console.log(`Thoat chuong trình`);
        break;
    default:
        break;
}
