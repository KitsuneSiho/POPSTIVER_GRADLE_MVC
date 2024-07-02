<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="root" value="${pageContext.request.contextPath }" />

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${root}/resources/css/mainCss/mainPopup.css">
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
    <button class="festivalButton" onclick="window.location.href='mainFestival'">
        FESTIVAL 보러가기
    </button>
</div>

<div class="mainPoster">
    <img src="${root}/resources/asset/포스터이미지/워터밤가로.webp" alt="">
</div>

<div class="main-content">
    <div class="popular">
        <div class="popularPosterText">
            <p class="popularText1">인기</p>
            <p class="popularText2" onclick="window.location.href='popularAddPopup'">더보기</p>
        </div>
        <div class="popularPoster">
            <div class="slide-container">
                <div class="slide-track">
                    <c:forEach items="${popularPopups}" var="event">
                        <div class="poster-item">
                            <img src="${event.attachment}" alt="${event.title}" onclick="window.location.href='posterInfo?event_no=${event.event_no}&event_type=${event.event_type}'">
                            <p class="poster-caption">${event.title}</p>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
    </div>


    <div class="open">
        <div class="openPosterText">
            <p class="openText1">오픈 예정</p>
            <p class="openText2" onclick="window.location.href='openAddPopup'">더보기</p>
        </div>
        <div class="openPoster">
            <div class="slide-container">
                <div class="slide-track">
                    <c:choose>
                        <c:when test="${empty upcomingPopups}">
                            <p style="color: white;">현재 오픈 예정인 팝업이 없습니다.</p>
                        </c:when>
                        <c:otherwise>
                            <c:forEach items="${upcomingPopups}" var="popup">
                                <div class="open-item">
                                    <img src="${popup.popupAttachment}" alt="${popup.popupTitle}">
                                    <p class="open-caption">${popup.popupTitle}</p>
                                    <div class="poster-overlay">
                                        <img src="${root}/resources/asset/좋아요.svg" class="bookmark" alt="">
                                        <a href="${root}/popup_Details/${popup.popupNo}" class="poster-button">자세히 보기</a>
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
