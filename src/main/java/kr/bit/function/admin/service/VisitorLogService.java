package kr.bit.function.admin.service;

public interface VisitorLogService {
    void logVisit(String ipAddress, String userAgent, String pageVisited);
}
