<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
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

        .container {
            width: 800px;
            margin: 0 auto;
            padding: 20px;
            background-color: #333;
            border-radius: 10px;
            position: relative;
        }

        .map {
            margin-top: 20px;
        }

        @font-face {
            font-family: KBO;
            src: url('${root}/resources/font/KBO.ttf');
        }

        .customoverlay {
            position: absolute;
            bottom: 80px;
            left: 50%;
            transform: translateX(-50%);
            border-radius: 6px;
            background-color: white;
            color: black;
            text-align: center;
            white-space: nowrap;
            padding: 5px;
            font-size: 20px;
        }

        #currentLocationButton {
            position: absolute;
            top: 20px;
            right: 20px;
            background-color: rgba(255, 255, 255, 0.8);
            border: none;
            border-radius: 5px;
            padding: 10px;
            cursor: pointer;
        }

        .dropdown-wrapper {
            position: relative;
            display: inline-block;
            margin-right: 10px;
        }

        .dropdown-content {
            display: none;
            position: absolute;
            background-color: #f9f9f9;
            min-width: 160px;
            box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
            z-index: 1;
        }

        .dropdown-wrapper:hover .dropdown-content {
            display: block;
        }
        /* 모달 창 스타일 */
        .modal {
            color:black;
            display: none;
            position: fixed;
            z-index: 1000;
            left: 0;
            top: 0;
            width: 100%;
            height: 100%;
            overflow: auto;
            background-color: rgb(0,0,0);
            background-color: rgba(0,0,0,0.4);
            padding-top: 60px;
        }

        .modal-content {
            background-color: #fefefe;
            margin: 5% auto;
            padding: 20px;
            border: 1px solid #888;
            width: 80%;
        }

        .close {
            color: #aaa;
            float: right;
            font-size: 28px;
            font-weight: bold;
        }

        .close:hover,
        .close:focus {
            color: black;
            text-decoration: none;
            cursor: pointer;
        }
    </style>
    <!-- Kakao Maps API -->
    <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=9441e4fcdaf29ae0ef64a498fa8c752d&libraries=services"></script>
    <!-- Bootstrap Icons -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css" rel="stylesheet">
</head>

<body>
<jsp:include page="/WEB-INF/views/page/fix/header.jsp" />

