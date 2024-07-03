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