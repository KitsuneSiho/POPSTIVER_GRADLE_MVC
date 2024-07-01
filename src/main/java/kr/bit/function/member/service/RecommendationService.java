package kr.bit.function.member.service;

import kr.bit.function.board.boardEntity.FestivalEntity;
import kr.bit.function.board.boardEntity.PopupEntity;
import kr.bit.function.member.repository.UserTagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@PropertySource("classpath:properties/application.properties")
@Service
public class RecommendationService {

    private final UserTagRepository userTagRepository;
    private final RestTemplate restTemplate;

    @Value("${ServerURL}")
    private String ServerURL;

    @Autowired
    public RecommendationService(UserTagRepository userTagRepository, RestTemplate restTemplate) {
        this.userTagRepository = userTagRepository;
        this.restTemplate = restTemplate;
    }

    public Map<String, List<Map<String, Object>>> getRecommendations(String userId) {
        String pythonServerUrl = ServerURL + "/recommendations?user_id=" + userId;
        return restTemplate.getForObject(pythonServerUrl, Map.class);
    }
}
