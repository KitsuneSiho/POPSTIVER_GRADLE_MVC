package kr.bit.function.page.pageMapper;

import kr.bit.function.like.LikeEntity;
import org.apache.ibatis.annotations.*;

@Mapper
public interface LikeMapper {

    @Insert("INSERT INTO like_list (user_name, user_id, event_no, event_type) " +
            "VALUES (#{user_name}, #{user_id}, #{event_no}, #{event_type})")
    void insertLike(LikeEntity like);

    @Delete("DELETE FROM like_list " +
            "WHERE user_id = #{user_id} AND event_no = #{event_no} AND event_type = #{event_type}")
    void deleteLike(LikeEntity like);

    @Select("SELECT like_that " +
            "FROM ${event_type == 1 ? 'festival' : 'popup'} " +
            "WHERE ${event_type == 1 ? 'festival_no' : 'popup_no'} = #{event_no}")
    int getLikeCount(@Param("event_no") int event_no, @Param("event_type") int event_type);

    @Update("UPDATE ${event_type == 1 ? 'festival' : 'popup'} " +
            "SET like_that = like_that + #{count} " +
            "WHERE ${event_type == 1 ? 'festival_no' : 'popup_no'} = #{event_no}")
    void updateLikeCount(@Param("event_no") int event_no, @Param("event_type") int event_type, @Param("count") int count);

    @Select("SELECT COUNT(*) > 0 " +
            "FROM like_list " +
            "WHERE user_id = #{user_id} AND event_no = #{event_no} AND event_type = #{event_type}")
    boolean existsLike(LikeEntity like);
}
