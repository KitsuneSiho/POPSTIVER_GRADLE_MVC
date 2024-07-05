// myPage.js

const forbiddenWords = [
    '관리자', 'admin', 'ADMIN', 'test', 'TEST', 'example', 'EXAMPLE', 'root', 'ROOT', 'superuser', 'SUPERUSER', 'guest', 'GUEST', 'temp', 'TEMP', 'user', 'USER', 'username', 'USERNAME', 'sample', 'SAMPLE',
    'fuck', 'shit', 'bitch', 'asshole', 'bastard', 'damn', 'crap', 'dick', 'pussy', 'cunt', 'faggot', 'douche', 'nigger', 'slut', 'whore',
    '시발', '병신', '존나', '좆', '새끼', 'ㅅㅂ', 'ㅄ', 'ㅈㄴ', 'ㄷㅊ', '애미', '니미', '니애미', '꺼져', '닥쳐', '미친', '개새끼' ,'씨발' ,"씨바" ,"씌바" , "씌발" ,"개새"
]; // 금지어 목록

let isNicknameAvailable = false;
let isNicknameChecked = false;

$(document).ready(function () {
    // 문서 로드 시 사용자 정보 로드
    loadUserInfo();

    var gender = $("#user_gender").val();
    console.log(gender);

    // gender 값에 따라 버튼 선택 상태 설정
    if (gender === 'M') {
        $('#male').addClass('selected');
        $('#female').removeClass('selected');
        $("#user_gender").val("male");
    } else if (gender === 'F') {
        $('#female').addClass('selected');
        $('#male').removeClass('selected');
        $("#user_gender").val("female");
    }

    $('#user').addClass('selected'); // 맨 처음에는 사용자 선택된게 기본 값이도록
    $('#user_type').val("ROLE_USER");

    setTags();
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

    modalContent.appendChild(messageParagraph);
    modalContent.appendChild(closeButton);
    modal.appendChild(modalContent);
    document.body.appendChild(modal);
    modal.style.display = 'block';
}

function containsOnlyConsonantsOrVowels(input) {
    const consonants = 'ㄱㄴㄷㄹㅁㅂㅅㅇㅈㅊㅋㅌㅍㅎㄲㄴㄸㄹㅁㅃㅆㅇㅉㅊㅋㅌㅍㅎㄻㄼㄳㄱㄷㄵ';
    const vowels = 'ㅏㅑㅓㅕㅗㅛㅜㅠㅡㅣㅚㅟㅢ';
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
    return forbiddenWords.some(word => input.includes(word));
}

function validateForm() {
    const userNickName = document.getElementById('user_nickName').value.trim();
    const userBirth = document.getElementById('user_birth').value.trim();
    const userType = document.getElementById('user_type').value.trim();
    const userGender = document.getElementById('user_gender').value.trim();

    console.log(userGender);
    console.log(userType);

    if (userType === '') {
        showCustomAlert('회원 유형을 선택해주세요.');
        document.getElementById('host').focus();
        return false;
    }

    if (userGender === '') {
        showCustomAlert('성별을 선택해주세요.');
        document.getElementById('male').focus();
        return false;
    }

    if (userNickName === '') {
        showCustomAlert('닉네임을 입력해주세요.');
        document.getElementById('user_nickName').focus();
        return false;
    }

    if (userNickName.length < 2 || userNickName.length > 10) {
        showCustomAlert('닉네임은 2글자 이상 10글자 이하로 입력해주세요.');
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

    if (!isNicknameChecked) {
        showCustomAlert('닉네임 중복 확인 후 가입해주세요.');
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

    const today = new Date().toISOString().split('T')[0];
    if (userBirth > today) {
        showCustomAlert('생일은 미래 날짜가 될 수 없습니다.');
        document.getElementById('user_birth').focus();
        return false;
    }

    if (!saveUserTags()) {
        showCustomAlert('태그를 하나 이상 선택해주세요.');
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

window.onclick = function(event) {
    const modal = document.getElementById('myModal');
    if (event.target === modal) {
        modal.style.display = 'none';
    }
}

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

    if (nickname.length < 2 || nickname.length > 10) {
        showCustomAlert('닉네임은 2글자 이상 10글자 이하로 입력해주세요.');
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

function toggleTagSelection(button) {
    button.classList.toggle('selected');
    console.log('Tag selected:', button.getAttribute('data-tag-no'));
}

function setTags() {
    document.querySelectorAll('.tag-button').forEach(button => {
        button.addEventListener('click', function() {
            this.classList.toggle('selected');
        });
    });
}

function saveUserTags() {
    const selectedTags = Array.from(document.querySelectorAll('.tag-button.selected')).map(button => button.getAttribute('data-tag-no'));
    const tagsField = document.getElementById('tags');
    tagsField.value = selectedTags.join(',');

    if (selectedTags.length === 0) {
        return false;
    } else {
        return true;
    }
}

function toggleTypeSelection(button) {
    button.classList.toggle('selected');
    if (button.id === 'host' && button.classList.contains('selected')) {
        console.log("주최자 선택");
        document.getElementById('user').classList.remove('selected');
        $('#user_type').val('ROLE_HOST');
    } else if (button.id === 'user' && button.classList.contains('selected')) {
        console.log("사용자 선택");
        document.getElementById('host').classList.remove('selected');
        $('#user_type').val('ROLE_USER');
    }
}

function toggleGenderSelection(button) {
    button.classList.toggle('selected');
    if (button.id === 'male' && button.classList.contains('selected')) {
        console.log("남 선택");
        document.getElementById('female').classList.remove('selected');
        $('#user_gender').val('male');
    } else if (button.id === 'female' && button.classList.contains('selected')) {
        console.log("여 선택");
        document.getElementById('male').classList.remove('selected');
        $('#user_gender').val('female');
    }
}