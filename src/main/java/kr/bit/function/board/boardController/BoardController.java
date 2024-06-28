package kr.bit.function.board.boardController;


import kr.bit.function.board.boardDAO.BoardRepository;
import kr.bit.function.board.boardDTO.*;
import kr.bit.function.board.boardService.BoardService;
import kr.bit.function.board.boardService.CommentService;
import kr.bit.function.member.dto.CustomOAuth2User;
import kr.bit.function.member.dto.GoogleResponse;
import kr.bit.function.member.dto.KakaoResponse;
import kr.bit.function.member.dto.NaverResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;

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
    public String festivalDetails(@PathVariable("festival_no") int festivalNo, Model model) {
        try {
            // 특정 축제 정보
            FestivalBoardDTO festival = boardService.selectOneFestival(festivalNo);
            model.addAttribute("festival", festival);

            // 모든 축제 정보
            List<FestivalBoardDTO> allFestivals = boardService.selectAllFestival();
            model.addAttribute("allFestivals", allFestivals);

            // 모든 후기
            List<FestivalCommentDTO> allComments = boardService.selectFestivalComment(festivalNo);
            model.addAttribute("allComments", allComments);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "page/searchResult/festival_Details";
    }

    @RequestMapping(value = "/popup_Details/{popup_no}", method = RequestMethod.GET)
    public String popupDetails(@PathVariable("popup_no") int popupNo, Model model) {
        try {
            // 특정 축제 정보
            PopupBoardDTO popup = boardService.selectOnePopup(popupNo);
            model.addAttribute("popup", popup);

            // 모든 축제 정보
            List<PopupBoardDTO> allPopups = boardService.selectAllPopup();
            model.addAttribute("allPopups", allPopups);

            // 모든 후기
            List<PopupCommentDTO> allComments = commentService.selectPopupComment(popupNo);
            model.addAttribute("allComments", allComments);


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
            model.addAttribute("list",boardService.selectAllFestival());
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
            model.addAttribute("list",boardService.selectAllPopup());
        }catch(Exception e){
            e.printStackTrace();
        }
        return "page/test/popup_view";
    }

    //=====================================================================================//
    //                              ⚠️⚠️ NOTICE  공지게시판 ⚠️⚠️                            //
    //=====================================================================================//

    @RequestMapping(value = "/contact", method = RequestMethod.GET)
    public String contact(Model model) {
        logger.info("contact.jsp start");
        try {
            model.addAttribute("list",boardService.selectAllNotice());
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

    //=====================================================================================//
    //                               📖📖 COMMUNITY 자유게시판 📖📖                         //
    //=====================================================================================//

    @RequestMapping(value = "/free", method = RequestMethod.GET)
    public String communityBoardList(Model model){
        try{
            model.addAttribute("community_list", boardService.selectAllCommunity());
        }catch (Exception e){
            e.printStackTrace();
        }
        return "page/board/free";
    }
    @RequestMapping(value = "/free/{board_no}", method = RequestMethod.GET)
    //Pathvariable 어노테이션으로 notice_no 값을 notice_no라는 이름의 매개변수로 만든다.
    public String selectCommunityOne(@PathVariable("board_no") int board_no, Model model) {
        try {
            //위에서 선언한 service의 selectOne()메소드 요청한다.
            //매개변수로 선언한 studentid를 인자로 하여 selectOne()에 넣는다.
            //selectOne메소드를 통해 나온 리턴값을 value로 해서
            //'list'란 key값으로 model에 담는다.
            model.addAttribute("community",boardService.selectCommunityOne(board_no));
        }catch(Exception e) {
            e.printStackTrace();
        }
        //oneviewDB.jsp로 이동한다.
        return "page/board/communityDetails";
    }

    @RequestMapping(value = "/freeBoard")
    @Controller
    class InsertCommunityController{

        @PutMapping("/insertWrite")
        @ResponseBody
        public void insertFreeWrite(@RequestBody CommunityDTO communityDTO){
            try {
                System.out.println("제목:"+communityDTO.getBoard_title());
                System.out.println("내용:"+communityDTO.getBoard_content());
                System.out.println("사용자아이디:"+communityDTO.getUser_id());
                System.out.println("사용자명:"+communityDTO.getUser_name());
                boardService.insertCommunity(communityDTO);

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


    //=====================================================================================//
    //                             📤📤 REPORT  제보게시판 📤📤                             //
    //=====================================================================================//


    //=====================================================================================//
    //                            🧑‍🤝‍🧑🧑‍🤝‍🧑 COMPANION  동행게시판 🧑‍🤝‍🧑🧑‍🤝‍🧑                           //
    //=====================================================================================//




}