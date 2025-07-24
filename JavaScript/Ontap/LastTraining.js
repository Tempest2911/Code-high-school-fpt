let slide = document.getElementById("slide");
let img = ["img/dreamy.png", "img/lala.jpg", "img/rei.png", "img/doom.png", "img/capy.gif"];
let i = 0;
let playimg;

let play = document.getElementById("play");
play.addEventListener(`click`, function () {
    clearInterval(playimg);
    playimg = setInterval(changeImg, 1000)
    function changeImg() {
        slide.setAttribute('src', img[i]);

        i++;
        if (i >= img.length) i = 0;
    }
})

let stopp = document.getElementById("stop");
stopp.addEventListener('click', function () {
    clearInterval(playimg);
})

function Next(params) {
    slide.setAttribute('src', img[i]);
    i++;
    if (i >= img.length) i = 0;
}

function Pre() {
    i--;
    if (i < 0) i = img.length - 1;
    slide.setAttribute('src', img[i]);
}





// function checkname(params) {
//     let name = document.getElementById("name").value;
//     let errname = document.getElementById("errname");

//     if (name === '') {
//         errname.innerHTML = 'Không được để trống'
//     } else {
//         errname.innerHTML = '';
//     }

// }

// function checkaddress() {
//     let address = document.getElementById("address").value;
//     let erraddress = document.getElementById("erraddress");

//     if (address === '') {
//         erraddress.innerHTML = 'Không được để trống'
//     } else {
//         erraddress.innerHTML = '';
//     }
// }

// let radios = document.querySelectorAll('input[type="radio"]');

// radios.forEach(radio => {
//     radio.addEventListener('mousedown', function () {
//         // Nếu nút radio đã được chọn, hủy chọn trước khi click xảy ra
//         if (this.checked) {
//             this.previousValue = true; // Lưu trạng thái đã chọn
//         } else {
//             this.previousValue = false; // Lưu trạng thái chưa chọn
//         }
//     });

//     radio.addEventListener('click', function (e) {
//         // Nếu trước đó đã chọn, hủy chọn sau khi click
//         if (this.previousValue) {
//             this.checked = false;
//         }
//     });
// });

// function validategd() {
//     let radios = document.querySelectorAll('input[name="gd"]:checked');
//     let errradio = document.getElementById("errradio");

//     if (radios.length === 0) {
//         errradio.innerHTML = 'Cần chọn'
//         return false;
//     } else {
//         errradio.innerHTML = '';
//         return true;
//     }
// }


// function checktel() {
//     let dien = document.getElementById("phone").value;
//     let errtel = document.getElementById("errtel");
//     let phonepattern = /^[1-9]\d*$/

//     if (dien === '') {
//         errtel.innerHTML = 'Không được bỏ trống';
//         return false;
//     } else if (!phonepattern.test(dien)) {
//         errtel.innerHTML = 'Số điện không hợp lệ và phải >1';
//         return false;
//     } else {
//         errtel.innerHTML = '';
//         return true;
//     }                                                                        
// }


// function tinh() {
//         let ketqua = document.getElementById("ketqua");

//         let name = document.getElementById("name").value;
//         let address = document.getElementById("address").value;
//         let radios = document.querySelector('input[name="gd"]:checked').value;
//         let dien = parseInt(document.getElementById("phone").value);
//         let price;
//         let total;


//             if (radios === 'Hộ Kinh Doanh') {
//                 price = 3000;
//             } else if(radios === 'Nhà ở') {
//                 price = 2000;
//             }

//             total = price*dien;

//         ketqua.innerHTML = `<p>Tên chủ hộ: ${name}</p>
//         <p>Địa chỉ: ${address}</p>
//         <p>Hộ gia đình: ${radios}</p>
//         <p>Số điện: ${dien}</p>
//         <p>Giá tiền điện: ${total}</p>`;
// }






// function checkname(params) {
//     let name = document.getElementById("name").value;
//     let errname = document.getElementById("errname");

//     if (name === '') {
//         errname.innerHTML = "Ko đc để trống";
//         return false;
//     } else {
//         errname.innerHTML = "";
//         return true;
//     }
// }

// function checktel(params) {
//     let phone = document.getElementById("phone").value;
//     let errtel = document.getElementById("errtel");
//     let phonepattern = /^[0-9]{10,11}$/;

//     if (phone === '') {
//         errtel.innerHTML="Ko đc để trống";
//         return false;
//     } else if(!phonepattern.test(phone)){
//         errtel.innerHTML ="Không đúng định dạng tel";
//         return false;
//     }else{
//         errtel.innerHTML = "";
//         return true;
//     }
// }

// function checkrank(params) {
//     let rank = document.getElementById("rank").value;
//     let errrank = document.getElementById("errrank");

//     if (rank = '') {
//         errrank.innerHTML = 'Bọn cần phải chọn';
//     } else {
//         errrank.innerHTML = '';
//     }

// }

// function Check() {
//     let ketqua = document.getElementById("ketqua");
//     let name = document.getElementById("name").value;
//     let phone = document.getElementById("phone").value;
//     let rank = document.getElementById("rank").value;
//     let alll;

//     let phonepattern = /^[0-9]{10,11}$/;

//     if (rank === 'Top 100') {
//         alll = 'Bạn đủ điều kiện miễn thi';
//     } else if(rank === 'Top 200') {
//         alll = 'Vui lòng đăng kí lớp học';
//     }else{
//         alll = 'Cố lên nha!!'
//     }

