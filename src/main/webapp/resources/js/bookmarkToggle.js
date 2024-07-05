let userLikes = [];

function getUserInfoAndSetUserId2() {
    $.ajax({
        type: "GET",
        url: "/member/getUserInfo",
        success: function(response) {
            if (response && response.user_id && response.user_nickname) {
// Set the user_id and user_nickname in the hidden input fields
                $("#user_id").val(response.user_id);
                $("#user_name").val(response.user_nickname);
                console.log(response.user_id);
                console.log(response.user_nickname);
                userId = response.user_id;
                userName = response.user_nickname;
            } else {
                console.error("사용자 정보를 가져오는 데 실패했습니다.");
            }
        },
        error: function (xhr, status, error) {
            console.error("사용자 정보를 가져오는 중 오류 발생: " + error);
        }
    });
}


var userId ="";
var userName ="";

function loadUserLikes() {
    return fetch('/api/like/user-likes')
        .then(response => response.json())
        .then(likes => {
            userLikes = likes;
            updateBookmarkIcons();
        })
        .catch(error => console.error('Error loading user likes:', error));
}

function updateBookmarkIcons() {
    document.querySelectorAll('.bookmark').forEach(bookmark => {
        const eventNo = bookmark.getAttribute('data-event-no');
        const eventType = bookmark.getAttribute('data-event-type');
        const isLiked = userLikes.some(like =>
            like.event_no.toString() === eventNo && like.event_type.toString() === eventType
        );
        bookmark.setAttribute('src', isLiked ? '/resources/asset/좋아요.svg' : '/resources/asset/아니좋아요.svg');
    });
}

function toggleLike(eventNo, eventType, element) {
    const csrfToken = document.querySelector('meta[name="_csrf"]')?.getAttribute('content');

    console.log("User ID:", userId);
    console.log("User Name:", userName); //여기가 문제


    fetch('/api/like/toggle', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'X-CSRF-TOKEN': csrfToken || ''
        },
        body: JSON.stringify({ event_no: eventNo, event_type: eventType, user_id: userId, user_name:userName }),
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(data => {
            console.log("Received response:", data);
            if (data.isLiked) {
                userLikes.push({ event_no: eventNo, event_type: eventType });
            } else {
                userLikes = userLikes.filter(like =>
                    !(like.event_no.toString() === eventNo && like.event_type.toString() === eventType)
                );
            }
            updateBookmarkIcons();

            // 좋아요 수 업데이트
            const likeCountElement = element.nextElementSibling;
            if (likeCountElement && likeCountElement.classList.contains('like-count')) {
                likeCountElement.textContent = data.likeCount;
            }

            // 다른 페이지의 좋아요 상태 업데이트
            updateOtherPages(eventNo, eventType, data.isLiked, data.likeCount);

            console.log("bookmarkToggle.js에 tgglelike도 받았다고 하네요~")
            console.log(userName, userId)
        })
        .catch(error => console.error('Error:', error));
}

function updateOtherPages(eventNo, eventType, isLiked, likeCount) {
    console.log("Updating other pages");
    console.log("Event No:", eventNo);
    console.log("Event Type:", eventType);
    console.log("Is Liked:", isLiked);
    console.log("Like Count:", likeCount);

    const bookmarks = document.querySelectorAll(`.bookmark[data-event-no="${eventNo}"][data-event-type="${eventType}"]`);
    bookmarks.forEach(bookmark => {
        bookmark.setAttribute("src", isLiked ? "/resources/asset/좋아요.svg" : "/resources/asset/아니좋아요.svg");
        const likeCountElement = bookmark.nextElementSibling;
        if (likeCountElement && likeCountElement.classList.contains('like-count')) {
            likeCountElement.textContent = likeCount;
        }
    });
}




document.addEventListener('DOMContentLoaded', () => {
    getUserInfoAndSetUserId2();

    loadUserLikes().then(() => {
        document.querySelectorAll('.bookmark').forEach(bookmark => {
            bookmark.addEventListener('click', function(event) {
                event.preventDefault();
                const eventNo = this.getAttribute('data-event-no');
                const eventType = this.getAttribute('data-event-type');
                toggleLike(eventNo, eventType, this);
            });
        });
    });
});

