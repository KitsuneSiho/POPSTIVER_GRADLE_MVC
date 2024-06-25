package kr.bit.function.page.pageController;


import kr.bit.function.page.pageEntity.SearchResult;
import kr.bit.function.page.pageService.SearchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
@RequestMapping("/main")  // "/main" 경로로 매핑되는 컨트롤러 클래스
public class MainController {

    @Autowired
    private SearchService searchService;

    @GetMapping  // "/main" 페이지로의 GET 요청 처리
    public String mainPage() {
        // main 페이지에 대한 로직
        return "main";  // main.jsp 또는 main.html 등의 뷰 페이지 반환
    }

    @GetMapping("/search")  // "/main/searchResult" 경로로 매핑되는 검색 결과 처리
    public String search(@RequestParam("keyword") String keyword, Model model) {
        System.out.println("서치되나?");
        List<SearchResult> searchResult = searchService.searchEvents(keyword);
        model.addAttribute("result", searchResult);
        return "searchResult";  // searchResult.jsp 또는 searchResult.html 등의 뷰 페이지 반환
    }
}

