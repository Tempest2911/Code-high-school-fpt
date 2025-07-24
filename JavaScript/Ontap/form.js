

function updateSiSo() {
    let lophoc = document.getElementById("lophoc").value;
    let siso = document.getElementById("siso");

    if (lophoc === `SD1801`) {
        siso.value = 31;
    }else if(lophoc === `SD1802`){
        siso.value = 30;
    }else{
        siso.value = '';
    }
}

function updateHanhDong() {
    let phat1 = document.getElementById("phat1");
    let phat2 = document.getElementById("phat2");
    let hanhdong = document.getElementById("hanhdong");

    if (phat1.checked) {
        hanhdong.value = 'Xem BTS 100 tiếng';
    } else if(phat2.checked){
        hanhdong.value = 'Trói lên cây';
    }else{
        hanhdong.value = '';
    }
}

