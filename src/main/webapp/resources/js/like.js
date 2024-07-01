function toggleLike(event_no, event_type) {
    const user_id = document.getElementById('user_id').value;
    const user_name = document.getElementById('user_name').value;

    fetch('/api/like/toggle', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({
            user_id: user_id,
            user_name: user_name,
            event_no: event_no,
            event_type: event_type
        }),
    })
        .then(response => response.json())
        .then(data => {
            const likeButton = document.querySelector('.bookmark');
            const likeCountElement = document.querySelector('.like-count');
            if (data.isLiked) {
                likeButton.src = "/resources/asset/좋아요.svg";
            } else {
                likeButton.src = "/resources/asset/아니좋아요.svg";
            }
            likeCountElement.textContent = data.likeCount;
        })
        .catch((error) => {
            console.error('Error:', error);
        });
}