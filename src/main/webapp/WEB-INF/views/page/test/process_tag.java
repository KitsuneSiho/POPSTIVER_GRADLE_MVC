import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.json.JSONObject;
import org.json.JSONArray;

@WebServlet("/process_tag")
public class ProcessTagServlet extends HttpServlet {

    private final RestTemplate restTemplate = new RestTemplate();
    private final String FLASK_URL = "http://localhost:8000/extract_tags";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String title = request.getParameter("title");
        String content = request.getParameter("content");

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Content-Type", "application/json");

            JSONObject requestBody = new JSONObject();
            requestBody.put("title", title);
            requestBody.put("content", content);

            HttpEntity<String> entity = new HttpEntity<>(requestBody.toString(), headers);

            ResponseEntity<String> flaskResponse = restTemplate.exchange(FLASK_URL, HttpMethod.POST, entity, String.class);

            JSONArray tags = new JSONArray(flaskResponse.getBody());
            StringBuilder tagsString = new StringBuilder();
            for (int i = 0; i < tags.length(); i++) {
                if (i > 0) {
                    tagsString.append(", ");
                }
                tagsString.append(tags.getString(i));
            }

            response.sendRedirect("tag_form.jsp?tags=" + tagsString.toString());

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error: " + e.getMessage());
        }
    }
}
