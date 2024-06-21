function logoutNaver() {
    fetch('${root}/logout/naver', { method: 'POST' })
        .then(response => {
            if (response.ok) {
                document.cookie.split(";").forEach(function(c) {
                    document.cookie = c.trim().split("=")[0] + '=;expires=Thu, 01 Jan 1970 00:00:00 UTC;path=/;';
                });
                window.location.href = '${root}/login';
            }
        })
        .catch(error => console.error('Error:', error));
}

function logoutKakao() {
    fetch('${root}/logout/kakao', { method: 'POST' })
        .then(response => {
            if (response.ok) {
                document.cookie.split(";").forEach(function(c) {
                    document.cookie = c.trim().split("=")[0] + '=;expires=Thu, 01 Jan 1970 00:00:00 UTC;path=/;';
                });
                window.location.href = 'https://kauth.kakao.com/oauth/logout?client_id=${root}/oauth2/authorization/kakao&logout_redirect_uri=http://localhost:8080/login';
            }
        })
        .catch(error => console.error('Error:', error));
}

function logoutGoogle() {
    fetch('${root}/logout/google', { method: 'POST' })
        .then(response => {
            if (response.ok) {
                document.cookie.split(";").forEach(function(c) {
                    document.cookie = c.trim().split("=")[0] + '=;expires=Thu, 01 Jan 1970 00:00:00 UTC;path=/;';
                });
                window.location.href = '${root}/login';
            }
        })
        .catch(error => console.error('Error:', error));
}