//     if (name === '' || phone === '' || rank === '') {
//         alert("Phải điền tất cả")
//     } else if(!phonepattern.test(phone)){
//         alert("Phải đúng định dạng phone")
//     }else{
//         ketqua=alert(`Name: ${name} 
// Phone: ${phone} 
// Rank: ${rank}
// ${alll}`);
//     }   
//     }






// function showprice(params) {
//     let car = document.getElementById("car").value;
//     let price = document.getElementById("price");

//     if (car === 'Xe máy') {
//         price.innerHTML = '100000/ngày';
//     } else if (car === 'Ô tô 4 chỗ') {
//         price.innerHTML = '500000/ngày';
//     } else if (car === 'Ô tô 7 chỗ') {
//         price.innerHTML = '700000/ngày';
//     }

// }
// function check(event) {
//     event.preventDefault();

//     let isvalid = true;

//     let name = document.getElementById("name").value;
//     let errname = document.getElementById("errname");
//     if (name === '') {
//         errname.innerHTML = 'Ko đc để trống';
//         isvalid = false;
//     } else {
//         errname.innerHTML = '';
//         isvalid;
//     }


//     let address = document.getElementById("address").value;
//     let erraddress = document.getElementById("erraddress");
//     if (address === '') {
//         erraddress.innerHTML = 'Ko đc trống';
//         isvalid = false;
//     } else {
//         erraddress.innerHTML = '';
//         isvalid;
//     }


//     let email = document.getElementById("email").value;
//     let erremail = document.getElementById("erremail");
//     let emailpattern = /^\w+@\w+\.\w+$/i;

//     if (email === '') {
//         erremail.innerHTML = 'Không đc trống';
//         isvalid = false;
//     } else if (!emailpattern.test(email)) {
//         erremail.innerHTML = 'Phải đúng định dạng Email';
//         isvalid = false;
//     } else {
//         erremail.innerHTML = '';
//         isvalid;
//     }

//     let total = document.getElementById("total");
//     let time = document.getElementById("time").value;
//     let car = document.getElementById("car").value;
//     let price = document.getElementById("price");

//     if (car === 'Xe máy') {
//         price = 100000;
//     } else if (car === 'Ô tô 4 chỗ') {
//         price = 500000;
//     } else if (car === 'Ô tô 7 chỗ') {
//         price = 700000;
//     }


//     if (name === '' || address === '' || email === '') {
//         alert('Phải điền hết thông tin');
//     } else if (!emailpattern.test(email)) {
//         alert('Sai Email')
//     } else {
//         console.log(price);
//         console.log(time);
//         total.innerHTML = `Tong tien: ${price * time}`;

//     }
// }






let radios = document.querySelectorAll('input[type="radio"]');

radios.forEach(radio => {
    radio.addEventListener('mousedown', function () {
        // Nếu nút radio đã được chọn, hủy chọn trước khi click xảy ra
        if (this.checked) {
            this.previousValue = true; // Lưu trạng thái đã chọn
        } else {
            this.previousValue = false; // Lưu trạng thái chưa chọn
        }
    });

    radio.addEventListener('click', function (e) {
        // Nếu trước đó đã chọn, hủy chọn sau khi click
        if (this.previousValue) {
            this.checked = false;
        }
    });
});

function upgradesiso() {
    let lop = document.getElementById('lophoc').value;
    let siso = document.getElementById("siso");

    if (lop === 'SD1801') {
        siso.value = 31;
    }else if(lop === 'SD1802'){
        siso.value = 30;
    }
}

function checkphat() {
    let radios = document.querySelectorAll('input[name="phatt"]:checked');
    let errphat = document.getElementById("errphat");
    

    if (radios.length ===0) {
        errphat.innerHTML = 'Cần chọn'
    } else {
        errphat.innerHTML = '';
    }

    
}

function upgradephat(params) {
    let phat1 = document.getElementById("phat1").checked;
    let phat2 = document.getElementById("phat2").checked;
    let action = document.getElementById("action");


    if (phat1) {
        action.value = 'ádasd';
    } else if(phat2) {
        action.value = '123';
    }else if(!phat1 || !phat2){
        action.value = '';

    }

}


function phat(event) {
    event.preventDefault();

    let isvalid = true;

    let name = document.getElementById("name").value;
    let errname = document.getElementById("errname");

    if (name === '') {
       errname.innerHTML = "Ko đc trống" 
       isvalid = false;
    } else {
        errname.innerHTML = '';
        isvalid;
    }

    let lop = document.getElementById('lophoc').value;
    let siso = document.getElementById("siso");

    if (lop === 'SD1801') {
        siso.value = 31;
    }else if(lop === 'SD1802'){
        siso.value = 30;
    }


    let quantity = document.getElementById("quantity").value;
    let errquantity = document.getElementById("errquantity");
    let quantityPatterm = /^[0-9]\d*$/

    if (quantity === '') {
        errquantity.innerHTML = 'Ko đc trống'
    } else if(!quantityPatterm.test(quantity)){
        errquantity.innerHTML = 'Chỉ đc nhập số';
    }else if(quantity > siso.value){
        errquantity.innerHTML = 'Ko đc lớn hơn sĩ số'
    }else{
        errquantity.innerHTML = '';
    }


    let trangthai = document.getElementById("trangthai");
    let errtrangthai = document.getElementById("errtrangthai");

    trangthai.value = siso.value-quantity;
    
    if (trangthai.value <=0) {
        errtrangthai.innerHTML = 'Vui lòng kiểm tra lại'
    }else{
        errtrangthai.innerHTML = ''
    }

}





