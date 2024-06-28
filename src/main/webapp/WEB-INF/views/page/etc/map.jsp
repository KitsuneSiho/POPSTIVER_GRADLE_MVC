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

        @font-face {
            font-family: Pre;
            src: url('${root}/resources/font/Pre.ttf');
        }
    </style>
    <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=9441e4fcdaf29ae0ef64a498fa8c752d&libraries=services"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css" rel="stylesheet">

</head>

<body>
<jsp:include page="/WEB-INF/views/page/fix/header.jsp" />

<div class="map">
    <h3>모든 행사 위치 (카카오 지도)</h3>
    <div class="dropdown-wrapper">
        <button class="city-button" data-city="전체">전체</button>
    </div>
    <div class="dropdown-wrapper">
        <button class="city-button" data-city="서울특별시">서울특별시</button>
        <div class="dropdown-content">
            <label>
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
            </label>
        </div>
    </div>

    <div class="dropdown-wrapper">
        <button class="city-button" data-city="경기도">경기도</button>
        <div class="dropdown-content">
            <label>
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
            </label>
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

    <div id="multiMap">
        <button id="currentLocationButton">내 위치로 돌아가기</button>
    </div>

</div>

<div id="festivalModal" class="festivalModal">
    <div class="festivalModal-content">
        <span class="festivalModalClose">&times;</span>
        <h2 id="festivalTitle"></h2>
        <div class="festivalModalAddress">
            <img src="${root}/resources/asset/위치표시.svg" alt="">
            <p id="festivalDist"></p><p id="festivalSubdist"></p><p id="festivalLocation"></p>
        </div>
        <div class="festivalModalAddress">
            <img src="${root}/resources/asset/날짜.svg" class="cardDate" alt="">
            <p id="festivalStart"></p> <p id="festivalEnd"></p>
        </div>
        <a id="festivalLink" href="#" target="_blank">상세 페이지로 이동</a>
    </div>
</div>


<jsp:include page="/WEB-INF/views/page/fix/footer.jsp" />

<script src="${root}/resources/js/searchResult.js"></script>
<script src="${root}/resources/js/bookmarkToggle.js"></script>
<script>
    var geocoder = new kakao.maps.services.Geocoder();
    var festivalMarkers = []; // 지역 축제 마커 배열
    var popupMarkers = []; // 팝업 마커 배열

    var festivalMarkerImage = new kakao.maps.MarkerImage(
        '${root}/resources/asset/파랑마커.png',
        new kakao.maps.Size(40, 40),
        {
            offset: new kakao.maps.Point(25, 25)
        }
    );

    var popupMarkerImage = new kakao.maps.MarkerImage(
        '${root}/resources/asset/노랑마커.png',
        new kakao.maps.Size(40, 40),
        {
            offset: new kakao.maps.Point(25, 25)
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

    // 카카오맵 API 로드 후 초기화
    kakao.maps.load(function() {
        var multiMapContainer = document.getElementById('multiMap');

        getUserLocation(function(userLocation) {
            var multiMapOption = {
                center: new kakao.maps.LatLng(userLocation.lat, userLocation.lng),
                level: 4
            };

            var multiMap = new kakao.maps.Map(multiMapContainer, multiMapOption);

// 지도 확대 축소를 제어할 수 있는 줌 컨트롤을 생성합니다
            var zoomControl = new kakao.maps.ZoomControl();
            multiMap.addControl(zoomControl, kakao.maps.ControlPosition.RIGHT);

            var imageUrl = '${root}/resources/asset/내위치(빨강)마커.png'; // 상대 경로로 설정
            var imageSize = new kakao.maps.Size(50, 50);
            var imageOption = { offset: new kakao.maps.Point(25, 25) };


// 중앙에 마커를 생성합니다
            var centerMarker = new kakao.maps.Marker({
                position: new kakao.maps.LatLng(userLocation.lat, userLocation.lng),
                image: new kakao.maps.MarkerImage(imageUrl, imageSize, imageOption)
            });

// 지도에 중앙 마커를 표시합니다
            centerMarker.setMap(multiMap);



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
                    link: "${pageContext.request.contextPath}/popup_Details/${popup.popup_no}",
                    dist: "${popup.popup_dist}",
                    subdist: "${popup.popup_subdist}",
                    start: "${popup.popup_start}",
                    end: "${popup.popup_end}"
                }<c:if test="${!loop.last}">,</c:if>
                </c:forEach>
            ];

            // 함수 내에서 마커를 비동기적으로 추가
            function addMarkers(map, geocoder, festivals) {
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

                            festivalMarkers.push(marker);

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
                                document.getElementById('festivalLink').href = popup.link;
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

                            let isCenterSet = false;
                            // 선택된 도시와 구에 해당하는 축제만 표시
                            festivals.forEach(function(festival) {
                                if (festival.dist === city && festival.subdist === selectedDistrict) {
                                    geocoder.addressSearch(festival.dist + festival.subdist + festival.location, function(result, status) {
                                        if (status === kakao.maps.services.Status.OK) {
                                            var coords = new kakao.maps.LatLng(result[0].y, result[0].x);

                                            // 첫 번째 축제 데이터를 처리할 때 맵의 중심을 설정
                                            if (!isCenterSet) {
                                                multiMap.setCenter(coords);
                                                isCenterSet = true;
                                            }

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

                                            // 첫 번째 축제 데이터를 처리할 때 맵의 중심을 설정
                                            if (!isCenterSet) {
                                                multiMap.setCenter(coords);
                                                isCenterSet = true;
                                            }

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
                    } else if (city === '전체'){
                        addMarkers(multiMap, geocoder, festivals);
                        addPopupMarkers(multiMap, geocoder, popups);
                    }
                    else {
                        // 모든 마커 숨기기 (보이지 않게 설정)
                        festivalMarkers.forEach(function(marker) {
                            marker.setMap(null);
                        });
                        // 팝업 모든 마커 숨기기 (보이지 않게 설정)
                        popupMarkers.forEach(function(marker) {
                            marker.setMap(null);
                        });

                        let isCenterSet = false;
                        // 서울특별시와 경기도를 제외한 버튼은 바로 해당 도시의 행사만 표시
                        festivals.forEach(function(festival) {
                            if (festival.dist === city) {
                                geocoder.addressSearch(festival.dist + festival.subdist + festival.location, function(result, status) {
                                    if (status === kakao.maps.services.Status.OK) {
                                        var coords = new kakao.maps.LatLng(result[0].y, result[0].x);

                                        // 첫 번째 축제 데이터를 처리할 때 맵의 중심을 설정
                                        if (!isCenterSet) {
                                            multiMap.setCenter(coords);
                                            isCenterSet = true;
                                        }

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

                        popups.forEach(function(popup) {
                            if (popup.dist === city) {
                                geocoder.addressSearch(popup.dist + popup.subdist + popup.location, function(result, status) {
                                    if (status === kakao.maps.services.Status.OK) {
                                        var coords = new kakao.maps.LatLng(result[0].y, result[0].x);

                                        // 첫 번째 축제 데이터를 처리할 때 맵의 중심을 설정
                                        if (!isCenterSet) {
                                            multiMap.setCenter(coords);
                                            isCenterSet = true;
                                        }

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
            var span = document.getElementsByClassName('festivalModalClose')[0];
            span.onclick = function() {
                modal.style.display = "none";
            }
            window.onclick = function(event) {
                if (event.target === modal) {
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
