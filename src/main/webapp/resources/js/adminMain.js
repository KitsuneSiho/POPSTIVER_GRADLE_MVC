document.addEventListener('DOMContentLoaded', function() {
    var visitorData = JSON.parse(document.getElementById('visitorData').textContent);
    var chatData = JSON.parse(document.getElementById('chatData').textContent);
    var likedPostsData = JSON.parse(document.getElementById('likedPostsData').textContent);
    var recentReviewsData = JSON.parse(document.getElementById('recentReviewsData').textContent);

    function formatDate(timestamp) {
        var date = new Date(timestamp);
        return date.toLocaleDateString(); // 또는 원하는 날짜 형식으로 변환
    }

    var visitorChartCtx = document.getElementById('visitorChart').getContext('2d');
    var visitorChart = new Chart(visitorChartCtx, {
        type: 'line',
        data: {
            labels: visitorData.map(v => formatDate(v.visitDate)),
            datasets: [{
                label: 'Visitors',
                data: visitorData.map(v => v.visitCount),
                backgroundColor: 'rgba(54, 162, 235, 0.2)',
                borderColor: 'rgba(54, 162, 235, 1)',
                borderWidth: 1
            }]
        },
        options: {
            scales: {
                y: {
                    beginAtZero: true
                }
            }
        }
    });

    var chatChartCtx = document.getElementById('chatChart').getContext('2d');
    var chatChart = new Chart(chatChartCtx, {
        type: 'line',
        data: {
            labels: ['Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday', 'Sunday'],
            datasets: [{
                label: 'Chats',
                data: chatData,
                backgroundColor: 'rgba(153, 102, 255, 0.2)',
                borderColor: 'rgba(153, 102, 255, 1)',
                borderWidth: 1
            }]
        },
        options: {
            scales: {
                y: {
                    beginAtZero: true
                }
            }
        }
    });

    var likedPostsChartCtx = document.getElementById('likedPostsChart').getContext('2d');
    var likedPostsChart = new Chart(likedPostsChartCtx, {
        type: 'bar',
        data: {
            labels: likedPostsData.labels,
            datasets: [{
                label: 'Likes',
                data: likedPostsData.data,
                backgroundColor: 'rgba(255, 99, 132, 0.2)',
                borderColor: 'rgba(255, 99, 132, 1)',
                borderWidth: 1
            }]
        },
        options: {
            scales: {
                y: {
                    beginAtZero: true
                }
            }
        }
    });

    var recentReviewsChartCtx = document.getElementById('recentReviewsChart').getContext('2d');
    var recentReviewsChart = new Chart(recentReviewsChartCtx, {
        type: 'line',
        data: {
            labels: recentReviewsData.labels,
            datasets: [{
                label: 'Reviews',
                data: recentReviewsData.data,
                backgroundColor: 'rgba(75, 192, 192, 0.2)',
                borderColor: 'rgba(75, 192, 192, 1)',
                borderWidth: 1
            }]
        },
        options: {
            scales: {
                y: {
                    beginAtZero: true
                }
            }
        }
    });
});
