package kr.bit.function.board.boardController;


import kr.bit.function.board.boardDTO.CommunityDTO;
import kr.bit.function.board.boardDTO.FestivalBoardDTO;
import kr.bit.function.board.boardService.BoardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

@Controller
public class BoardController {
    @Autowired
    private BoardService boardService;

    //로그객체 선언하기.
    private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

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

    //-----------------------FESTIVAL------------------------//
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

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "page/searchResult/festival_Details";
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
//----------------------POPUP-------------------------------//
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

    //-------------------------------------------------------//
    //                        NOTICE                         //
    //-------------------------------------------------------//
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

    //------------------------------------------------------//
    //                    COMMUNITY 자유게시판                //
    //------------------------------------------------------//
    @PostMapping("/communityForm")
    public String insertCommunity(@ModelAttribute CommunityDTO communityDTO, RedirectAttributes redirectAttributes) {
        try {
            System.out.println("제목:"+communityDTO.getBoard_title());
            boardService.insertCommunity(communityDTO);
            redirectAttributes.addFlashAttribute("message", "작성성공");
            return "redirect:/free";
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("message", "작성실패");
            return "redirect:/free";
        }
    }
}