package kr.bit.function.page.pageMapper;


import kr.bit.function.page.pageEntity.SearchResult;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;


import java.util.List;

@Mapper
public interface SearchMapper {

    @Select("SELECT event_no, event_type, title, content, location, host, attachment, start_date, end_date " +
            "FROM search_table2 " +
            "WHERE (title LIKE CONCAT('%', #{keyword}, '%') " +
            "OR content LIKE CONCAT('%', #{keyword}, '%') " +
            "OR location LIKE CONCAT('%', #{keyword}, '%')) ")

    List<SearchResult> search(@Param("keyword") String keyword);
}
