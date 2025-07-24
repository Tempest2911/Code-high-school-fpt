// Type conversion  -Xem là số hay String(chữ)

// let x = "pizza";
// let y = "pizza";
// let z = "pizza";

// x = Number(x);
// y = String(y);
// z = Boolean(z);

// console.log(x, typeof x);
// console.log(y, typeof y);
// console.log(z, typeof z);

// CONSTANTS - Hằng số

// let pi = 3.142;
// let radius;
// let circumference;

// console.log(circumference);

// document.getElementById("mysubmit").onclick = function() {
//     radius = document.getElementById("mytext").value;
//     radius = Number(radius);
//     circumference = 2 * pi * radius;
//     circumference = circumference.toFixed(3);
//     document.getElementById("myh3").textContent = circumference + "cm";
// }

// Counter Program - Chương trình đếm

// let cong = document.getElementById("cong");
// let tru = document.getElementById("tru");
// let reset = document.getElementById("reset");
// let number = document.getElementById("number");
// let count = 0;

// cong.onclick = function(){
//     count++;
//     number.textContent = count;
// }

// tru.onclick = function(){
//     count--;
//     number.textContent = count;
// }

// reset.onclick = function(){
//     count=0;
//     number.textContent = count;
// }

// MATH -Toán

// let x = -3.21;
// let y = 2;
// let z = 1;

// z = Math.round(x); //làm tròn số
// z = Math.floor(x); //làm tròn xuống
// z = Math.ceil(x); //làm tròn lên 
// z = Math.trunc(x); //xóa dấu thập phân
// z = Math.pow(x,y); //lũy thừa
// z = Math.sqrt(x); //căn bậc 2
// z = Math.log(x); 
// z = Math.sin(x);
//  z = Math.cos(x);
// z = Math.tan(x);
// z = Math.abs(x);  //chuyển thành số dương
// z = Math.sign(x);
// z = Math.max(x,y,z);
// z = Math.min(x,y,z);
// console.log(z);

// RANDOM NUMBER -Gacha số

// let number = document.getElementById("number");
// let roll = document.getElementById("roll");
// let reset = document.getElementById("reset");
// let random;

// roll.onclick = function () {
//     random = Math.trunc(Math.random()*100)+1;
//     number.textContent = random;
//     console.log(random);
// }

// reset.onclick = function () {
//     random =0;
//     number.textContent = random;
// }

// IF ELSE -Nếu như

// let mytext = document.getElementById("mytext");
// let mysubmit = document.getElementById("mysubmit");
// let text = document.getElementById("text");
// let age;

// mysubmit.onclick = function(){

//     age = mytext.value;
//     age = Number(age);

//     if (age==0){
//         text.textContent = `You were just born`;
//     } else if(age >=18){
//         text.textContent = `You are old enough to join this site`;
//     }else if(age<0){
//         text.textContent = `Your age can't be below 0`;
//     }else{
//         text.textContent = `you must be >18 to join this site`;
//     }
// }

// CHECKED -Kiểm tra 

// let subscribe = document.getElementById("subscribe");
// let visa = document.getElementById("visa");
// let master = document.getElementById("master");
// let paypal = document.getElementById("paypal");
// let submit = document.getElementById("submit");
// let subResult = document.getElementById("subResult");
// let paymentResult = document.getElementById("paymentResult");

// submit.onclick = function(){
//     if (subscribe.checked) {
//         subResult.textContent = `you so sigma`;
//     } else {
//         subResult.textContent = `you are not a sigma`;
//     }

//     if (visa.checked) {
//         paymentResult.textContent = `you use visa`;
//     } else if(master.checked){
//         paymentResult.textContent = `you use master card`;
//     }else if(paypal.checked){
//         paymentResult.textContent = `you use paypal`;
//     }else{
//         paymentResult.textContent = `You must select the payment type`;
//     }
// }

// Ternary operator - Toán tử bậc 3

// let age = 20;
// let message = age >=18 ? `mmb` : `ditcham`;
// console.log(message);

// let student = true;
// let message = student ? `you are student` : `you are not student`
// console.log(message);

// let price = 99;

// let message = price >= 100 ? 10 : 0;

// console.log(`Your total is $${price - price * (message/100)}`);

// SWITCH - Chọn

// let score = prompt(`Your score :`);
// let grade;

// switch (true) {
//     case score > 100:
//         grade = `Score must be <=100`;
//         break;
//     case score > 80:
//         grade = `A`;
//         break;
//     case score >= 70:
//         grade = `b`;
//         break;
//     case score >= 60:
//         grade = `C`;
//         break;
//     case score >= 50:
//         grade = `D`;
//         break;
//     default:
//         grade = `F`;
//         break;
// }

// console.log(grade);

// STRING SLICING - Cắt chuỗi

// let fullname = "Phong Nigga";
// let email = "dragonroblox2k7@gmail.com";

// let firstname = fullname.slice(0,5);
// let lastname = fullname.slice(6,11);

// let firstchar = fullname.slice(0,5);
// let lastchar = fullname.slice(-5);

// let firstname = fullname.slice(0, fullname.indexOf(" "));
// let lastname = fullname.slice(fullname.startsWith(" ") +6);

// let username = email.slice(0 , email.indexOf("@"));
// let exetension = email.slice(email.indexOf("@")+1);


// console.log(firstname);
// console.log(lastname);

// console.log(username);
// console.log(exetension);

// console.log(firstchar);
// console.log(lastchar);


// METHOD CHAINING   - Chuỗi phương thức / giống 1 combo trong game

// let username = prompt("Enter your username: ");

// no method chaining  

// username = username.trim(); - xóa khoảng trống, trắng
// let letter = username.charAt(0); - lấy chữ ở đầu vì là số 0
// letter = letter.toUpperCase();  - viết hoa

// let extrachar = username.slice(1); - cắt chữ ở đầu
// extrachar = extrachar.toLowerCase();  - viết thường
// username = letter + extrachar; 

// console.log(username);

// ----------Real method chainning------------//

// username = username.trim().charAt(0).toUpperCase() + username.slice(1).toLowerCase(); //Combo

// console.log(username);


// Locial Operators - Toán tử logic AND = &&    OR = ||   NOT = !

// let temp = 20;

// // if (temp > 0 && temp <= 30) {
// //     console.log("Weathe is good");
// // } else {
// //     console.log("Weather is bad");
// // }

// if (temp <= 0 || temp >30) {
//     console.log("The weather is bad");
// } else {
//     console.log("The weather is good");
// }



