package kr.bit.function.page.pageService;

import kr.bit.function.page.pageEntity.SearchResult;
import kr.bit.function.page.pageMapper.SearchMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class SearchService {

    @Autowired
    private SearchMapper searchMapper;

    public List<SearchResult> searchEvents(String keyword) {
        return searchMapper.search(keyword);
    }
}
