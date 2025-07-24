// // let obj = [
// //     {
// //         name : `Hương`,
// //         age : 25
// //     },
// //     {
// //         name : `SD1801`,
// //         age : 99
// //     }
// // ]

// // for (let nigga of obj) {
// //     if (nigga.age>18) {
// //         console.log(`Cac ban du tuoi tren 18 la: ${nigga.name}`);
// //     }
// // }

// // let i = `JavaScript`;

// // for (let cc of i) {
// //     console.log(cc);
// // }

// // function myInfo(){      //Hàm giống void
// //     console.log(`Hello javascript`);
// // }

// // myInfo();

// //Luyện tập

// let SD1801 = [];

// let num = 5;

// for (let i = 0; i < num; i++) {
//     let name = prompt("Name: ");
//     let age = prompt("Age: ");
//     let gender = prompt("Gender: ");
//     let classs = prompt("Class: ");
//     let score = prompt("Score: ");

//     let student = {
//         name: name,
//         age: age,
//         gender: gender,
//         classs: classs,
//         score: score
//     }

//     SD1801.push(student);
// }

//     let luachon = prompt("1. Hiển thị danh sách sinh viên lớp SD1801\n2. Hiển thị các tên của sinh viên có giới tính là Nữ\n3. Hiển thị đánh giá điểm của sinh viên\n\nMời bạn chọn: ");
//     luachon = Number(luachon);

//     switch (luachon) {
//         case 1:

//             console.log(SD1801);
//             break;
//         case 2:
//             console.log("Cac ban nu la:");
//             for (let female of SD1801) {
//                 if (female.gender === "nữ") {
//                     console.log(`${female.name}`);
//                 }
//             }
//             break;

//         case 3:

//             for (let diem of SD1801) {
//                 if (diem.score > 8) {
//                     console.log(`Student ${diem.name} is a good student`);
//                 } else if (diem.score > 6) {
//                     console.log(`Student ${diem.name} is a normal student`);
//                 } else if (diem.score < 6) {
//                     console.log(`Student ${diem.name} is a bad student`);
//                 }
//             }
//             break;

//         default:
//             break;
//     }

