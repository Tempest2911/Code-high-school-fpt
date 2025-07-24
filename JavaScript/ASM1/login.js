function subBtn() {
    var username = document.getElementById("username").value;
    var password = document.getElementById("password").value;

    if (username === "admin" && password === "123456") {
        // Chuyển hướng đến trang chính sau khi đăng nhập thành công
        window.location.href = "asm1_nologin.html";
    } else {
        alert("Sai tên đăng nhập hoặc mật khẩu.");
    }
}

// let sliderImg = document.getElementById("sliderImg");
// let image = ["image/wallhaven-k9lekd.jpg", "image/banner2", "image/banner3"];
// let i =0;
// let timeImg = setInterval(changeImg,2000);

// function changeImg() {
//     if(i >= image.length -1) i= -1;
//     i++;
//     return setImg();
// }

// function setImg(){
//     return sliderImg.setAttribute('src', ''+image[i]);
// }

let sliderImg = document.getElementById("sliderImg");
let image = ["wallhaven-k9lekd.jpg", "banner2.jpg", "wallhaven-85pz2y.png", "wallhaven-968k1d.jpg", "wallhaven-2yeem9.png", "bhanner.png"];
let i =0;
let timeImg = setInterval(changeImg,2000);

function changeImg() {
    if(i >= image.length -1) i= -1;
    i++;
    return setImg();
}

function setImg(){
    return sliderImg.setAttribute('src', 'image/'+image[i]);
}