document.addEventListener("DOMContentLoaded", function() {
    const bookmarks = document.querySelectorAll(".bookmark");

    bookmarks.forEach(function(bookmark) {
        bookmark.addEventListener("click", function() {
            const eventNo = this.getAttribute("data-event-no");
            const eventType = this.getAttribute("data-event-type");

            fetch('/api/like/toggle', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    'X-CSRF-TOKEN': document.querySelector('meta[name="_csrf"]').getAttribute('content')
                },
                body: JSON.stringify({
                    event_no: eventNo,
                    event_type: eventType
                }),
            })
                .then(response => response.json())
                .then(data => {
                    if (data.isLiked) {
                        this.setAttribute("src", "/resources/asset/좋아요.svg");
                    } else {
                        this.setAttribute("src", "/resources/asset/아니좋아요.svg");
                    }
                    // 좋아요 수 업데이트
                    const likeCountElement = this.nextElementSibling;
                    if (likeCountElement && likeCountElement.classList.contains('like-count')) {
                        likeCountElement.textContent = data.likeCount;
                    }
                    // 다른 페이지의 좋아요 상태 업데이트
                    updateOtherPages(eventNo, eventType, data.isLiked, data.likeCount);
                })
                .catch((error) => {
                    console.error('Error:', error);
                });
        });
    });
});

function updateOtherPages(eventNo, eventType, isLiked, likeCount) {
    const bookmarks = document.querySelectorAll(`.bookmark[data-event-no="${eventNo}"][data-event-type="${eventType}"]`);
    bookmarks.forEach(bookmark => {
        bookmark.setAttribute("src", isLiked ? "/resources/asset/좋아요.svg" : "/resources/asset/아니좋아요.svg");
        const likeCountElement = bookmark.nextElementSibling;
        if (likeCountElement && likeCountElement.classList.contains('like-count')) {
            likeCountElement.textContent = likeCount;
        }
    });
}