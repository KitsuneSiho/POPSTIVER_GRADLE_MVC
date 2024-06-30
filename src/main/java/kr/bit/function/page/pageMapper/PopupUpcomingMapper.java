package kr.bit.function.page.pageMapper;

import kr.bit.function.page.pageEntity.PopupUpcoming;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PopupUpcomingMapper {
    @Select("SELECT * FROM popup WHERE popup_start > CURDATE() ORDER BY popup_start ASC LIMIT 12")
    List<PopupUpcoming> getUpcomingPopups();
}