package kr.bit.function.admin.service;

import java.util.List;
import java.util.Map;

public interface VisitorStatsService {
    List<Map<String, Object>> getMonthlyVisitorStats();
}
