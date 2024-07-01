package kr.bit.function.page.pageMapper;

import kr.bit.function.like.BookmarkDTO;
import kr.bit.function.like.LikeEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface LikeMapper {
    @Insert("INSERT INTO Like_List (event_type, event_no, user_id, user_name) " +
            "VALUES (#{event_type}, #{event_no}, #{user_id}, #{user_name})")
    @Options(useGeneratedKeys = true, keyProperty = "like_no")
    void insertLike(LikeEntity like);

    @Delete("DELETE FROM Like_List " +
            "WHERE user_id = #{user_id} AND event_no = #{event_no} AND event_type = #{event_type}")
    void deleteLike(LikeEntity like);

    @Update("UPDATE ${event_type == 3 ? 'popup' : 'festival'} " +
            "SET like_that = COALESCE(like_that, 0) + #{count} " +
            "WHERE ${event_type == 3 ? 'popup_no' : 'festival_no'} = #{event_no}")
    int updateLikeCount(@Param("event_no") int event_no, @Param("event_type") int event_type, @Param("count") int count);

    @Select("SELECT COUNT(*) > 0 FROM Like_List " +
            "WHERE user_id = #{user_id} AND event_no = #{event_no} AND event_type = #{event_type}")
    boolean existsLike(LikeEntity like);

    @Select("SELECT COALESCE(like_that, 0) FROM ${event_type == 3 ? 'popup' : 'festival'} " +
            "WHERE ${event_type == 3 ? 'popup_no' : 'festival_no'} = #{event_no}")
    int getLikeCount(@Param("event_no") int event_no, @Param("event_type") int event_type);


    //관심행사페이지 추가 쿼리
    @Select("SELECT l.*, " +
            "CASE WHEN l.event_type IN (1, 2) THEN f.festival_title ELSE p.popup_title END as title, " +
            "CASE WHEN l.event_type IN (1, 2) THEN f.festival_start ELSE p.popup_start END as startDate, " +
            "CASE WHEN l.event_type IN (1, 2) THEN f.festival_end ELSE p.popup_end END as endDate, " +
            "CASE WHEN l.event_type IN (1, 2) THEN f.festival_location ELSE p.popup_location END as location, " +
            "CASE WHEN l.event_type IN (1, 2) THEN f.festival_attachment ELSE p.popup_attachment END as attachment " +
            "FROM Like_List l " +
            "LEFT JOIN festival f ON l.event_no = f.festival_no AND l.event_type IN (1, 2) " +
            "LEFT JOIN popup p ON l.event_no = p.popup_no AND l.event_type = 3 " +
            "WHERE l.user_id = #{userId}")
    List<BookmarkDTO> getLikedEventsByUserId(String userId);

    @Select("SELECT popup_no as event_no, popup_title as title, popup_start as startDate, " +
            "popup_end as endDate, popup_location as location, popup_attachment as attachment, " +
            "3 as event_type FROM popup WHERE popup_no = #{eventNo}")
    BookmarkDTO getPopupEvent(int eventNo);

    @Select("SELECT festival_no as event_no, festival_title as title, festival_start as startDate, " +
            "festival_end as endDate, festival_location as location, festival_attachment as attachment, " +
            "1 as event_type FROM festival WHERE festival_no = #{eventNo}")
    BookmarkDTO getFestivalEvent(int eventNo);
}
