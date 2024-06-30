<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>SNS 가입 분류 통계</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
    <link rel="stylesheet" href="<c:url value='/resources/css/adminCss/admin.css' />">
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    <style>
        .chart-container {
            position: relative;
            margin: auto;
            height: 400px;
            width: 400px;
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
            <h2>SNS 가입 분류 통계</h2>
            <!-- SNS 가입 분류 통계 내용 -->
            <div class="chart-container">
                <canvas id="snsChart"></canvas>
            </div>
            <jsp:include page="/WEB-INF/views/page/admin/layout/footer.jsp"/>
        </main>
    </div>
</div>
<script>
    document.addEventListener("DOMContentLoaded", function() {
        var ctx = document.getElementById('snsChart').getContext('2d');

        // JSON 형식의 snsStats 데이터를 가져옴
        var snsStats = JSON.parse('${snsStatsJson}');

        var labels = snsStats.map(stat => stat.user_sns);
        var data = snsStats.map(stat => stat.count);

        var snsData = {
            labels: labels,
            datasets: [{
                data: data,
                backgroundColor: ['#4285F4', '#FFEB3B', '#00C853'],
                hoverBackgroundColor: ['#357AE8', '#FBC02D', '#00E676']
            }]
        };

        var snsChart = new Chart(ctx, {
            type: 'pie',
            data: snsData,
            options: {
                responsive: true,
                maintainAspectRatio: false,
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
