package kr.bit.function.like;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

public interface LikeService {
    boolean toggleLike(String user_name, String user_id, int event_no, int event_type);
}



