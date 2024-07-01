package kr.bit.function.member.service;

import kr.bit.function.board.boardDAO.BoardRepository;
import kr.bit.function.board.boardEntity.FestivalEntity;
import kr.bit.function.board.boardEntity.PopupEntity;
import kr.bit.function.member.repository.UserTagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
public class RecommendationService {

    private final UserTagRepository userTagRepository;
    private final BoardRepository boardRepository;
    private final RestTemplate restTemplate;

    @Autowired
    public RecommendationService(UserTagRepository userTagRepository, BoardRepository boardRepository, RestTemplate restTemplate) {
        this.userTagRepository = userTagRepository;
        this.boardRepository = boardRepository;
        this.restTemplate = restTemplate;
    }

    public List<FestivalEntity> recommendFestivals(String userId) {
        List<Integer> userTags = userTagRepository.findTagNosByUserId(userId);
        if (userTags.isEmpty()) {
            return Collections.emptyList();
        }
        return boardRepository.findFestivalsByTags(userTags);
    }

    public List<PopupEntity> recommendPopups(String userId) {
        List<Integer> userTags = userTagRepository.findTagNosByUserId(userId);
        if (userTags.isEmpty()) {
            return Collections.emptyList();
        }
        return boardRepository.findPopupsByTags(userTags);
    }

    public Map<String, List<Map<String, Object>>> getRecommendationsFromPythonServer(String userId) {
        String pythonServerUrl = "http://localhost:5000/recommendations?user_id=" + userId;
        return restTemplate.getForObject(pythonServerUrl, Map.class);
    }
}