<div class="container">
    <div class="map">
        <h3>모든 행사 위치 (카카오 지도)</h3>
        <div id="multiMap" style="width:100%;height:600px;"></div>
        <div class="dropdown-wrapper">
            <button class="city-button" data-city="서울특별시">서울특별시</button>
            <div class="dropdown-content">
                <select class="district-select">
                    <option value="">구 선택</option>
                    <option value="강남구">강남구</option>
                    <option value="강동구">강동구</option>
                    <option value="강북구">강북구</option>
                    <option value="강서구">강서구</option>
                    <option value="관악구">관악구</option>
                    <option value="광진구">광진구</option>
                    <option value="구로구">구로구</option>
                    <option value="금천구">금천구</option>
                    <option value="노원구">노원구</option>
                    <option value="도봉구">도봉구</option>
                    <option value="동대문구">동대문구</option>
                    <option value="동작구">동작구</option>
                    <option value="마포구">마포구</option>
                    <option value="서대문구">서대문구</option>
                    <option value="서초구">서초구</option>
                    <option value="성동구">성동구</option>
                    <option value="성북구">성북구</option>
                    <option value="송파구">송파구</option>
                    <option value="양천구">양천구</option>
                    <option value="영등포구">영등포구</option>
                    <option value="용산구">용산구</option>
                    <option value="은평구">은평구</option>
                    <option value="종로구">종로구</option>
                    <option value="중구">중구</option>
                    <option value="중랑구">중랑구</option>
                </select>
            </div>
        </div>

        <div class="dropdown-wrapper">
            <button class="city-button" data-city="경기도">경기도</button>
            <div class="dropdown-content">
                <select class="district-select">
                    <option value="">시 또는 군 선택</option>
                    <option value="가평군">가평군</option>
                    <option value="고양시">고양시</option>
                    <option value="과천시">과천시</option>
                    <option value="광명시">광명시</option>
                    <option value="광주시">광주시</option>
                    <option value="구리시">구리시</option>
                    <option value="군포시">군포시</option>
                    <option value="김포시">김포시</option>
                    <option value="남양주시">남양주시</option>
                    <option value="동두천시">동두천시</option>
                    <option value="부천시">부천시</option>
                    <option value="성남시">성남시</option>
                    <option value="수원시">수원시</option>
                    <option value="시흥시">시흥시</option>
                    <option value="안산시">안산시</option>
                    <option value="안성시">안성시</option>
                    <option value="안양시">안양시</option>
                    <option value="양주시">양주시</option>
                    <option value="양평군">양평군</option>
                    <option value="여주시">여주시</option>
                    <option value="연천군">연천군</option>
                    <option value="오산시">오산시</option>
                    <option value="용인시">용인시</option>
                    <option value="의왕시">의왕시</option>
                    <option value="의정부시">의정부시</option>
                    <option value="이천시">이천시</option>
                    <option value="파주시">파주시</option>
                    <option value="평택시">평택시</option>
                    <option value="포천시">포천시</option>
                    <option value="하남시">하남시</option>
                    <option value="화성시">화성시</option>
                </select>
            </div>
        </div>
        <div class="dropdown-wrapper">
            <button class="city-button" data-city="경상남도">경상남도</button>
        </div>

        <div class="dropdown-wrapper">
            <button class="city-button" data-city="대구광역시">대구광역시</button>
        </div>

        <div class="dropdown-wrapper">
            <button class="city-button" data-city="제주특별자치도">제주특별자치도</button>
        </div>

        <div class="dropdown-wrapper">
            <button class="city-button" data-city="대전광역시">대전광역시</button>
        </div>

        <div class="dropdown-wrapper">
            <button class="city-button" data-city="전북특별자치도">전북특별자치도</button>
        </div>

        <div class="dropdown-wrapper">
            <button class="city-button" data-city="전라남도">전라남도</button>
        </div>

        <div class="dropdown-wrapper">
            <button class="city-button" data-city="부산광역시">부산광역시</button>
        </div>

        <div class="dropdown-wrapper">
            <button class="city-button" data-city="경상북도">경상북도</button>
        </div>

        <div class="dropdown-wrapper">
            <button class="city-button" data-city="인천광역시">인천광역시</button>
        </div>

        <div class="dropdown-wrapper">
            <button class="city-button" data-city="충청북도">충청북도</button>
        </div>

        <div class="dropdown-wrapper">
            <button class="city-button" data-city="충청남도">충청남도</button>
        </div>

        <div class="dropdown-wrapper">
            <button class="city-button" data-city="광주광역시">광주광역시</button>
        </div>

        <div class="dropdown-wrapper">
            <button class="city-button" data-city="울산광역시">울산광역시</button>
        </div>

        <div class="dropdown-wrapper">
            <button class="city-button" data-city="세종특별자치시">세종특별자치시</button>
        </div>

        <button id="currentLocationButton">내 위치로 돌아가기</button>
    </div>
</div>
<div id="festivalModal" class="modal">
    <div class="modal-content">
        <span class="close">&times;</span>
        <h2 id="festivalTitle"></h2>
        <p id="festivalDist"></p> <p id="festivalSubdist"></p> <p id="festivalLocation"></p>
        <p id="festivalStart"></p> <p id="festivalEnd"></p>
        <a id="festivalLink" href="#" target="_blank">상세 페이지로 이동</a>
    </div>
</div>
<div class="searchList">
    <!-- 생략: 다른 부분 -->
</div>

<jsp:include page="/WEB-INF/views/page/fix/footer.jsp" />

