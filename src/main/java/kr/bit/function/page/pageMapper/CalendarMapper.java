package kr.bit.function.page.pageMapper;


import kr.bit.function.page.pageEntity.CalendarEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CalendarMapper {

    @Select("SELECT event_no, title, start_date, end_date, event_type " +
            "FROM search_table2 ")


    List<CalendarEntity> findAll();
}