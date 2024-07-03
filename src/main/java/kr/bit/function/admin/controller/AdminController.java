package kr.bit.function.admin.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import kr.bit.function.admin.dao.MemberStatsDAO;
import kr.bit.function.admin.dao.UserListDao;
import kr.bit.function.admin.dao.VisitorStatisticsDAO;
import kr.bit.function.admin.model.VisitorStatistic;
import kr.bit.function.admin.model.businessContents;
import kr.bit.function.admin.service.BusinessContentsService;
import kr.bit.function.board.boardDTO.FestivalCommentDTO;
import kr.bit.function.board.boardDTO.PopupCommentDTO;
import kr.bit.function.board.boardDTO.TotalCommentDTO;
import kr.bit.function.board.boardService.CommentService;
import kr.bit.function.like.BookmarkDTO;
import kr.bit.function.like.LikeService;
import kr.bit.function.member.entity.MemberEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.sql.DataSource;
import java.sql.Connection;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class AdminController {

    @Autowired
    private UserListDao userDao;

    @Autowired
    private MemberStatsDAO memberStatsDAO;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private BusinessContentsService businessContentsService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private LikeService likeService;

    @Autowired
    private DataSource dataSource;

    @GetMapping("/admin")
    public String adminDashboard(Model model) throws JsonProcessingException {
        // 유저 통계
        int totalUsers = userDao.getTotalUsers();
        int newSignups = userDao.getNewSignups();
        int previousTotalUsers = userDao.getTotalUsersFromPreviousWeek();
        double signupGrowthRate = ((double) (newSignups) / (previousTotalUsers + totalUsers)) * 100;

        // 비즈니스 문의 수
        int businessInquiries = businessContentsService.getAllBusinessContents().size();
        int previousBusinessInquiries = businessContentsService.getBusinessContentsFromLastWeek().size();
        double businessInquiriesGrowthRate = ((double) (businessInquiries - previousBusinessInquiries) / previousBusinessInquiries) * 100;

        // 방문자 통계
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

            // 방문자 데이터를 모델에 추가
            model.addAttribute("visitorDataJson", objectMapper.writeValueAsString(statistics));
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 1:1 채팅 통계 (예시)
        List<Integer> chatData = List.of(5, 10, 15, 20, 25, 30, 35);
        model.addAttribute("chatDataJson", objectMapper.writeValueAsString(chatData));

        // 좋아요 많은 게시글 통계
        List<BookmarkDTO> popularPopupEvents = likeService.getPopularPopupEvents(10);
        List<BookmarkDTO> popularFestivalEvents = likeService.getPopularFestivalEvents(10);

        // ObjectMapper를 사용하여 JSON 문자열로 변환
        ObjectMapper objectMapper = new ObjectMapper();
        String popularPopupEventsJson = objectMapper.writeValueAsString(popularPopupEvents);
        String popularFestivalEventsJson = objectMapper.writeValueAsString(popularFestivalEvents);

        model.addAttribute("popularPopupEventsJson", popularPopupEventsJson);
        model.addAttribute("popularFestivalEventsJson", popularFestivalEventsJson);

        // 최근 댓글 데이터 추가
        List<TotalCommentDTO> recentComments = commentService.getRecentComments(5);
        model.addAttribute("recentComments", recentComments);

        // 모델에 데이터 추가
        model.addAttribute("totalUsers", totalUsers);
        model.addAttribute("newSignups", newSignups);
        model.addAttribute("signupGrowthRate", signupGrowthRate);
        model.addAttribute("businessInquiries", businessInquiries);
        model.addAttribute("businessInquiriesGrowthRate", businessInquiriesGrowthRate);
        model.addAttribute("previousTotalUsers", previousTotalUsers);

        return "page/admin/adminMain";
    }

    @GetMapping("/usersList")
    public String getUsersList(
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "size", defaultValue = "15") int size,
            Model model) {
        int offset = (page - 1) * size;
        List<MemberEntity> usersList = userDao.getUsers(offset, size);
        int totalUsers = userDao.getTotalUsers();

        model.addAttribute("usersList", usersList);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", (int) Math.ceil((double) totalUsers / size));
        model.addAttribute("totalUsers", totalUsers);

        return "page/admin/usersList";
    }

    @GetMapping("/memberStats")
    public String getMemberStats(Model model) throws JsonProcessingException {
        List<Map<String, Object>> genderStats = memberStatsDAO.getGenderStats();
        List<Map<String, Object>> snsStats = memberStatsDAO.getSnsStats();
        List<Map<String, Object>> ageStats = memberStatsDAO.getAgeGroupStats();
        List<Map<String, Object>> tagStats = memberStatsDAO.getPopularTags();

        String genderStatsJson = objectMapper.writeValueAsString(genderStats);
        String snsStatsJson = objectMapper.writeValueAsString(snsStats);
        String ageStatsJson = objectMapper.writeValueAsString(ageStats);
        String tagStatsJson = objectMapper.writeValueAsString(tagStats);

        int newUsersLast30Days = userDao.getNewUsersLast30Days(); // 추가된 코드
        model.addAttribute("newUsersLast30Days", newUsersLast30Days); // 추가된 코드

        model.addAttribute("genderStatsJson", genderStatsJson);
        model.addAttribute("snsStatsJson", snsStatsJson);
        model.addAttribute("ageStatsJson", ageStatsJson);
        model.addAttribute("tagStatsJson", tagStatsJson);

        return "page/admin/memberStats";
    }


    @GetMapping("/api/newUsersLast30Days")
    @ResponseBody
    public Map<String, Integer> getNewUsersLast30Days() {
        int newUsersLast30Days = userDao.getNewUsersLast30Days();
        Map<String, Integer> response = new HashMap<>();
        response.put("newUsersLast30Days", newUsersLast30Days);
        return response;
    }

    @GetMapping("/likedPostsStats")
    public String getLikedPostsStats(Model model) {
        List<BookmarkDTO> popularPopupEvents = likeService.getPopularPopupEvents(10);
        List<BookmarkDTO> popularFestivalEvents = likeService.getPopularFestivalEvents(10);

        model.addAttribute("popularPopupEvents", popularPopupEvents);
        model.addAttribute("popularFestivalEvents", popularFestivalEvents);

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
    public String getRecentReviews(Model model) {
        // 축제 댓글 가져오기
        List<FestivalCommentDTO> festivalComments = commentService.getFestivalComments();
        // 팝업 댓글 가져오기
        List<PopupCommentDTO> popupComments = commentService.getPopupComments();

        // 모델에 데이터 추가
        model.addAttribute("festivalComments", festivalComments);
        model.addAttribute("popupComments", popupComments);

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

            // 평균 방문자 수 소수점 첫째 자리까지 포맷
            DecimalFormat df = new DecimalFormat("#.0");
            model.addAttribute("averageDailyVisits", df.format(averageDailyVisits));

            model.addAttribute("totalVisits", totalVisits);
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
