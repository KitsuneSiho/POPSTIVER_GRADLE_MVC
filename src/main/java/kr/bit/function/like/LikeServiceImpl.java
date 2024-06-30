package kr.bit.function.like;

import kr.bit.function.page.pageMapper.LikeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LikeServiceImpl implements LikeService {
    @Autowired
    private LikeMapper likeMapper;

    @Override
    @Transactional
    public boolean toggleLike(String user_name, String user_id, int event_no, int event_type) {
        LikeEntity like = new LikeEntity();
        like.setUser_name(user_name);
        like.setUser_id(user_id);
        like.setEvent_no(event_no);
        like.setEvent_type(event_type);

        boolean exists = likeMapper.existsLike(like);
        if (exists) {
            likeMapper.deleteLike(like);
            likeMapper.updateLikeCount(event_no, event_type, -1);
            return false;
        } else {
            likeMapper.insertLike(like);
            likeMapper.updateLikeCount(event_no, event_type, 1);
            return true;
        }
    }
}
