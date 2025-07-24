
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

function checkmess() {
    let message = document.getElementById("message").value;
    let errmess = document.getElementById("errmess");

    if (message === '') {
        errmess.innerHTML = 'Please fill out your message';
    } else if (email.length > 30) {
        errmess.innerHTML = 'Message should not exceed 30 characters.';
    } else {
        errmess.innerHTML = '';
    }
}

function checkIncludes() {
    let checkItem = document.querySelectorAll('input[name="Includes"]:checked');
    let errIncludes = document.getElementById('errIncludes');

    if (checkItem.length === 0) {
        errIncludes.innerHTML = 'Please select at least one option.';
    } else {
        errIncludes.innerHTML = '';
    }
}

function checkDate(){
    let Datee = document.getElementById("Datee").value;
    let errDate = document.getElementById("errDate");

    if (Datee === "") {
        errDate.innerHTML= 'Please fill out the deliver date.'
    } else {
        errDate.innerHTML= ''
    }
}

function checkDeliverTo(params) {
    let DeliverTo = document.getElementById("DeliverTo").value;
    let errDeliverTo = document.getElementById("errDeliverTo");


    if (DeliverTo === '') {
        errDeliverTo.innerHTML = 'Please fill out the address.';
    } else if (DeliverTo.length > 500) {
        errDeliverTo.innerHTML = 'Address should not exceed 500 characters.';
    } else {
        errDeliverTo.innerHTML = '';
    }
}


function order() {
    let errname = document.getElementById("errname").innerHTML;
    let errmess = document.getElementById("errmess").innerHTML;
    let errIncludes = document.getElementById('errIncludes').innerHTML;
    let errDate = document.getElementById("errDate").innerHTML;
    let errDeliverTo = document.getElementById("errDeliverTo").innerHTML;

    let name = document.getElementById("name").value;
    let message = document.getElementById("message").value;
    let checkItem = document.querySelectorAll('input[name="Includes"]:checked');
    let Datee = document.getElementById("Datee").value;
    let DeliverTo = document.getElementById("DeliverTo").value;
    

    if (errname === '' && errmess === '' && errIncludes === '' && errDate === '' && errDeliverTo === '' && name !== '' && message !== '' && checkItem.length > 0 && Datee !== '' && DeliverTo !== '') {
        alert("Complete!!!!");
    } else {
        alert("Check again!!!");
    }
}

