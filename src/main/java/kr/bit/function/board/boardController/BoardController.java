package kr.bit.function.board.boardController;


import jakarta.servlet.http.HttpSession;
import kr.bit.function.board.boardDAO.BoardRepository;
import kr.bit.function.board.boardDTO.*;
import kr.bit.function.board.boardEntity.FestivalEntity;
import kr.bit.function.board.boardEntity.PopupEntity;
import kr.bit.function.board.boardService.BoardService;
import kr.bit.function.board.boardService.CommentService;
import kr.bit.function.member.dto.CustomOAuth2User;
import kr.bit.function.member.dto.GoogleResponse;
import kr.bit.function.member.dto.KakaoResponse;
import kr.bit.function.member.dto.NaverResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.swing.*;
import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
public class BoardController {
    @Autowired
    private BoardService boardService;

    @Autowired
    private BoardRepository boardRepository;


    //로그객체 선언하기.
    private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
    @Autowired
    private CommentService commentService;


    // 해당경로('프로젝트/보드이름')로 URL이동하면 해당 컨트롤러 메소드로 매핑된다.
    @RequestMapping(value = "/testfestival", method = RequestMethod.GET)
    public String festival() {
        //로그 표시 후
        logger.info("festival_home.jsp start");
        //festival.jsp파일로 이동한다.
        return "page/test/festival_home";
    }

    //URL에 '/menu'를 더 적으면 해당 컨트롤러 메소드로 매핑된다.
    @RequestMapping(value = "/festival_menu", method = RequestMethod.GET)
    public String menu() {
        //로그 표시 후
        logger.info("festival_menu.jsp start");
        //menu.jsp파일로 이동한다.
        return "page/test/festival_menu";
    }

    //=====================================================================================//
    //                               🎇🎇 FESTIVAL 축제 🎇🎇                               //
    //=====================================================================================//

    //URL에 '/page'를 더 적으면 해당 컨트롤러 메소드로 매핑된다.
    @RequestMapping(value = "/festival_page", method = RequestMethod.GET)
    public String home() {
        return "page/test/festival_page";
    }
    @RequestMapping(value = "/festival_insert", method = RequestMethod.GET)
    public String insert(Model model) {
        logger.info("festival_insert.jsp start");
        String ret = null;
        try{
            boardService.insertFestivalManual();
            ret = "DB SAVE COMPLETE";
        }catch(Exception e){
            ret = "DB SAVE FAILED"+e;
        }
        //위 처리에 따라 넣어진 메시지 값을 value로 하고
        //'msg'라는 key값을 가진 model에 값을 넣는다.
        model.addAttribute("msg",ret);
        return "page/test/festival_insert";
    }

    @RequestMapping(value = "/festival_Details/{festival_no}", method = RequestMethod.GET)
    public String festivalDetails(@PathVariable("festival_no") int festivalNo, Model model, HttpSession session) {
        try {
            // 세션에서 조회한 게시물 ID 리스트를 가져옵니다.
            Set<Integer> viewedFestivalNo = (Set<Integer>) session.getAttribute("viewedFestivalNo");
            if (viewedFestivalNo == null) {
                viewedFestivalNo = new HashSet<>();
            }

            // 게시물을 조회합니다.
            try {
                // 게시물을 조회합니다.
                FestivalBoardDTO festivalBoardDTO = boardService.selectFestivalOne(festivalNo);
                if (!viewedFestivalNo.contains(festivalNo)) {
                    boardService.increaseFestivalViews(festivalNo); // 조회수 증가 메서드 호출
                    viewedFestivalNo.add(festivalNo); // 세션에 조회한 게시물 ID 추가
                    session.setAttribute("viewedFestivalNo", viewedFestivalNo); // 세션 업데이트
                }
            } catch(Exception e){
                e.printStackTrace();
            }
            // 특정 축제 정보
            FestivalBoardDTO festival = boardService.selectFestivalOne(festivalNo);
            model.addAttribute("festival", festival);

            // 모든 축제 정보
            List<FestivalBoardDTO> allFestivals = boardService.selectFestivalAll();
            model.addAttribute("allFestivals", allFestivals);

            // 모든 후기
            List<FestivalCommentDTO> allComments = boardService.selectFestivalComment(festivalNo);
            model.addAttribute("allComments", allComments);

            // 축제 평균 별점 조회
            double avgStarRate = commentService.getFestivalStarAvg(festivalNo);
            // 평균 별점을 소수점 한 자리까지 포맷팅
            String formattedAvgStarRate = String.format("%.1f", avgStarRate);
            model.addAttribute("avgStarRate", formattedAvgStarRate);


        } catch (Exception e) {
            e.printStackTrace();
        }

        return "page/searchResult/festival_Details";
    }

