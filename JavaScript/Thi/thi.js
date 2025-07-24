let slide = document.getElementById("slide");
let img = ['image/doom.png', 'image/dreamy.png', 'image/lala.jpg', 'image/rei.png', 'image/capy.gif'];
let i = 0;

let chuyen = setInterval(changeimg, 1500);

function changeimg() {
    slide.setAttribute('src', img[i]);
    i++;
    if (i >= img.length) i = 0;
}

function doigia(params) {
    let items = document.getElementById("items").value;
    let price = document.getElementById("price");

    if (items === '1') {
        price.value = '500000';
    } else if (items === '2') {
        price.value = '300000';
    } else if (items === '3') {
        price.value = '100000';
    }
}

let radios = document.querySelectorAll('input[type="radio"]');
radios.forEach(radio => {
    radio.addEventListener('mousedown', function () {
        if (this.checked) {
            this.previousValue = true;
        } else {
            this.previousValue = false;
        }
    });

    radio.addEventListener('click', function (e) {
        if (this.previousValue) {
            this.checked = false;
        }
    });
});

function doiphi() {
    let nhan1 = document.getElementById("nhan1");
    let nhan2 = document.getElementById("nhan2");
    let ship = document.getElementById("ship");

    if (nhan1.checked) {
        ship.value = '0';
    } else if (nhan2.checked) {
        ship.value = '30000';
    }
}



function buy(event) {
    event.preventDefault();




    let name = document.getElementById("name").value;
    let errname = document.getElementById("errname");
    if (name === '') {
        errname.innerHTML = 'Ko đc bỏ trống';
    } else {
        errname.innerHTML = ''
    }

    let quanity = document.getElementById("quanity").value;
    let errquanity = document.getElementById("errquanity");
    let quanitypattern = /^[0-9]+\d*$/

    if (quanity === '') {
        errquanity.innerHTML = 'Ko đc bỏ trống số lượng'
    } else if (quanity <= 0) {
        errquanity.innerHTML = 'Số lượng phải lớn hơn 0'
    } else if (!quanitypattern.test(quanity)) {
        errquanity.innerHTML = 'Chỉ đc nhập số thôi!!!!!!!!'
    } else {
        errquanity.innerHTML = '';
    }



    let money = document.getElementById("money");
    let items = document.getElementById("items").value;
    let price = document.getElementById("price");
    let errmoney = document.getElementById("errmoney");

    if (items === '1') {
        price.value = '500000';
    } else if (items === '2') {
        price.value = '300000';
    } else if (items === '3') {
        price.value = '100000';
    }

    money.value = quanity * price.value;


    if (money.value < 0) {
        errmoney.innerHTML = 'Vui lòng kiểm tra lại'
    } else {
        errmoney.innerHTML = ''
    }


    let nhan1 = document.getElementById("nhan1");
    let nhan2 = document.getElementById("nhan2");
    let errnhan = document.getElementById("errnhan");

    if (!nhan1.checked && !nhan2.checked) {
        errnhan.innerHTML = 'Phải chọn nơi nhận';
    } else {
        errnhan.innerHTML = '';
    }
}




