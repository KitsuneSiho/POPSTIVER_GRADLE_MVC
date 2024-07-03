package kr.bit.function.like;

import java.util.List;

public interface LikeService {
    boolean toggleLike(String userId, String userName, int eventNo, int eventType);
    int getLikeCount(int eventNo, int eventType);
    List<BookmarkDTO> getLikedEvents(String userId);
    List<BookmarkDTO> getPopularPopupEvents(int limit); // 팝업 게시글을 가져오는 메서드 추가
    List<BookmarkDTO> getPopularFestivalEvents(int limit); // 페스티벌 게시글을 가져오는 메서드 추가
}
