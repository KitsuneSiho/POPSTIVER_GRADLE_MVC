function toggleShareModal() {
    const shareModal = document.getElementById('shareModal');
    const shareButton = document.getElementById('shareButton');

    if (shareModal.style.display === 'none' || shareModal.style.display === '') {
        shareModal.style.display = 'block';

        // 모달 위치 조정
        const buttonRect = shareButton.getBoundingClientRect();
        shareModal.style.top = `${buttonRect.bottom}px`;
        shareModal.style.right = `${window.innerWidth - buttonRect.right}px`;
    } else {
        shareModal.style.display = 'none';
    }
}

// 창 크기가 변경될 때 모달 위치 재조정
window.addEventListener('resize', function() {
    const shareModal = document.getElementById('shareModal');
    const shareButton = document.getElementById('shareButton');

    if (shareModal.style.display === 'block') {
        const buttonRect = shareButton.getBoundingClientRect();
        shareModal.style.top = `${buttonRect.bottom}px`;
        shareModal.style.right = `${window.innerWidth - buttonRect.right}px`;
    }
});

function copyToClipboard(elementId) {
    const element = document.getElementById(elementId);
    if (element && element.tagName === 'A') {
        const copyText = element.href;
        const textarea = document.createElement('textarea');
        textarea.value = copyText;
        document.body.appendChild(textarea);
        textarea.select();
        document.execCommand('copy');
        document.body.removeChild(textarea);
        alert('링크가 복사되었습니다: ' + copyText);
    } else {
        alert('복사할 내용을 찾을 수 없습니다.');
    }
}
