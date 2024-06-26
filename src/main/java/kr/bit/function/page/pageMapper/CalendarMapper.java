package kr.bit.function.page.pageMapper;


import kr.bit.function.page.pageEntity.CalendarEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CalendarMapper {

    @Select("SELECT title, start_date, end_date " +
            "FROM search_table ")

    List<CalendarEntity> findAll();
}