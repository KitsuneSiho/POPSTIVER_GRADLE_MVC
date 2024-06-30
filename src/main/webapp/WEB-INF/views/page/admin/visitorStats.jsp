<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>방문자 통계</title>
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@300;400;500;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.css">
    <link rel="stylesheet" href="<c:url value='/resources/css/adminCss/admin.css' />">
    <style>
        :root {
            --primary-color: #3498db;
            --secondary-color: #2c3e50;
            --background-color: #f3f3f3;
            --card-bg-color: #ffffff;
            --text-color: #34495e;
            --accent-color: #e74c3c;
            --box-shadow: 0 10px 30px rgba(0, 0, 0, 0.1);
            --border-radius: 15px;
        }
        body {
            font-family: 'Noto Sans KR', sans-serif;
            background-color: var(--background-color);
            color: var(--text-color);
        }
        .main-content {
            padding: 2rem;
        }
        .chart-container {
            margin-top: 2rem;
            padding: 2rem;
            background-color: var(--card-bg-color);
            border-radius: var(--border-radius);
            box-shadow: var(--box-shadow);
            transition: all 0.3s ease;
            opacity: 0;
            transform: translateY(20px);
            animation: fadeInUp 0.5s ease forwards;
        }
        @keyframes fadeInUp {
            to {
                opacity: 1;
                transform: translateY(0);
            }
        }
        h2 {
            text-align: center;
            margin-bottom: 1.5rem;
            color: var(--primary-color);
            font-weight: 700;
        }
        .chart-header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 1rem;
        }
        .chart-actions button {
            background-color: var(--primary-color);
            color: white;
            border: none;
            padding: 0.5rem 1rem;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }
        .chart-actions button:hover {
            background-color: #2980b9;
        }
        .stats-summary {
            display: flex;
            justify-content: space-around;
            flex-wrap: wrap;
            margin-bottom: 2rem;
        }
        .stat-card {
            background-color: var(--card-bg-color);
            border-radius: var(--border-radius);
            padding: 1.5rem;
            margin: 0.5rem;
            flex: 1 1 200px;
            text-align: center;
            box-shadow: var(--box-shadow);
            transition: all 0.3s ease;
        }
        .stat-card:hover {
            transform: translateY(-5px);
            box-shadow: 0 6px 12px rgba(0, 0, 0, 0.15);
        }
        .stat-card h3 {
            color: var(--secondary-color);
            font-size: 1.2rem;
            margin-bottom: 0.5rem;
        }
        .stat-card p {
            color: var(--accent-color);
            font-size: 1.8rem;
            font-weight: bold;
        }
        @media (max-width: 768px) {
            .main-content {
                padding: 1rem;
            }
            .chart-container {
                padding: 1rem;
            }
            .stat-card {
                flex: 1 1 100%;
            }
        }
        #slider {
            margin: 20px;
        }
    </style>
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js"></script>
    <script type="text/javascript">
        google.charts.load('current', {'packages':['corechart', 'bar']});
        google.charts.setOnLoadCallback(drawChart);

        // 통합되지 않은 원본 데이터
        var rawData = [
            <c:forEach var="stat" items="${statistics}">
            ['<fmt:formatDate value="${stat.visitDate}" pattern="yyyy-MM-dd"/>', ${stat.visitCount}],
            </c:forEach>
        ];

        // 데이터를 날짜별로 집계하는 함수
        function aggregateData(data) {
            let aggregatedData = {};

            data.forEach(entry => {
                let date = entry[0];
                let count = entry[1];

                if (aggregatedData[date]) {
                    aggregatedData[date] += count;
                } else {
                    aggregatedData[date] = count;
                }
            });

            let aggregatedArray = [];
            for (let date in aggregatedData) {
                aggregatedArray.push([date, aggregatedData[date]]);
            }

            return aggregatedArray;
        }

        // 원본 데이터를 집계
        var allData = aggregateData(rawData);

        function drawChart(startIndex = 0) {
            var data = new google.visualization.DataTable();
            data.addColumn('string', 'Date');
            data.addColumn('number', 'Visits');

            var viewData = allData.slice(startIndex, startIndex + 7);

            if (viewData.length === 0) {
                viewData.push(['No Data', 0]);
            }

            data.addRows(viewData);

            var options = {
                title: '웹사이트 방문자 통계',
                hAxis: {
                    title: '날짜',
                    gridlines: {color: '#f3f3f3'},
                    textStyle: {color: '#666'}
                },
                vAxis: {
                    title: '방문 수',
                    minValue: 0,
                    gridlines: {color: '#f3f3f3'},
                    textStyle: {color: '#666'}
                },
                colors: ['#3498db'],
                backgroundColor: 'transparent',
                chartArea: {width: '85%', height: '80%'},
                animation: {
                    startup: true,
                    duration: 1000,
                    easing: 'out',
                },
                bar: { groupWidth: '75%' },
            };

            var chart = new google.visualization.ColumnChart(document.getElementById('curve_chart'));

            chart.draw(data, options);
        }

        $(function() {
            $("#slider").slider({
                min: 0,
                max: allData.length - 7,
                slide: function(event, ui) {
                    drawChart(ui.value);
                }
            });
        });
    </script>
</head>
<body>
<div class="container-fluid">
    <div class="row">
        <!-- 사이드바 -->
        <nav class="col-md-2 d-none d-md-block bg-light sidebar">
            <jsp:include page="/WEB-INF/views/page/admin/layout/sidebar.jsp"/>
        </nav>

        <!-- 메인 콘텐츠 -->
        <main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4 main-content">
            <jsp:include page="/WEB-INF/views/page/admin/layout/header.jsp"/>

            <div class="stats-summary">
                <div class="stat-card">
                    <h3>총 방문자 수</h3>
                    <p>${totalVisits}</p>
                </div>
                <div class="stat-card">
                    <h3>일일 평균 방문자 수</h3>
                    <p><fmt:formatNumber value="${averageDailyVisits}" type="number" maxFractionDigits="1" /></p>
                </div>
                <div class="stat-card">
                    <h3>최다 방문일</h3>
                    <p>${peakVisitDate}</p>
                </div>
                <div class="stat-card">
                    <h3>최다 방문자 수</h3>
                    <p>${peakVisitCount}</p>
                </div>
            </div>

            <div class="chart-container">
                <div class="chart-header">
                    <h2>방문자 통계</h2>
                    <div class="chart-actions">
                        <button onclick="location.reload()"><i class="fas fa-sync-alt"></i> 새로고침</button>
                    </div>
                </div>
                <div id="curve_chart" style="width: 100%; height: 500px;"></div>
                <div id="slider"></div>
            </div>
            <jsp:include page="/WEB-INF/views/page/admin/layout/footer.jsp"/>
        </main>
    </div>
</div>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
