function thue(event) {
    event.preventDefault();

    let isvalid = true;

    let name = document.getElementById("name").value;
    let errorname = document.getElementById("errorname");
    if (name === '') {
        errorname.innerHTML = 'Không được để trống họ và tên';
        isvalid = false;
    } else {
        errorname.innerHTML = '';
        isvalid;
    }

    let address = document.getElementById("address").value;
    let erroraddress = document.getElementById("erroraddress");

    if (address === '') {
        erroraddress.innerHTML = 'Không được để trống địa chỉ';
        isvalid = false
    } else {
        erroraddress.innerHTML = '';
        isvalid;
    }

    let email = document.getElementById("email").value;
    let erroremail = document.getElementById("erroremail");
    let emailPattern = /^[\w]+@[\w]+\.[\w]+$/;

    if (email === '') {
        erroremail.innerHTML = 'Không được để trống email';
        isvalid = false;
    } else if (!emailPattern.test(email)) {
        erroremail.innerHTML = 'Phải đúng định dạng email';
        isvalid = false;
    } else {
        erroremail.innerHTML = '';
        isvalid;
    }

    let car = document.getElementById('car').value;
    let time = document.getElementById("time").value;
    let price = document.getElementById("price");
    
    price.innerHTML = parseInt(car)*parseInt(time);

   
}