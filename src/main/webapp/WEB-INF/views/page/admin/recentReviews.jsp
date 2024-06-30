<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>최근 리뷰</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
    <link rel="stylesheet" href="<c:url value='/resources/css/adminCss/admin.css' />">
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    <style>
        .reviews-container {
            margin-top: 20px;
        }
        .review-card {
            margin-bottom: 20px;
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
            <h2 class="mt-4">최근 리뷰</h2>
            <div class="reviews-container">
                <!-- 임시 리뷰 데이터 (실제 데이터로 교체 필요) -->
                <div class="card review-card">
                    <div class="card-body">
                        <h5 class="card-title">사용자 A</h5>
                        <h6 class="card-subtitle mb-2 text-muted">게시글 제목</h6>
                        <p class="card-text">리뷰 내용입니다. 이 제품은 정말 좋았습니다.</p>
                        <p class="card-text"><small class="text-muted">2023-06-30 작성</small></p>
                    </div>
                </div>
                <div class="card review-card">
                    <div class="card-body">
                        <h5 class="card-title">사용자 B</h5>
                        <h6 class="card-subtitle mb-2 text-muted">다른 게시글 제목</h6>
                        <p class="card-text">이 서비스는 개선이 필요해 보입니다.</p>
                        <p class="card-text"><small class="text-muted">2023-06-29 작성</small></p>
                    </div>
                </div>
                <!-- 추가 리뷰 카드들... -->
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