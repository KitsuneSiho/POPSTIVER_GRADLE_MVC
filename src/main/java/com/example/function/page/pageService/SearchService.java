package com.example.function.page.pageService;


import com.example.function.page.pageEntity.SearchResultEntity;
import com.example.function.page.pageRepository.SearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchService {

    @Autowired
    private SearchRepository searchRepository;

    public List<SearchResultEntity> searchEvents(String keyword) {
        return searchRepository.findByKeyword(keyword);
    }

}
