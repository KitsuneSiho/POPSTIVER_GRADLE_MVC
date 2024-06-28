package kr.bit.function.page.pageService;

import kr.bit.function.page.pageEntity.PopupUpcoming;
import kr.bit.function.page.pageMapper.PopupUpcomingMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PopupUpcomingService {
    private final PopupUpcomingMapper popupUpcomingMapper;

    @Autowired
    public PopupUpcomingService(PopupUpcomingMapper popupUpcomingMapper) {
        this.popupUpcomingMapper = popupUpcomingMapper;
    }

    public List<PopupUpcoming> getUpcomingPopups() {
        return popupUpcomingMapper.getUpcomingPopups();
    }
}