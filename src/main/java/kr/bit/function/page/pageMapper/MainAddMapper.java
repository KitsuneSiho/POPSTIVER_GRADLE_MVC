package kr.bit.function.page.pageMapper;

import kr.bit.function.board.boardEntity.PopupEntity;
import kr.bit.function.board.boardEntity.FestivalEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import java.util.List;

@Mapper
public interface MainAddMapper {
    @Select("SELECT popup_title, popup_location, popup_start, popup_end, popup_attachment FROM popup")
    List<PopupEntity> getAllPopups();

    @Select("SELECT festival_title, festival_location, festival_start, festival_end, festival_attachment FROM festival")
    List<FestivalEntity> getAllFestivals();

    @Select("SELECT popup_no, popup_title, popup_location, popup_start, popup_end, popup_attachment FROM popup WHERE popup_start > CURRENT_DATE()")
    List<PopupEntity> getOpenPopups();

    @Select("SELECT festival_no, festival_title, festival_location, festival_start, festival_end, festival_attachment FROM festival WHERE festival_start > CURRENT_DATE()")
    List<FestivalEntity> getOpenFestivals();
}