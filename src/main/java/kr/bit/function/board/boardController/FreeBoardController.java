package kr.bit.function.board.boardController;

import kr.bit.function.board.boardDTO.CommunityDTO;
import kr.bit.function.board.boardService.BoardService;
import kr.bit.function.board.boardService.FreeBoardService;
import kr.bit.function.member.dto.CustomOAuth2User;
import kr.bit.function.member.dto.GoogleResponse;
import kr.bit.function.member.dto.KakaoResponse;
import kr.bit.function.member.dto.NaverResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Map;

@RequestMapping(value = "/freeBoard")
@Controller
public class FreeBoardController {

    @Autowired
    private FreeBoardService boardService;

    // 자유 게시판 글 등록
    @PutMapping("/insertWrite")
    @ResponseBody
    public String insertFreeWrite(@RequestBody CommunityDTO communityDTO,
                                  @AuthenticationPrincipal CustomOAuth2User customOAuth2User) {

        String provider = customOAuth2User.getProvider();
        Object attribute = customOAuth2User.getAttributes();
        String user_id = "";

        switch (provider) {
            case "google":
                GoogleResponse googleResponse = new GoogleResponse((Map<String, Object>) attribute);
                user_id = googleResponse.getProviderId();
                break;
            case "kakao":
                KakaoResponse kakaoResponse = new KakaoResponse((Map<String, Object>) attribute);
                user_id = kakaoResponse.getProviderId();
                break;
            case "naver":
                NaverResponse naverResponse = new NaverResponse((Map<String, Object>) attribute);
                user_id = naverResponse.getProviderId();
                break;
        }

        try {
            System.out.println("제목:"+communityDTO.getBoard_title());
            System.out.println("내용:"+communityDTO.getBoard_content());
            System.out.println("사용자아이디:"+communityDTO.getUser_id());
            System.out.println("사용자명:"+communityDTO.getUser_name());
            System.out.println("조회수:"+communityDTO.getBoard_views());
            System.out.println("사진:"+communityDTO.getBoard_attachment());
            boardService.insertWrite(communityDTO);
            return "redirect:/free";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/free";
        }
    }
}
