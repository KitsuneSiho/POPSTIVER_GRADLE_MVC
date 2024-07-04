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
    <button class="festivalButton" onclick="window.location.href='${root}/mainFestival'">
        FESTIVAL 보러가기
    </button>
</div>

<div class="mainPoster">
    <div class="popular">
        <div class="popularPosterText">
            <p class="popularText1">인기 팝업</p>
            <p class="popularText2" onclick="window.location.href='${root}/popularAddPopup'">더보기</p>
        </div>
        <div class="popularPoster">
            <button class="arrow-btn prev-btn">&lt;</button>
            <div class="popularSlide-container">
                <div class="popularSlide-track">
                    <c:forEach items="${popularPopups}" var="event" varStatus="status">
                        <div class="popularPoster-item">
                            <img src="${event.attachment}" alt="${event.title}">
                            <div class="popularPoster-overlay">
                                <p class="popularPoster-caption">${event.title}</p>
                                <a href="${root}/popup_Details/${event.event_no}" class="popularPoster-button">자세히 보기</a>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
            <button class="arrow-btn next-btn">&gt;</button>
            <div class="slide-dots"></div>
        </div>
    </div>
</div>

<div class="main-content">

    <div class="open">
        <div class="openPosterText">
            <p class="openText1">오픈 예정 팝업</p>
            <p class="openText2" onclick="window.location.href='${root}/openAddPopup'">더보기</p>
        </div>
        <hr>
        <div class="openPoster">
            <div class="openSlide-container">
                <div class="openSlide-track">
                    <c:choose>
                        <c:when test="${empty upcomingPopups}">
                            <p style="color: white;">현재 오픈 예정인 팝업이 없습니다.</p>
                        </c:when>
                        <c:otherwise>
                            <c:forEach items="${upcomingPopups}" var="popup" begin="0" end="5">
                                <div class="openPoster-item">
                                    <img src="${popup.popupAttachment}" alt="${popup.popupTitle}">
                                    <p class="openPoster-caption">${popup.popupTitle}</p>
                                    <div class="openPoster-overlay">
                                        <a href="${root}/popup_Details/${popup.popupNo}" class="openPoster-button">자세히 보기</a>
                                    </div>
                                </div>
                            </c:forEach>
                        </c:otherwise>
                    </c:choose>
                </div>
                <div class="openSlide-track">
                    <c:choose>
                        <c:when test="${empty upcomingPopups}">
                            <p style="color: white;">현재 오픈 예정인 팝업이 없습니다.</p>
                        </c:when>
                        <c:otherwise>
                            <c:forEach items="${upcomingPopups}" var="popup" begin="6" end="11">
                                <div class="openPoster-item">
                                    <img src="${popup.popupAttachment}" alt="${popup.popupTitle}">
                                    <p class="openPoster-caption">${popup.popupTitle}</p>
                                    <div class="openPoster-overlay">
                                        <a href="${root}/popup_Details/${popup.popupNo}" class="openPoster-button">자세히 보기</a>
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
        const slider = document.querySelector('.openSlide-container');
        const tracks = slider.querySelectorAll('.openSlide-track');

        let isDown = false;
        let startX;
        let scrollLeft;
        let velocity = 0;
        let rafId = null;

        function stopMomentumTracking() {
            cancelAnimationFrame(rafId);
        }

        function momentumTracking() {
            slider.scrollLeft += velocity;
            velocity *= 0.95;

            if (Math.abs(velocity) > 0.5) {
                rafId = requestAnimationFrame(momentumTracking);
            }

            // Handle infinite scroll
            const maxScroll = tracks[0].scrollWidth;
            if (slider.scrollLeft >= maxScroll) {
                slider.scrollLeft = 0;  // 즉시 처음으로 돌아감
            } else if (slider.scrollLeft <= 0) {
                slider.scrollLeft = maxScroll - 1;  // 끝으로 즉시 이동
            }
        }

        slider.addEventListener('mouseenter', () => {
            tracks.forEach(track => {
                track.style.animationPlayState = 'paused';
            });
        });

        slider.addEventListener('mouseleave', () => {
            tracks.forEach(track => {
                track.style.animationPlayState = 'running';
            });
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


        // Set initial scroll position
        slider.scrollLeft = tracks[0].scrollWidth / 4;
    });
    document.addEventListener('DOMContentLoaded', function() {
        const posterItems = document.querySelectorAll('.openPoster-item');

        posterItems.forEach(item => {
            const overlay = item.querySelector('.popularPoster-overlay, .openPoster-overlay');
            const button = item.querySelector('.popularPoster-button, .openPoster-button');

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



    document.addEventListener('DOMContentLoaded', function() {
        const container = document.querySelector('.popularSlide-container');
        const track = document.querySelector('.popularSlide-track');
        const items = track.querySelectorAll('.popularPoster-item');
        const prevBtn = document.querySelector('.prev-btn');
        const nextBtn = document.querySelector('.next-btn');
        const dotsContainer = document.querySelector('.slide-dots');

        const itemWidth = items[0].offsetWidth;
        const totalSlides = items.length;
        let currentIndex = 0;
        let isAnimating = false;

        // 슬라이드 dots 생성
        for (let i = 0; i < totalSlides; i++) {
            const dot = document.createElement('div');
            dot.classList.add('dot');
            dotsContainer.appendChild(dot);
        }

        const dots = dotsContainer.querySelectorAll('.dot');

        function updateDots() {
            dots.forEach((dot, index) => {
                dot.classList.toggle('active', index === currentIndex);
            });
        }

        function moveSlide(index) {
            if (isAnimating) return;

            if (index < 0) {
                index = totalSlides - 1;
            } else if (index >= totalSlides) {
                index = 0;
            }

            const start = container.scrollLeft;
            const end = index * itemWidth;
            const duration = 500;
            let startTime = null;

            isAnimating = true;

            function animation(currentTime) {
                if (!startTime) startTime = currentTime;
                const timeElapsed = currentTime - startTime;
                const progress = Math.min(timeElapsed / duration, 1);
                const ease = easeInOutQuad(progress);
                container.scrollLeft = start + (end - start) * ease;

                if (progress < 1) {
                    requestAnimationFrame(animation);
                } else {
                    isAnimating = false;
                }
            }

            requestAnimationFrame(animation);

            currentIndex = index;
            updateDots();
        }

        function easeInOutQuad(t) {
            return t < 0.5 ? 2 * t * t : 1 - Math.pow(-2 * t + 2, 2) / 2;
        }

        prevBtn.addEventListener('click', () => {
            moveSlide(currentIndex - 1);
        });

        nextBtn.addEventListener('click', () => {
            moveSlide(currentIndex + 1);
        });

        dots.forEach((dot, index) => {
            dot.addEventListener('click', () => {
                moveSlide(index);
            });
        });

        // 초기 상태 설정
        updateDots();
    });

</script>
<script src="${root}/resources/js/bookmarkToggle.js"></script>
</body>
</html>