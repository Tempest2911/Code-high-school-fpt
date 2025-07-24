function validateuser(){
    let userinput = document.getElementById("userinput");
    let errornoti = document.getElementById("errornoti");
    if (userinput.value === '') {
        errornoti.innerHTML = 'Ko dc de trong';
        return false;
    } else if(userinput.value.length >= 16){
        errornoti.innerHTML = 'Ko dc qua 16 chu'
        return false;
    }
    errornoti.innerHTML = '';
    return true;
}

    // function validatepass(){
    //     let passinput = document.getElementById("passinput");
    //     let errorpass = document.getElementById("errorpass");
    //     if (passinput.value === '') {
    //         errorpass.innerHTML = 'Ko dc de trong';
    //         return false;
    //     } else if(passinput.value.length <9 || passinput.value.length >=16){
    //         errorpass.innerHTML = 'Ko dc duoi 9 chu va tren 16 chu'
    //         return false;
    //     }
    //     errorpass.innerHTML = '';    
    //     return true;                   
    // }
function validatepass(){
    let passinput = document.getElementById("passinput");
    let errorpass = document.getElementById("errorpass");
    if (passinput.value === '') {
        errorpass.innerHTML = 'Ko dc de trong';
        return false;
    } else if (passinput.value.length < 9 || passinput.value.length > 16) {
        errorpass.innerHTML = 'Ko dc duoi 9 chu va tren 16 chu';
        return false;
    }
    errorpass.innerHTML = ''; // Clear error message if input is valid
    return true;
}

function validateemail() {
    let inputemail = document.getElementById("inputemail").value;
    let erroremail = document.getElementById("erroremail");
    let emailrex = /^[\w]+@[a-z]+\.[a-z]{3,}$/;

    if (!emailrex.test(inputemail)) {
        erroremail.innerHTML = 'Vui Long nhap dung dinh dang email';
        return false;
    }
    erroremail.innerHTML = '';
    return true;
}

function validategender() {
    let male = document.getElementById("male");
    let female = document.getElementById("female");
    let errorgender = document.getElementById("errorgender");

    if (!male.checked && !female.checked) {
        errorgender.innerHTML = 'Vui lòng chọn giới tính';
        return false;
    }

    errorgender.innerHTML = '';
    return true;
}

function validateMajor() {
    let major = document.querySelectorAll('input[name="major"]:checked');
    let errormajor = document.getElementById("errormajor");

    if (major.length === 0) {
        errormajor.innerHTML = 'Vui lòng chọn chuyên ngành'
        return false;
    }
    errormajor.innerHTML = '';
    return true;
}

function checkInfo(event) {
    event.preventDefault();
    if (validateuser()&&validateemail()&&validategender()) {
        
    
    let userinput = document.getElementById("userinput").value;
    let inputemail = document.getElementById("inputemail").value;
    let male = document.getElementById("male").value;
    let female = document.getElementById("female").value;
    let major = document.querySelectorAll('input[name="major"]:checked');
        let majorlist = '';
for (let i = 0; i < major.length; i++) {
    majorlist += major[i].value;
}

    let info = `Username = ${userinput}
    Email = ${inputemail}\n
    Gender = ${male || female}\n
    Chuyên Ngành = ${majorlist}
    `;
    alert(info)

}else{
    alert('Chekc your info')
}


}


