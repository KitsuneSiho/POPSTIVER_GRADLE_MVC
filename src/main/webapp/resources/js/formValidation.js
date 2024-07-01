const forbiddenWords = [
    '관리자', 'admin', 'ADMIN', 'test', 'TEST', 'example', 'EXAMPLE', 'root', 'ROOT', 'superuser', 'SUPERUSER', 'guest', 'GUEST', 'temp', 'TEMP', 'user', 'USER', 'username', 'USERNAME', 'sample', 'SAMPLE',
    'fuck', 'shit', 'bitch', 'asshole', 'bastard', 'damn', 'crap', 'dick', 'pussy', 'cunt', 'faggot', 'douche', 'nigger', 'slut', 'whore',
    '시발', '병신', '존나', '좆', '새끼', 'ㅅㅂ', 'ㅄ', 'ㅈㄴ', 'ㄷㅊ', '애미', '니미', '니애미', '꺼져', '닥쳐', '미친', '개새끼' ,'씨발'
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

function showCustomAlert(message) {
    // 모달 요소 생성
    const modal = document.createElement('div');
    modal.className = 'custom-alert-modal';

    const modalContent = document.createElement('div');
    modalContent.className = 'custom-alert-content';

    const messageParagraph = document.createElement('p');
    messageParagraph.textContent = message;

    const closeButton = document.createElement('button');
    closeButton.className = 'custom-alert-close';
    closeButton.textContent = '확인';
    closeButton.onclick = function() {
        document.body.removeChild(modal);
    };

    // 모달 콘텐츠에 요소 추가
    modalContent.appendChild(messageParagraph);
    modalContent.appendChild(closeButton);

    // 모달에 모달 콘텐츠 추가
    modal.appendChild(modalContent);

    // 모달을 body에 추가
    document.body.appendChild(modal);

    // 모달 표시
    modal.style.display = 'block';
}

function validateForm() {
    const userNickName = document.getElementById('user_nickName').value.trim();
    const userBirth = document.getElementById('user_birth').value.trim();

    if (userNickName === '') {
        showCustomAlert('닉네임을 입력해주세요.');
        document.getElementById('user_nickName').focus();
        return false;
    }
    if (containsForbiddenWord(userNickName)) {
        showCustomAlert('닉네임에 금지된 단어가 포함되어 있습니다.');
        document.getElementById('user_nickName').focus();
        return false;
    }
    if (containsOnlyConsonantsOrVowels(userNickName)) {
        showCustomAlert('닉네임에 자음이나 모음만 사용할 수 없습니다.');
        document.getElementById('user_nickName').focus();
        return false;
    }
    if (!isNicknameAvailable) {
        showCustomAlert('중복된 닉네임입니다. 다른 닉네임을 입력해주세요.');
        return false;
    }
    if (userBirth === '') {
        showCustomAlert('생일을 입력해주세요.');
        document.getElementById('user_birth').focus();
        return false;
    }
    if (!/^[0-9]+$/.test(userBirth)) {
        showCustomAlert('생일은 숫자만 입력 가능합니다.');
        document.getElementById('user_birth').focus();
        return false;
    }
    if (!isValidDateFormat(userBirth)) {
        showCustomAlert('생일을 yyyymmdd 형식으로 입력해주세요.');
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
        }
    });
}

// 생일 입력란에 숫자만 입력할 수 있도록 이벤트 추가
document.getElementById('user_birth').addEventListener('input', function (e) {
    const userBirth = e.target.value;
    if (!/^[0-9]*$/.test(userBirth)) {
        showCustomAlert('생일은 숫자만 입력 가능합니다.');
        e.target.value = userBirth.replace(/[^0-9]/g, ''); // 숫자 외의 문자는 제거
        e.target.focus();
    }
});
