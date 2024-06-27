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
error: function(xhr, status, error) {
console.error("사용자 정보를 가져오는 중 오류 발생: " + error);
}
});
}

// 저장하기 버튼을 누르면
function submitForm(event){
// 폼 데이터 변수로 가져오기

event.preventDefault(); // 폼 제출 방지


// 폼 데이터 변수로 가져오기
var boardTitle = $("input[name='board_title']").val();
var boardContent = $("textarea[name='board_content']").val();
var userId = $("input[name='user_id']").val();
var userName = $("input[name='user_name']").val();

console.log(boardTitle);
console.log(boardContent);
console.log(userId);
console.log(userName);

$.ajax({
method: "put",
url: "freeBoard/insertWrite",
contentType: 'application/json;charset=utf-8',
// StudentAndInfo 객체를 JSON 문자열로 변환하여 전송
data: JSON.stringify({
"board_title" : boardTitle,
"board_content" : boardContent,
"user_id" : userId,
"user_name" : userName

}),

success: function(response) {
// 업데이트 성공 시 처리할 코드
alert("저장이 완료되었습니다!");
window.location.href = "/free";
// 필요한 경우 추가적인 UI 업데이트 등을 수행할 수 있음
},
error: function(xhr, status, error) {
// 실패 시 처리할 코드
alert("게시글 저장 중 오류가 발생했습니다.");
window.location.href = "/free";
}
});


}