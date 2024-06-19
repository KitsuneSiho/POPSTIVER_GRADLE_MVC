
const posts = [
    { title: "공지사항 1", author: "관리자", date: "2024-06-12 15:12" },
    { title: "공지사항 2", author: "관리자", date: "2024-06-12 15:13" },
    { title: "공지사항 1", author: "관리자", date: "2024-06-12 15:12" },
    { title: "공지사항 2", author: "관리자", date: "2024-06-12 15:13" },
    { title: "공지사항 1", author: "관리자", date: "2024-06-12 15:12" },
    { title: "공지사항 2", author: "관리자", date: "2024-06-12 15:13" },
    { title: "공지사항 1", author: "관리자", date: "2024-06-12 15:12" },
    { title: "공지사항 2", author: "관리자", date: "2024-06-12 15:13" },
    { title: "공지사항 1", author: "관리자", date: "2024-06-12 15:12" },
    { title: "공지사항 2", author: "관리자", date: "2024-06-12 15:13" },
    { title: "공지사항 1", author: "관리자", date: "2024-06-12 15:12" },
    { title: "공지사항 2", author: "관리자", date: "2024-06-12 15:13" },
    { title: "공지사항 1", author: "관리자", date: "2024-06-12 15:12" },
    { title: "공지사항 2", author: "관리자", date: "2024-06-12 15:13" },
    { title: "공지사항 1", author: "관리자", date: "2024-06-12 15:12" },
    { title: "공지사항 2", author: "관리자", date: "2024-06-12 15:13" },
    { title: "공지사항 1", author: "관리자", date: "2024-06-12 15:12" },
    { title: "공지사항 2", author: "관리자", date: "2024-06-12 15:13" },
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
        pageItem.innerHTML = `<a href="#" onclick="displayPosts(${i})">${i}</a>`;
        if (i === 1) {
            pageItem.querySelector('a').classList.add('pageOn');
        }
        pageNumberList.appendChild(pageItem);
    }
}

setupPagination();
displayPosts(1);

