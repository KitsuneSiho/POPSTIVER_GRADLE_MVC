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

// $(document).ready(function () {
//     getUserInfoAndSetUserId();
//
//     $('.bookmark').click(function() {
//         var isLiked = $(this).hasClass('liked');
//         var eventType = $(this).closest('.card-content').data('eventtype');
//         var eventNo = $(this).closest('.card-content').data('eventno');
//         var userId = $("#user_id").val();
//         var userName = $("#user_name").val();
//
//         var requestType = isLiked ? 'delete' : 'put';
//
//         if(requestType === 'delete'){
//             $.ajax({
//                 type: "delete",
//                 url: '/like/remove/' + eventNo + '/' + userId + '/' + eventType,
//                 success: function(response) {
//                     if (response === 'liked') {
//                         $('.bookmark').addClass('liked');
//                     } else if (response === 'unliked') {
//                         $('.bookmark').removeClass('liked');
//                     }
//                 },
//                 error: function(xhr, status, error) {
//                     console.error('Error:', error);
//                 }
//             });
//         } else {
//             $.ajax({
//                 type: "put",
//                 url: '/like/add',
//                 contentType: 'application/json;charset=utf-8',
//                 data: JSON.stringify({
//                     "event_type": eventType,
//                     "event_no": eventNo,
//                     "user_id": userId,
//                     "user_name": userName
//                 }),
//                 success: function(response) {
//                     if (response === 'liked') {
//                         $('.bookmark').addClass('liked');
//                     } else if (response === 'unliked') {
//                         $('.bookmark').removeClass('liked');
//                     }
//                 },
//                 error: function(xhr, status, error) {
//                     console.error('Error:', error);
//                 }
//             });
//         }
//     });
// });

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
            contentElement.style.display = "block";
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

    function updatePage() {
        cards.forEach((card, index) => {
            card.style.display = (index >= (currentPage - 1) * pageSize && index < currentPage * pageSize) ? '' : 'none';
        });
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
    if (popupInfo.style.display === "none" || popupInfo.style.display === "") {
        popupInfo.style.display = "block";
        arrow.classList.add("on");
    } else {
        popupInfo.style.display = "none";
        arrow.classList.remove("on");
    }
}

// function toggleMenu() {
//     var modal = document.getElementById('menuModal');
//     if (modal.style.display === "none" || modal.style.display === "") {
//         modal.style.display = "block";
//     } else {
//         modal.style.display = "none";
//     }
// }