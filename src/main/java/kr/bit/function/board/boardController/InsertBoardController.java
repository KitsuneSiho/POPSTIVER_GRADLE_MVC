package kr.bit.function.board.boardController;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import kr.bit.function.board.boardDTO.CommunityDTO;
import kr.bit.function.board.boardService.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;


@PropertySource("classpath:properties/application.properties") //프로퍼티 소스를 불러오겠다!
@Controller
@RequestMapping(value = "/freeBoard")
public class InsertBoardController {

    @Value("${ServerURL}")
    private String ServerURL;

    private static final Logger logger = LoggerFactory.getLogger(InsertBoardController.class);
    private BoardService boardService;

    public InsertBoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @PostMapping("/insertWrite")
    @ResponseBody
    public String insertFreeWrite(@RequestParam("board_title") String boardTitle,
                                  @RequestParam("board_content") String boardContent,
                                  @RequestParam("user_id") String userId,
                                  @RequestParam("user_name") String userName,
                                  @RequestParam(value = "board_views", defaultValue = "0") int boardViews,
                                  @RequestPart("file") MultipartFile boardAttachment) {

        try {
            // CommunityDTO 객체 생성
            CommunityDTO communityDTO = new CommunityDTO();
            communityDTO.setBoard_title(boardTitle);
            communityDTO.setBoard_content(boardContent);
            communityDTO.setUser_id(userId);
            communityDTO.setUser_name(userName);
            communityDTO.setBoard_views(boardViews);

            // 파일 처리
            String filePath = null;
            if (boardAttachment != null && !boardAttachment.isEmpty()) {
                filePath = uploadFileToServer(boardAttachment, userId);
                communityDTO.setBoard_attachment(filePath);
            }

            // 서비스 호출
            boardService.insertCommunity(communityDTO);

            // 파일 경로를 반환
            return filePath != null ? "{\"filePath\":\"" + filePath + "\"}" : "{}";
        } catch (Exception e) {
            logger.error("게시글 저장 중 오류 발생", e);
            e.printStackTrace();
            return "{\"error\":\"게시글 저장 중 오류 발생\"}";
        }
    }

    private String uploadFileToServer(MultipartFile file, String userId) throws IOException {
        String uploadUrl = ServerURL + "/upload"; // Flask 서버의 파일 업로드 엔드포인트 URL
        URL url = new URL(uploadUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setDoOutput(true);
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW");

        OutputStream outputStream = connection.getOutputStream();
        PrintWriter writer = new PrintWriter(new OutputStreamWriter(outputStream, StandardCharsets.UTF_8), true);

        // user_id 폼 데이터
        writer.append("------WebKitFormBoundary7MA4YWxkTrZu0gW\r\n");
        writer.append("Content-Disposition: form-data; name=\"user_id\"\r\n\r\n");
        writer.append(userId).append("\r\n");

        // 파일 데이터
        writer.append("------WebKitFormBoundary7MA4YWxkTrZu0gW\r\n");
        writer.append("Content-Disposition: form-data; name=\"file\"; filename=\"" + file.getOriginalFilename() + "\"\r\n");
        writer.append("Content-Type: ").append("application/octet-stream").append("\r\n\r\n");
        writer.flush();
        IOUtils.copy(file.getInputStream(), outputStream);
        outputStream.flush();
        writer.append("\r\n").append("------WebKitFormBoundary7MA4YWxkTrZu0gW--").append("\r\n");
        writer.close();

        // 응답 처리
        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            InputStream responseStream = connection.getInputStream();
            String response = new BufferedReader(new InputStreamReader(responseStream, StandardCharsets.UTF_8))
                    .lines().collect(Collectors.joining("\n"));
            responseStream.close();

            // JSON 파싱
            JSONObject jsonResponse = new JSONObject(response);
            return jsonResponse.getString("file_path");
        } else {
            throw new IOException("File upload failed with response code " + responseCode);
        }
    }
}

