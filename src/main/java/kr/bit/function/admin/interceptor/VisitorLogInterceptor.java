package kr.bit.function.admin.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.bit.function.admin.service.VisitorLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Component
public class VisitorLogInterceptor implements HandlerInterceptor {

    @Autowired
    private VisitorLogService visitorLogService;

    private static final Set<String> EXCLUDE_EXTENSIONS = new HashSet<>();
    private Map<String, Long> visitCache = new HashMap<>();
    private static final long THRESHOLD = 5000; // 5 seconds

    static {
        EXCLUDE_EXTENSIONS.add("css");
        EXCLUDE_EXTENSIONS.add("js");
        EXCLUDE_EXTENSIONS.add("png");
        EXCLUDE_EXTENSIONS.add("jpg");
        EXCLUDE_EXTENSIONS.add("jpeg");
        EXCLUDE_EXTENSIONS.add("gif");
        EXCLUDE_EXTENSIONS.add("woff");
        EXCLUDE_EXTENSIONS.add("woff2");
        EXCLUDE_EXTENSIONS.add("ttf");
        EXCLUDE_EXTENSIONS.add("svg");
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uri = request.getRequestURI();
        String extension = getExtension(uri);

        if (!EXCLUDE_EXTENSIONS.contains(extension)) {
            String ipAddress = request.getRemoteAddr();
            String userAgent = request.getHeader("User-Agent");
            String pageVisited = request.getRequestURI();

            String key = ipAddress + ":" + userAgent + ":" + pageVisited;
            long currentTime = System.currentTimeMillis();

            if (!visitCache.containsKey(key) || currentTime - visitCache.get(key) > THRESHOLD) {
                visitCache.put(key, currentTime);

//                System.out.println("VisitorLogInterceptor - IP: " + ipAddress + ", User-Agent: " + userAgent + ", Page: " + pageVisited);

                visitorLogService.logVisit(ipAddress, userAgent, pageVisited);
            }
        }

        return true;
    }

    private String getExtension(String uri) {
        int lastIndex = uri.lastIndexOf('.');
        if (lastIndex == -1) {
            return "";
        }
        return uri.substring(lastIndex + 1);
    }
}
