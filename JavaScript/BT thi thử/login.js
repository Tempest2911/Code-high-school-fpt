document.getElementById('emailform').addEventListener('submit', function () {
    let emailinput = document.getElementById('email').value;
    let emailpattern = /^[a-z0-9._+-]+@[a-z]+\.[a-z]{2,}$/i;
    if (!emailpattern.test(emailinput)) {
        alert('Email phải bao gồm @ và dấu chấm');
        event.preventDefault();
    }

    let passinput = document.getElementById('password').value;
    let passpattern = /^(?=[A-Z0-9]).{6,}$/;
    if (!passpattern.test(passinput)) {
        alert("Mật khẩu phải ít nhất 6 kí tự và 1 kí tự in hoa");
        event.preventDefault();
    }
});



let queryimg = document.querySelector("#queryimg");
let myimg = ["img/9JEh8E5.png", "img/432763246_122124388466225590_4609908499544947202_n.png", "img/22b34b27d09ae346bb1d921f6f5074bc-removebg-preview.png"];
let i = 0;

function pre() {
    if (i <= 0) i = myimg.length;
        i--;
    return setimg();
}

function next() {
    if(i >= myimg.length-1) i= -1;
    i++;
    return setimg();
}

let start = document.getElementById("start");
start.addEventListener('click', function () {
    settime = setInterval(changimg, 1000);
    function changimg() {
        if (i <= 0) i = myimg.length;
            i--;
        return setimg();
    }
});

let stopp = document.getElementById("stop");
stopp.addEventListener('click', function () {
    clearInterval(settime);
})

function setimg() {
    return queryimg.setAttribute('src', "" + myimg[i]);
}