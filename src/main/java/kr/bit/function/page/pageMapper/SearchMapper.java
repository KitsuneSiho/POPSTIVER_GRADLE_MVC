package kr.bit.function.page.pageMapper;


import kr.bit.function.page.pageEntity.SearchResult;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.List;

@Mapper
public interface SearchMapper {

    @Select("SELECT 'popup' AS type, popup_attachment AS attachment, popup_title AS title, popup_content AS content, popup_location AS location, popup_start AS startDate, popup_end AS endDate " +
            "FROM popup " +
            "WHERE popup_title LIKE CONCAT('%', #{keyword}, '%') " +
            "OR popup_content LIKE CONCAT('%', #{keyword}, '%') " +
            "OR popup_location LIKE CONCAT('%', #{keyword}, '%') " +
            "UNION ALL " +
            "SELECT 'festival' AS type, festival_attachment AS attachment, festival_title AS title, festival_content AS content, festival_location AS location, festival_start AS startDate, festival_end AS endDate " +
            "FROM festival " +
            "WHERE festival_title LIKE CONCAT('%', #{keyword}, '%') " +
            "OR festival_content LIKE CONCAT('%', #{keyword}, '%') " +
            "OR festival_location LIKE CONCAT('%', #{keyword}, '%') " +
            "ORDER BY CASE " +
            "WHEN title LIKE CONCAT('%', #{keyword}, '%') THEN 1 " +
            "WHEN content LIKE CONCAT('%', #{keyword}, '%') THEN 2 " +
            "ELSE 3 END")

    List<SearchResult> search(@Param("keyword") String keyword);
}
