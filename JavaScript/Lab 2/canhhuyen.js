// let a = prompt(`Nhập a: `);
// let b = prompt(`Nhập b: `);

// let c = Math.pow(Number(a),2) + Math.pow(Number(b),2);

// console.log(Math.pow(Number(c),2));

// let a = prompt(`Nhap m²: `);
// let sao = Number(a) / 360;
// sao= sao.toPrecision(3);
// console.log(`${sao} sào`);
// console.log(`${Number(a) / 10000} mẫu nam bộ`);
// console.log(`${Number(a) / 10000} ha`);

// let weight = prompt(`Enter weight (kg): `);
// let height = prompt(`Enter height (m): `);

// let bmi = Number(weight) / (Number(height) * Number(height));

// console.log(`BMI: ${bmi.toFixed(2)}`);

// if (bmi < 18.5 && bmi > 0) {
//     console.log(`Thieu can (Underweight)`);
// } else if (bmi >= 18.5 && bmi <= 24.99) {
//     console.log(`Binh Thuong (Normal weight)`);
// } else if (bmi >= 25 && bmi <= 29.99) {
//     console.log(`Thua can (Overweight)`);
// } else if (bmi >= 30) {
//     console.log(`Beo Phi (Obese)`);
// }

// Bai 5

let a = prompt(`Nhap so ban muon: `);
let x = Math.floor((Math.random() * 5) + 10);

console.log(a);
console.log(x);

if (a == x) {
    console.log(`Ban da dung`);
} else if (a > x) {
    console.log(`So ban lon hon so bi mat`);
} else if (a < x) {
    console.log(`So ban be hon so bi mat`);
}
