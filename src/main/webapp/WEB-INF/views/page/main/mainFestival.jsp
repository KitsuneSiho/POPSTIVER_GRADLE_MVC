<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<c:set var="root" value="${pageContext.request.contextPath }" />

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${root}/resources/css/mainCss/mainFestival.css">
    <title>POPSTIVER</title>
    <style>
        @font-face {
            font-family: Giants;
            src: url('${root}/resources/font/Giants-Inline.ttf');
        }

        @font-face {
            font-family: KBO;
            src: url('${root}/resources/font/KBO.ttf');
        }

        @font-face {
            font-family: Pre;
            src: url('${root}/resources/font/Pre.ttf');
        }
    </style>
</head>

<body>

<jsp:include page="/WEB-INF/views/page/fix/header.jsp" />

<div class="mainButton">
    <button class="popupButton" onclick="window.location.href='mainPopup'">
        POP-UP 보러가기
    </button>
</div>

<div class="mainPoster">
    <img src="${root}/resources/asset/포스터이미지/워터밤가로.webp" alt="">
</div>

<div class="main-content">
    <div class="popular">
        <div class="popularPosterText">
            <p class="popularText1">인기</p>
            <p class="popularText2" onclick="window.location.href='popularAddFestival'">더보기</p>
        </div>
        <div class="popularPoster">
            <div class="slide-container">
                <div class="slide-track">
                    <div class="poster-item">
                        <img src="${root}/resources/asset/포스터이미지/서울.webp" alt="" onclick="window.location.href='posterInfo'">
                        <img src="${root}/resources/asset/${isLiked ? '좋아요' : '아니좋아요'}.svg"
                             class="bookmark"
                             alt=""
                             data-event-no="${result.event_no}"
                             data-event-type="${result.event_type}">
                        <span class="like-count">${likeCount}</span>
                        <p class="poster-caption">서울 포스터</p>
                    </div>
                    <div class="poster-item">
                        <img src="${root}/resources/asset/포스터이미지/대구.webp" alt="">
                        <img src="${root}/resources/asset/좋아요.svg" class="bookmark" alt="">
                        <p class="poster-caption">대구 포스터</p>
                    </div>
                    <div class="poster-item">
                        <img src="${root}/resources/asset/포스터이미지/대전.webp" alt="">
                        <img src="${root}/resources/asset/좋아요.svg" class="bookmark" alt="">
                        <p class="poster-caption">대전 포스터</p>
                    </div>
                    <div class="poster-item">
                        <img src="${root}/resources/asset/포스터이미지/부산.webp" alt="">
                        <img src="${root}/resources/asset/좋아요.svg" class="bookmark" alt="">
                        <p class="poster-caption">부산 포스터</p>
                    </div>
                    <div class="poster-item">
                        <img src="${root}/resources/asset/포스터이미지/속초.webp" alt="">
                        <img src="${root}/resources/asset/좋아요.svg" class="bookmark" alt="">
                        <p class="poster-caption">속초 포스터</p>
                    </div>
                    <div class="poster-item">
                        <img src="${root}/resources/asset/포스터이미지/대구.webp" alt="">
                        <img src="${root}/resources/asset/좋아요.svg" class="bookmark" alt="">
                        <p class="poster-caption">대구 포스터</p>
                    </div>
                    <div class="poster-item">
                        <img src="${root}/resources/asset/포스터이미지/대전.webp" alt="">
                        <img src="${root}/resources/asset/좋아요.svg" class="bookmark" alt="">
                        <p class="poster-caption">대전 포스터</p>
                    </div>
                    <div class="poster-item">
                        <img src="${root}/resources/asset/포스터이미지/부산.webp" alt="">
                        <img src="${root}/resources/asset/좋아요.svg" class="bookmark" alt="">
                        <p class="poster-caption">부산 포스터</p>
                    </div>
                    <div class="poster-item">
                        <img src="${root}/resources/asset/포스터이미지/속초.webp" alt="">
                        <img src="${root}/resources/asset/좋아요.svg" class="bookmark" alt="">
                        <p class="poster-caption">속초 포스터</p>
                    </div>
                    <div class="poster-item">
                        <img src="${root}/resources/asset/포스터이미지/대구.webp" alt="">
                        <img src="${root}/resources/asset/좋아요.svg" class="bookmark" alt="">
                        <p class="poster-caption">대구 포스터</p>
                    </div>
                    <div class="poster-item">
                        <img src="${root}/resources/asset/포스터이미지/부산.webp" alt="">
                        <img src="${root}/resources/asset/좋아요.svg" class="bookmark" alt="">
                        <p class="poster-caption">부산 포스터</p>
                    </div>
                    <div class="poster-item">
                        <img src="${root}/resources/asset/포스터이미지/속초.webp" alt="">
                        <img src="${root}/resources/asset/좋아요.svg" class="bookmark" alt="">
                        <p class="poster-caption">속초 포스터</p>
                    </div>

                </div>
            </div>
        </div>
    </div>


    <div class="open">
        <div class="openPosterText">
            <p class="openText1">오픈 예정</p>
            <p class="openText2" onclick="window.location.href='openAddFestival'">더보기</p>
        </div>
        <div class="openPoster">
            <div class="slide-container">
                <div class="slide-track">
                    <c:choose>
                        <c:when test="${empty upcomingFestivals}">
                            <p style="color: white;">현재 오픈 예정인 페스티벌이 없습니다.</p>
                        </c:when>
                        <c:otherwise>
                            <c:forEach items="${upcomingFestivals}" var="festival">
                                <div class="open-item">
                                    <img src="${festival.festivalAttachment}" alt="${festival.festivalTitle}">
                                    <p class="open-caption">${festival.festivalTitle}</p>
                                    <div class="poster-overlay">
                                        <img src="${root}/resources/asset/좋아요.svg" class="bookmark" alt="">
                                        <a href="${root}/festival_Details/${festival.festivalNo}" class="poster-button">자세히 보기</a>
                                    </div>
                                </div>
                            </c:forEach>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </div>
    </div>
