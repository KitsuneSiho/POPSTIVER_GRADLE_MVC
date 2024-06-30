<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>회원 통계</title>
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@300;400;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
    <link rel="stylesheet" href="<c:url value='/resources/css/adminCss/admin.css' />">
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    <style>
        body {
            font-family: 'Noto Sans KR', sans-serif;
            background-color: #f8f9fa;
        }
        .main-content {
            background-color: #ffffff;
            border-radius: 15px;
            box-shadow: 0 0 20px rgba(0,0,0,0.1);
            padding: 30px;
            margin-top: 20px;
        }
        h2 {
            color: #333;
            border-bottom: 2px solid #007bff;
            padding-bottom: 10px;
            margin-bottom: 30px;
        }
        .chart-container {
            position: relative;
            margin: auto;
            height: 400px;
            width: 400px;
            transition: all 0.3s ease;
        }
        .chart-container:hover {
            transform: scale(1.02);
        }
        .stat-summary {
            margin-top: 30px;
            text-align: center;
        }
        .stat-item {
            background-color: #f1f3f5;
            border-radius: 10px;
            padding: 20px;
            margin: 10px;
            transition: all 0.3s ease;
        }
        .stat-item:hover {
            background-color: #e9ecef;
            transform: translateY(-5px);
        }
        .stat-label {
            font-weight: bold;
            color: #495057;
        }
        .stat-value {
            font-size: 1.5em;
            color: #007bff;
        }
        .total-members {
            background-color: #007bff;
            color: white;
            padding: 15px;
            border-radius: 10px;
            margin-bottom: 20px;
            text-align: center;
        }
        .total-members .stat-label {
            color: #e9ecef;
        }
        .total-members .stat-value {
            color: white;
            font-size: 2em;
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
            <div class="main-content">
                <h2>회원 통계</h2>
                <div class="total-members">
                    <div class="stat-label">총 회원 수</div>
                    <div class="stat-value" id="totalMembers"></div>
                </div>
                <div class="chart-container">
                    <canvas id="genderChart"></canvas>
                </div>
                <div class="stat-summary row">
                    <div class="col-md-6">
                        <div class="stat-item">
                            <div class="stat-label">남성</div>
                            <div class="stat-value" id="maleCount"></div>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="stat-item">
                            <div class="stat-label">여성</div>
                            <div class="stat-value" id="femaleCount"></div>
                        </div>
                    </div>
                </div>
            </div>
            <jsp:include page="/WEB-INF/views/page/admin/layout/footer.jsp"/>
        </main>
    </div>
</div>
<script>
    document.addEventListener("DOMContentLoaded", function() {
        var ctx = document.getElementById('genderChart').getContext('2d');

        var genderStats = JSON.parse('${genderStatsJson}');

        var labels = genderStats.map(stat => stat.user_gender);
        var data = genderStats.map(stat => stat.count);

        var genderData = {
            labels: labels,
            datasets: [{
                data: data,
                backgroundColor: ['#4e73df', '#ff758f'],
                hoverBackgroundColor: ['#2e59d9', '#ff4d6d']
            }]
        };

        var genderChart = new Chart(ctx, {
            type: 'doughnut',
            data: genderData,
            options: {
                responsive: true,
                maintainAspectRatio: false,
                cutout: '70%',
                plugins: {
                    legend: {
                        position: 'bottom',
                    },
                    tooltip: {
                        callbacks: {
                            label: function(context) {
                                var label = context.label || '';
                                var value = context.parsed;
                                var total = context.dataset.data.reduce((acc, data) => acc + data, 0);
                                var percentage = Math.round((value / total) * 100);
                                return label + ': ' + value + ' (' + percentage + '%)';
                            }
                        }
                    }
                },
                animation: {
                    animateScale: true,
                    animateRotate: true
                }
            }
        });

        // 통계 요약 업데이트
        var totalMembers = data.reduce((a, b) => a + b, 0);
        document.getElementById('totalMembers').textContent = totalMembers;
        document.getElementById('maleCount').textContent = data[0];
        document.getElementById('femaleCount').textContent = data[1];
    });
</script>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>