    @RequestMapping(value = "/popup_Details/{popup_no}", method = RequestMethod.GET)
    public String popupDetails(@PathVariable("popup_no") int popupNo, Model model, HttpSession session) {
        try {
            // 세션에서 조회한 게시물 ID 리스트를 가져옵니다.
            Set<Integer> viewedPopupNo = (Set<Integer>) session.getAttribute("viewedPopupNo");
            if (viewedPopupNo == null) {
                viewedPopupNo = new HashSet<>();
            }

            // 게시물을 조회합니다.
            try {
                // 게시물을 조회합니다.
                PopupBoardDTO popupBoardDTO = boardService.selectPopupOne(popupNo);
                if (!viewedPopupNo.contains(popupNo)) {
                    boardService.increasePopupViews(popupNo); // 조회수 증가 메서드 호출
                    viewedPopupNo.add(popupNo); // 세션에 조회한 게시물 ID 추가
                    session.setAttribute("viewedPopupNo", viewedPopupNo); // 세션 업데이트
                }
            } catch(Exception e){
                e.printStackTrace();
            }
            // 특정 축제 정보
            PopupBoardDTO popup = boardService.selectPopupOne(popupNo);
            model.addAttribute("popup", popup);

            // 모든 축제 정보
            List<PopupBoardDTO> allPopups = boardService.selectPopupAll();
            model.addAttribute("allPopups", allPopups);

            // 모든 후기
            List<PopupCommentDTO> allComments = boardService.selectPopupComment(popupNo);
            model.addAttribute("allComments", allComments);

            // 축제 평균 별점 조회
            Double avgStarRate = commentService.getPopupStarAvg(popupNo);

            // 평균 별점이 null인 경우 0으로 처리
            if (avgStarRate == null) {
                avgStarRate = 0.0; // 또는 원하는 기본값으로 설정
            }


            // 평균 별점을 소수점 한 자리까지 포맷팅
            String formattedAvgStarRate = String.format("%.1f", avgStarRate);
            model.addAttribute("avgStarRate", formattedAvgStarRate);


        } catch (Exception e) {
            e.printStackTrace();
        }

        return "page/searchResult/popup_Details";
    }

    @RequestMapping(value = "/festival_view", method = RequestMethod.GET)
    public String views(Model model) {
        //log임
        logger.info("festival_view.jsp start");
        try{
            //위에서 선언한 service의 selectAll메소드 요청한다.
            //selectAll메소드를 통해 나온 리턴값을 value로 해서
            //'list'란 key값으로 model에 담는다.
            model.addAttribute("list",boardService.selectFestivalAll());
        }catch(Exception e){
            e.printStackTrace();
        }
        return "page/test/festival_view";
    }
    //=====================================================================================//
    //                            🎁🎁 POPUP  팝업스토어 🎁🎁                               //
    //=====================================================================================//

