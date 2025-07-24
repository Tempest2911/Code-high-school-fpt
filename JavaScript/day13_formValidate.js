function checkUser() {
    let username = document.getElementById("username").value;
    let error = document.getElementById("error");
    if (username === '') {
        error.textContent = "Ko được để trống";
        return false;
    }else if (username.length > 16){
        error.textContent = "ko được quá 16 kí tự";
        return false;
    }else{
        error.innerHTML = "";
        return true;
    }
}

function checkPass() {
    let password = document.getElementById("password").value;
    let error2 = document.getElementById("error");
    if (password === '') {
        error2.textContent = "Ko được để trống";
        return false;
    }else if (password.length > 16){
        error2.textContent = "ko được quá 16 kí tự";
        return false;
    }else{
        error2.textContent = "";
        return true;
    }
}

function checkPhone() {
    let phonenumber = document.getElementById("phone").value;
    let phonepattern = /^[0-9]{10,11}$/;
    let errorPhone = document.getElementById("error");
    if (phonenumber === '') {
        errorPhone.textContent = "Ko được để trống";
        return false;
    }else if (!phonepattern.test(phonenumber)){
        errorPhone.textContent = "ko được quá 10 kí tự";
        return false;
    }else{
        errorPhone.textContent = "";
        return true;
    }
}

function checkEmail() {
    let email = document.getElementById("email");
    let emailpattern = /.^[]{10,11}$/;
    let erroremail = document.getElementById("error");
    if (email === '') {
        erroremail.textContent = "Ko được để trống";
        return false;
    }else if (email.length > 10){
        erroremail.textContent = "ko được quá 10 kí tự";
        return false;
    }else{
        erroremail.textContent = "";
        return true;
    }
}

function submitBtn() {
    document.getElementById("form").addEventListener("submit", function(event) {
        event.preventDefault();
        // Xử lý dữ liệu ở đây
        console.log("Form submitted!");
    });
}

// Gọi hàm submitBtn sau khi DOM đã tải xong
document.addEventListener("DOMContentLoaded", submitBtn);                   