</div>

<jsp:include page="/WEB-INF/views/page/fix/footer.jsp" />
<script>
    document.addEventListener('DOMContentLoaded', function() {
        const sliders = document.querySelectorAll('.slide-container');

        sliders.forEach(slider => {
            const track = slider.querySelector('.slide-track');
            let isDown = false;
            let startX;
            let scrollLeft;
            let velocity = 0;
            let rafId = null;

            function stopMomentumTracking() {
                cancelAnimationFrame(rafId);
            }

            function momentumTracking() {
                const currentScrollLeft = slider.scrollLeft;

                slider.scrollLeft += velocity;
                velocity *= 0.95;

                if (Math.abs(velocity) > 0.5) {
                    rafId = requestAnimationFrame(momentumTracking);
                }

                // Handle infinite scroll
                const maxScroll = track.scrollWidth / 2;
                if (slider.scrollLeft >= maxScroll) {
                    slider.scrollLeft -= maxScroll;
                } else if (slider.scrollLeft <= 0) {
                    slider.scrollLeft += maxScroll;
                }
            }

            slider.addEventListener('mouseenter', () => {
                track.style.animationPlayState = 'paused';
            });

            slider.addEventListener('mouseleave', () => {
                track.style.animationPlayState = 'running';
            });

            slider.addEventListener('mousedown', (e) => {
                isDown = true;
                slider.classList.add('active');
                startX = e.pageX - slider.offsetLeft;
                scrollLeft = slider.scrollLeft;
                stopMomentumTracking();
            });

            slider.addEventListener('mouseleave', () => {
                isDown = false;
                slider.classList.remove('active');
            });

            slider.addEventListener('mouseup', () => {
                isDown = false;
                slider.classList.remove('active');
                momentumTracking();
            });

            slider.addEventListener('mousemove', (e) => {
                if(!isDown) return;
                e.preventDefault();
                const x = e.pageX - slider.offsetLeft;
                const walk = (x - startX) * 2;
                const prevScrollLeft = slider.scrollLeft;
                slider.scrollLeft = scrollLeft - walk;
                velocity = slider.scrollLeft - prevScrollLeft;
            });

            // Clone items for infinite scroll
            const items = track.querySelectorAll('.poster-item, .open-item');
            items.forEach(item => {
                const clone = item.cloneNode(true);
                track.appendChild(clone);
            });

            // Set initial scroll position
            slider.scrollLeft = track.scrollWidth / 4;
        });
    });

    document.addEventListener('DOMContentLoaded', function() {
        const posterItems = document.querySelectorAll('.poster-item, .open-item');

        posterItems.forEach(item => {
            const overlay = item.querySelector('.poster-overlay');
            const button = item.querySelector('.poster-button');

            if (overlay && button) {
                item.addEventListener('mouseenter', () => {
                    overlay.style.opacity = '1';
                });

                item.addEventListener('mouseleave', () => {
                    overlay.style.opacity = '0';
                });

                button.addEventListener('click', (e) => {
                    e.stopPropagation();
                    // 버튼 클릭 시 수행할 동작 추가
                    console.log('자세히 보기 버튼 클릭됨');
                });
            }
        });
    });
</script>
<script src="${root}/resources/js/bookmarkToggle.js"></script>
</body>
</html>
