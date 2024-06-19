package com.example.function.page.pageController;


import com.example.function.page.pageEntity.SearchResultEntity;
import com.example.function.page.pageService.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class SearchController {

    @Autowired
    private SearchService searchService;

    @GetMapping("/search")
    public String searchEvents(@RequestParam("keyword") String keyword, Model model) {
        List<SearchResultEntity> results = searchService.searchEvents(keyword);
        model.addAttribute("results", results);

        return "search"; // View name is search.jsp
    }
}
