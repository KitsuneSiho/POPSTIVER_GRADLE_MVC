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
    var tempTitle = $("input[name='temp_title']").val();
    var tempType = $("input[name='event_type']:checked").val();
    var tempContent = $("textarea[name='temp_content']").val();
    var tempDist = $("input[name='temp_dist']").val();
    var tempSubdist = $("input[name='temp_subdist']").val();
    var tempLocation = $("input[name='temp_location']").val();
    var tempStart = $("input[name='temp_start']").val();
    var tempEnd = $("input[name='temp_end']").val();
    var openTime = $("textarea[name='open_time']").val();
    var tempHost = $("input[name='temp_host']").val();
    var brandLink = $("input[name='brand_link']").val();
    var brandSNS = $("input[name='brand_sns']").val();

    $.ajax({
        method: "put",
        url: "money/register",
        contentType: 'application/json;charset=utf-8',
// StudentAndInfo 객체를 JSON 문자열로 변환하여 전송
        data: JSON.stringify({
            "temp_title": tempTitle,
            "event_type": tempType,
            "temp_content": tempContent,
            "temp_dist" : tempDist,
            "temp_subdist" : tempSubdist,
            "temp_location": tempLocation,
            "temp_start" : tempStart,
            "temp_end" : tempEnd,
            "open_time" : openTime,
            "temp_host" : tempHost,
            "brand_link" : brandLink,
            "brand_sns" : brandSNS
        }),

        success: function (response) {
// 업데이트 성공 시 처리할 코드
            alert("등록이 요청되었습니다! 등록에 다소 시간이 소요될 수 있습니다.");
            window.location.href = "/contact";
// 필요한 경우 추가적인 UI 업데이트 등을 수행할 수 있음
        },
        error: function (xhr, status, error) {
// 실패 시 처리할 코드
            alert("등록에 실패하였습니다.");
            window.location.href = "/contact";
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
                // 조합된 참고항목을 해당 필드에 넣는다.
                document.getElementById("temp_location").value = addr + extraAddr;

            } else {
                document.getElementById("temp_location").value = addr;
            }

            // 우편번호 필드에 값을 입력한다.
            //document.getElementById("postcode").value = data.zonecode;
        }
    }).open();
}