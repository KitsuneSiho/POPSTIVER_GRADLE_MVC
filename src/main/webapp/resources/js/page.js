    document.addEventListener('DOMContentLoaded', function() {
    const postsPerPage = 10;
    const posts = Array.from(document.querySelectorAll('#boardBody tr')).slice(0);
    const totalPages = Math.ceil(posts.length / postsPerPage);

    function setupPagination() {
    const pageNumberList = document.getElementById('pageNumberList');
    pageNumberList.innerHTML = '';

    for (let i = 1; i <= totalPages; i++) {
    const pageItem = document.createElement('li');
    const pageLink = document.createElement('a');
    pageLink.href = "#";
    pageLink.textContent = i;
    pageLink.onclick = function(e) {
    e.preventDefault();
    displayPosts(i);
    const allPageLinks = document.querySelectorAll('.pageNumber ul li a');
    allPageLinks.forEach(link => link.classList.remove('pageOn'));
    this.classList.add('pageOn');
};
    pageItem.appendChild(pageLink);
    pageNumberList.appendChild(pageItem);
}

    if (pageNumberList.firstChild) {
    pageNumberList.firstChild.firstChild.classList.add('pageOn');
}
}

    function displayPosts(page) {
    const startIndex = (page - 1) * postsPerPage;
    const endIndex = startIndex + postsPerPage;

    posts.forEach((post, index) => {
    if (index >= startIndex && index < endIndex) {
    post.style.display = '';
} else {
    post.style.display = 'none';
}
});
}

    if (posts.length > postsPerPage) {
    setupPagination();
    displayPosts(1);
}
});
