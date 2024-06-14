package com.example.function.page.pageRepository;


import com.example.function.page.pageEntity.CommBoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CommBoardRepository extends JpaRepository<CommBoardEntity, Integer> {
}