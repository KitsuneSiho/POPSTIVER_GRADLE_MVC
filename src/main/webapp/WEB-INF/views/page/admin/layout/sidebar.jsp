<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="sidebar-sticky">
    <ul class="nav flex-column">
        <li class="nav-item">
            <a class="nav-link" href="<c:url value='/' />">
                Popstiver
            </a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="<c:url value='/visitorStats' />">
                방문자 통계
            </a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="<c:url value='/genderStats' />">
                성별 통계
            </a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="<c:url value='/likedPostsStats' />">
                좋아요 누른 게시글 통계
            </a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="<c:url value='/mostViewedPostsStats' />">
                조회수 많은 게시글 통계
            </a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="<c:url value='/usersList' />">
                회원 관리
            </a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="<c:url value='/businessContents' />">
                비즈니스 문의 글 관리
            </a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="<c:url value='/chatManagement' />">
                1:1 채팅 관리
            </a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="<c:url value='/snsJoinedStats' />">
                SNS 가입 분류 통계
            </a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="<c:url value='/recentReviews' />">
                최근 리뷰
            </a>
        </li>
    </ul>
</div>
