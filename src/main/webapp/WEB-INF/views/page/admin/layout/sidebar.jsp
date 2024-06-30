<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="sidebar-sticky bg-dark">
    <ul class="nav flex-column">
        <li class="nav-item">
            <a class="nav-link" href="<c:url value='/' />">
                <i class="fas fa-home"></i> Popstiver
            </a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="<c:url value='/admin' />">
                <i class="fas fa-crown"></i> adminPage
            </a>
        </li>

        <li class="nav-item">
            <a class="nav-link" href="<c:url value='/visitor-stats' />">
                <i class="fas fa-chart-line"></i> 방문자 통계
            </a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="<c:url value='/genderStats' />">
                <i class="fas fa-venus-mars"></i> 회원 통계
            </a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="<c:url value='/likedPostsStats' />">
                <i class="fas fa-heart"></i> 좋아요 게시글 통계
            </a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="<c:url value='/mostViewedPostsStats' />">
                <i class="fas fa-eye"></i> 조회수 많은 게시글
            </a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="<c:url value='/usersList' />">
                <i class="fas fa-users"></i> 회원 관리
            </a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="<c:url value='/businessContents' />">
                <i class="fas fa-briefcase"></i> 비즈니스 문의
            </a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="<c:url value='/chatManagement' />">
                <i class="fas fa-comments"></i> 1:1 채팅 관리
            </a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="<c:url value='/snsJoinedStats' />">
                <i class="fas fa-share-alt"></i> SNS 가입 통계
            </a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="<c:url value='/recentReviews' />">
                <i class="fas fa-star"></i> 최근 리뷰
            </a>
        </li>
    </ul>
</div>