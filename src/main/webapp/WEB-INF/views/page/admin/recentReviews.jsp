<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>최근 리뷰</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/5.1.3/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
    <link rel="stylesheet" href="<c:url value='/resources/css/adminCss/admin.css' />">
    <style>
        .reviews-container {
            margin-top: 20px;
        }
        .review-card {
            margin-bottom: 10px;
            border-radius: 10px;
            box-shadow: 0 2px 4px rgba(0,0,0,0.1);
            width: 250px; /* 카드 너비 설정 */
            font-size: 0.8rem; /* 폰트 크기 조정 */
        }
        .review-card .card-body {
            padding: 10px;
        }
        .festival-review {
            border-left: 5px solid #007bff;
        }
        .popup-review {
            border-left: 5px solid #28a745;
        }
        .card-category {
            font-size: 0.8rem;
            font-weight: bold;
            margin-bottom: 5px;
        }
        .main-content {
            max-width: 1000px; /* 메인 콘텐츠 너비 조정 */
            margin: 0 auto; /* 중앙 정렬 */
            padding: 20px;
        }
        .filter-buttons {
            margin-top: 20px;
            margin-bottom: 20px;
        }
        .btn-custom {
            color: white; /* 텍스트 색상 통일 */
        }
        .btn-primary {
            background-color: #007bff;
            border: none;
        }
        .btn-warning {
            background-color: #ffc107;
            border: none;
            color: white; /* 텍스트 색상 흰색으로 설정 */
        }
        .btn-secondary {
            background-color: #6c757d;
            border: none;
        }
    </style>
    <script>
        function filterReviews(type) {
            var festivalReviews = document.getElementsByClassName('festival-review');
            var popupReviews = document.getElementsByClassName('popup-review');

            if (type === 'festival') {
                for (var i = 0; i < festivalReviews.length; i++) {
                    festivalReviews[i].style.display = 'block';
                }
                for (var i = 0; i < popupReviews.length; i++) {
                    popupReviews[i].style.display = 'none';
                }
            } else if (type === 'popup') {
                for (var i = 0; i < festivalReviews.length; i++) {
                    festivalReviews[i].style.display = 'none';
                }
                for (var i = 0; i < popupReviews.length; i++) {
                    popupReviews[i].style.display = 'block';
                }
            } else {
                for (var i = 0; i < festivalReviews.length; i++) {
                    festivalReviews[i].style.display = 'block';
                }
                for (var i = 0; i < popupReviews.length; i++) {
                    popupReviews[i].style.display = 'block';
                }
            }
        }
    </script>
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
        <main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-md-4 main-content">
            <h2 class="mt-4">최근 리뷰</h2>
            <div class="filter-buttons">
                <button class="btn btn-secondary btn-custom" onclick="filterReviews('all')">모두 보기</button>
                <button class="btn btn-primary btn-custom" onclick="filterReviews('festival')">축제 리뷰 보기</button>
                <button class="btn btn-warning btn-custom" onclick="filterReviews('popup')">팝업 리뷰 보기</button>
            </div>
            <div class="reviews-container d-flex flex-wrap">
                <!-- 모든 리뷰 데이터 -->
                <c:forEach var="comment" items="${festivalComments}">
                    <div class="card review-card festival-review m-2">
                        <div class="card-body">
                            <div class="card-category text-primary">축제 리뷰</div>
                            <h5 class="card-title">${comment.comment_writer}</h5>
                            <h6 class="card-subtitle mb-2 text-muted">축제 게시글 번호: ${comment.festival_no}</h6>
                            <p class="card-text">${comment.comment_content}</p>
                            <p class="card-text"><small class="text-muted">작성일: ${comment.comment_date}</small></p>
                            <p class="card-text"><small class="text-muted">방문일: ${comment.visit_date}</small></p>
                            <p class="card-text"><small class="text-muted">별점: ${comment.star_rate}</small></p>
                        </div>
                    </div>
                </c:forEach>
                <c:forEach var="comment" items="${popupComments}">
                    <div class="card review-card popup-review m-2">
                        <div class="card-body">
                            <div class="card-category text-warning">팝업 리뷰</div>
                            <h5 class="card-title">${comment.comment_writer}</h5>
                            <h6 class="card-subtitle mb-2 text-muted">팝업 게시글 번호: ${comment.popup_no}</h6>
                            <p class="card-text">${comment.comment_content}</p>
                            <p class="card-text"><small class="text-muted">작성일: ${comment.comment_date}</small></p>
                            <p class="card-text"><small class="text-muted">방문일: ${comment.visit_date}</small></p>
                            <p class="card-text"><small class="text-muted">별점: ${comment.star_rate}</small></p>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </main>
    </div>
</div>
<!-- 푸터 -->
<footer class="mt-auto">
    <jsp:include page="/WEB-INF/views/page/admin/layout/footer.jsp"/>
</footer>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/5.1.3/js/bootstrap.min.js"></script>
</body>
</html>
