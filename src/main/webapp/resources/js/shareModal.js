function toggleShareModal() {
    const shareModal = document.getElementById('shareModal');
    if (shareModal.style.display === 'none' || shareModal.style.display === '') {
        shareModal.style.display = 'block';
    } else {
        shareModal.style.display = 'none';
    }
}

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
