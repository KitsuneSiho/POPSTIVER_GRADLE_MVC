<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Admin Dashboard</title>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" rel="stylesheet">
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="<c:url value='/resources/css/adminCss/admin.css' />">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.5.0/sockjs.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>
    <style>
        .dashboard-card {
            transition: transform 0.3s ease, box-shadow 0.3s ease;
        }
        .dashboard-card:hover {
            transform: translateY(-10px);
            box-shadow: 0 10px 20px rgba(0, 0, 0, 0.2);
        }
        .card-title {
            font-size: 1.5rem;
            font-weight: bold;
        }
        .card-subtitle {
            font-size: 1.2rem;
            color: #6c757d;
        }
        .card-link {
            font-size: 1rem;
            color: #007bff;
            text-decoration: none;
        }
        .card-link:hover {
            text-decoration: underline;
        }
        .review-item {
            border-bottom: 1px solid #dee2e6;
            padding: 10px 0;
        }
        .review-item:last-child {
            border-bottom: none;
        }
        .chart-container {
            text-align: center; /* 차트 컨테이너 가운데 정렬 */
            margin-bottom: 20px; /* 차트 간 간격 추가 */
        }
        canvas {
            display: inline-block; /* 캔버스를 인라인 블록으로 설정 */
            max-width: 100%; /* 캔버스 최대 너비 설정 */
            height: auto; /* 캔버스 높이를 자동으로 설정 */
        }
    </style>
</head>
<body>
<!-- 헤더 -->
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
        <main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-md-4">
            <h2 class="mt-4">Dashboard</h2>
            <div class="container mt-4">
                <!-- 대시보드 내용 -->
                <div class="row">
                    <div class="col-md-4 mb-4">
                        <div class="card dashboard-card">
                            <div class="card-body">
                                <h5 class="card-title">Total Users</h5>
                                <h6 class="card-subtitle mb-2 text-muted">${totalUsers}</h6>
                                <p class="card-text">${totalUsers - previousTotalUsers > 0 ? '+' : ''}${totalUsers - previousTotalUsers} Since last week</p>
                                <a href="<c:url value='/usersList' />" class="card-link">View Details</a>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4 mb-4">
                        <div class="card dashboard-card">
                            <div class="card-body">
                                <h5 class="card-title">New Signups</h5>
                                <h6 class="card-subtitle mb-2 text-muted">${newSignups}</h6>
                                <p class="card-text">
                                    <fmt:formatNumber value="${signupGrowthRate}" pattern="+#,##0.0;-#,##0.0" />% Since last week
                                </p>
                                <a href="<c:url value='/memberStats' />" class="card-link">View Details</a>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4 mb-4">
                        <div class="card dashboard-card">
                            <div class="card-body">
                                <h5 class="card-title">Business Inquiries</h5>
                                <h6 class="card-subtitle mb-2 text-muted">${businessInquiries}</h6>
                                <p class="card-text">
                                    <fmt:formatNumber value="${businessInquiriesGrowthRate}" pattern="+#,##0.0;-#,##0.0" />% Since last week
                                </p>
                                <a href="<c:url value='/businessContents' />" class="card-link">View Details</a>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- 추가 카드 및 그래프 -->
                <div class="row">
                    <div class="col-md-6 mb-4">
                        <div class="card dashboard-card">
                            <div class="card-body">
                                <h5 class="card-title">Visitors</h5>
                                <div class="chart-container">
                                    <canvas id="visitorChart"></canvas>
                                </div>
                                <a href="<c:url value='/visitor-stats' />" class="card-link">View Details</a>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6 mb-4">
                        <div class="card dashboard-card">
                            <div class="card-body">
                                <h5 class="card-title">1:1 Chats</h5>
                                <div id="chatNotifications" class="mb-3"></div>
                                <a href="<c:url value='/chatManagement' />" class="card-link">View Details</a>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-6 mb-4">
                        <div class="card dashboard-card">
                            <div class="card-body">
                                <h5 class="card-title">Top Liked Posts</h5>
                                <div class="chart-container">
                                    <canvas id="likedPostsChart"></canvas>
                                </div>
                                <a href="<c:url value='/likedPostsStats' />" class="card-link">View Details</a>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6 mb-4">
                        <div class="card dashboard-card">
                            <div class="card-body">
                                <h5 class="card-title">Recent Reviews</h5>
                                <div class="review-list">
                                    <c:forEach var="review" items="${recentComments}" varStatus="status">
                                        <c:if test="${status.index < 5}">
                                            <div class="review-item">
                                                <strong>${review.comment_writer}</strong> - ${review.comment_content}
                                                <br><small class="text-muted">${review.comment_date}</small>
                                            </div>
                                        </c:if>
                                    </c:forEach>
                                </div>
                                <a href="<c:url value='/recentReviews' />" class="card-link">View All Reviews</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </main>
    </div>
</div>
<!-- 푸터 -->
<footer class="mt-auto">
    <jsp:include page="/WEB-INF/views/page/admin/layout/footer.jsp"/>
</footer>
<!-- 데이터를 JSON 형태로 전달 -->
<script id="visitorData" type="application/json">${visitorDataJson}</script>
<script id="chatData" type="application/json">${chatDataJson}</script>
<script id="likedPostsData" type="application/json">${popularPopupEventsJson}</script>
<script id="festivalLikedPostsData" type="application/json">${popularFestivalEventsJson}</script>
<script id="recentReviewsData" type="application/json">${recentReviewsDataJson}</script>
<!-- JavaScript 파일 포함 -->
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
<script src="<c:url value='/resources/js/adminMain.js' />"></script>
</body>
</html>