    @RequestMapping(value = "/popup_view", method = RequestMethod.GET)
    public String viewPopup(Model model) {
        //log임
        logger.info("popup_view.jsp start");
        try{
            //위에서 선언한 service의 selectAll메소드 요청한다.
            //selectAll메소드를 통해 나온 리턴값을 value로 해서
            //'list'란 key값으로 model에 담는다.
            model.addAttribute("list",boardService.selectPopupAll());
        }catch(Exception e){
            e.printStackTrace();
        }
        return "page/test/popup_view";
    }

    //=====================================================================================//
    //                              ⚠️⚠️ NOTICE  공지게시판 ⚠️⚠️                            //
    //=====================================================================================//

    @RequestMapping(value = "/contact")
    @Controller
    class InsertNoticeController{

        @PutMapping("/insertWrite")
        @ResponseBody
        public void registerReport(@RequestBody NoticeDTO noticeDTO,
                                   @AuthenticationPrincipal CustomOAuth2User customOAuth2User, RedirectAttributes redirectAttributes) {
            String provider = customOAuth2User.getProvider();
            Object attribute = customOAuth2User.getAttributes();
            String user_id = "";

            switch (provider) {
                case "google":
                    GoogleResponse googleResponse = new GoogleResponse((Map<String, Object>) attribute);
                    user_id = "google" + googleResponse.getProviderId();
                    break;
                case "kakao":
                    KakaoResponse kakaoResponse = new KakaoResponse((Map<String, Object>) attribute);
                    user_id = "kakao" + kakaoResponse.getProviderId();
                    break;
                case "naver":
                    NaverResponse naverResponse = new NaverResponse((Map<String, Object>) attribute);
                    user_id = "naver" + naverResponse.getProviderId();
                    break;
            }

            try {
                boardService.insertNotice(noticeDTO);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    @RequestMapping(value = "/contact", method = RequestMethod.GET)
    public String contact(Model model) {
        logger.info("contact.jsp start");
        try {
            model.addAttribute("list",boardService.selectNoticeAll());
        }catch(Exception e){
            e.printStackTrace();
        }
        return "page/board/contact";
    }

    @RequestMapping(value = "/contact/{notice_no}", method = RequestMethod.GET)
    //Pathvariable 어노테이션으로 notice_no 값을 notice_no라는 이름의 매개변수로 만든다.
    public String selectOneNotice(@PathVariable("notice_no") int notice_no, Model model) {
        try {
            //위에서 선언한 service의 selectOne()메소드 요청한다.
            //매개변수로 선언한 studentid를 인자로 하여 selectOne()에 넣는다.
            //selectOne메소드를 통해 나온 리턴값을 value로 해서
            //'list'란 key값으로 model에 담는다.
            model.addAttribute("notice",boardService.selectNoticeOne(notice_no));
        }catch(Exception e) {
            e.printStackTrace();
        }
        //oneviewDB.jsp로 이동한다.
        return "page/board/noticeDetails";
    }


    @RequestMapping(value = "/editNotice/{notice_no}", method = RequestMethod.GET)
    public String editNotice(@PathVariable("notice_no") int notice_no, HttpSession session, Model model) {
        try {
            model.addAttribute("current_notice",boardService.selectNoticeOne(notice_no));
        }catch (Exception e){
            e.printStackTrace();
        }

        //oneviewDB.jsp로 이동한다.
        return "page/board/noticeEdit";
    }

    @RequestMapping(value = "/contact")
    @Controller
    class EditNoticeController {

        @PutMapping("/updateEdit")
        @ResponseBody
        public void editNotice(@RequestBody NoticeDTO noticeDTO,
                               @AuthenticationPrincipal CustomOAuth2User customOAuth2User, RedirectAttributes redirectAttributes) {
            String provider = customOAuth2User.getProvider();
            Object attribute = customOAuth2User.getAttributes();
            String user_id = "";

            switch (provider) {
                case "google":
                    GoogleResponse googleResponse = new GoogleResponse((Map<String, Object>) attribute);
                    user_id = "google" + googleResponse.getProviderId();
                    break;
                case "kakao":
                    KakaoResponse kakaoResponse = new KakaoResponse((Map<String, Object>) attribute);
                    user_id = "kakao" + kakaoResponse.getProviderId();
                    break;
                case "naver":
                    NaverResponse naverResponse = new NaverResponse((Map<String, Object>) attribute);
                    user_id = "naver" + naverResponse.getProviderId();
                    break;
            }

            try {
                boardService.updateNotice(noticeDTO);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }



    //=====================================================================================//
    //                               📖📖 COMMUNITY 자유게시판 📖📖                         //
    //=====================================================================================//

    @RequestMapping(value = "/free", method = RequestMethod.GET)
    public String communityBoardList(Model model){
        try{
            model.addAttribute("community_list", boardService.selectCommunityAll());
        }catch (Exception e){
            e.printStackTrace();
        }
        return "page/board/free";
    }
    @RequestMapping(value = "/free/{board_no}", method = RequestMethod.GET)
    public String selectCommunityOne(@PathVariable("board_no") int board_no, HttpSession session, Model model) {
        try {

            // 세션에서 조회한 게시물 ID 리스트를 가져옵니다.
            Set<Integer> viewedBoardNo = (Set<Integer>) session.getAttribute("viewedBoardNo");
            if (viewedBoardNo == null) {
                viewedBoardNo = new HashSet<>();
            }

            // 게시물을 조회합니다.
            try {
                CommunityDTO communityDTO = boardService.selectCommunityOne(board_no);
                if (!viewedBoardNo.contains(board_no)) {
                    boardService.increaseCommunityViews(board_no); // 조회수 증가 메서드 호출
                    viewedBoardNo.add(board_no); // 세션에 조회한 게시물 ID 추가
                    session.setAttribute("viewedBoardNo", viewedBoardNo); // 세션 업데이트
                }
            } catch(Exception e){
                e.printStackTrace();
            }
            model.addAttribute("community",boardService.selectCommunityOne(board_no));
        }catch(Exception e) {
            e.printStackTrace();
        }
        //oneviewDB.jsp로 이동한다.
        return "page/board/communityDetails";
    }

    @RequestMapping(value = "/deleteCommunity/{board_no}", method = RequestMethod.GET)
    public String deleteCommunity(@PathVariable("board_no") int board_no, RedirectAttributes redirectAttributes) {
        try {
            boardService.deleteCommunity(board_no);
            redirectAttributes.addFlashAttribute("message", "게시글이 성공적으로 삭제되었습니다.");
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("message", "게시글 삭제 중 오류가 발생했습니다.");
        }
        return "redirect:/free"; // 삭제 후 리다이렉트할 페이지
    }
    @RequestMapping(value = "/editCommunity/{board_no}", method = RequestMethod.GET)
    public String editCommunity(@PathVariable("board_no") int board_no, HttpSession session, Model model) {
            try {
                model.addAttribute("current_community",boardService.selectCommunityOne(board_no));
                }catch (Exception e){
                e.printStackTrace();
            }

        //oneviewDB.jsp로 이동한다.
        return "page/board/freeEdit";
    }

    @RequestMapping(value = "/freeBoard")
    @Controller
    class EditCommunityController{

        @PutMapping("/updateEdit")
        @ResponseBody
        public void editCommunity(@RequestBody CommunityDTO communityDTO,
                                   @AuthenticationPrincipal CustomOAuth2User customOAuth2User, RedirectAttributes redirectAttributes) {
            String provider = customOAuth2User.getProvider();
            Object attribute = customOAuth2User.getAttributes();
            String user_id = "";

            switch (provider) {
                case "google":
                    GoogleResponse googleResponse = new GoogleResponse((Map<String, Object>) attribute);
                    user_id = "google" + googleResponse.getProviderId();
                    break;
                case "kakao":
                    KakaoResponse kakaoResponse = new KakaoResponse((Map<String, Object>) attribute);
                    user_id = "kakao" + kakaoResponse.getProviderId();
                    break;
                case "naver":
                    NaverResponse naverResponse = new NaverResponse((Map<String, Object>) attribute);
                    user_id = "naver" + naverResponse.getProviderId();
                    break;
            }

            try {
                boardService.updateCommunity(communityDTO);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }



    // 자유 게시판 글 등록


    //=====================================================================================//
    //                          📢📢 BUSINESS  주최자등록게시판 📢📢                         //
    //=====================================================================================//
    @RequestMapping(value = "/money")
    @Controller
    class InsertBusinessController{

        @PutMapping("/register")
        @ResponseBody
        public void registerBusiness(@RequestBody TemporaryPostDTO temporaryPostDTO,
                                    @AuthenticationPrincipal CustomOAuth2User customOAuth2User, RedirectAttributes redirectAttributes) {
            String provider = customOAuth2User.getProvider();
            Object attribute = customOAuth2User.getAttributes();
            String user_id = "";

            switch (provider) {
                case "google":
                    GoogleResponse googleResponse = new GoogleResponse((Map<String, Object>) attribute);
                    user_id = "google" + googleResponse.getProviderId();
                    break;
                case "kakao":
                    KakaoResponse kakaoResponse = new KakaoResponse((Map<String, Object>) attribute);
                    user_id = "kakao" + kakaoResponse.getProviderId();
                    break;
                case "naver":
                    NaverResponse naverResponse = new NaverResponse((Map<String, Object>) attribute);
                    user_id = "naver" + naverResponse.getProviderId();
                    break;
            }

            try {
                boardService.insertBusiness(temporaryPostDTO);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @RequestMapping(value = "/deleteNotice/{notice_no}", method = RequestMethod.GET)
    public String deleteNotice(@PathVariable("notice_no") int notice_no, RedirectAttributes redirectAttributes) {
        try {
            boardService.deleteNotice(notice_no);
            redirectAttributes.addFlashAttribute("message", "공지글이 성공적으로 삭제되었습니다.");
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("message", "공지글 삭제 중 오류가 발생했습니다.");
        }
        return "redirect:/contact"; // 삭제 후 리다이렉트할 페이지
    }


    //=====================================================================================//
    //                             📤📤 REPORT  제보게시판 📤📤                             //
    //=====================================================================================//

    @RequestMapping(value = "/report")
    @Controller
    class InsertReportController{

        @PutMapping("/insertWrite")
        @ResponseBody
        public void registerReport(@RequestBody ReportDTO reportDTO,
                                     @AuthenticationPrincipal CustomOAuth2User customOAuth2User, RedirectAttributes redirectAttributes) {
            String provider = customOAuth2User.getProvider();
            Object attribute = customOAuth2User.getAttributes();
            String user_id = "";

            switch (provider) {
                case "google":
                    GoogleResponse googleResponse = new GoogleResponse((Map<String, Object>) attribute);
                    user_id = "google" + googleResponse.getProviderId();
                    break;
                case "kakao":
                    KakaoResponse kakaoResponse = new KakaoResponse((Map<String, Object>) attribute);
                    user_id = "kakao" + kakaoResponse.getProviderId();
                    break;
                case "naver":
                    NaverResponse naverResponse = new NaverResponse((Map<String, Object>) attribute);
                    user_id = "naver" + naverResponse.getProviderId();
                    break;
            }

            try {
                boardService.insertReport(reportDTO);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    @RequestMapping(value = "/report", method = RequestMethod.GET)
    public String report(Model model) {
        try {
            model.addAttribute("report_list",boardService.selectReportAll());
        }catch(Exception e){
            e.printStackTrace();
        }
        return "page/board/report";
    }

    @RequestMapping(value = "/report/{report_no}", method = RequestMethod.GET)
    //Pathvariable 어노테이션으로 notice_no 값을 notice_no라는 이름의 매개변수로 만든다.
    public String selectReportOne(@PathVariable("report_no") int report_no, Model model) {
        try {
            //위에서 선언한 service의 selectOne()메소드 요청한다.
            //매개변수로 선언한 studentid를 인자로 하여 selectOne()에 넣는다.
            //selectOne메소드를 통해 나온 리턴값을 value로 해서

            model.addAttribute("report_detail",boardService.selectReportOne(report_no));
        }catch(Exception e) {
            e.printStackTrace();
        }
        //oneviewDB.jsp로 이동한다.
        return "page/board/reportDetails";
    }

    @RequestMapping(value = "/deleteReport/{report_no}", method = RequestMethod.GET)
    public String deleteReport(@PathVariable("report_no") int report_no, RedirectAttributes redirectAttributes) {
        try {
            boardService.deleteReport(report_no);
            redirectAttributes.addFlashAttribute("message", "게시글이 성공적으로 삭제되었습니다.");
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("message", "게시글 삭제 중 오류가 발생했습니다.");
        }
        return "redirect:/report"; // 삭제 후 리다이렉트할 페이지
    }

    @RequestMapping(value = "/editReport/{report_no}", method = RequestMethod.GET)
    public String editReport(@PathVariable("report_no") int report_no, HttpSession session, Model model) {
        try {
            model.addAttribute("current_report",boardService.selectReportOne(report_no));
        }catch (Exception e){
            e.printStackTrace();
        }

        //oneviewDB.jsp로 이동한다.
        return "page/board/reportEdit";
    }

    @RequestMapping(value = "/report")
    @Controller
    class EditReportController{

        @PutMapping("/updateEdit")
        @ResponseBody
        public void editReport(@RequestBody ReportDTO reportDTO,
                                  @AuthenticationPrincipal CustomOAuth2User customOAuth2User, RedirectAttributes redirectAttributes) {
            String provider = customOAuth2User.getProvider();
            Object attribute = customOAuth2User.getAttributes();
            String user_id = "";

            switch (provider) {
                case "google":
                    GoogleResponse googleResponse = new GoogleResponse((Map<String, Object>) attribute);
                    user_id = "google" + googleResponse.getProviderId();
                    break;
                case "kakao":
                    KakaoResponse kakaoResponse = new KakaoResponse((Map<String, Object>) attribute);
                    user_id = "kakao" + kakaoResponse.getProviderId();
                    break;
                case "naver":
                    NaverResponse naverResponse = new NaverResponse((Map<String, Object>) attribute);
                    user_id = "naver" + naverResponse.getProviderId();
                    break;
            }

            try {
                boardService.updateReport(reportDTO);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    //=====================================================================================//
    //                            🧑‍🤝‍🧑🧑‍🤝‍🧑 COMPANION  동행게시판 🧑‍🤝‍🧑🧑‍🤝‍🧑                           //
    //=====================================================================================//
    @RequestMapping(value = "/together/{comp_no}", method = RequestMethod.GET)
    public String selectCompanionOne(@PathVariable("comp_no") int comp_no, Model model, HttpSession session) {
        try {
            //위에서 선언한 service의 selectOne()메소드 요청한다.
            //매개변수로 선언한 studentid를 인자로 하여 selectOne()에 넣는다.
            //selectOne메소드를 통해 나온 리턴값을 value로 해서
            // 세션에서 조회한 게시물 ID 리스트를 가져옵니다.
            Set<Integer> viewedCompNo = (Set<Integer>) session.getAttribute("viewedCompNo");
            if (viewedCompNo == null) {
                viewedCompNo = new HashSet<>();
            }

            // 게시물을 조회합니다.
            try {
                CompanionDTO companionDTO = boardService.selectCompanionOne(comp_no);
                if (!viewedCompNo.contains(comp_no)) {
                    boardService.increaseCompanionViews(comp_no); // 조회수 증가 메서드 호출
                    viewedCompNo.add(comp_no); // 세션에 조회한 게시물 ID 추가
                    session.setAttribute("viewedPopupNo", viewedCompNo); // 세션 업데이트
                }
            } catch(Exception e){
                e.printStackTrace();
            }
            model.addAttribute("together",boardService.selectCompanionOne(comp_no));
        }catch(Exception e) {
            e.printStackTrace();
        }
        //oneviewDB.jsp로 이동한다.
        return "page/board/togetherDetails";
    }

    @RequestMapping(value = "/together")
    @Controller
    class InsertCompanionController{

        @PutMapping("/insertWrite")
        @ResponseBody
        public void registerTogether(@RequestBody CompanionDTO companionDTO,
                                   @AuthenticationPrincipal CustomOAuth2User customOAuth2User, RedirectAttributes redirectAttributes) {
            String provider = customOAuth2User.getProvider();
            Object attribute = customOAuth2User.getAttributes();
            String user_id = "";

            switch (provider) {
                case "google":
                    GoogleResponse googleResponse = new GoogleResponse((Map<String, Object>) attribute);
                    user_id = "google" + googleResponse.getProviderId();
                    break;
                case "kakao":
                    KakaoResponse kakaoResponse = new KakaoResponse((Map<String, Object>) attribute);
                    user_id = "kakao" + kakaoResponse.getProviderId();
                    break;
                case "naver":
                    NaverResponse naverResponse = new NaverResponse((Map<String, Object>) attribute);
                    user_id = "naver" + naverResponse.getProviderId();
                    break;
            }

            try {
                boardService.insertCompanion(companionDTO);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    @RequestMapping(value = "/together", method = RequestMethod.GET)
    public String together(Model model) {
        try {
            model.addAttribute("comp_list",boardService.selectCompanionAll());
        }catch(Exception e){
            e.printStackTrace();
        }
        return "page/board/together";
    }

    @RequestMapping(value = "/deleteTogether/{comp_no}", method = RequestMethod.GET)
    public String deleteCompanion(@PathVariable("comp_no") int comp_no, RedirectAttributes redirectAttributes) {
        try {
            boardService.deleteCompanion(comp_no);
            redirectAttributes.addFlashAttribute("message", "게시글이 성공적으로 삭제되었습니다.");
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("message", "게시글 삭제 중 오류가 발생했습니다.");
        }
        return "redirect:/together"; // 삭제 후 리다이렉트할 페이지
    }

    @RequestMapping(value = "/editTogether/{comp_no}", method = RequestMethod.GET)
    public String editCompanion(@PathVariable("comp_no") int comp_no, HttpSession session, Model model) {
        try {
            model.addAttribute("current_together",boardService.selectCompanionOne(comp_no));
        }catch (Exception e){
            e.printStackTrace();
        }

        //oneviewDB.jsp로 이동한다.
        return "page/board/togetherEdit";
    }

    @RequestMapping(value = "/together")
    @Controller
    class EditTogetherController{

        @PutMapping("/updateEdit")
        @ResponseBody
        public void editCompanion(@RequestBody CompanionDTO companionDTO,
                               @AuthenticationPrincipal CustomOAuth2User customOAuth2User, RedirectAttributes redirectAttributes) {
            String provider = customOAuth2User.getProvider();
            Object attribute = customOAuth2User.getAttributes();
            String user_id = "";

            switch (provider) {
                case "google":
                    GoogleResponse googleResponse = new GoogleResponse((Map<String, Object>) attribute);
                    user_id = "google" + googleResponse.getProviderId();
                    break;
                case "kakao":
                    KakaoResponse kakaoResponse = new KakaoResponse((Map<String, Object>) attribute);
                    user_id = "kakao" + kakaoResponse.getProviderId();
                    break;
                case "naver":
                    NaverResponse naverResponse = new NaverResponse((Map<String, Object>) attribute);
                    user_id = "naver" + naverResponse.getProviderId();
                    break;
            }

            try {
                boardService.updateCompanion(companionDTO);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
