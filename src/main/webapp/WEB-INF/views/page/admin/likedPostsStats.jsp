<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>좋아요 누른 게시글 통계</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
    <link rel="stylesheet" href="<c:url value='/resources/css/adminCss/admin.css' />">
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;500&display=swap" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    <style>
        body {
            font-family: 'Roboto', sans-serif;
            background-color: #f5f7fa;
        }
        .content-wrapper {
            padding: 20px;
            background-color: #ffffff;
            border-radius: 8px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
            margin-top: 20px;
        }
        h2 {
            color: #2c3e50;
            margin-bottom: 30px;
        }
        .btn-group {
            margin-bottom: 20px;
        }
        .btn-custom {
            background-color: #3498db;
            color: white;
            border: none;
            transition: background-color 0.3s;
        }
        .btn-custom:hover {
            background-color: #2980b9;
        }
        .chart-container {
            position: relative;
            height: 400px;
            width: 100%;
        }
    </style>
</head>
<body>
<header>
    <jsp:include page="/WEB-INF/views/page/admin/layout/header.jsp"/>
</header>
<div class="container-fluid">
    <div class="row">
        <!-- 사이드바 -->
        <nav class="col-md-2 d-none d-md-block bg-light sidebar">
            <jsp:include page="/WEB-INF/views/page/admin/layout/sidebar.jsp"/>
        </nav>

        <!-- 메인 콘텐츠 -->
        <main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4">
            <div class="content-wrapper">
                <h2><i class="fas fa-chart-bar"></i> 좋아요 많이 누른 게시글 통계</h2>
                <div class="btn-group" role="group">
                    <button type="button" class="btn btn-custom" onclick="showChart('all')">전체</button>
                    <button type="button" class="btn btn-custom" onclick="showChart('festival')">페스티벌</button>
                    <button type="button" class="btn btn-custom" onclick="showChart('popup')">팝업</button>
                </div>
                <div class="chart-container">
                    <canvas id="likedPostsChart"></canvas>
                </div>
            </div>
        </main>
    </div>
</div>

<!-- 푸터 -->
<footer class="mt-auto">
    <jsp:include page="/WEB-INF/views/page/admin/layout/footer.jsp"/>
</footer>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

<script>
    let myChart;
    let popupData;
    let festivalData;

    function showChart(type) {
        let labels = [];
        let datasets = [];

        if (type === 'popup') {
            labels = popupData.labels;
            datasets.push({
                label: '팝업 좋아요 수',
                data: popupData.data,
                backgroundColor: 'rgba(255, 99, 132, 0.6)',
                borderColor: 'rgba(255, 99, 132, 1)',
                borderWidth: 1
            });
        } else if (type === 'festival') {
            labels = festivalData.labels;
            datasets.push({
                label: '페스티벌 좋아요 수',
                data: festivalData.data,
                backgroundColor: 'rgba(54, 162, 235, 0.6)',
                borderColor: 'rgba(54, 162, 235, 1)',
                borderWidth: 1
            });
        } else if (type === 'all') {
            // Combine labels and sort them
            labels = [...new Set(popupData.labels.concat(festivalData.labels))].sort();

            // Prepare data for popup and festival, filling missing values with 0
            let popupLikes = labels.map(label => {
                let index = popupData.labels.indexOf(label);
                return index !== -1 ? popupData.data[index] : 0;
            });

            let festivalLikes = labels.map(label => {
                let index = festivalData.labels.indexOf(label);
                return index !== -1 ? festivalData.data[index] : 0;
            });

            datasets.push({
                label: '팝업 좋아요 수',
                data: popupLikes,
                backgroundColor: 'rgba(255, 99, 132, 0.6)',
                borderColor: 'rgba(255, 99, 132, 1)',
                borderWidth: 1
            });
            datasets.push({
                label: '페스티벌 좋아요 수',
                data: festivalLikes,
                backgroundColor: 'rgba(54, 162, 235, 0.6)',
                borderColor: 'rgba(54, 162, 235, 1)',
                borderWidth: 1
            });
        }

        if (myChart) {
            myChart.destroy();
        }

        const ctx = document.getElementById('likedPostsChart').getContext('2d');
        myChart = new Chart(ctx, {
            type: 'bar',
            data: {
                labels: labels,
                datasets: datasets
            },
            options: {
                responsive: true,
                maintainAspectRatio: false,
                scales: {
                    y: {
                        beginAtZero: true,
                        stacked: true,
                        ticks: {
                            stepSize: 1,
                            precision: 0
                        }
                    },
                    x: {
                        stacked: true,
                        ticks: {
                            autoSkip: false,
                            maxRotation: 45, // 레이블을 45도 기울임
                            minRotation: 45,
                            font: {
                                size: 10 // 폰트 크기를 줄임
                            }
                        }
                    }
                },
                plugins: {
                    legend: {
                        display: true // 범례 표시
                    },
                    tooltip: {
                        callbacks: {
                            label: function(context) {
                                return `좋아요 수: ${context.parsed.y}`;
                            }
                        }
                    }
                }
            }
        });
    }

    document.addEventListener('DOMContentLoaded', function() {
        popupData = {
            labels: [<c:forEach items="${popularPopupEvents}" var="event">'${fn:escapeXml(event.title)}',</c:forEach>],
            data: [<c:forEach items="${popularPopupEvents}" var="event">${event.likeCount},</c:forEach>]
        };
        festivalData = {
            labels: [<c:forEach items="${popularFestivalEvents}" var="event">'${fn:escapeXml(event.title)}',</c:forEach>],
            data: [<c:forEach items="${popularFestivalEvents}" var="event">${event.likeCount},</c:forEach>]
        };

        showChart('all');
    });
</script>
</body>
</html>
