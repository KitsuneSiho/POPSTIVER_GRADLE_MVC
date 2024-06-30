<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>회원 및 SNS 가입 통계</title>
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@300;400;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
    <link rel="stylesheet" href="<c:url value='/resources/css/adminCss/admin.css' />">
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    <style>
        body {
            font-family: 'Noto Sans KR', sans-serif;
            background-color: #f9f9f9;
            color: #333;
        }
        .main-content {
            background-color: #ffffff;
            border-radius: 20px;
            box-shadow: 0 10px 30px rgba(0,0,0,0.1);
            margin-bottom: 50px;
            padding: 40px;
            transition: all 0.3s ease;
        }
        .main-content:hover {
            box-shadow: 0 15px 40px rgba(0,0,0,0.15);
        }
        h2 {
            color: #34495e;
            border-bottom: 2px solid #3498db;
            padding-bottom: 15px;
            margin-bottom: 40px;
            font-size: 2.2em;
            font-weight: 700;
        }
        .chart-container {
            position: relative;
            margin: auto;
            height: 450px;
            width: 100%;
            transition: all 0.4s ease;
        }
        .chart-container:hover {
            transform: scale(1.03);
        }
        .stat-summary {
            margin-top: 40px;
        }
        .stat-item {
            background-color: #ffffff;
            border-radius: 15px;
            padding: 25px;
            margin: 15px;
            transition: all 0.3s ease;
            box-shadow: 0 5px 15px rgba(0,0,0,0.08);
        }
        .stat-item:hover {
            transform: translateY(-8px);
            box-shadow: 0 8px 25px rgba(0,0,0,0.12);
        }
        .stat-label {
            font-weight: 600;
            color: #34495e;
            font-size: 1.1em;
            margin-bottom: 10px;
        }
        .stat-value {
            font-size: 2em;
            color: #3498db;
            font-weight: 700;
        }
        .total-members {
            background-color: #3498db;
            color: white;
            padding: 25px;
            border-radius: 15px;
            margin-bottom: 30px;
            text-align: center;
            box-shadow: 0 10px 30px rgba(52, 152, 219, 0.3);
        }
        .total-members .stat-label {
            color: #ffffff;
            font-size: 1.2em;
        }
        .total-members .stat-value {
            color: white;
            font-size: 3em;
            font-weight: 700;
        }
        .table-container {
            margin-top: 30px;
            overflow: hidden;
            border-radius: 15px;
            box-shadow: 0 5px 15px rgba(0,0,0,0.08);
        }
        table {
            width: 100%;
            border-collapse: separate;
            border-spacing: 0;
        }
        th, td {
            border: none;
            padding: 15px;
            text-align: left;
        }
        th {
            background-color: #f2f6fa;
            color: #34495e;
            font-weight: 600;
        }
        tr:nth-child(even) {
            background-color: #f9f9f9;
        }
        tr:hover {
            background-color: #f2f6fa;
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
                <div class="row">
                    <div class="col-md-6">
                        <div class="chart-container">
                            <canvas id="genderChart"></canvas>
                        </div>
                    </div>
                    <div class="col-md-6">
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
                </div>

                <h2 class="mt-5">SNS 가입 분류 통계</h2>
                <div class="stats-container row">
                    <div class="col-md-4">
                        <div class="stat-item">
                            <div class="stat-label">총 가입자 수</div>
                            <div class="stat-value" id="totalUsers"></div>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="stat-item">
                            <div class="stat-label">가장 많은 가입 SNS</div>
                            <div class="stat-value" id="topSNS"></div>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="stat-item">
                            <div class="stat-label">최근 30일 신규 가입자</div>
                            <div class="stat-value" id="newUsers"></div>
                        </div>
                    </div>
                </div>

                <div class="row mt-4">
                    <div class="col-md-6">
                        <div class="chart-container">
                            <canvas id="snsChart"></canvas>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="table-container">
                            <table>
                                <thead>
                                <tr>
                                    <th>SNS</th>
                                    <th>가입자 수</th>
                                    <th>비율</th>
                                </tr>
                                </thead>
                                <tbody id="snsTableBody">
                                </tbody>
                            </table>
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
        // 성별 차트
        var genderCtx = document.getElementById('genderChart').getContext('2d');
        var genderStats = JSON.parse('${genderStatsJson}');
        var genderLabels = genderStats.map(stat => stat.user_gender);
        var genderData = genderStats.map(stat => stat.count);

        var genderChart = new Chart(genderCtx, {
            type: 'doughnut',
            data: {
                labels: genderLabels,
                datasets: [{
                    data: genderData,
                    backgroundColor: ['#3498db', '#e74c3c'],
                    hoverBackgroundColor: ['#2980b9', '#c0392b']
                }]
            },
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

        // 성별 통계 요약 업데이트
        var totalMembers = genderData.reduce((a, b) => a + b, 0);
        document.getElementById('totalMembers').textContent = totalMembers;
        document.getElementById('maleCount').textContent = genderData[genderLabels.indexOf('male')] || 0;
        document.getElementById('femaleCount').textContent = genderData[genderLabels.indexOf('female')] || 0;

        // SNS 차트
        var snsCtx = document.getElementById('snsChart').getContext('2d');
        var snsStats = JSON.parse('${snsStatsJson}');
        var snsLabels = snsStats.map(stat => stat.user_sns);
        var snsData = snsStats.map(stat => stat.count);

        var snsChart = new Chart(snsCtx, {
            type: 'doughnut',
            data: {
                labels: snsLabels,
                datasets: [{
                    data: snsData,
                    backgroundColor: ['#3498db', '#f1c40f', '#2ecc71', '#e74c3c'],
                    hoverBackgroundColor: ['#2980b9', '#f39c12', '#27ae60', '#c0392b']
                }]
            },
            options: {
                responsive: true,
                maintainAspectRatio: false,
                cutout: '60%',
                plugins: {
                    legend: {
                        position: 'bottom',
                    },
                    tooltip: {
                        callbacks: {
                            label: function(context) {
                                var label = context.label || '';
                                var value = context.parsed || 0;
                                var total = context.dataset.data.reduce((a, b) => a + b, 0);
                                var percentage = ((value / total) * 100).toFixed(1);
                                return `${label}: ${value} (${percentage}%)`;
                            }
                        }
                    }
                }
            }
        });

        // SNS 테이블 데이터 채우기
        var tableBody = document.getElementById('snsTableBody');
        var snsTotal = snsData.reduce((a, b) => a + b, 0);
        snsStats.forEach(stat => {
            var row = tableBody.insertRow();
            var cellSNS = row.insertCell(0);
            var cellCount = row.insertCell(1);
            var cellPercentage = row.insertCell(2);
            cellSNS.textContent = stat.user_sns;
            cellCount.textContent = stat.count;
            cellPercentage.textContent = ((stat.count / snsTotal) * 100).toFixed(1) + '%';
        });

        // SNS 요약 통계 채우기
        document.getElementById('totalUsers').textContent = snsTotal;
        document.getElementById('topSNS').textContent = snsLabels[snsData.indexOf(Math.max(...snsData))];

        // 최근 30일 신규 가입자 수는 서버에서 받아와야 합니다.
        // 여기서는 임시로 0으로 설정합니다.
        document.getElementById('newUsers').textContent = '0';
    });
</script>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>