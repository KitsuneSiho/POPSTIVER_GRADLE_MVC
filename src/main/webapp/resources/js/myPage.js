// myPage.js

const forbiddenWords = [
    '관리자', 'admin', 'ADMIN', 'test', 'TEST', 'example', 'EXAMPLE', 'root', 'ROOT', 'superuser', 'SUPERUSER', 'guest', 'GUEST', 'temp', 'TEMP', 'user', 'USER', 'username', 'USERNAME', 'sample', 'SAMPLE',
    'fuck', 'shit', 'bitch', 'asshole', 'bastard', 'damn', 'crap', 'dick', 'pussy', 'cunt', 'faggot', 'douche', 'nigger', 'slut', 'whore',
    '시발', '병신', '존나', '좆', '새끼', 'ㅅㅂ', 'ㅄ', 'ㅈㄴ', 'ㄷㅊ', '애미', '니미', '니애미', '꺼져', '닥쳐', '미친', '개새끼'
]; // 금지어 목록

let isNicknameAvailable = false;
let isNicknameChecked = false;
$(document).ready(function() {
    // 문서 로드 시 사용자 정보 로드
    loadUserInfo();
});



function loadUserInfo() {
    $.ajax({
        type: "GET",
        url: "member/getUserInfo",
        success: function(response) {
            // 사용자 정보가 성공적으로 로드되면 폼에 데이터 설정
            var user = response;
            $("input[name='user_name']").val(user.user_name);
            $("input[name='user_email']").val(user.user_email);
            $("input[name='user_birth']").val(user.user_birth);
            $("input[name='user_nickName']").val(user.user_nickname);
            // 성별 설정
            if (user.user_gender === 'male') {
                $("#male").addClass('selected');
                $("#female").removeClass('selected');
                $("#user_gender").val("male");
            } else if (user.user_gender === 'female') {
                $("#female").addClass('selected');
                $("#male").removeClass('selected');
                $("#user_gender").val("female");
            }

            // 회원 유형 설정
            if (user.user_type === 'ROLE_HOST') {
                $("#host").addClass('selected');
                $("#user").removeClass('selected');
                $('#user_type').val("ROLE_HOST");
            } else if (user.user_type === 'ROLE_USER') {
                $("#user").addClass('selected');
                $("#host").removeClass('selected');
                $('#user_type').val("ROLE_USER");
            }
        },
        error: function(xhr, status, error) {
            console.error("사용자 정보를 가져오는 중 오류 발생: " + error);
        }
    });
}




// 저장하기 버튼을 누르면
function submitForm(event) {
    event.preventDefault(); // 폼 제출 방지

    // 폼 데이터 변수로 가져오기
    var userEmail = $("input[name='user_email']").val();
    var userNickname = $("input[name='user_nickName']").val();
    var userType = $("input[name='user_type']:checked").val();

    if(!isNicknameChecked){
        showCustomAlert('닉네임 중복 확인 후 가입해주세요.');
        document.getElementById('user_nickName').focus();
        return false;
    }

    if (containsForbiddenWord(userNickname)) {
        showCustomAlert("닉네임에 금지된 단어가 포함되어 있습니다.");
        return;
    }

    if (containsOnlyConsonantsOrVowels(userNickname)) {
        showCustomAlert('닉네임에 자음이나 모음만 사용할 수 없습니다.');
        document.getElementById('user_nickName').focus();
        return;
    }

    if (!isNicknameAvailable) {
        showCustomAlert("중복된 닉네임입니다. 다른 닉네임을 입력해주세요.");
        return;
    }

    $.ajax({
        method: "PUT",
        url: "member/updateUser",
        contentType: 'application/json;charset=utf-8',
        data: JSON.stringify({
            "user_email": userEmail,
            "user_nickname": userNickname,
            "user_type": userType
        }),
        success: function(response) {
            // 업데이트 성공 시 처리할 코드
            showCustomAlert("회원 정보가 업데이트되었습니다!");
            // 필요한 경우 추가적인 UI 업데이트 등을 수행할 수 있음
            $("#editButton").css("display", "block"); // 수정 버튼은 보이게
            $("#saveButton").css("display", "none"); // 저장 버튼은 안 보이게
            $("input[name='user_nickName']").prop("readonly", true);
            $("input[type='radio']").prop("disabled", true);
        },
        error: function(xhr, status, error) {
            // 실패 시 처리할 코드
            showCustomAlert("회원 정보 업데이트 중 오류가 발생했습니다.");
        }
    });
}

function checkNickname() {
    const nickname = document.getElementById('user_nickName').value.trim();
    if (nickname === '') {
        showCustomAlert('닉네임을 입력해주세요.');
        document.getElementById('user_nickName').focus();
        return;
    }
    if (containsForbiddenWord(nickname)) {
        showCustomAlert('닉네임에 금지된 단어가 포함되어 있습니다.');
        document.getElementById('user_nickName').focus();
        return;
    }
    if (containsOnlyConsonantsOrVowels(nickname)) {
        showCustomAlert('닉네임에 자음이나 모음만 사용할 수 없습니다.');
        document.getElementById('user_nickName').focus();
        return;
    }

    // AJAX 요청을 사용하여 서버에 닉네임 중복 확인
    $.ajax({
        url: '/member/checkNickname',
        type: 'POST',
        data: { nickname: nickname },
        success: function(response) {
            if (response.available) {
                isNicknameChecked = true;
                document.getElementById('nicknameCheckResult').textContent = '사용 가능한 닉네임입니다.';
                document.getElementById('nicknameCheckResult').style.color = 'green';
                isNicknameAvailable = true;
            } else {
                document.getElementById('nicknameCheckResult').textContent = '이미 사용 중인 닉네임입니다.';
                document.getElementById('nicknameCheckResult').style.color = 'red';
                isNicknameAvailable = false;
            }
        },
        error: function() {
            showCustomAlert('닉네임 중복 확인 중 오류가 발생했습니다.');
            isNicknameAvailable = false;
            isNicknameChecked = false;
        }
    });
}

function showCustomAlert(message) {
    $("#customAlertMessage").text(message);
    $("#customAlertModal").css("display", "flex");
}

function closeCustomAlert() {
    $("#customAlertModal").css("display", "none");
}

function containsOnlyConsonantsOrVowels(input) {
    const consonants = 'ㄱㄴㄷㄹㅁㅂㅅㅇㅈㅊㅋㅌㅍㅎ';
    const vowels = 'ㅏㅑㅓㅕㅗㅛㅜㅠㅡㅣ';
    let containsConsonant = false;
    let containsVowel = false;

    for (let char of input) {
        if (consonants.includes(char)) {
            containsConsonant = true;
        } else if (vowels.includes(char)) {
            containsVowel = true;
        }
    }

    return (containsConsonant && !containsVowel) || (!containsConsonant && containsVowel);
}

function containsForbiddenWord(input) {
    for (let i = 0; i < forbiddenWords.length; i++) {
        if (input.includes(forbiddenWords[i])) {
            return true;
        }
    }
    return false;
}