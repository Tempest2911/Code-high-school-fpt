//Add EventListener

// let displayBtn = document.getElementById(`display`);
// let text = document.getElementById(`text`);

// displayBtn.addEventListener(
//    'click', function() {
//     if (text.style.display === 'none') {
//         text.style.display = `block`;
//     } else {
//         text.style.display = `none`;
//     }
//    } 
// );

// let myImg = ["image/wallhaven-6d28zq.jpg","image/wallhaven-p955gm.jpg","image/wallhaven-p9vjq9.jpg","image/wallhaven-vqqzlp.png","image/wallhaven-yxgd3d.jpg"];
// console.log(myImg);
// let currIndex = 0; //đặt vị trí hiện tại = 0;

// //get quyền truy cập DOM của các element

// let slide = document.getElementById("slide"); //thẻ div chứa ảnh
// let firstImg = document.getElementById("firstImg"); //ảnh mặc định
// let preBtn = document.getElementById("pre"); //chuyển ảnh trc
// let nextBtn = document.getElementById("next"); //chuyển ảnh tiếp

// //hàm để hiện thị ảnh dựa theo index

// function showImage(index){
//     firstImg.src = myImg[index];
//     //tại index nào  thì thay đổi attribute src của thuộc tính đấy
// }

// function getDirection(currIndex, direction){
//     return(currIndex+direction+myImg.length);
//     //hàm xác định hướng đi của ảnh
// }

// let sliderImg = document.getElementById("query_Img")














let sliderImg = document.querySelector("#query_Img");
let image = ["image/wallhaven-6d28zq.jpg", "image/wallhaven-p955gm.jpg", "image/wallhaven-p9vjq9.jpg", "image/wallhaven-vqqzlp.png", "image/wallhaven-yxgd3d.jpg", "image/red.png", "image/wallhaven-6d28zq.jpg"];
let i = 0;
let setTime;


function pre() {
    if (i <= 0) i = image.length;
    i--;
    return setImg();
}

function next() {
    if (i >= image.length - 1) i = 0;
    i++;
    return setImg();
}

function setImg() {
    return sliderImg.setAttribute('src', "" + image[i]);
}

let btn = document.getElementById("play");
btn.addEventListener('click', function () {
        clearInterval(setTime);
        setTime = setInterval(changeImg, 2000);
        function changeImg() {
            if (i >= image.length - 1) i = 0;
            i++;
            return setImg();
        }
})

let resume = document.getElementById("stop");
resume.addEventListener('click', function () {
    clearInterval(setTime);
})

