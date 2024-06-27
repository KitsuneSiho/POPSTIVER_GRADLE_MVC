package kr.bit.function.admin.dao;

public interface VisitorLogDao {
    void insertVisitorLog(String ipAddress, String userAgent, String pageVisited);
}
