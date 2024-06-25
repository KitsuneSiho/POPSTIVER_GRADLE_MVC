
const posts = [
    { title: "안녕", author: "홍길동", date: "2024-06-12 15:12" },
    { title: "뚜비", author: "홍길동", date: "2024-06-12 15:12" },
    { title: "뚜밥", author: "홍길동", date: "2024-06-12 15:12" },
    { title: "오오", author: "홍길동", date: "2024-06-12 15:12" },
    { title: "롯데", author: "홍길동", date: "2024-06-12 15:12" },
    { title: "이대호", author: "홍길동", date: "2024-06-12 15:12" },
    { title: "대~호", author: "홍길동", date: "2024-06-12 15:12" },
    { title: "붓싼갈메기", author: "홍길동", date: "2024-06-12 15:12" },
    { title: "안녕", author: "홍길동", date: "2024-06-12 15:12" },
    { title: "뚜비", author: "홍길동", date: "2024-06-12 15:12" },
    { title: "뚜밥", author: "홍길동", date: "2024-06-12 15:12" },
    { title: "오오", author: "홍길동", date: "2024-06-12 15:12" },
    { title: "롯데", author: "홍길동", date: "2024-06-12 15:12" },
    { title: "이대호", author: "홍길동", date: "2024-06-12 15:12" },
    { title: "대~호", author: "홍길동", date: "2024-06-12 15:12" },
    { title: "붓싼갈메기", author: "홍길동", date: "2024-06-12 15:12" },
    { title: "안녕", author: "홍길동", date: "2024-06-12 15:12" },
    { title: "뚜비", author: "홍길동", date: "2024-06-12 15:12" },
    { title: "뚜밥", author: "홍길동", date: "2024-06-12 15:12" },
    { title: "오오", author: "홍길동", date: "2024-06-12 15:12" },
    { title: "롯데", author: "홍길동", date: "2024-06-12 15:12" },
    { title: "이대호", author: "홍길동", date: "2024-06-12 15:12" },
    { title: "대~호", author: "홍길동", date: "2024-06-12 15:12" },
    { title: "붓싼갈메기", author: "홍길동", date: "2024-06-12 15:12" },

    // 더 많은 게시글을 여기 추가
];

const postsPerPage = 15;
const totalPages = Math.ceil(posts.length / postsPerPage);
const boardBody = document.getElementById('boardBody');
const pageNumberList = document.getElementById('pageNumberList');

function displayPosts(page) {
    boardBody.innerHTML = "";
    const start = (page - 1) * postsPerPage;
    const end = start + postsPerPage;
    const pagePosts = posts.slice(start, end);

    pagePosts.forEach(post => {
        const row = document.createElement('tr');
        row.innerHTML = `<td><a href="#">${post.title}</a></td><td>${post.author}</td><td>${post.date}</td>`;
        boardBody.appendChild(row);
    });
}

function setupPagination() {
    for (let i = 1; i <= totalPages; i++) {
        const pageItem = document.createElement('li');
        const pageLink = document.createElement('a');
        pageLink.href = "#";
        pageLink.textContent = i;
        pageLink.onclick = function() {
            displayPosts(i);
            // 모든 페이지 번호 링크의 클래스 제거
            const allPageLinks = document.querySelectorAll('.pageNumber ul li a');
            allPageLinks.forEach(link => link.classList.remove('pageOn'));
            // 현재 클릭된 페이지 번호 링크에 클래스 추가
            this.classList.add('pageOn');
        };
        pageItem.appendChild(pageLink);
        if (i === 1) {
            pageLink.classList.add('pageOn'); // 초기 페이지에 클래스 추가
        }
        pageNumberList.appendChild(pageItem);
    }
}

setupPagination();
displayPosts(1);

