package kr.bit.function.member.memberController;

import kr.bit.function.member.dao.memberDAO;
import kr.bit.function.member.memberEntity.memberEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class memberController {

    @Autowired
    private memberDAO memberDAO;

    // 저장 성공 임시페이지로 이동
    @RequestMapping(value="/saveSuccess", method= RequestMethod.GET)
    public String login_practice() {
        return "/page/saveSuccess"; // login.jsp로 이동
    }

    @PostMapping("/saveUser")
    public String saveUser(@RequestParam("userEmail") String userEmail) {
        // userEmail을 바로 데이터베이스에 저장
        memberEntity user = new memberEntity();
        user.setUser_email(userEmail);
        memberDAO.saveUser(user);

        // 저장 후에 어디로 이동할지 리다이렉트 경로를 반환
        return "redirect:/saveSuccess";
    }
}