<script src="${root}/resources/js/searchResult.js"></script>
<script src="${root}/resources/js/bookmarkToggle.js"></script>
<script>
    var geocoder = new kakao.maps.services.Geocoder();
    var festivalMarkers = []; // 지역 축제 마커 배열
    var popupMarkers = []; // 팝업 마커 배열

    var festivalMarkerImage = new kakao.maps.MarkerImage(
        'http://t1.daumcdn.net/localimg/localimages/07/2018/pc/img/marker_spot.png',
        new kakao.maps.Size(30, 30),
        {
            offset: new kakao.maps.Point(15, 30)
        }
    );

    var popupMarkerImage = new kakao.maps.MarkerImage(
        'http://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markers_sprites.png',
        new kakao.maps.Size(24, 35),
        {
            offset: new kakao.maps.Point(15, 30)
        }
    );

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

    kakao.maps.load(function() {
        var multiMapContainer = document.getElementById('multiMap');

        getUserLocation(function(userLocation) {
            var multiMapOption = {
                center: new kakao.maps.LatLng(userLocation.lat, userLocation.lng),
                level: 7
            };

            var multiMap = new kakao.maps.Map(multiMapContainer, multiMapOption);

            // 사용자 위치 마커 설정
            var userMarkerImageSrc = 'https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png';
            var userMarkerImageSize = new kakao.maps.Size(24, 35);
            var userMarkerImageOption = { offset: new kakao.maps.Point(13, 35) };
            var userMarkerImage = new kakao.maps.MarkerImage(userMarkerImageSrc, userMarkerImageSize, userMarkerImageOption);

            var userMarker = new kakao.maps.Marker({
                position: new kakao.maps.LatLng(userLocation.lat, userLocation.lng),
                image: userMarkerImage,
                map: multiMap
            });

            var content = '<div class="customoverlay">내 위치</div>';
            var userOverlay = new kakao.maps.CustomOverlay({
                content: content,
                map: multiMap,
                position: userMarker.getPosition(),
                yAnchor: 1
            });

            // 지역 축제 데이터
            var festivals = [
                <c:forEach var="festival" items="${allFestivals}" varStatus="loop">
                {
                    title: "${festival.festival_title}",
                    location: "${festival.festival_location}",
                    link: "${pageContext.request.contextPath}/festival_Details/${festival.festival_no}",
                    dist: "${festival.festival_dist}",
                    subdist: "${festival.festival_subdist}",
                    start: "${festival.festival_start}",
                    end: "${festival.festival_end}"
                }<c:if test="${!loop.last}">,</c:if>
                </c:forEach>
            ];

            // 팝업 데이터
            var popups = [
                <c:forEach var="popup" items="${allPopups}" varStatus="loop">
                {
                    title: "${popup.popup_title}",
                    location: "${popup.popup_location}",
                    // link: "${pageContext.request.contextPath}/festival_Details/${popup.popup_no}",
                    dist: "${popup.popup_dist}",
                    subdist: "${popup.popup_subdist}",
                    start: "${popup.popup_start}",
                    end: "${popup.popup_end}"
                }<c:if test="${!loop.last}">,</c:if>
                </c:forEach>
            ];

            function addMarkers(map, geocoder, festivals) {
                console.log("지역축제 : " + festivals);
                festivals.forEach(function(festival) {
                    geocoder.addressSearch(festival.dist + festival.subdist + festival.location, function(result, status) {
                        if (status === kakao.maps.services.Status.OK) {
                            var coords = new kakao.maps.LatLng(result[0].y, result[0].x);

                            var marker = new kakao.maps.Marker({
                                map: map,
                                position: coords,
                                image: festivalMarkerImage,
                                data: festival
                            });

                            festivalMarkers.push(marker); // 지역 축제 마커 배열에 추가

                            kakao.maps.event.addListener(marker, 'click', function() {
                                var modal = document.getElementById('festivalModal');
                                document.getElementById('festivalTitle').textContent = festival.title;
                                document.getElementById('festivalDist').textContent = festival.dist;
                                document.getElementById('festivalSubdist').textContent = festival.subdist;
                                document.getElementById('festivalLocation').textContent = festival.location;
                                document.getElementById('festivalStart').textContent = festival.start;
                                document.getElementById('festivalEnd').textContent = festival.end;
                                document.getElementById('festivalLink').href = festival.link;
                                modal.style.display = "block";
                            });
                        } else {
                            console.error('주소를 찾을 수 없습니다:', festival.location);
                        }
                    });
                });

            }

            function addPopupMarkers(map, geocoder, popups) {
                console.log("팝업 : " + popups);
                popups.forEach(function(popup) {
                    geocoder.addressSearch(popup.dist + popup.subdist + popup.location, function(result, status) {
                        if (status === kakao.maps.services.Status.OK) {

                            var coords = new kakao.maps.LatLng(result[0].y, result[0].x);

                            var marker = new kakao.maps.Marker({
                                map: map,
                                position: coords,
                                image: popupMarkerImage,
                                data: popup
                            });

                            popupMarkers.push(marker); // 팝업 마커 배열에 추가

                            kakao.maps.event.addListener(marker, 'click', function() {
                                var modal = document.getElementById('festivalModal');
                                document.getElementById('festivalTitle').textContent = popup.title;
                                document.getElementById('festivalDist').textContent = popup.dist;
                                document.getElementById('festivalSubdist').textContent = popup.subdist;
                                document.getElementById('festivalLocation').textContent = popup.location;
                                document.getElementById('festivalStart').textContent = popup.start;
                                document.getElementById('festivalEnd').textContent = popup.end;
                                //document.getElementById('festivalLink').href = popup.link;
                                modal.style.display = "block";
                            });
                        } else {
                            console.error('주소를 찾을 수 없습니다:', popup.location);
                        }
                    });
                });

            }

            addMarkers(multiMap, geocoder, festivals);
            addPopupMarkers(multiMap, geocoder, popups);

            // 모든 도 버튼에 대한 이벤트 리스너 추가
            document.querySelectorAll('.city-button').forEach(function(button) {
                button.addEventListener('click', function() {
                    var city = this.getAttribute('data-city');
                    console.log("선택된 도시:", city);

                    // 지역축제 모든 마커 숨기기 (보이지 않게 설정)
                    festivalMarkers.forEach(function(marker) {
                        marker.setMap(null);
                    });

                    // 팝업 모든 마커 숨기기 (보이지 않게 설정)
                    popupMarkers.forEach(function(marker) {
                        marker.setMap(null);
                    });

                    // 모든 버튼의 active 클래스 제거
                    document.querySelectorAll('.city-button').forEach(function(btn) {
                        btn.classList.remove('active');
                    });

                    // 모든 구 선택 드롭다운 메뉴 숨기기
                    document.querySelectorAll('.dropdown-content').forEach(function(content) {
                        content.style.display = 'none';
                    });

                    // 선택된 버튼에 active 클래스 추가
                    this.classList.add('active');

                    // 서울특별시와 경기도 버튼에 대해서는 subdist 선택을 위한 드롭다운을 표시
                    if (city === '서울특별시' || city === '경기도') {
                        var dropdownContent = this.nextElementSibling; // 다음 형제 요소인 .dropdown-content 선택
                        dropdownContent.style.display = 'block';

                        // 선택된 도시와 구에 해당하는 축제만 표시
                        var districtSelect = dropdownContent.querySelector('.district-select');
                        districtSelect.addEventListener('change', function() {
                            var selectedDistrict = this.value;
                            console.log("선택된 도시:", city, "선택된 구:", selectedDistrict);

                            // 모든 마커 숨기기 (보이지 않게 설정)
                            festivalMarkers.forEach(function(marker) {
                                marker.setMap(null);
                            });
                            // 팝업 모든 마커 숨기기 (보이지 않게 설정)
                            popupMarkers.forEach(function(marker) {
                                marker.setMap(null);
                            });

                            // 선택된 버튼에 active 클래스 추가
                            button.classList.add('active');

                            // 선택된 도시와 구에 해당하는 축제만 표시
                            festivals.forEach(function(festival) {
                                if (festival.dist === city && festival.subdist === selectedDistrict) {
                                    geocoder.addressSearch(festival.dist + festival.subdist + festival.location, function(result, status) {
                                        if (status === kakao.maps.services.Status.OK) {
                                            var coords = new kakao.maps.LatLng(result[0].y, result[0].x);

                                            var marker = new kakao.maps.Marker({
                                                map: multiMap,
                                                position: coords,
                                                image: festivalMarkerImage,
                                                data: festival // 축제 데이터를 마커에 연결
                                            });

                                            festivalMarkers.push(marker); // markers 배열에 추가

                                            (function(marker, festival) {
                                                // 마커 클릭 시 모달 창에 정보 표시
                                                kakao.maps.event.addListener(marker, 'click', function() {
                                                    var modal = document.getElementById('festivalModal');
                                                    document.getElementById('festivalTitle').textContent = festival.title;
                                                    document.getElementById('festivalDist').textContent = festival.dist;
                                                    document.getElementById('festivalSubdist').textContent = festival.subdist;
                                                    document.getElementById('festivalLocation').textContent = festival.location;
                                                    document.getElementById('festivalStart').textContent = festival.start;
                                                    document.getElementById('festivalEnd').textContent = festival.end;
                                                    document.getElementById('festivalLink').href = festival.link;
                                                    modal.style.display = "block";
                                                });
                                            })(marker, festival);
                                        } else {
                                            console.error('주소를 찾을 수 없습니다:', festival.location);
                                        }
                                    });
                                }
                            });

                            // 선택된 도시와 구에 해당하는 축제만 표시
                            popups.forEach(function(popup) {
                                if (popup.dist === city && popup.subdist === selectedDistrict) {
                                    geocoder.addressSearch(popup.dist + popup.subdist + popup.location, function(result, status) {
                                        if (status === kakao.maps.services.Status.OK) {
                                            var coords = new kakao.maps.LatLng(result[0].y, result[0].x);

                                            var marker = new kakao.maps.Marker({
                                                map: multiMap,
                                                position: coords,
                                                image: popupMarkerImage,
                                                data: popup // 축제 데이터를 마커에 연결
                                            });

                                            popupMarkers.push(marker); // markers 배열에 추가

                                            (function(marker, popup) {
                                                // 마커 클릭 시 모달 창에 정보 표시
                                                kakao.maps.event.addListener(marker, 'click', function() {
                                                    var modal = document.getElementById('festivalModal');
                                                    document.getElementById('festivalTitle').textContent = popup.title;
                                                    document.getElementById('festivalDist').textContent = popup.dist;
                                                    document.getElementById('festivalSubdist').textContent = popup.subdist;
                                                    document.getElementById('festivalLocation').textContent = popup.location;
                                                    document.getElementById('festivalStart').textContent = popup.start;
                                                    document.getElementById('festivalEnd').textContent = popup.end;
                                                    document.getElementById('festivalLink').href = popup.link;
                                                    modal.style.display = "block";
                                                });
                                            })(marker, popup);
                                        } else {
                                            console.error('주소를 찾을 수 없습니다:', popup.location);
                                        }
                                    });
                                }
                            });
                        });
                    }
                    else {
                        // 서울특별시와 경기도를 제외한 버튼은 바로 해당 도시의 행사만 표시
                        festivals.forEach(function(festival) {
                            if (festival.dist === city) {
                                geocoder.addressSearch(festival.dist + festival.subdist + festival.location, function(result, status) {
                                    if (status === kakao.maps.services.Status.OK) {
                                        var coords = new kakao.maps.LatLng(result[0].y, result[0].x);

                                        var marker = new kakao.maps.Marker({
                                            map: multiMap,
                                            position: coords,
                                            image: festivalMarkerImage,
                                            data: festival // 축제 데이터를 마커에 연결
                                        });

                                        markers.push(marker); // markers 배열에 추가

                                        (function(marker, festival) {
                                            // 마커 클릭 시 모달 창에 정보 표시
                                            kakao.maps.event.addListener(marker, 'click', function() {
                                                var modal = document.getElementById('festivalModal');
                                                document.getElementById('festivalTitle').textContent = festival.title;
                                                document.getElementById('festivalDist').textContent = festival.dist;
                                                document.getElementById('festivalSubdist').textContent = festival.subdist;
                                                document.getElementById('festivalLocation').textContent = festival.location;
                                                document.getElementById('festivalStart').textContent = festival.start;
                                                document.getElementById('festivalEnd').textContent = festival.end;
                                                document.getElementById('festivalLink').href = festival.link;
                                                modal.style.display = "block";
                                            });
                                        })(marker, festival);
                                    } else {
                                        console.error('주소를 찾을 수 없습니다:', festival.location);
                                    }
                                });
                            }
                        });

                        popups.forEach(function(popup) {
                            if (popup.dist === city) {
                                geocoder.addressSearch(popup.dist + popup.subdist + popup.location, function(result, status) {
                                    if (status === kakao.maps.services.Status.OK) {
                                        var coords = new kakao.maps.LatLng(result[0].y, result[0].x);

                                        var marker = new kakao.maps.Marker({
                                            map: multiMap,
                                            position: coords,
                                            image: popupMarkerImage,
                                            data: popup // 축제 데이터를 마커에 연결
                                        });

                                        popupMarkers.push(marker); // markers 배열에 추가

                                        (function(marker, popup) {
                                            // 마커 클릭 시 모달 창에 정보 표시
                                            kakao.maps.event.addListener(marker, 'click', function() {
                                                var modal = document.getElementById('festivalModal');
                                                document.getElementById('festivalTitle').textContent = popup.title;
                                                document.getElementById('festivalDist').textContent = popup.dist;
                                                document.getElementById('festivalSubdist').textContent = popup.subdist;
                                                document.getElementById('festivalLocation').textContent = popup.location;
                                                document.getElementById('festivalStart').textContent = popup.start;
                                                document.getElementById('festivalEnd').textContent = popup.end;
                                                document.getElementById('festivalLink').href = popup.link;
                                                modal.style.display = "block";
                                            });
                                        })(marker, popup);
                                    } else {
                                        console.error('주소를 찾을 수 없습니다:', popup.location);
                                    }
                                });
                            }
                        });
                    }
                });
            });
            // 모달 창 닫기 기능
            var modal = document.getElementById('festivalModal');
            var span = document.getElementsByClassName('close')[0];
            span.onclick = function() {
                modal.style.display = "none";
            }
            window.onclick = function(event) {
                if (event.target == modal) {
                    modal.style.display = "none";
                }
            }

            // 내 위치로 돌아가는 버튼 클릭 시 지도 중심 이동
            document.getElementById('currentLocationButton').addEventListener('click', function() {
                getUserLocation(function(newUserLocation) {
                    multiMap.setCenter(new kakao.maps.LatLng(newUserLocation.lat, newUserLocation.lng));
                });
            });
        });
    });


</script>

</body>
</html>
