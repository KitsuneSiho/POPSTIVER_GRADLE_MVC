package com.example.function.page.pageRepository;



import com.example.function.page.pageEntity.SearchResultEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SearchRepository extends JpaRepository<SearchResultEntity, Long> {

    List<SearchResultEntity> findByKeyword(String keyword);
}
