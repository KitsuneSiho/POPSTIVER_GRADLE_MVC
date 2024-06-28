package kr.bit.function.page.pageMapper;


import kr.bit.function.page.pageEntity.Popup;
import org.apache.ibatis.annotations.Select;


import java.util.List;


public interface PopupUpcomingMapper {

    @Select("SELECT * FROM popup WHERE popup_start > NOW()")
    List<Popup> getUpcomingPopups();
}
