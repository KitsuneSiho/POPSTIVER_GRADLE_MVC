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

document.addEventListener('DOMContentLoaded', function() {
    const sections = [
        { id: 'ongoing', contentId: 'ongoingContent', hasResults: hasOngoing },
        { id: 'upcoming', contentId: 'upcomingContent', hasResults: hasUpcoming },
        { id: 'ended', contentId: 'endedContent', hasResults: hasEnded }
    ];

    sections.forEach(section => {
        if (section.hasResults) {
            const sectionElement = document.getElementById(section.id + 'Section');
            const contentElement = document.getElementById(section.contentId);

            // 검색 결과가 있는 섹션을 열린 상태로 설정
            contentElement.style.display = "block";
            contentElement.classList.add('open');
            sectionElement.querySelector('img.arrow').classList.add("on");

            initPagination(section.id);
        }
    });
});

function initPagination(sectionId) {
    const content = document.querySelector(`#carousel-content-${sectionId}`);
    const cards = content.querySelectorAll('.card');
    const pageSize = 6;
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