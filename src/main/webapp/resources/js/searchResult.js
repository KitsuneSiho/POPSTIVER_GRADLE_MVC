$(document).ready(function () {
    getUserInfoAndSetUserId();
});

function getUserInfoAndSetUserId() {
    $.ajax({
        type: "GET",
        url: "/member/getUserInfo",
        success: function (response) {
            if (response && response.user_id && response.user_nickname) {
// Set the user_id and user_nickname in the hidden input fields
                $("#user_id").val(response.user_id);
                $("#user_name").val(response.user_nickname);
            } else {
                console.error("사용자 정보를 가져오는 데 실패했습니다.");
            }
        },
        error: function (xhr, status, error) {
            console.error("사용자 정보를 가져오는 중 오류 발생: " + error);
        }
    });
}
$(document).ready(function () {
    // 페이지 로드 시 사용자 정보를 가져와서 hidden input 필드에 설정
    getUserInfoAndSetUserId();

    // 좋아요 버튼 클릭 이벤트 처리
    $('.bookmark').click(function() {
        var isLiked = $(this).hasClass('liked'); // 좋아요 여부 확인
        var eventType = $(this).closest('.card-content').data('eventtype'); // 이벤트 타입
        var eventNo = $(this).closest('.card-content').data('eventno'); // 이벤트 번호
        var userId = $("#user_id").val(); // hidden input에서 사용자 ID 가져오기
        var userName = $("#user_name").val(); // hidden input에서 사용자 이름 가져오기

        console.log(isLiked);
        console.log(eventType);
        console.log(eventNo);
        console.log(userId);
        console.log(userName);

        var requestType = isLiked ? 'delete' : 'put'; // 좋아요 상태에 따라 요청 타입 결정

        if(requestType === 'delete'){
            $.ajax({
                type: "delete",
                url: '/like/remove/' + eventNo + '/' + userId + '/' + eventType,
                success: function(response) {
                    if (response === 'liked') {
                        $('.bookmark').addClass('liked'); // 좋아요 상태로 변경
                    } else if (response === 'unliked') {
                        $('.bookmark').removeClass('liked'); // 좋아요 취소 상태로 변경
                    }
                },
                error: function(xhr, status, error) {
                    // 요청 실패 시 에러 처리
                    console.error('Error:', error);
                }
            });
        } else {
            $.ajax({
                type: "put",
                url: '/like/add',
                contentType: 'application/json;charset=utf-8',
                data: JSON.stringify({
                    "event_type": eventType,
                    "event_no": eventNo,
                    "user_id": userId,
                    "user_name": userName
                }),
                success: function(response) {
                    if (response === 'liked') {
                        $('.bookmark').addClass('liked'); // 좋아요 상태로 변경
                    } else if (response === 'unliked') {
                        $('.bookmark').removeClass('liked'); // 좋아요 취소 상태로 변경
                    }
                },
                error: function(xhr, status, error) {
                    // 요청 실패 시 에러 처리
                    console.error('Error:', error);
                }
            });
        }

    });
});

document.addEventListener('DOMContentLoaded', function() {
    const sections = [
        { id: 'ongoingSection', contentId: 'ongoingContent', hasResults: hasOngoing },
        { id: 'upcomingSection', contentId: 'upcomingContent', hasResults: hasUpcoming },
        { id: 'endedSection', contentId: 'endedContent', hasResults: hasEnded }
    ];

    sections.forEach(section => {
        if (section.hasResults) {
            const sectionElement = document.getElementById(section.id);
            const contentElement = document.getElementById(section.contentId);
            contentElement.style.display = "block";
            sectionElement.querySelector('img.arrow').classList.add("on");
        }
    });
});

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