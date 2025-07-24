//Change style (css)
// set attribute
// creative and remove element
//1.change style
let changeCss = document.getElementById(`heading`);
let displayImg = document.getElementById(`myImg`);
function displayImage() {
    changeCss.style.color = "blue";
    changeCss.innerHTML = `Duck`
    displayImg.style.display = `block`;
    displayImg.style.width = `500px`;
    displayImg.style.height = `500px`;
    // changeCss.style.backgroundColor = "pink";
    // changeCss.setAttribute(`class`, `box`);
    //ten attribute , link
    //tenbien.style.tên thuộc tính
}


function hideImage() {
    displayImg.style.display = "none";
}

function changeImage() {
    changeCss.style.color = "red";
    changeCss.setAttribute(`class`, `box`);
    displayImg.setAttribute(`src`, `image/red.png`);
}
//remove attribute
function enableBtn() {
    let endBtn = document.getElementById(`btnDisplay`);
    endBtn.removeAttribute(`disabled`);
}
//create element
let headParent = document.getElementById(`headTag`)
function displayText() {
    let headChild = document.createElement(`p`);
    //truyền vào thẻ tag html sử dụng p , div, h1, vv
    let textChild = document.createTextNode(`Eminem`)
    // cha.appendChild(con)
    headChild.appendChild(textChild);
    headParent.appendChild(headChild);
}

//remove element
function removeText() {
    let deleteNode = document.getElementById(`deleteText`);//xóa qua cha
    let contentNode= document.getElementById(`text`)
    deleteNode.removeChild(contentNode);
    //xóa trực tiếp
    //contentNode.remove();
}