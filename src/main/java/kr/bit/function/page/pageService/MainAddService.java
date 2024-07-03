package kr.bit.function.page.pageService;

import kr.bit.function.board.boardEntity.PopupEntity;
import kr.bit.function.board.boardEntity.FestivalEntity;
import kr.bit.function.page.pageMapper.MainAddMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MainAddService {

    @Autowired
    private MainAddMapper mainAddMapper;

    public List<PopupEntity> getAllPopups() {
        return mainAddMapper.getAllPopups();
    }

    public List<FestivalEntity> getAllFestivals() {
        return mainAddMapper.getAllFestivals();
    }

    public List<PopupEntity> getOpenPopups() { return mainAddMapper.getOpenPopups(); }

    public List<FestivalEntity> getOpenFestivals() { return mainAddMapper.getOpenFestivals(); }
}