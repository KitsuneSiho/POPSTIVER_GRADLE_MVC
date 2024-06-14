function toggleSearchList(element) {
    var popupInfo = element.nextElementSibling;
    var arrow = element.querySelector('img.arrow');
    if (popupInfo.style.display === "none" || popupInfo.style.display === "") {
        popupInfo.style.display = "block";
        arrow.classList.add("on");
    } else {
        popupInfo.style.display = "none";
        arrow.classList.remove("on");
    }
}

function toggleMenu() {
    var modal = document.getElementById('menuModal');
    if (modal.style.display === "none" || modal.style.display === "") {
        modal.style.display = "block";
    } else {
        modal.style.display = "none";
    }
}

let currentSlide = 0;

function showSlide(index) {
    const slides = document.querySelectorAll('.carousel-content .card');
    const totalSlides = Math.ceil(slides.length / 4);
    if (index >= totalSlides) {
        currentSlide = 0;
    } else if (index < 0) {
        currentSlide = totalSlides - 1;
    } else {
        currentSlide = index;
    }
    const newTransform = -currentSlide * 100 + '%';
    document.getElementById('carousel-content').style.transform = `translateX(${newTransform})`;
}