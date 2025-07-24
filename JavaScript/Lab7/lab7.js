function openYT() {
    let tuoi = prompt("Nhập tuổi để xác minh: ");
    if (tuoi >=18) {
        location.href = "https://www.youtube.com/";
    } else {
        alert("Bạn chưa đủ tuổi")
    }

}
function openFB() {
    let chuyen = confirm("Bạn chắc chắn muốn chuyển hướng");
    if (chuyen === true) {
        location.href = "https://www.facebook.com/";
    }else{
        alert("Dừng truy cập Facebook");
    }
    
}

function profile(){
    location.href = "https://ap.poly.edu.vn/sinh-vien/ho-so-ca-nhan";
}

let height = document.getElementById("height");
let width = document.getElementById("width")
let lang = document.getElementById("lang");
let link = document.getElementById("link");


document.getElementById("height").innerHTML = window.innerHeight;
document.getElementById("width").innerHTML = window.innerWidth;
document.getElementById("lang").innerHTML = navigator.language;
document.getElementById("link").innerHTML = location.href; 








