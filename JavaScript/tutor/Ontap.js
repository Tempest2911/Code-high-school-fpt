let slideImg = document.getElementById("images");
let images  = ["img/89ajod.gif", "img/584f8a13c1ff4c9b0b92e48795b229c4-removebg-preview.png", "img/4624a8236941a0adbec2a21180d0989c-removebg-preview.png", "img/60d3891be1db0a41088404ba4f4f1e08-removebg-preview.png","img/392e75be3bda93ed4babb7994452b0d8-removebg-preview.png"];
let i = 0;

let setTime = setInterval(changeImg, 1500);

function changeImg() {
    if (i >= images.length - 1) i = -1;
    i++;
    return setImg();
}

function setImg(){
    slideImg.setAttribute("src","" + images[i]);
}