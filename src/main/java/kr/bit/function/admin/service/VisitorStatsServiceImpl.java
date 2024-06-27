package kr.bit.function.admin.service;

import kr.bit.function.admin.dao.VisitorStatsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class VisitorStatsServiceImpl implements VisitorStatsService {

    @Autowired
    private VisitorStatsDao visitorStatsDao;

    @Override
    public List<Map<String, Object>> getMonthlyVisitorStats() {
        return visitorStatsDao.getMonthlyVisitorStats();
    }
}
