document.addEventListener("DOMContentLoaded", function() {
    const bookmarks = document.querySelectorAll(".bookmark");

    bookmarks.forEach(function(bookmark) {
        bookmark.addEventListener("click", function() {
            const likedSrc = "${root}/resources/asset/좋아요.svg";
            const unlikedSrc = "${root}/resources/asset/아니좋아요.svg";
            const currentSrc = bookmark.getAttribute("src");

            if (currentSrc === likedSrc) {
                bookmark.setAttribute("src", unlikedSrc);
            } else {
                bookmark.setAttribute("src", likedSrc);
            }
        });
    });
});
