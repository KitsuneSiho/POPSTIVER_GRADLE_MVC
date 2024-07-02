<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="root" value="${pageContext.request.contextPath }" />

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="_csrf" content="${_csrf.token}"/>
    <meta name="_csrf_header" content="${_csrf.headerName}"/>
    <link rel="stylesheet" href="${root}/resources/css/searchResultCss/posterInfo.css">
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
    <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=9441e4fcdaf29ae0ef64a498fa8c752d&libraries=services"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="${root}/resources/js/popup_Details.js"></script>
    <script>
        // 이미지가 로드된 후 실행할 함수 정의
        function adjustImageSize() {
            var img = document.querySelector('.mainPoster img'); // 이미지 요소 선택

            if (img.complete) { // 이미지가 로드되었는지 확인
                var maxWidth = window.innerWidth; // 현재 창의 너비
                var maxHeight = window.innerHeight; // 현재 창의 높이

                var ratio = Math.min(maxWidth / img.naturalWidth, maxHeight / img.naturalHeight); // 이미지 비율 계산

                img.style.width = (img.naturalWidth * ratio) + 'px'; // 이미지 너비 설정
                img.style.height = (img.naturalHeight * ratio) + 'px'; // 이미지 높이 설정
            }
        }

        // 페이지 로드 시 실행할 함수 등록
        window.onload = function() {
            adjustImageSize(); // 이미지 크기 조정 함수 호출
        }

        // 창 크기 변경 시에도 이미지 크기 조정
        window.onresize = function() {
            adjustImageSize(); // 이미지 크기 조정 함수 호출
        }

    </script>
</head>
<body>

<input type="hidden" id="user_id" value="${sessionScope.user_id}">
<input type="hidden" id="user_name" value="${sessionScope.user_name}">

<jsp:include page="/WEB-INF/views/page/fix/header.jsp"/>

<div class="mainPoster">
    <img src="${popup.popup_attachment}" alt="">
</div>

<div class="detailInfo">
    <div class="detailInfoTitle">
        <ul>
            <li><button>${popup.popup_tag1}</button></li>
            <li><button>${popup.popup_tag2}</button></li>
            <li><button>${popup.popup_tag3}</button></li>
            <li><button>${popup.popup_tag4}</button></li>
            <li><button>${popup.popup_tag5}</button></li>

            <li><img src="${root}/resources/asset/조회수.svg" alt=""><p>${popup.views}</p></li>
            <li>
                <img src="${root}/resources/asset/${isLiked ? '좋아요' : '아니좋아요'}.svg"
                     class="bookmark"
                     alt=""
                     data-event-no="${popup.popup_no}"
                     data-event-type="${popup.event_type}">
<%--                <span class="like-count">${likeCount}</span>--%>
            </li>
            <li><img src="${root}/resources/asset/공유버튼.svg" alt="" onclick="toggleShareModal()">
                <!-- 공유 모달 창 -->
                <div id="shareModal" class="share-modal">
                    <div class="share-modal-content">
                        <div class="brand-item">
                            <p>브랜드 홈페이지</p>
                            <div class="link-container">
                                <div class="link-wrapper">
                                    <a id="brandWebsiteLink" href="${popup.brand_link}" target="_blank">${popup.brand_link}</a>
                                </div>
                                <button onclick="copyToClipboard('brandWebsiteLink')">
                                    <img src="${root}/resources/asset/복사버튼.svg" alt="">
                                </button>
                            </div>
                        </div>

                        <div class="brand-item">
                            <p>브랜드 SNS</p>
                            <div class="link-container">
                                <div class="link-wrapper">
                                    <a id="brandSNSLink" href="${popup.brand_sns}" target="_blank">${popup.brand_sns}</a>
                                </div>
                                <button onclick="copyToClipboard('brandSNSLink')">
                                    <img src="${root}/resources/asset/복사버튼.svg" alt="">
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </li>
        </ul>
        <h1 class="title">${popup.popup_title}</h1>
        <p class="detailDate">${popup.popup_start} ~ ${popup.popup_end}</p>
        <p class="detailAddress">
            <img src="${root}/resources/asset/위치표시.svg" alt="">
            ${popup.popup_location}
        </p>

        <div class="detailInfoTime">
            <p class="detailInfoTimeTitle">운영시간</p>
            <p class="detailInfoTimeInfo">
            <p>${popup.open_time}</p>
        </div>
        <p class="detailIntroduceTitle">팝업스토어, 페스티벌 소개</p>
        <div class="detailIntroduce">
            <p>${popup.popup_content}</p>
        </div>
        <p class="detailInfoMapTitle">행사 위치 (카카오 지도)</p>
        <div class="detailInfoMap">
            <div id="singleMap"></div>
        </div>
        <div class="detailInfoReview">
            <p class="detailInfoReviewTitle">후기</p>
            <p>댓글 ${allComments.size()}개</p>
            <form method="post" onsubmit="submitForm(event)">

                <input type="hidden" name="popup_no" value="${popup.popup_no}">
                <input type="hidden" name="event_type" value="${popup.event_type}">
                <input type="hidden" id="user_name" name="user_name" value="">
                <input type="hidden" id="user_id" name="user_id" value="">
                <div class="commentArea">
                    <input class="commentDate" type="date" name="visit_date" placeholder="방문일을 입력해주세요.">
                    <input class="commentContent" type="text" name="comment_content" placeholder="후기를 입력해주세요.">

                    <div class="stars" id="starRating">
                        <span class="new-star" data-value="1">&#9733;</span>
                        <span class="new-star" data-value="2">&#9733;</span>
                        <span class="new-star" data-value="3">&#9733;</span>
                        <span class="new-star" data-value="4">&#9733;</span>
                        <span class="new-star" data-value="5">&#9733;</span>
                    </div>
                    <input type="hidden" name="star_rate" id="star_rate">
                    <button type="submit">등록</button>
                </div>
            </form>
            <div class="detailInfoReviewTable">
                <table>
                    <!-- 댓글을 반복하여 출력 -->
                    <c:forEach var="comment" items="${allComments}">
                        <tr>
                            <td>
                                <div class="comment-header">
                                    <div class="name">${comment.comment_writer}</div>
                                    <div class="date">${comment.visit_date}</div>
                                </div>
                                <div class="star_rate">
                                    <c:forEach var="i" begin="1" end="5">
                                        <span class="star ${i <= comment.star_rate ? 'selected readonly' : 'readonly'}">&#9733;</span>
                                    </c:forEach>

                                    <img src="${root}/resources/asset/삭제버튼.svg" alt="" onclick="deleteComment(${comment.comment_no})"
                                         class="delete" style="display: none;" data-comment-writer="${comment.comment_writer}">
                                </div>
                            </td>
                            <td>
                                <div class="comment-text">${comment.comment_content}</div>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </div>

        </div>
    </div>
