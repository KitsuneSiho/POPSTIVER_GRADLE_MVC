// DOM이 로드되면 실행될 함수
document.addEventListener('DOMContentLoaded', function() {

    // 섹션 토글 기능 설정
    const sections = [
        { id: 'ongoingSection', contentId: 'ongoingContent', hasResults: hasOngoing },
        { id: 'upcomingSection', contentId: 'upcomingContent', hasResults: hasUpcoming },
        { id: 'endedSection', contentId: 'endedContent', hasResults: hasEnded }
    ];

    sections.forEach(section => {
        if (section.hasResults) {
            const sectionElement = document.getElementById(section.id);
            const contentElement = document.getElementById(section.contentId);
            if (contentElement) contentElement.style.display = "block";
            if (sectionElement) {
                const arrow = sectionElement.querySelector('img.arrow');
                if (arrow) arrow.classList.add("on");
            }
        }
    });
});

// 사용자 정보 가져오기 함수
// function getUserInfoAndSetUserId() {
//     fetch("/member/getUserInfo")
//         .then(response => {
//             if (!response.ok) {
//                 throw new Error('Network response was not ok');
//             }
//             return response.json();
//         })
//         .then(data => {
//             if (data && data.user_id && data.user_nickname) {
//                 const userIdElement = document.getElementById("user_id");
//                 const userNameElement = document.getElementById("user_name");
//                 if (userIdElement) userIdElement.value = data.user_id;
//                 if (userNameElement) userNameElement.value = data.user_nickname;
//             } else {
//                 console.error("사용자 정보를 가져오는 데 실패했습니다.");
//             }
//         })
//         .catch(error => {
//             console.error("사용자 정보를 가져오는 중 오류 발생:", error);
//         });
// }



// 검색 목록 토글 함수
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

// 캐러셀 관련 변수와 함수 (필요한 경우)
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