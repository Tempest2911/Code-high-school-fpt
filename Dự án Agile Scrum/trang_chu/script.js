function toggleMenu() {
    const navLinks = document.getElementById('dropdown-mobile');
    if (navLinks.style.display === "block") {
        navLinks.style.display = "none";
    } else {
        navLinks.style.display = "block";
    }
}


document.getElementById('scrollButton').addEventListener('click', function (event) {
    event.preventDefault(); const target = document.getElementById('Phong_phan4');
    const offsetTop = target.getBoundingClientRect().top + window.pageYOffset - 100;
    window.scrollTo({ top: offsetTop, behavior: 'smooth' });
});


//Banner
// Giả sử currentSlide và totalSlides đã được khai báo
const slider = document.querySelector('.slider');
const slides = document.querySelectorAll('.slider img');
const slideWidth = slides[0].clientWidth; // Lấy chiều rộng của một ảnh
const navLinks = document.querySelectorAll('.slider-nav a');

let currentSlide = 1;

// Thêm sự kiện click cho mỗi thẻ <a>
navLinks.forEach((link, index) => {
    link.addEventListener('click', (event) => {
        event.preventDefault(); // Ngăn hành vi mặc định của thẻ <a>
        currentSlide = index + 1; // Đặt currentSlide theo số thứ tự slide
        slider.scrollLeft = (currentSlide - 1) * slideWidth; // Cuộn ngang trong slider
    });
});

// Tự động chuyển slide sau 2 giây
setInterval(NextBanner, 5000);

function NextBanner() {
    currentSlide++;
    if (currentSlide > slides.length) {
        currentSlide = 1; // Quay lại slide đầu tiên nếu vượt quá số lượng ảnh
    }
    slider.scrollLeft = (currentSlide - 1) * slideWidth; // Cuộn ngang trong slider
}

var swiper = new Swiper(".mySwiper", {
    effect: "coverflow",
    grabCursor: true,
    centeredSlides: true,
    slidesPerView: "auto",
    coverflowEffect: {
        rotate: 50,
        stretch: 0,
        depth: 100,
        modifier: 1,
        slideShadows: true,
    },
    pagination: {
        el: ".swiper-pagination",
    },
    autoplay: {
        delay: 2500,
        disableOnInteraction: false,
    },
});



