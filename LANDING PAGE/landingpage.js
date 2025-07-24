window.addEventListener("load", function () {
    const slider = document.querySelector(".slider");
    const sliderMain = document.querySelector(".slider-main");
    const sliderItems = document.querySelectorAll(".slider-item");
    const nextBtn = document.querySelector(".slider-next");
    const prevBtn = document.querySelector(".slider-prev");
    const dotItem = document.querySelectorAll(".slider-dot-item");
    const sliderItemWidth = sliderItems[0].offsetWidth;
    const slidesLehgth = sliderItems.length;
    let positionX = 0;
    let index = 0;
    nextBtn.addEventListener("click", function () {
        handChangeSlide(1);
    });
    prevBtn.addEventListener("click", function () {
        handChangeSlide(-1);
    });

    [...dotItem].forEach((item) =>
        item.addEventListener("click", function (e) {
            [...dotItem].forEach((el) => el.classList.remove("active"));
            e.target.classList.add("active");
            const slideindex = parseInt(e.target.dataset.index);
            index = slideindex;
            positionX = -1 * index * sliderItemWidth;
            sliderMain.style = `transform: translate(${-1 * index * sliderItemWidth}px)`;
        })
    );


    let imgtag2 = document.getElementById("slideshow2");
    let imggarr2 = ['img/banner2.png', 'img/banner3.png', 'img/banner4.png', 'img/banner5.png'];
    let ia = 0;
    function slideshow_auto2() {
        imgtag2.setAttribute('src', imggarr2[ia]);
        ia++;
        if (ia == imggarr2.length)
            ia = 0;

    } setInterval(slideshow_auto2, 1000);

    function handChangeSlide(direction) {

        if (direction === 1) {
            if (index >= slidesLehgth - 1) {
                index = slidesLehgth - 1;
                return;
            }
            positionX = positionX - sliderItemWidth;
            sliderMain.style = `transform: translate(${positionX}px)`;
            console.log(index);
            index++;
        } else if (direction === -1) {
            console.log("prev slide");

            if (index <= 0) {
                index = 0;
                return;
            };
            positionX = positionX + sliderItemWidth;
            sliderMain.style = `transform: translateX(${positionX}px)`;
            index--;
        }
        [...dotItem].forEach((el) => el.classList.remove("active"));

        dotItem[index].classList.add("active");


    }

}
);

