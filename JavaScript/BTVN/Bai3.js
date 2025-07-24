let BT1 = document.getElementById("BT1");
let BT2 = document.getElementById("BT2");
let BT3 = document.getElementById("BT3");

function complete1(params) {
    BT1.style.color = "#054a29"
    BT1.style.backgroundColor = "green";
}

function complete2(params) {
    BT2.style.color = "#054a29"
    BT2.style.backgroundColor = "green";
}

function complete3(params) {
    BT3.style.color = "#054a29"
    BT3.style.backgroundColor = "green";
}

function notcomplete1(params) {
    BT1.style.color = "whitesmoke";
    BT1.style.backgroundColor = "red";
}

function notcomplete2(params) {
    BT2.style.color = "whitesmoke";
    BT2.style.backgroundColor = "red";
}

function notcomplete3(params) {
    BT3.style.color = "whitesmoke";
    BT3.style.backgroundColor = "red";
}