document.addEventListener('DOMContentLoaded', function() {
    function safeParseJSON(jsonString, fallback) {
        try {
            return JSON.parse(jsonString);
        } catch (e) {
            console.error('Error parsing JSON:', e);
            return fallback;
        }
    }

    var visitorData = safeParseJSON(document.getElementById('visitorData').textContent, []);
    var chatData = safeParseJSON(document.getElementById('chatData').textContent, []);
    var likedPostsData = safeParseJSON(document.getElementById('likedPostsData').textContent, []);
    var festivalLikedPostsData = safeParseJSON(document.getElementById('festivalLikedPostsData').textContent, []);

    function formatDate(timestamp) {
        var date = new Date(timestamp);
        return date.toLocaleDateString();
    }

    // 차트 인스턴스를 저장할 전역 변수를 선언합니다.
    var visitorChart;
    var chatChart;
    var likedPostsChart;

    function createVisitorChart() {
        console.log('Creating visitor chart...');
        var visitorChartCanvas = document.getElementById('visitorChart');
        if (!visitorChartCanvas) {
            console.error('Cannot find canvas element with id "visitorChart"');
            return;
        }

        var visitorChartCtx = visitorChartCanvas.getContext('2d');

        // 기존 차트 인스턴스가 존재하면 제거합니다.
        if (visitorChart) {
            visitorChart.destroy();
        }

        visitorChart = new Chart(visitorChartCtx, {
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
                maintainAspectRatio: false,
                scales: {
                    y: {
                        beginAtZero: true
                    }
                }
            }
        });
    }

    function createChatChart() {
        console.log('Creating chat chart...');
        var chatChartCanvas = document.getElementById('chatChart');
        if (!chatChartCanvas) {
            console.error('Cannot find canvas element with id "chatChart"');
            return;
        }

        var chatChartCtx = chatChartCanvas.getContext('2d');

        // 기존 차트 인스턴스가 존재하면 제거합니다.
        if (chatChart) {
            chatChart.destroy();
        }

        chatChart = new Chart(chatChartCtx, {
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
                maintainAspectRatio: false,
                scales: {
                    y: {
                        beginAtZero: true
                    }
                }
            }
        });
    }

    function createLikedPostsChart() {
        console.log('Creating liked posts chart...');
        var likedPostsChartCanvas = document.getElementById('likedPostsChart');
        if (!likedPostsChartCanvas) {
            console.error('Cannot find canvas element with id "likedPostsChart"');
            return;
        }

        var likedPostsChartCtx = likedPostsChartCanvas.getContext('2d');

        // 기존 차트 인스턴스가 존재하면 제거합니다.
        if (likedPostsChart) {
            likedPostsChart.destroy();
        }

        var combinedLikedPostsData = likedPostsData.concat(festivalLikedPostsData);
        combinedLikedPostsData.sort((a, b) => b.likeCount - a.likeCount);
        var top5LikedPosts = combinedLikedPostsData.slice(0, 5);

        likedPostsChart = new Chart(likedPostsChartCtx, {
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
                maintainAspectRatio: false,
                scales: {
                    x: {
                        stacked: true,
                        ticks: {
                            callback: function(value) {
                                return this.getLabelForValue(value).substr(0, 20) + (this.getLabelForValue(value).length > 20 ? '...' : '');
                            }
                        }
                    },
                    y: {
                        beginAtZero: true,
                        title: {
                            display: true,
                            text: 'Likes'
                        }
                    }
                },
                plugins: {
                    legend: {
                        display: true,
                        position: 'top',
                    },
                    tooltip: {
                        callbacks: {
                            title: function(context) {
                                return context[0].label;
                            },
                            label: function(context) {
                                var post = top5LikedPosts[context.dataIndex];
                                return [
                                    'Likes: ' + context.parsed.y,
                                    'Type: ' + (post.event_type === 3 ? '팝업' : '페스티벌')
                                ];
                            }
                        }
                    }
                },
                barPercentage: 0.8,
                categoryPercentage: 0.9
            }
        });
    }

    // 차트를 생성하는 함수를 약간의 지연 후 호출합니다.
    setTimeout(function() {
        createVisitorChart();
        createChatChart();
        createLikedPostsChart();
    }, 100);
});