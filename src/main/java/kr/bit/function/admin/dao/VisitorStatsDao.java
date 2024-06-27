package kr.bit.function.admin.dao;

import java.util.List;
import java.util.Map;

public interface VisitorStatsDao {
    List<Map<String, Object>> getMonthlyVisitorStats();
}
