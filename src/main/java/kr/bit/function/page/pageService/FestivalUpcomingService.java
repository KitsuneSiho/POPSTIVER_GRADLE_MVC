package kr.bit.function.page.pageService;

import kr.bit.function.page.pageDao.FestivalUpcomingDAO;

import kr.bit.function.page.pageEntity.FestivalUpcomingEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FestivalUpcomingService {

    @Autowired
    private FestivalUpcomingDAO festivalUpcomingDAO;

    public List<FestivalUpcomingEntity> getUpcomingFestivals() {
        List<FestivalUpcomingEntity> festivals = festivalUpcomingDAO.getUpcomingFestivals();
        return festivals;
    }
}
