document.addEventListener("DOMContentLoaded", function() {
    const bookmarks = document.querySelectorAll(".bookmark");
    const csrfToken = document.querySelector('meta[name="_csrf"]')?.getAttribute('content');

    bookmarks.forEach(function(bookmark) {
        bookmark.addEventListener("click", function(event) {
            event.preventDefault();
            const eventNo = this.getAttribute("data-event-no");
            const eventType = this.getAttribute("data-event-type");

            console.log("Sending like toggle request");
            console.log("Event No:", eventNo);
            console.log("Event Type:", eventType);

            if (!eventNo || !eventType) {
                console.error("Event No or Event Type is missing");
                return;
            }

            fetch('/api/like/toggle', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    'X-CSRF-TOKEN': csrfToken || ''
                },
                body: JSON.stringify({
                    event_no: eventNo,
                    event_type: eventType
                }),
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