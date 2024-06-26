package kr.bit.controller;

import kr.bit.function.member.entity.TagEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.json.JSONObject;
import org.json.JSONArray;

import java.util.List;
import java.util.Map;

@Controller
public class TagController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final RestTemplate restTemplate = new RestTemplate();
    private final String FLASK_URL = "http://localhost:8000/extract_tags";

    @GetMapping("/tag_form")
    public String showTagForm() {
        return "page/test/tag_form";
    }

    @PostMapping("/process_tag")
    public String processTag(@RequestParam("title") String title,
                             @RequestParam("content") String content,
                             Model model) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Content-Type", "application/json");

            JSONObject request = new JSONObject();
            request.put("title", title);
            request.put("content", content);

            HttpEntity<String> entity = new HttpEntity<>(request.toString(), headers);

            ResponseEntity<String> response = restTemplate.exchange(FLASK_URL, HttpMethod.POST, entity, String.class);

            JSONArray tags = new JSONArray(response.getBody());
            model.addAttribute("tags", tags.toString());

            return "redirect:/tag_form?tags=" + tags.toString();
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Error: " + e.getMessage());
            return "page/test/tag_form";
        }
    }

    @PostMapping("/initialize_festival_tags")
    public String initializeFestivalTags(Model model) {
        try {
            String selectFestivalSql = "SELECT festival_no, festival_title, festival_content FROM festival WHERE festival_no NOT IN (SELECT DISTINCT festival_no FROM festival_tag)";
            List<Map<String, Object>> festivals = jdbcTemplate.queryForList(selectFestivalSql);

            for (Map<String, Object> festival : festivals) {
                int festivalNo = (int) festival.get("festival_no");
                String title = (String) festival.get("festival_title");
                String content = (String) festival.get("festival_content");

                HttpHeaders headers = new HttpHeaders();
                headers.set("Content-Type", "application/json");

                JSONObject request = new JSONObject();
                request.put("title", title);
                request.put("content", content);

                HttpEntity<String> entity = new HttpEntity<>(request.toString(), headers);

                ResponseEntity<String> response = restTemplate.exchange(FLASK_URL, HttpMethod.POST, entity, String.class);

                JSONArray tags = new JSONArray(response.getBody());
                saveTagsToDb(festivalNo, tags, "festival");
            }
            model.addAttribute("message", "Initial festival tags generated and saved successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Error generating initial tags: " + e.getMessage());
        }
        return "initialize_festival_tags";
    }

    @PostMapping("/add_festival")
    public String addFestival(@RequestParam("title") String title,
                              @RequestParam("content") String content,
                              Model model) {
        try {
            // 새로운 축제 정보를 데이터베이스에 저장
            String insertFestivalSql = "INSERT INTO festival (festival_title, festival_content) VALUES (?, ?)";
            jdbcTemplate.update(insertFestivalSql, title, content);

            // 방금 추가된 축제의 번호를 가져옴
            String selectLastInsertedSql = "SELECT festival_no FROM festival WHERE festival_title = ? AND festival_content = ?";
            Integer festivalNo = jdbcTemplate.queryForObject(selectLastInsertedSql, new Object[]{title, content}, Integer.class);

            // 태그 생성
            HttpHeaders headers = new HttpHeaders();
            headers.set("Content-Type", "application/json");

            JSONObject request = new JSONObject();
            request.put("title", title);
            request.put("content", content);

            HttpEntity<String> entity = new HttpEntity<>(request.toString(), headers);

            ResponseEntity<String> response = restTemplate.exchange(FLASK_URL, HttpMethod.POST, entity, String.class);

            JSONArray tags = new JSONArray(response.getBody());
            saveTagsToDb(festivalNo, tags, "festival");

            model.addAttribute("message", "Festival and tags added successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Error adding festival and tags: " + e.getMessage());
        }
        return "add_festival";
    }

    @GetMapping("/user_tags")
    public String showUserTagsForm(Model model) {
        List<String> allTags = getAllTags();
        model.addAttribute("allTags", allTags);
        return "page/test/user_tags";
    }

    @PostMapping("/save_user_tags")
    public String saveUserTags(@RequestParam("user_id") String userId,
                               @RequestParam("tags") List<String> tags,
                               Model model) {
        try {
            // 기존 태그 삭제
            String deleteTagsSql = "DELETE FROM user_tag WHERE user_id = ?";
            jdbcTemplate.update(deleteTagsSql, userId);

            // 새로운 태그 저장
            for (String tag : tags) {
                int tagNo = getOrCreateTagNo(tag);
                String insertUserTagSql = "INSERT INTO user_tag (user_id, tag_no) VALUES (?, ?)";
                jdbcTemplate.update(insertUserTagSql, userId, tagNo);
            }
            model.addAttribute("message", "User tags saved successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Error saving user tags: " + e.getMessage());
        }
        return "page/test/user_tags";
    }

    private List<String> getAllTags() {
        String selectTagsSql = "SELECT tag_name FROM tag";
        return jdbcTemplate.queryForList(selectTagsSql, String.class);
    }

    private void saveTagsToDb(int itemNo, JSONArray tags, String table) {
        try {
            for (int i = 0; i < tags.length(); i++) {
                String tag = tags.getString(i);

                // 태그가 존재하는지 확인하고, 없으면 추가
                int tagNo = getOrCreateTagNo(tag);

                if (table.equals("festival")) {
                    insertTagMapping("festival_tag", "festival_no", itemNo, tagNo);
                } else if (table.equals("popup")) {
                    insertTagMapping("popup_tag", "popup_no", itemNo, tagNo);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private int getOrCreateTagNo(String tagName) throws Exception {
        String selectTagSql = "SELECT tag_no FROM tag WHERE tag_name = ?";
        Integer tagNo = jdbcTemplate.queryForObject(selectTagSql, new Object[]{tagName}, Integer.class);

        if (tagNo != null) {
            return tagNo;
        } else {
            String insertTagSql = "INSERT INTO tag (tag_name) VALUES (?)";
            jdbcTemplate.update(insertTagSql, tagName);

            return jdbcTemplate.queryForObject(selectTagSql, new Object[]{tagName}, Integer.class);
        }
    }

    private void insertTagMapping(String tableName, String columnName, int itemNo, int tagNo) {
        String insertTagMappingSql = String.format("INSERT INTO %s (%s, tag_no) VALUES (?, ?)", tableName, columnName);
        jdbcTemplate.update(insertTagMappingSql, itemNo, tagNo);
    }
}
