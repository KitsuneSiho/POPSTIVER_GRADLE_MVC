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

    @Select("SELECT l.*, " +
            "CASE WHEN l.event_type IN (1, 2) THEN f.festival_title ELSE p.popup_title END as title, " +
            "CASE WHEN l.event_type IN (1, 2) THEN f.festival_start ELSE p.popup_start END as startDate, " +
            "CASE WHEN l.event_type IN (1, 2) THEN f.festival_end ELSE p.popup_end END as endDate, " +
            "CASE WHEN l.event_type IN (1, 2) THEN f.festival_location ELSE p.popup_location END as location, " +
            "CASE WHEN l.event_type IN (1, 2) THEN f.festival_attachment ELSE p.popup_attachment END as attachment, " +
            "CASE WHEN l.event_type IN (1, 2) THEN f.festival_dist ELSE p.popup_dist END as dist, " + // 추가
            "CASE WHEN l.event_type IN (1, 2) THEN f.festival_subdist ELSE p.popup_subdist END as subdist " + // 추가
            "FROM Like_List l " +
            "LEFT JOIN festival f ON l.event_no = f.festival_no AND l.event_type IN (1, 2) " +
            "LEFT JOIN popup p ON l.event_no = p.popup_no AND l.event_type = 3 " +
            "WHERE l.user_id = #{userId}")
    List<BookmarkDTO> getLikedEventsByUserId(String userId);

    @Select("SELECT popup.popup_no as event_no, popup.popup_title as title, popup.popup_start as startDate, " +
            "popup.popup_end as endDate, popup.popup_location as location, popup.popup_attachment as attachment, " +
            "popup.popup_dist as dist, popup.popup_subdist as subdist, " +
            "3 as event_type, COUNT(like_list.like_no) as likeCount " +
            "FROM popup " +
            "LEFT JOIN like_list ON popup.popup_no = like_list.event_no AND like_list.event_type = 3 " +
            "GROUP BY popup.popup_no, popup.popup_title, popup.popup_start, popup.popup_end, popup.popup_location, popup.popup_attachment, popup.popup_dist, popup.popup_subdist " +
            "ORDER BY COUNT(like_list.like_no) DESC " +
            "LIMIT #{limit}")
    List<BookmarkDTO> getPopularPopupEvents(@Param("limit") int limit);

    @Select("SELECT festival.festival_no as event_no, festival.festival_title as title, festival.festival_start as startDate, " +
            "festival.festival_end as endDate, festival.festival_location as location, festival.festival_attachment as attachment, " +
            "festival.festival_dist as dist, festival.festival_subdist as subdist, " +
            "1 as event_type, COUNT(like_list.like_no) as likeCount " +
            "FROM festival " +
            "LEFT JOIN like_list ON festival.festival_no = like_list.event_no AND like_list.event_type IN (1, 2) " +
            "GROUP BY festival.festival_no, festival.festival_title, festival.festival_start, festival.festival_end, festival.festival_location, festival.festival_attachment, festival.festival_dist, festival.festival_subdist " +
            "ORDER BY COUNT(like_list.like_no) DESC " +
            "LIMIT #{limit}")
    List<BookmarkDTO> getPopularFestivalEvents(@Param("limit") int limit);
}
