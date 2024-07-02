package kr.bit.function.like;

import java.util.List;

public interface LikeService {
    boolean toggleLike(String userId, String userName, int eventNo, int eventType);
    int getLikeCount(int eventNo, int eventType);
    List<BookmarkDTO> getLikedEvents(String userId);
    List<BookmarkDTO> getPopularEvents(int limit); // 새 메서드 추가
}
