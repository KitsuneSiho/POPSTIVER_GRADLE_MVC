package kr.bit.function.admin.service;

import kr.bit.function.admin.dao.VisitorLogDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VisitorLogServiceImpl implements VisitorLogService {

    @Autowired
    private VisitorLogDao visitorLogDao;

    @Override
    public void logVisit(String ipAddress, String userAgent, String pageVisited) {
//        System.out.println("Logging visit - IP: " + ipAddress + ", User-Agent: " + userAgent + ", Page: " + pageVisited);
        visitorLogDao.insertVisitorLog(ipAddress, userAgent, pageVisited);
    }
}
