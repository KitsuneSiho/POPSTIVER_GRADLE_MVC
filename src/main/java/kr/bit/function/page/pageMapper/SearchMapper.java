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

    @Select("SELECT event_type, title, content, location, attachment, start_date, end_date " +
            "FROM search_table " +
            "WHERE (title LIKE CONCAT('%', #{keyword}, '%') " +
            "OR content LIKE CONCAT('%', #{keyword}, '%') " +
            "OR location LIKE CONCAT('%', #{keyword}, '%')) ")

    List<SearchResult> search(@Param("keyword") String keyword);
}
