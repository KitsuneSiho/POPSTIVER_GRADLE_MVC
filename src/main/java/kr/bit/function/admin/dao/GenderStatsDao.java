package kr.bit.function.admin.dao;

import java.util.List;
import java.util.Map;

public interface GenderStatsDao {
    List<Map<String, Object>> getGenderStats();
}
