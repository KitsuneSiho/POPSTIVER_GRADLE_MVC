package kr.bit.function.board.boardController;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import kr.bit.function.board.boardDTO.CommunityDTO;
import kr.bit.function.board.boardService.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Controller
@RequestMapping(value = "/freeBoard")
public class InsertBoardController {

    @Autowired
    private BoardService boardService;

    private static final Logger logger = LoggerFactory.getLogger(InsertBoardController.class);

    @Autowired
    private ServletContext servletContext;

    @PutMapping("/insertWrite")
    public void insertFreeWrite(@RequestParam("board_title") String boardTitle,
                                                  @RequestParam("board_content") String boardContent,
                                                  @RequestParam("user_id") String userId,
                                                  @RequestParam("user_name") String userName,
                                                    @RequestPart("file") MultipartFile boardAttachment) {

        logger.debug("Received board_title: {}", boardTitle);
        logger.debug("Received board_content: {}", boardContent);
        logger.debug("Received user_id: {}", userId);
        logger.debug("Received user_name: {}", userName);


        try {
            CommunityDTO communityDTO = new CommunityDTO();
            communityDTO.setBoard_title(boardTitle);
            communityDTO.setBoard_content(boardContent);
            communityDTO.setUser_id(userId);
            communityDTO.setUser_name(userName);

            // 파일 처리
            if (boardAttachment != null && !boardAttachment.isEmpty()) {
                // 파일 저장 경로 설정 (예시: C:/POPSTIVER_GRADLE_MVC_TEST/src/main/webapp/resources/uploads 디렉토리)
                String uploadDirectory = "C:\\POPSTIVER_GRADLE_MVC_TEST\\src\\main\\webapp\\resources\\uploads\\";
                File uploadDir = new File(uploadDirectory);
                if (!uploadDir.exists()) {
                    uploadDir.mkdirs();
                }

                String originalFilename = boardAttachment.getOriginalFilename();
                String filePath = uploadDirectory + originalFilename;

                logger.debug("파일 경로: {}", filePath);

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
