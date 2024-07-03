package kr.bit.function.page.pageService;

import kr.bit.function.board.boardEntity.PopupEntity;
import kr.bit.function.board.boardEntity.FestivalEntity;
import kr.bit.function.page.pageMapper.PopularAddMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PopularAddService {

    @Autowired
    private PopularAddMapper popularAddMapper;

    public List<PopupEntity> getAllPopups() {
        return popularAddMapper.getAllPopups();
    }

    public List<FestivalEntity> getAllFestivals() {
        return popularAddMapper.getAllFestivals();
    }
}