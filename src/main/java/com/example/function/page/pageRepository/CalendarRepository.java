package com.example.function.page.pageRepository;


import com.example.function.page.pageEntity.CalendarEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CalendarRepository extends JpaRepository<CalendarEntity, Long> {
}