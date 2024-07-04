$(document).ready(function () {
    getUserInfoAndSetUserId();
});

function getUserInfoAndSetUserId() {
    $.ajax({
        type: "GET",
        url: "/member/getUserInfo",
        success: function (response) {
            if (response && response.user_id && response.user_nickname) {
                $("#user_id").val(response.user_id);
                $("#user_name").val(response.user_nickname);
                console.log("사용자 정보 갖다 줌");
                console.log(response.user_id);
                console.log(response.user_nickname);
            } else {
                console.error("사용자 정보를 가져오는 데 실패했습니다.");
            }
        },
        error: function (xhr, status, error) {
            console.error("사용자 정보를 가져오는 중 오류 발생: " + error);
        }
    });
}

document.addEventListener('DOMContentLoaded', (event) => {
    // 현재 날짜를 yyyy-mm-dd 형식으로 포맷팅하는 함수
    function formatDateString(dateString) {
        const date = new Date(dateString);
        const year = date.getFullYear();
        const month = String(date.getMonth() + 1).padStart(2, '0'); // 월은 0부터 시작하므로 +1
        const day = String(date.getDate()).padStart(2, '0');
        return year + "-" + month + "-" + day;
    }

    // 페이지 로드 시 실행
    document.querySelectorAll('.date-field').forEach((element) => {
        const originalDate = element.textContent.trim();
        element.textContent = formatDateString(originalDate);
    });

    // 각 캐러셀에 대해 페이지네이션 구현
    const carousels = document.querySelectorAll('.carousel');
    carousels.forEach(carousel => {
        const content = carousel.querySelector('.carousel-content');
        const cards = Array.from(content.children);
        const totalItems = cards.length;
        const itemsPerPage = 2; // 페이지당 보여줄 카드 수
        let currentPage = 1;

        const prevButton = carousel.querySelector('.prev-page');
        const nextButton = carousel.querySelector('.next-page');
        const pageInfo = carousel.querySelector('.page-info');

        function showPage(page) {
            const startIndex = (page - 1) * itemsPerPage;
            const endIndex = startIndex + itemsPerPage;

            // 모든 카드 숨기기
            cards.forEach(card => card.style.display = 'none');

            // 현재 페이지에 해당하는 카드들만 보이도록 설정
            for (let i = startIndex; i < endIndex && i < totalItems; i++) {
                cards[i].style.display = 'block';
            }

            // 페이지네이션 컨트롤 업데이트
            pageInfo.textContent = `${page}/${Math.ceil(totalItems / itemsPerPage)}`;
            currentPage = page;
        }

        prevButton.addEventListener('click', () => {
            if (currentPage > 1) {
                showPage(currentPage - 1);
            }
        });

        nextButton.addEventListener('click', () => {
            if (currentPage < Math.ceil(totalItems / itemsPerPage)) {
                showPage(currentPage + 1);
            }
        });

        showPage(currentPage);
    });
});





function initPagination(sectionId) {
    const content = document.querySelector(`#carousel-content-${sectionId}`);
    const cards = content.querySelectorAll('.card');
    const pageSize = 2;
    const pageCount = Math.ceil(cards.length / pageSize);
    let currentPage = 1;

    const prevButton = content.parentElement.querySelector('.prev-page');
    const nextButton = content.parentElement.querySelector('.next-page');
    const pageInfo = content.parentElement.querySelector('.page-info');

    // 카드들을 페이지별로 그룹화
    for (let i = 0; i < pageCount; i++) {
        const page = document.createElement('div');
        page.className = 'carousel-page';
        content.appendChild(page);
        for (let j = i * pageSize; j < (i + 1) * pageSize && j < cards.length; j++) {
            page.appendChild(cards[j]);
        }
    }

    function updatePage() {
        content.style.transform = `translateX(-${(currentPage - 1) * 100}%)`;
        pageInfo.textContent = `${currentPage}/${pageCount}`;
        prevButton.disabled = currentPage === 1;
        nextButton.disabled = currentPage === pageCount;
    }

    prevButton.addEventListener('click', () => {
        if (currentPage > 1) {
            currentPage--;
            updatePage();
        }
    });

    nextButton.addEventListener('click', () => {
        if (currentPage < pageCount) {
            currentPage++;
            updatePage();
        }
    });

    updatePage();
}






function toggleSearchList(element) {
    var popupInfo = element.nextElementSibling;
    var arrow = element.querySelector('img.arrow');

    if (!popupInfo.classList.contains('open')) {
        popupInfo.style.display = "block";
        setTimeout(() => {
            popupInfo.classList.add('open');
        }, 10);
        arrow.classList.add("on");
    } else {
        popupInfo.classList.remove('open');
        arrow.classList.remove("on");
        setTimeout(() => {
            popupInfo.style.display = "none";
        }, 500); // This should match the transition duration
    }
}