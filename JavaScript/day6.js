// //Truy xuất html element bằng ID
// let haha = document.getElementById(`hello`).innerHTML;
// console.log(haha);
// //Truy xuất html element bằng class
// let classElements = document.getElementsByClassName(`classname`);
// console.log(classElements);
// //Truy xuất html element bằng tag name
// let tagname = document.getElementsByTagName(`div`);
// console.log(tagname);
// //Truy xuất html element bằng CSS selector(Selector/all)
// let cssSelect = document.querySelector(`.classname`).innerHTML;
// console.log(cssSelect);
// let cssSelect2 = document.querySelectorAll(`.classname`);
// console.log(cssSelect2);

// function display() {
//     let ten = `Phong`;
//     let ID = `TH03089`;
//     let lop = `SD1801`;

//     document.getElementById(`name`).innerHTML = ten;
//     document.getElementById(`id`).innerHTML = ID;
//     document.getElementById(`class`).innerHTML = lop;
// }


//lấy id ở phần nhập (input)

function display(){
    let inputname = document.getElementById("inputname").value;
    let inputid = document.getElementById("inputid").value;
    let inputclass = document.getElementById("inputclass").value;
    
    
    document.getElementById("name").innerHTML = inputname;
    document.getElementById("id").innerHTML = inputid;
    document.getElementById("class").innerHTML = inputclass;
    
}



