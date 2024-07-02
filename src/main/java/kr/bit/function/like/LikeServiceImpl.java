package kr.bit.function.like;

import kr.bit.function.page.pageMapper.LikeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class LikeServiceImpl implements LikeService {
    @Autowired
    private LikeMapper likeMapper;

    @Override
    @Transactional
    public boolean toggleLike(String userId, String userName, int eventNo, int eventType) {
        LikeEntity like = new LikeEntity();
        like.setUser_id(userId);
        like.setUser_name(userName);
        like.setEvent_no(eventNo);
        like.setEvent_type(eventType);

        boolean exists = likeMapper.existsLike(like);

        if (exists) {
            likeMapper.deleteLike(like);
            likeMapper.updateLikeCount(eventNo, eventType, -1);
            return false;
        } else {
            likeMapper.insertLike(like);
            likeMapper.updateLikeCount(eventNo, eventType, 1);
            return true;
        }
    }

    @Override
    public int getLikeCount(int eventNo, int eventType) {
        return likeMapper.getLikeCount(eventNo, eventType);
    }

    @Override
    public List<BookmarkDTO> getLikedEvents(String userId) {
        return likeMapper.getLikedEventsByUserId(userId);
    }

    @Override
    public List<BookmarkDTO> getPopularEvents(int limit) {
        return likeMapper.getPopularEvents(limit);
    }
}
