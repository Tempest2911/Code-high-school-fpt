function cart(event) {
    // Lấy phần tử sản phẩm được chọn
    const productElement = event.target.closest('.item1');
    
    // Lấy tên và giá sản phẩm
    const productName = productElement.querySelector('.sp').textContent;
    const productPrice = parseFloat(productElement.querySelector('.price').textContent.replace('$', ''));
    
    // Nhận số lượng sản phẩm từ người dùng
    const quantity = parseInt(prompt("Nhập số lượng sản phẩm:"));

    if (isNaN(quantity) || quantity <= 0) {
        alert('Vui lòng nhập số lượng hợp lệ.');
        return;
    }

    // Tạo và thêm phần tử tên sản phẩm vào div#info
    const productNameElement = document.createElement('h1');
    productNameElement.textContent = '+Tên sản phẩm: ' + productName;
    document.getElementById('nana').appendChild(productNameElement);

    // Tạo và thêm phần tử số lượng sản phẩm vào div#info
    const quantityElement = document.createElement('h1');
    quantityElement.textContent = '+Số lượng: ' + quantity;
    document.getElementById('nana').appendChild(quantityElement);

    // Lưu trữ tổng giá của sản phẩm trong thuộc tính data-price của phần tử
    productElement.setAttribute('data-price', productPrice * quantity);
}

function purchase() {
    let total = 0;

    // Tính tổng giá tiền từ tất cả các phần tử có thuộc tính data-price
    document.querySelectorAll('[data-price]').forEach(function(element) {
        total += parseFloat(element.getAttribute('data-price'));
    });

    // Hiển thị tổng số tiền cần thanh toán
    alert('Tổng số tiền cần thanh toán: $' + total);
}


let sliderImg = document.getElementById("sliderImg");
let image = ["wallhaven-k9lekd.jpg", "banner2.jpg", "wallhaven-85pz2y.png", "wallhaven-968k1d.jpg", "wallhaven-2yeem9.png", "bhanner.png"];
let i = 0;
let timeImg = setInterval(changeImg, 2000);

function changeImg() {
    if (i >= image.length - 1) i = -1;
    i++;
    return setImg();
}

function setImg() {
    return sliderImg.setAttribute('src', 'image/' + image[i]);
}

