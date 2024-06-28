// formValidation.js

const forbiddenWords = [
    '관리자', 'admin', 'ADMIN', 'test', 'TEST', 'example', 'EXAMPLE', 'root', 'ROOT', 'superuser', 'SUPERUSER', 'guest', 'GUEST', 'temp', 'TEMP', 'user', 'USER', 'username', 'USERNAME', 'sample', 'SAMPLE',
    'fuck', 'shit', 'bitch', 'asshole', 'bastard', 'damn', 'crap', 'dick', 'pussy', 'cunt', 'faggot', 'douche', 'nigger', 'slut', 'whore',
    '시발', '병신', '존나', '좆', '새끼', 'ㅅㅂ', 'ㅄ', 'ㅈㄴ', 'ㄷㅊ', '애미', '니미', '니애미', '꺼져', '닥쳐', '미친', '개새끼'
]; // 금지어 목록

let isNicknameAvailable = false;

function containsForbiddenWord(input) {
    for (let i = 0; i < forbiddenWords.length; i++) {
        if (input.includes(forbiddenWords[i])) {
            return true;
        }
    }
    return false;
}

function isValidDateFormat(date) {
    const regex = /^[0-9]{8}$/;
    if (!regex.test(date)) {
        return false;
    }
    const year = parseInt(date.substring(0, 4));
    const month = parseInt(date.substring(4, 6));
    const day = parseInt(date.substring(6, 8));
    if (month < 1 || month > 12) {
        return false;
    }
    if (day < 1 || day > 31) {
        return false;
    }
    return true;
}

function validateForm() {
    const userNickName = document.getElementById('user_nickName').value.trim();
    const userBirth = document.getElementById('user_birth').value.trim();

    if (userNickName === '') {
        alert('닉네임을 입력해주세요.');
        document.getElementById('user_nickName').focus();
        return false;
    }
    if (containsForbiddenWord(userNickName)) {
        alert('닉네임에 금지된 단어가 포함되어 있습니다.');
        document.getElementById('user_nickName').focus();
        return false;
    }
    if (!isNicknameAvailable) {
        alert('중복된 닉네임입니다. 다른 닉네임을 입력해주세요.');
        return false;
    }
    if (userBirth === '') {
        alert('생일을 입력해주세요.');
        document.getElementById('user_birth').focus();
        return false;
    }
    if (!isValidDateFormat(userBirth)) {
        alert('생일을 yyyymmdd 형식으로 입력해주세요.');
        document.getElementById('user_birth').focus();
        return false;
    }

    return true;
}

function showModal() {
    document.getElementById('myModal').style.display = 'block';
}

function closeModal() {
    document.getElementById('myModal').style.display = 'none';
}

// 모달 외부 클릭 시 모달 닫기
window.onclick = function(event) {
    const modal = document.getElementById('myModal');
    if (event.target === modal) {
        modal.style.display = 'none';
    }
}

// 페이지가 열리면 모달 실행
window.onload = function() {
    showModal();
}

function checkNickname() {
    const nickname = document.getElementById('user_nickName').value.trim();
    if (nickname === '') {
        alert('닉네임을 입력해주세요.');
        document.getElementById('user_nickName').focus();
        return;
    }
    if (containsForbiddenWord(nickname)) {
        alert('닉네임에 금지된 단어가 포함되어 있습니다.');
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
            alert('닉네임 중복 확인 중 오류가 발생했습니다.');
            isNicknameAvailable = false;
        }
    });
}
