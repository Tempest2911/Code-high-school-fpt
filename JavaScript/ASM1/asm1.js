function cart() {
    let soluong = prompt("Nhập số lượng bạn muốn: ");

    if (soluong <=0) {
        alert("Số lượng hàng phải lớn hơn hoặc bằng 1")
    } else {
        alert("Bạn đã cho vô giỏ hàng thành công")
    }
}

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