<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="root" value="${pageContext.request.contextPath }" />

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${root}/resources/css/etcCss/map.css">
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
    </style>
    <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=9441e4fcdaf29ae0ef64a498fa8c752d&libraries=services"></script>


</head>

<body>
<jsp:include page="/WEB-INF/views/page/fix/header.jsp" />

<div class="map">
    <h3>모든 행사 위치 (카카오 지도)</h3>
    <div id="multiMap"></div>
</div>


<div class="searchList">
    <article>
        <div class="searchListOpen" onclick="toggleSearchList(this)">
            <p>진행 중</p>
            <img src="${root}/resources/asset/화살표.svg" class="arrow" alt="화살표">
        </div>
        <div class="popupFestivalInfo">
            <div class="carousel">
                <div class="carousel-content" id="carousel-content">
                    <div class="card">
                        <div class="card-content">
                            <img src="${root}/resources/asset/포스터이미지/흠뻑쇼6.gif" alt="포스터1">
                            <img src="${root}/resources/asset/좋아요.svg" class="bookmark" alt="">
                            <h3>It's Your Day: 이번 광고, 생일 카페 주인공은 바로 너!</h3>
                            <p>
                                <img src="${root}/resources/asset/위치표시.svg" class="cardAddress" alt="">
                                서울특별시 마포구
                            </p>
                            <p>
                                <img src="${root}/resources/asset/날짜.svg" class="cardDate" alt="">
                                24.05.02 - 24.06.30
                            </p>
                        </div>
                    </div>
                    <div class="card">
                        <div class="card-content">
                            <img src="${root}/resources/asset/포스터이미지/흠뻑쇼6.gif" alt="포스터1">
                            <img src="${root}/resources/asset/좋아요.svg" class="bookmark" alt="">
                            <h3>It's Your Day: 이번 광고, 생일 카페 주인공은 바로 너!</h3>
                            <p>
                                <img src="${root}/resources/asset/위치표시.svg" class="cardAddress" alt="">
                                서울특별시 마포구
                            </p>
                            <p>
                                <img src="${root}/resources/asset/날짜.svg" class="cardDate" alt="">
                                24.05.02 - 24.06.30
                            </p>
                        </div>
                    </div>
                    <div class="card">
                        <div class="card-content">
                            <img src="${root}/resources/asset/포스터이미지/흠뻑쇼6.gif" alt="포스터1">
                            <img src="${root}/resources/asset/좋아요.svg" class="bookmark" alt="">
                            <h3>It's Your Day: 이번 광고, 생일 카페 주인공은 바로 너!</h3>
                            <p>
                                <img src="${root}/resources/asset/위치표시.svg" class="cardAddress" alt="">
                                서울특별시 마포구
                            </p>
                            <p>
                                <img src="${root}/resources/asset/날짜.svg" class="cardDate" alt="">
                                24.05.02 - 24.06.30
                            </p>
                        </div>
                    </div>
                    <div class="card">
                        <div class="card-content">
                            <img src="${root}/resources/asset/포스터이미지/흠뻑쇼6.gif" alt="포스터1">
                            <img src="${root}/resources/asset/좋아요.svg" class="bookmark" alt="">
                            <h3>It's Your Day: 이번 광고, 생일 카페 주인공은 바로 너!</h3>
                            <p>
                                <img src="${root}/resources/asset/위치표시.svg" class="cardAddress" alt="">
                                서울특별시 마포구
                            </p>
                            <p>
                                <img src="${root}/resources/asset/날짜.svg" class="cardDate" alt="">
                                24.05.02 - 24.06.30
                            </p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </article>
</div>

<jsp:include page="/WEB-INF/views/page/fix/footer.jsp" />

<script src="${root}/resources/js/searchResult.js"></script>
<script src="${root}/resources/js/bookmarkToggle.js"></script>
<script>
    var geocoder = new kakao.maps.services.Geocoder();

    // 사용자의 위치를 가져오는 함수
    function getUserLocation(callback) {
        if (navigator.geolocation) {
            navigator.geolocation.getCurrentPosition(function(position) {
                var userLocation = {
                    lat: position.coords.latitude,
                    lng: position.coords.longitude
                };
                callback(userLocation);
            });
        } else {
            console.error('Geolocation is not supported by this browser.');
            // 기본 위치 (서울)
            var defaultLocation = {
                lat: 37.5665,
                lng: 126.9780
            };
            callback(defaultLocation);
        }
    }

    // 카카오맵 API 로드 후 초기화
    kakao.maps.load(function() {
        var multiMapContainer = document.getElementById('multiMap');

        getUserLocation(function(userLocation) {
            var multiMapOption = {
                center: new kakao.maps.LatLng(userLocation.lat, userLocation.lng),
                level: 7
            };

            var multiMap = new kakao.maps.Map(multiMapContainer, multiMapOption);

            var festivals = [
                <c:forEach var="festival" items="${allFestivals}" varStatus="loop">
                {
                    location: "${festival.festival_location}",
                    link: "${pageContext.request.contextPath}/festival_Details/${festival.festival_no}"
                }<c:if test="${!loop.last}">,</c:if>
                </c:forEach>
            ];

            // 함수 내에서 마커를 비동기적으로 추가
            function addMarkers(map, geocoder, festivals) {
                festivals.forEach(function(festival) {
                    geocoder.addressSearch(festival.location, function(result, status) {
                        if (status === kakao.maps.services.Status.OK) {
                            var coords = new kakao.maps.LatLng(result[0].y, result[0].x);

                            var marker = new kakao.maps.Marker({
                                map: map,
                                position: coords
                            });

                            kakao.maps.event.addListener(marker, 'click', function() {
                                window.location.href = festival.link;
                            });
                        } else {
                            console.error('주소를 찾을 수 없습니다:', festival.location);
                        }
                    });
                });
            }

            // 모든 주소에 대해 마커를 추가하는 함수 호출
            addMarkers(multiMap, geocoder, festivals);
        });
    });
</script>
</body>
</html>
