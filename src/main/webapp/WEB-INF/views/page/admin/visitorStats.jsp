<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>방문자 통계</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="<c:url value='/css/styles.css' />">
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    <style>
        .chart-container {
            position: relative;
            margin: auto;
            height: 400px;
            width: 600px;
        }
    </style>
</head>
<body>
<div class="container-fluid">
    <div class="row">
        <!-- 사이드바 -->
        <nav class="col-md-2 d-none d-md-block bg-light sidebar">
            <jsp:include page="/WEB-INF/views/page/admin/layout/sidebar.jsp"/>
        </nav>

        <!-- 메인 콘텐츠 -->
        <main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4">
            <jsp:include page="/WEB-INF/views/page/admin/layout/header.jsp"/>
            <h2>방문자 통계</h2>
            <!-- 방문자 통계 내용 -->
            <div class="chart-container">
                <canvas id="visitorChart"></canvas>
            </div>
            <jsp:include page="/WEB-INF/views/page/admin/layout/footer.jsp"/>
        </main>
    </div>
</div>
<script>
    document.addEventListener("DOMContentLoaded", function() {
        var ctx = document.getElementById('visitorChart').getContext('2d');

        // JSON 형식의 visitorStats 데이터를 가져옴
        var visitorStats = JSON.parse('${visitorStatsJson}');

        var labels = visitorStats.map(stat => stat.month);
        var data = visitorStats.map(stat => stat.total_visits);

        var visitorData = {
            labels: labels,
            datasets: [{
                label: '방문자 수',
                data: data,
                backgroundColor: '#36A2EB',
                borderColor: '#36A2EB',
                fill: false,
            }]
        };

        var visitorChart = new Chart(ctx, {
            type: 'line',
            data: visitorData,
            options: {
                responsive: true,
                maintainAspectRatio: false,
                scales: {
                    x: {
                        title: {
                            display: true,
                            text: '월'
                        }
                    },
                    y: {
                        title: {
                            display: true,
                            text: '방문자 수'
                        }
                    }
                },
                plugins: {
                    legend: {
                        position: 'top',
                    }
                }
            }
        });
    });
</script>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
