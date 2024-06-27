package kr.bit.function.admin.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import kr.bit.function.admin.dao.GenderStatsDao;
import kr.bit.function.admin.dao.UserListDao;
import kr.bit.function.member.entity.MemberEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;

@Controller
public class AdminController {

    @Autowired
    private UserListDao userDao;

    @Autowired
    private GenderStatsDao genderStatsDao;

    @Autowired
    private ObjectMapper objectMapper;

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

    @GetMapping("/visitorStats")
    public String getVisitorStats() {
        return "page/admin/visitorStats";
    }

    @GetMapping("/genderStats")
    public String getGenderStats(Model model) throws JsonProcessingException {
        List<Map<String, Object>> genderStats = genderStatsDao.getGenderStats();
        String genderStatsJson = objectMapper.writeValueAsString(genderStats);
        model.addAttribute("genderStatsJson", genderStatsJson);
        return "page/admin/genderStats";
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

    @GetMapping("/snsJoinedStats")
    public String getSnsStats() {
        return "page/admin/snsJoinedStats";
    }

    @GetMapping("/recentReviews")
    public String getRecentReviews() {
        return "page/admin/recentReviews";
    }
}
