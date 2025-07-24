let hocLuc = document.getElementById("hocLuc");

hocLuc.addEventListener('change', function () {
    let giatri = hocLuc.value;
    let result = document.getElementById("result");

    if (giatri === "pass") {
        result.textContent = "Điểm >5";
    } else if (giatri === "fail") {
        result.textContent = "Điểm <5";
    }

});



function submitBtn(event) {
    event.preventDefault();
    let username = document.getElementById("username").value;
    let password = document.getElementById("password").value;
    let tc = document.getElementById("tc");
    if (username === "123" && password === "123") {
        tc.textContent = "Thành Công"

    } else {
        tc.textContent = "Thất bại"
    }
    document.getElementById("displayUser").innerHTML = "Username: " + username;
    document.getElementById("displayPass").innerHTML = "Password: " + password;
    document.getElementById("displayScore").innerHTML = "Score: " + result.textContent;
}



