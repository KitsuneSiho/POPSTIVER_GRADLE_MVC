<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>조회수 많은 게시글 통계</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
    <link rel="stylesheet" href="<c:url value='/resources/css/adminCss/admin.css' />">
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    <style>
        body {
            display: flex;
            flex-direction: column;
            min-height: 100vh;
        }
        .container-fluid {
            flex: 1;
        }
        main {
            margin: 0 auto;
            float: none;
            padding: 20px;
        }
        .chart-container {
            width: 100%;
            height: 400px;
            margin-bottom: 30px;
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
        <main role="main" class="col-md-9 ml-sm-auto col-lg-8 px-4">
            <h2 class="mt-4 text-center">조회수 많은 게시글 통계</h2>

            <c:if test="${not empty topFestivals}">
                <div class="chart-container">
                    <canvas id="festivalChart"></canvas>
                </div>
            </c:if>
            <c:if test="${not empty topPopups}">
                <div class="chart-container">
                    <canvas id="popupChart"></canvas>
                </div>
            </c:if>
            <c:if test="${not empty topCommunityPosts}">
                <div class="chart-container">
                    <canvas id="communityChart"></canvas>
                </div>
            </c:if>
            <c:if test="${not empty topCompanionPosts}">
                <div class="chart-container">
                    <canvas id="companionChart"></canvas>
                </div>
            </c:if>
        </main>
    </div>
</div>
<!-- 푸터 -->
<footer class="mt-auto">
    <jsp:include page="/WEB-INF/views/page/admin/layout/footer.jsp"/>
</footer>
<script>
    // 차트 데이터 준비
    <c:if test="${not empty topFestivals}">
    const festivalData = {
        labels: [<c:forEach items="${topFestivals}" var="festival">'${festival.festival_title}',</c:forEach>],
        datasets: [{
            label: '축제 게시글 조회수',
            data: [<c:forEach items="${topFestivals}" var="festival">${festival.views},</c:forEach>],
            backgroundColor: 'rgba(255, 99, 132, 0.2)',
            borderColor: 'rgba(255, 99, 132, 1)',
            borderWidth: 1
        }]
    };
    </c:if>

    <c:if test="${not empty topPopups}">
    const popupData = {
        labels: [<c:forEach items="${topPopups}" var="popup">'${popup.popup_title}',</c:forEach>],
        datasets: [{
            label: '팝업 게시글 조회수',
            data: [<c:forEach items="${topPopups}" var="popup">${popup.views},</c:forEach>],
            backgroundColor: 'rgba(54, 162, 235, 0.2)',
            borderColor: 'rgba(54, 162, 235, 1)',
            borderWidth: 1
        }]
    };
    </c:if>

    <c:if test="${not empty topCommunityPosts}">
    const communityData = {
        labels: [<c:forEach items="${topCommunityPosts}" var="post">'${post.board_title}',</c:forEach>],
        datasets: [{
            label: '커뮤니티 게시글 조회수',
            data: [<c:forEach items="${topCommunityPosts}" var="post">${post.board_views},</c:forEach>],
            backgroundColor: 'rgba(255, 206, 86, 0.2)',
            borderColor: 'rgba(255, 206, 86, 1)',
            borderWidth: 1
        }]
    };
    </c:if>

    <c:if test="${not empty topCompanionPosts}">
    const companionData = {
        labels: [<c:forEach items="${topCompanionPosts}" var="post">'${post.comp_title}',</c:forEach>],
        datasets: [{
            label: '동행 게시글 조회수',
            data: [<c:forEach items="${topCompanionPosts}" var="post">${post.comp_views},</c:forEach>],
            backgroundColor: 'rgba(75, 192, 192, 0.2)',
            borderColor: 'rgba(75, 192, 192, 1)',
            borderWidth: 1
        }]
    };
    </c:if>

    // 차트 생성
    function createChart(ctx, data, title, maxYValue) {
        return new Chart(ctx, {
            type: 'bar',
            data: data,
            options: {
                responsive: true,
                scales: {
                    y: {
                        beginAtZero: true,
                        max: maxYValue,
                        stepSize: maxYValue / 10
                    }
                },
                plugins: {
                    title: {
                        display: true,
                        text: title
                    }
                }
            }
        });
    }

    // 차트 렌더링
    document.addEventListener('DOMContentLoaded', function() {
        <c:if test="${not empty topFestivals}">
        createChart(document.getElementById('festivalChart').getContext('2d'), festivalData, '페스티벌 게시글 조회수 Top 20', 6000);
        </c:if>
        <c:if test="${not empty topPopups}">
        createChart(document.getElementById('popupChart').getContext('2d'), popupData, '팝업 게시글 조회수 Top 20', 6000);
        </c:if>
        <c:if test="${not empty topCommunityPosts}">
        createChart(document.getElementById('communityChart').getContext('2d'), communityData, '커뮤니티 게시글 조회수 Top 20', 6000);
        </c:if>
        <c:if test="${not empty topCompanionPosts}">
        createChart(document.getElementById('companionChart').getContext('2d'), companionData, '동행 게시글 조회수 Top 20', 6000);
        </c:if>
    });
</script>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
