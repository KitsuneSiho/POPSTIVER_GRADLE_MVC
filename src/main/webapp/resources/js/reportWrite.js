$(document).ready(function() {
    getUserInfoAndSetUserId();
});

function getUserInfoAndSetUserId() {
    $.ajax({
        type: "GET",
        url: "member/getUserInfo",
        success: function(response) {
            if (response && response.user_id && response.user_nickname) {
// Set the user_id and user_nickname in the hidden input fields
                $("#user_id").val(response.user_id);
                $("#user_name").val(response.user_nickname);
            } else {
                console.error("사용자 정보를 가져오는 데 실패했습니다.");
            }
        },
        error: function (xhr, status, error) {
            console.error("사용자 정보를 가져오는 중 오류 발생: " + error);
        }
    });
}

// 저장하기 버튼을 누르면
function submitForm(event) {
// 폼 데이터 변수로 가져오기

    event.preventDefault(); // 폼 제출 방지


// 폼 데이터 변수로 가져오기
    var reportTitle = $("input[name='report_title']").val();
    var reportEventType = ($("input[name='event_type']:checked").val());
    var reportContent = $("textarea[name='report_content']").val();
    var reportDist = $("input[name='report_dist']").val();
    var reportSubdist = $("input[name='report_subdist']").val();
    var reportLocation = $("input[name='report_location']").val();
    var reportStart = $("input[name='report_start']").val();
    var reportEnd = $("input[name='report_end']").val();
    var openTime = $("textarea[name='open_time']").val();
    var reportHost = $("input[name='report_host']").val();
    var reportBrandLink = $("input[name='brand_link']").val();
    var reportBrandSns = $("input[name='brand_sns']").val();
    var userId = $("input[name='user_id']").val();
    var userName = $("input[name='user_name']").val();

    $.ajax({
        method: "put",
        url: "report/insertWrite",
        contentType: 'application/json;charset=utf-8',
// StudentAndInfo 객체를 JSON 문자열로 변환하여 전송
        data: JSON.stringify({
            "report_title": reportTitle,
            "event_type": reportEventType,
            "report_content": reportContent,
            "report_dist" : reportDist,
            "report_subdist" : reportSubdist,
            "report_location": reportLocation,
            "report_start" : reportStart,
            "report_end" : reportEnd,
            "open_time" : openTime,
            "report_host" : reportHost,
            "brand_link" : reportBrandLink,
            "brand_sns" : reportBrandSns,
            "user_id": userId,
            "user_name":userName
        }),

        success: function (response) {
// 업데이트 성공 시 처리할 코드
            alert("정상적으로 등록되었습니다. 제보해주셔서 감사합니다!");
            window.location.href = "/report";
// 필요한 경우 추가적인 UI 업데이트 등을 수행할 수 있음
        },
        error: function (xhr, status, error) {
// 실패 시 처리할 코드
            alert("제보에 실패하였습니다.");
            window.location.href = "/report";
        }
    });


}

function checkPost() {
    new daum.Postcode({
        oncomplete: function(data) {
            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분입니다.
            // 각 주소의 노출 규칙에 따라 주소를 조합한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            var addr = ''; // 주소 변수
            var extraAddr = ''; // 참고항목 변수

            //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
            if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                addr = data.roadAddress;
            } else { // 사용자가 지번 주소를 선택했을 경우(J)
                addr = data.jibunAddress;
            }

            // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
            if(data.userSelectedType === 'R'){
                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                    extraAddr += data.bname;
                }
                // 건물명이 있고, 공동주택일 경우 추가한다.
                if(data.buildingName !== '' && data.apartment === 'Y'){
                    extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                if(extraAddr !== ''){
                    extraAddr = ' (' + extraAddr + ')';
                }
                addr = addr + extraAddr;
            }

            // 주소 필드를 세 개로 나눈다.
            var addrParts = addr.split(' ');
            var city = addrParts[0]; // 시/도
            var district = addrParts[1]; // 구/군
            var location = addrParts.slice(2).join(' '); // 나머지 주소

            console.log(addrParts);

            console.log(city);
            console.log(district);
            console.log(location);

            document.getElementsByName("report_dist")[0].value = city;
            document.getElementsByName("report_subdist")[0].value = district;
            document.getElementsByName("report_location")[0].value = location; // 첫 번째 필드는 id가 temp_location인 필드이므로 두 번째 필드에 값을 설정

        }
    }).open();
}