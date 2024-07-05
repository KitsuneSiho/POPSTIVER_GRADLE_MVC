package kr.bit.function.page.pageService;

import kr.bit.function.page.pageDao.PopupUpcomingDAO;
import kr.bit.function.page.pageEntity.PopupUpcomingEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PopupUpcomingService {

    @Autowired
    private PopupUpcomingDAO popupUpcomingDAO;

    public List<PopupUpcomingEntity> getUpcomingPopups() {
        List<PopupUpcomingEntity> popups = popupUpcomingDAO.getUpcomingPopups();
        return popups;
    }
}

