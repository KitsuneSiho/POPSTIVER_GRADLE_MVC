<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="<c:url value='/resources/css/adminCss/admin.css' />"></head>
<body>
<div class="sidebar-sticky bg-dark text-white">
    <ul class="nav flex-column">
        <li class="nav-item">
            <a class="nav-link text-white" href="<c:url value='/' />">
                Popstiver
            </a>
        </li>
        <li class="nav-item">
            <a class="nav-link text-white" href="<c:url value='/visitor-stats' />">
                방문자 통계
            </a>
        </li>
        <li class="nav-item">
            <a class="nav-link text-white" href="<c:url value='/genderStats' />">
                성별 통계
            </a>
        </li>
        <li class="nav-item">
            <a class="nav-link text-white" href="<c:url value='/likedPostsStats' />">
                좋아요 누른 게시글 통계
            </a>
        </li>
        <li class="nav-item">
            <a class="nav-link text-white" href="<c:url value='/mostViewedPostsStats' />">
                조회수 많은 게시글 통계
            </a>
        </li>
        <li class="nav-item">
            <a class="nav-link text-white" href="<c:url value='/usersList' />">
                회원 관리
            </a>
        </li>
        <li class="nav-item">
            <a class="nav-link text-white" href="<c:url value='/businessContents' />">
                비즈니스 문의 글 관리
            </a>
        </li>
        <li class="nav-item">
            <a class="nav-link text-white" href="<c:url value='/chatManagement' />">
                1:1 채팅 관리
            </a>
        </li>
        <li class="nav-item">
            <a class="nav-link text-white" href="<c:url value='/snsJoinedStats' />">
                SNS 가입 분류 통계
            </a>
        </li>
        <li class="nav-item">
            <a class="nav-link text-white" href="<c:url value='/recentReviews' />">
                최근 리뷰
            </a>
        </li>
    </ul>
</div>
</body>
</html>
