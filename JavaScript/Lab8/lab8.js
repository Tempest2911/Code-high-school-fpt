function gui() {

    let isvalid = true;

    let username = document.getElementById("username").value;
    let usernameError = document.getElementById("usernameError");
    if (username === '') {
        usernameError.innerHTML = "Ko được để trống";
        isvalid =  false;
    }else if (username.length > 16 || username.length < 6){
        usernameError.innerHTML = "tối thiếu 6 ký tự và max 16 ký tự";
        isvalid = false;
    }else{
        usernameError.innerHTML = "";
        isvalid;
    }

    let email = document.getElementById("email").value;
    let emailError = document.getElementById("emailError");
    let emailPattern = /^[\w]+@[\w]+\.[\w]+$/;
    if (!emailPattern.test(email)) {
        emailError.innerHTML = "Phải đúng định dạng";
        isvalid= false;
    }else if(email === '') {
        erroremail.textContent = "Ko được để trống";
        return false;
    }else{
        emailError.innerHTML = "";
        isvalid;
    }

    let password = document.getElementById("password").value;
    let passwordError = document.getElementById("passwordError");
    let passPattern = /^(?=.*\d).{6,}$/;
    if (password === '') {
        passwordError.innerHTML = 'Ko được để trống'
        isvalid = false
    } else if(!passPattern.test(password)){
        passwordError.innerHTML = 'Phải ít nhất 6 kí tự và 1 số'
        isvalid = false
    } else{
        passwordError.innerHTML = '';
        isvalid;
    }

    let phone = document.getElementById("phone").value;
    let phoneError = document.getElementById("phoneError");
    if (phone === '') {
        phoneError.innerHTML = 'Ko được để trống'
        isvalid = false
    } else if(phone.length <8 || phone.length >11){
        phoneError.innerHTML = 'Chỉ đc phép nhập 8-11 số'
        isvalid = false
    } else{
        phoneError.innerHTML = '';
        isvalid;
    }

    let years = document.getElementById("years").value;
    let yearsError = document.getElementById("yearsError");
    if (years === '') {
        yearsError.innerHTML = 'Ko được để trống'
        isvalid = false
    } else if(!parseInt(years)){
        yearsError.innerHTML = 'Phải là số nguyên'
        isvalid = false
    } else{
        yearsError.innerHTML = '';
        isvalid;
    }
}

