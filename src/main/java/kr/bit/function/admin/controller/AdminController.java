package kr.bit.function.admin.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import kr.bit.function.admin.dao.GenderStatsDao;
import kr.bit.function.admin.dao.SnsStatsDao;
import kr.bit.function.admin.dao.UserListDao;
import kr.bit.function.admin.dao.VisitorStatisticsDAO;
import kr.bit.function.admin.model.VisitorStatistic;
import kr.bit.function.admin.model.businessContents;
import kr.bit.function.admin.service.BusinessContentsService;
import kr.bit.function.member.entity.MemberEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

@Controller
public class AdminController {

    @Autowired
    private UserListDao userDao;

    @Autowired
    private GenderStatsDao genderStatsDao;

    @Autowired
    private SnsStatsDao snsStatsDao;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private BusinessContentsService businessContentsService;

    @Autowired
    private DataSource dataSource;

    @GetMapping("/admin")
    public String admin_main() {
        return "page/admin/adminMain";
    }

    @GetMapping("/usersList")
    public String getUsersList(Model model) {
        List<MemberEntity> usersList = userDao.getAllUsers();
        model.addAttribute("usersList", usersList);
        return "page/admin/usersList";
    }

    @GetMapping("/genderStats")
    public String getGenderStats(Model model) throws JsonProcessingException {
        List<Map<String, Object>> genderStats = genderStatsDao.getGenderStats();
        String genderStatsJson = objectMapper.writeValueAsString(genderStats);
        model.addAttribute("genderStatsJson", genderStatsJson);
        return "page/admin/genderStats";
    }

    @GetMapping("/snsJoinedStats")
    public String getSnsStats(Model model) throws JsonProcessingException {
        List<Map<String, Object>> snsStats = snsStatsDao.getSnsStats();
        String snsStatsJson = objectMapper.writeValueAsString(snsStats);
        model.addAttribute("snsStatsJson", snsStatsJson);
        return "page/admin/snsJoinedStats";
    }

    @GetMapping("/likedPostsStats")
    public String getLikedPostsStats() {
        return "page/admin/likedPostsStats";
    }

    @GetMapping("/mostViewedPostsStats")
    public String getMostViewedPostsStats() {
        return "page/admin/mostViewedPostsStats";
    }

    @GetMapping("/chatManagement")
    public String getChatManagement() {
        return "page/admin/chatManagement";
    }

    @GetMapping("/recentReviews")
    public String getRecentReviews() {
        return "page/admin/recentReviews";
    }

    @GetMapping("/businessContents")
    public String getBusinessContents(Model model) {
        List<businessContents> posts = businessContentsService.getAllBusinessContents();
        model.addAttribute("posts", posts);
        return "page/admin/businessContents";
    }

    @GetMapping("/visitor-stats")
    public String getVisitorStatistics(Model model) {
        try (Connection connection = dataSource.getConnection()) {
            VisitorStatisticsDAO dao = new VisitorStatisticsDAO(connection);
            List<VisitorStatistic> statistics = dao.getVisitorStatistics();

            int totalVisits = 0;
            double averageDailyVisits = 0.0;
            String peakVisitDate = "";
            int peakVisitCount = 0;

            if (!statistics.isEmpty()) {
                totalVisits = statistics.stream().mapToInt(VisitorStatistic::getVisitCount).sum();
                averageDailyVisits = (double) totalVisits / statistics.size();

                VisitorStatistic peakVisit = statistics.stream()
                        .max((s1, s2) -> Integer.compare(s1.getVisitCount(), s2.getVisitCount()))
                        .orElse(null);
                if (peakVisit != null) {
                    peakVisitDate = new SimpleDateFormat("yyyy-MM-dd").format(peakVisit.getVisitDate());
                    peakVisitCount = peakVisit.getVisitCount();
                }
            }

            model.addAttribute("totalVisits", totalVisits);
            model.addAttribute("averageDailyVisits", averageDailyVisits);
            model.addAttribute("peakVisitDate", peakVisitDate);
            model.addAttribute("peakVisitCount", peakVisitCount);
            model.addAttribute("statistics", statistics);

        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("errorMessage", "방문자 통계를 가져오는 중 오류가 발생했습니다.");
        }
        return "page/admin/visitorStats";
    }
}
