package kr.bit.function.page.pageService;

import kr.bit.function.page.pageDao.PopupUpcomingDAO;
import kr.bit.function.page.pageEntity.PopupUpcomingEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PopupUpcomingService {
    private static final Logger logger = LoggerFactory.getLogger(PopupUpcomingService.class);

    @Autowired
    private PopupUpcomingDAO popupUpcomingDAO;

    public List<PopupUpcomingEntity> getUpcomingPopups() {
        List<PopupUpcomingEntity> popups = popupUpcomingDAO.getUpcomingPopups();
        logger.info("Retrieved {} upcoming popups from DAO", popups.size());
        return popups;
    }
}