</div>

<div id="customAlertModal" class="custom-alert-modal">
    <div class="custom-alert-content">
        <p id="customAlertMessage"></p>
        <button class="custom-alert-close" onclick="closeCustomAlert()">확인</button>
    </div>
</div>



<jsp:include page="/WEB-INF/views/page/fix/footer.jsp" />
<script src="${root}/resources/js/bookmarkToggle.js"></script>
<script src="${root}/resources/js/shareModal.js"></script>
<script>
    var geocoder = new kakao.maps.services.Geocoder();

    // 첫 번째 지도: 특정 행사 위치
    var singleMapContainer = document.getElementById('singleMap'), // 지도를 표시할 div
        singleMapOption = {
            center: new kakao.maps.LatLng(37.5665, 126.9780), // 서울을 중심으로 기본값 설정
            level: 3 // 지도의 확대 레벨
        };

    var singleMap = new kakao.maps.Map(singleMapContainer, singleMapOption); // 지도를 생성합니다

    geocoder.addressSearch("${popup.popup_dist} ${popup.popup_subdist} ${popup.popup_location}", function(result, status) {
        if (status === kakao.maps.services.Status.OK) {
            const coords = new kakao.maps.LatLng(result[0].y, result[0].x);

            // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
            singleMap.setCenter(coords);

            // 결과값으로 받은 위치에 마커를 생성합니다
            const marker = new kakao.maps.Marker({
                map: singleMap,
                position: coords
            });
        } else {
            alert('주소를 찾을 수 없습니다.');
        }
    });


    document.addEventListener("DOMContentLoaded", function() {
        // 새 댓글 폼의 별점 요소들 선택
        var stars = document.querySelectorAll('#starRating .new-star');

        // 별점 요소들에 클릭 이벤트 추가
        stars.forEach(function(star) {
            star.addEventListener('click', function() {
                var value = this.getAttribute('data-value');
                document.getElementById('star_rate').value = value;

                // 모든 별점 요소의 선택 상태 초기화
                stars.forEach(function(s) {
                    s.classList.remove('selected');
                });

                // 클릭된 별점까지 선택 상태로 설정
                for (var i = 0; i < value; i++) {
                    stars[i].classList.add('selected');
                }
            });

            star.addEventListener('mouseover', function() {
                var value = this.getAttribute('data-value');
                // 모든 별점 요소의 호버 상태 초기화
                stars.forEach(function(s) {
                    s.classList.remove('hover');
                });

                // 호버된 별점까지 호버 상태로 설정
                for (var i = 0; i < value; i++) {
                    stars[i].classList.add('hover');
                }
            });

            star.addEventListener('mouseout', function() {
                // 모든 별점 요소의 호버 상태 제거
                stars.forEach(function(s) {
                    s.classList.remove('hover');
                });
            });
        });
    });
</script>


</body>
</html>
