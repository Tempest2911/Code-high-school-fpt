// Thay đổi kích thước chữ (fontSize) thành 20px.
// Khi nhấn vào nút setAttribute:
// Đặt thuộc tính tùy chỉnh data-custom với giá trị customValue cho phần tử div có id là "content".
// Khi nhấn vào nút updateContent:
// Thay đổi nội dung của phần tử div có id là "content" thành Nội dung đã được cập nhật.
// Khi nhấn vào nút createElement:
// Tạo một phần tử p mới với nội dung Đây là phần tử mới.
// Thêm phần tử này vào cuối của thẻ body.
// Khi nhấn vào nút removeElement:
// Xóa phần tử p mới tạo ra khỏi thẻ body.
let content = document.getElementById("content");
function changStyle(){
    content.style.backgroundColor = "lightblue";
    content.style.color = "white";
    content.style.fontSize = "20px";
}

function setAttributee() {
    content.setAttribute(`color`, `blue`);
}

function upgradeContentt() {
    content.innerHTML = "LALALALLALALALA";
}

let newtextt = document.getElementById("newtext")
function createElementt() {
    let headChild = document.createElement(`p`);
    //truyền vào thẻ tag html sử dụng p , div, h1, vv
    let textChild = document.createTextNode(`Đây là phần tử mới`)
    // cha.appendChild(con)
    headChild.appendChild(textChild);
    newtextt.appendChild(headChild);
}

function removeElement() {
    content.remove();
}
