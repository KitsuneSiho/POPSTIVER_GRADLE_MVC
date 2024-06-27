package kr.bit.function.page.pageMapper;


import kr.bit.function.page.pageEntity.CalendarEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CalendarMapper {

    @Select("""
        SELECT festival_title AS title, festival_start AS startDate, festival_end AS endDate, 'festival' AS type 
        FROM festival 
        UNION ALL 
        SELECT popup_title AS title, popup_start AS startDate, popup_end AS endDate, 'popup' AS type
        FROM popup
        
    """)
    List<CalendarEntity> findAll();
}