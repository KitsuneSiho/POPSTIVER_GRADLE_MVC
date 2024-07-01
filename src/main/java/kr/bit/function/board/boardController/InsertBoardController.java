package kr.bit.function.board.boardController;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import kr.bit.function.board.boardDTO.CommunityDTO;
import kr.bit.function.board.boardService.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

@PropertySource("classpath:properties/application.properties")
@Controller
@RequestMapping(value = "/freeBoard")
public class InsertBoardController {

    @Autowired
    private BoardService boardService;

    private static final Logger logger = LoggerFactory.getLogger(InsertBoardController.class);

    @Autowired
    private ServletContext servletContext;



    @PutMapping("/insertWrite")
    @ResponseBody
    public void insertFreeWrite(@RequestParam("board_title") String boardTitle,
                                @RequestParam("board_content") String boardContent,
                                @RequestParam("user_id") String userId,
                                @RequestParam("user_name") String userName,
                                @RequestParam(value = "board_views", defaultValue = "0") int boardViews,
                                @RequestPart("file") MultipartFile boardAttachment) {

        try {
            CommunityDTO communityDTO = new CommunityDTO();
            communityDTO.setBoard_title(boardTitle);
            communityDTO.setBoard_content(boardContent);
            communityDTO.setUser_id(userId);
            communityDTO.setUser_name(userName);
            communityDTO.setBoard_views(boardViews);

            // 파일 처리
            if (boardAttachment != null && !boardAttachment.isEmpty()) {
                String uploadDirectory = "";
                File uploadDir = new File(uploadDirectory);
                if (!uploadDir.exists()) {
                    uploadDir.mkdirs();
                }

                String originalFilename = boardAttachment.getOriginalFilename();
                String filePath = uploadDirectory + originalFilename;

                // 파일 저장
                File dest = new File(filePath);
                boardAttachment.transferTo(dest);

                // DTO에 파일 경로 설정
                communityDTO.setBoard_attachment("/resources/uploads/" + originalFilename);
            }

            // 서비스 호출
            boardService.insertCommunity(communityDTO);

        } catch (Exception e) {
            logger.error("게시글 저장 중 오류 발생", e);
            e.printStackTrace();
        }
    }

}