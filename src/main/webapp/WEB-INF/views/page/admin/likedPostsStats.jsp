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
                <h2><i class="fas fa-chart-bar"></i> 좋아요 누른 게시글 통계</h2>
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
        let data = [];
        let labels = [];
        let backgroundColor = [];
        let borderColor = [];

        if (type === 'popup') {
            data = popupData.data;
            labels = popupData.labels;
            backgroundColor = 'rgba(255, 99, 132, 0.6)';
            borderColor = 'rgba(255, 99, 132, 1)';
        } else if (type === 'festival') {
            data = festivalData.data;
            labels = festivalData.labels;
            backgroundColor = 'rgba(54, 162, 235, 0.6)';
            borderColor = 'rgba(54, 162, 235, 1)';
        } else if (type === 'all') {
            const combinedData = popupData.data.concat(festivalData.data);
            const combinedLabels = popupData.labels.concat(festivalData.labels);

            // 좋아요 수 기준으로 정렬
            const combined = combinedData.map((likeCount, index) => ({
                likeCount,
                label: combinedLabels[index]
            }));

            combined.sort((a, b) => b.likeCount - a.likeCount);

            // 상위 20개 항목 선택
            const top20 = combined.slice(0, 20);

            data = top20.map(item => item.likeCount);
            labels = top20.map(item => item.label);
            backgroundColor = top20.map((item, index) => index < 10 ? 'rgba(255, 99, 132, 0.6)' : 'rgba(54, 162, 235, 0.6)');
            borderColor = top20.map((item, index) => index < 10 ? 'rgba(255, 99, 132, 1)' : 'rgba(54, 162, 235, 1)');
        }

        if (myChart) {
            myChart.destroy();
        }

        const ctx = document.getElementById('likedPostsChart').getContext('2d');
        myChart = new Chart(ctx, {
            type: 'bar',
            data: {
                labels: labels,
                datasets: [{
                    label: '좋아요 수',
                    data: data,
                    backgroundColor: backgroundColor,
                    borderColor: borderColor,
                    borderWidth: 1
                }]
            },
            options: {
                responsive: true,
                maintainAspectRatio: false,
                scales: {
                    y: {
                        beginAtZero: true,
                        ticks: {
                            stepSize: 1,
                            precision: 0
                        }
                    },
                    x: {
                        ticks: {
                            autoSkip: false,
                            maxRotation: 45,
                            minRotation: 45
                        }
                    }
                },
                plugins: {
                    legend: {
                        display: false
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

        // 초기 차트 표시 (전체)
        showChart('all');
    });
</script>
</body>
</html>