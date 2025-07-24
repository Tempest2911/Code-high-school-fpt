function checkname() {
    let name = document.getElementById("name").value;
    let errname = document.getElementById("errname");

    if (name === '') {
        errname.innerHTML = 'Please fill out your name.';
    } else if (name.length > 100) {
        errname.innerHTML = 'Your name should not exceed 100 characters.';
    } else {
        errname.innerHTML = '';
    }
}

function checkemail(params) {
    let email = document.getElementById("email").value;
    let erremail = document.getElementById("erremail");
    let emailpattern = /^[a-zA-Z0-9+=-_.]+@[a-zA-Z0-9+=-_.]+\.+[a-zA-Z0-9+=-_.]{2,}$/i;

    if (email === '') {
        erremail.innerHTML = 'Please fill out your email.';
    } else if (!emailpattern.test(email)) {
        erremail.innerHTML = 'Please leave correct gmail format.';
    } else if (email.length > 100) {
        erremail.innerHTML = 'Your email should not exceed 100 characters.';
    } else {
        erremail.innerHTML = '';
    }
}

function checksubject(params) {
    let subject = document.getElementById("subject").value;
    let errsubject = document.getElementById("errsubject");

    if (subject === '') {
        errsubject.innerHTML = 'Please fill out the subject.';
    } else if (subject.length < 50) {
        errsubject.innerHTML = 'Subject should not shorter than 50 characters.';
    } else if (subject.length > 250) {
        errsubject.innerHTML = 'Subject should not exceed 250 characters.';
    } else {
        errsubject.innerHTML = '';
    }
}

function checkmess(params) {
    let message = document.getElementById("messagee").value;
    let errmessage = document.getElementById("errmessage");

    if (message === '') {
        errmessage.innerHTML = 'Please fill out the message.';
    } else if (message.length > 500) {
        errmessage.innerHTML = 'Your name should not exceed 500 characters.';
    } else {
        errmessage.innerHTML = '';
    }
}

function send() {
    let errname = document.getElementById("errname").innerHTML;
    let erremail = document.getElementById("erremail").innerHTML;
    let errsubject = document.getElementById("errsubject").innerHTML;
    let errmessage = document.getElementById("errmessage").innerHTML;

    let name = document.getElementById("name").value;
    let email = document.getElementById("email").value;
    let subject = document.getElementById("subject").value;
    let message = document.getElementById("messagee").value;

    if (errname === '' && erremail === '' && errsubject === '' && errmessage === '' && name !== '' && email !== '' && subject !== '' && message !== '') {
        alert("Complete!!!!");
    } else {
        alert("Check again!!!");
    }
}

