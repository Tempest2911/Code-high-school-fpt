function toggleMenu() {
    const navLinks = document.getElementById('dropdown-mobile');
    if (navLinks.style.display === "block") {
        navLinks.style.display = "none";
    } else {
        navLinks.style.display = "block";
    }
}

function togglePopup(name) {
    const popup = document.getElementById('productPopup');
    const overlay = document.getElementById('overlay');

    // Toggle display
    if (popup.style.display === 'flex') {
        popup.style.display = 'none';
        overlay.style.display = 'none';
    } else {
        popup.style.display = 'flex';
        overlay.style.display = 'block';
    }

    document.getElementById('successMessage').style.display = 'none';

    // Set the product name in the popup
    if (name) {
        document.getElementById('SP').textContent = name;
    }
}

function selectSize(price, element) {
    // Update price
    document.getElementById('priceDisplay').textContent = price;

    // Update active button
    const buttons = document.querySelectorAll('.size-button');
    buttons.forEach(button => button.classList.remove('active'));
    element.classList.add('active');
}

function submitForm() {
    const name = document.getElementById('nameField').value;
    const phone = document.getElementById('phoneField').value;
    const address = document.getElementById('addressField').value;

    if (name && phone && address) {
        document.getElementById('successMessage').style.display = 'block';
    } else {
        alert("Vui lòng điền đầy đủ thông tin.");
    }
}

document.querySelectorAll('.btn_Buy').forEach(button => {
    button.addEventListener('click', function () {
        const productName = this.parentElement.querySelector('.NameSP').textContent;
        togglePopup(productName);
    });
});

// Set default price when popup is opened
document.querySelectorAll('.btn_Buy').forEach(button => {
    button.addEventListener('click', function () {
        const productPrice = this.parentElement.querySelector('.Price').textContent;
        document.getElementById('priceDisplay').textContent = productPrice;
    });
});

