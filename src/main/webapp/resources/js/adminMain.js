$(document).ready(function() {
    // 기존 코드 유지
    createVisitorChart();
    createLikedPostsChart();

    // 새로운 기능 추가
    let contextPath = 'http://localhost:8080';
    let stompClient = null;
    let chatNotifications = {};

    function connect() {
        let socket = new SockJS(contextPath + '/chat-websocket');
        stompClient = Stomp.over(socket);

        stompClient.connect({}, function(frame) {
            console.log('Connected: ' + frame);

            stompClient.subscribe('/topic/messages', function(messageOutput) {
                let message = JSON.parse(messageOutput.body);
                console.log("Received message:", message);

                if (message.receiver === 'admin') {
                    updateChatNotification(message);
                    saveNotification(message);
                }
            });

            stompClient.send("/app/chat.addUser", {}, JSON.stringify({sender: 'admin', type: 'JOIN'}));
        });
    }

    function updateChatNotification(message) {
        if (!chatNotifications[message.sender]) {
            chatNotifications[message.sender] = 0;
        }
        chatNotifications[message.sender]++;

        displayChatNotifications();
    }

    function displayChatNotifications() {
        let $chatNotifications = $("#chatNotifications");
        $chatNotifications.empty();

        for (let user in chatNotifications) {
            if (chatNotifications[user] > 0) {
                let $notification = $("<div>").addClass("alert alert-info")
                    .text(`${user}님으로부터 ${chatNotifications[user]}개의 메시지가 도착했습니다.`);
                $chatNotifications.append($notification);
            }
        }
    }

    function saveNotification(message) {
        $.ajax({
            type: "POST",
            url: contextPath + "/api/notifications/add",
            contentType: "application/json",
            data: JSON.stringify(message),
            success: function(response) {
                console.log(response);
            },
            error: function(error) {
                console.error("Error saving notification:", error);
            }
        });
    }

    function loadNotifications() {
        $.ajax({
            type: "GET",
            url: contextPath + "/api/notifications/get",
            success: function(notifications) {
                notifications.forEach(notification => {
                    updateChatNotification(notification);
                });
            },
            error: function(error) {
                console.error("Error loading notifications:", error);
            }
        });
    }

    function checkNewMessages() {
        $.ajax({
            type: "GET",
            url: contextPath + "/api/notifications/get",
            success: function(notifications) {
                notifications.forEach(notification => {
                    updateChatNotification(notification);
                });
            },
            error: function(error) {
                console.error("Error checking new messages:", error);
            }
        });
    }

    // 페이지 로드 시 실행
    connect();
    loadNotifications();

    // 5초마다 새 메시지 확인
    setInterval(checkNewMessages, 5000);
});

function createVisitorChart() {
    var visitorData = JSON.parse(document.getElementById('visitorData').textContent);
    var visitorChartCanvas = document.getElementById('visitorChart');
    if (visitorChartCanvas) {
        var visitorChartCtx = visitorChartCanvas.getContext('2d');
        new Chart(visitorChartCtx, {
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
    }
}

function createLikedPostsChart() {
    var likedPostsData = JSON.parse(document.getElementById('likedPostsData').textContent);
    var festivalLikedPostsData = JSON.parse(document.getElementById('festivalLikedPostsData').textContent);
    var likedPostsChartCanvas = document.getElementById('likedPostsChart');
    if (likedPostsChartCanvas) {
        var likedPostsChartCtx = likedPostsChartCanvas.getContext('2d');
        var combinedLikedPostsData = likedPostsData.concat(festivalLikedPostsData);
        combinedLikedPostsData.sort((a, b) => b.likeCount - a.likeCount);
        var top5LikedPosts = combinedLikedPostsData.slice(0, 5);

        new Chart(likedPostsChartCtx, {
            type: 'bar',
            data: {
                labels: top5LikedPosts.map(post => post.title),
                datasets: [
                    {
                        label: '페스티벌',
                        data: top5LikedPosts.map(post => post.event_type === 1 || post.event_type === 2 ? post.likeCount : 0),
                        backgroundColor: 'rgba(54, 162, 235, 0.6)',
                        borderColor: 'rgba(54, 162, 235, 1)',
                        borderWidth: 1
                    },
                    {
                        label: '팝업',
                        data: top5LikedPosts.map(post => post.event_type === 3 ? post.likeCount : 0),
                        backgroundColor: 'rgba(255, 99, 132, 0.6)',
                        borderColor: 'rgba(255, 99, 132, 1)',
                        borderWidth: 1
                    }
                ]
            },
            options: {
                scales: {
                    x: {
                        stacked: true
                    },
                    y: {
                        stacked: true,
                        beginAtZero: true
                    }
                }
            }
        });
    }
}

function formatDate(timestamp) {
    var date = new Date(timestamp);
    return date.toLocaleDateString();
}
