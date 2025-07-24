let display = document.getElementById("display");
let bigg = document.getElementById("bigg");
let smalll = document.getElementById("smalll");
let displaytext = document.getElementById("displaytext");

function hienthi() {
let inputtext = document.getElementById("inputtext").value;

    displaytext.innerHTML = inputtext; 
    bigg.style.display = `block`;
    smalll.style.display = `block`;
}

function big() {
    let currentSize = parseInt(displaytext.style.fontSize) || 50;
    let bigSize = currentSize + 2;
    displaytext.style.fontSize = bigSize + 'px';
}

function small() {
    let currentSize = parseInt(displaytext.style.fontSize) || 50;
    let smallSize = currentSize - 2;
    displaytext.style.fontSize = smallSize + 'px';
}