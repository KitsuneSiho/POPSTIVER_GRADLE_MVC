// 모달 열기 버튼과 모달 요소 가져오기
let modal = document.getElementById("menuModal");
let menuButton = document.querySelector(".menuButton");
let modalIsOpen = false; // 모달 열림 여부 확인을 위한 변수

// 메뉴 버튼 클릭 시 모달 열기 또는 닫기
menuButton.addEventListener("click", function() {
    if (modalIsOpen) { // 모달이 열려있는 경우 닫기
        modal.style.display = "none";
        modalIsOpen = false;
    } else { // 모달이 닫혀있는 경우 열기
        modal.style.display = "block";
        modalIsOpen = true;
    }
});


