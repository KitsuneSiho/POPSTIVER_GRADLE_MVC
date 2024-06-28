package kr.bit.function.page.pageService;

import kr.bit.function.page.pageEntity.Popup;
import kr.bit.function.page.pageMapper.PopupUpcomingMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PopupUpcomingService {

    @Autowired
    private PopupUpcomingMapper popupUpcomingMapper;

    public List<Popup> getUpcomingPopups() {
        return popupUpcomingMapper.getUpcomingPopups();
    }
}
