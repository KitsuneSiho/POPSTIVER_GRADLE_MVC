function toggleLike(event_no, event_type) {
    const user_name = document.getElementById('user_name').value;
    const user_id = document.getElementById('user_id').value;

    fetch('/api/like/toggle', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({
            user_name: user_name,
            user_id: user_id,
            event_no: event_no,
            event_type: event_type
        }),
    })
        .then(response => response.json())
        .then(data => {
            const likeButton = document.querySelector('.bookmark');
            if (data.isLiked) {
                likeButton.classList.add('liked');
            } else {
                likeButton.classList.remove('liked');
            }
        })
        .catch((error) => {
            console.error('Error:', error);
        });
}