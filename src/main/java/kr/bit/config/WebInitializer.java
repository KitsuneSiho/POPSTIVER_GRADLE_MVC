package kr.bit.config;

import jakarta.servlet.*;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import java.io.File;

public class WebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    // DispatcherServlet의 URL 매핑을 정의합니다. "/"는 모든 요청을 처리한다는 의미입니다.
    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" };
    }

    // Spring MVC 설정을 위한 클래스 지정
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] { WebConfig.class }; 
    }

    // 애플리케이션 전역 설정을 위한 클래스 지정
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] { RootConfig.class, SecurityConfig.class };
    }

    // UTF-8 인코딩 필터를 추가합니다.
    @Override
    protected Filter[] getServletFilters() {
        CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
        encodingFilter.setEncoding("UTF-8");
        encodingFilter.setForceEncoding(true); // 인코딩 강제 적용

        DelegatingFilterProxy securityFilterChain = new DelegatingFilterProxy("springSecurityFilterChain");
        logger.debug("Security Filter Chain Initialized");

        return new Filter[] { encodingFilter, securityFilterChain  };
    }


    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration) {

        String uploadDirectory = "C:\\POPSTIVER_GRADLE_MVC_TEST\\src\\main\\webapp\\resources\\uploads\\";
        // MultipartConfigElement를 설정하여 최대 파일 크기, 최대 요청 크기를 지정합니다.
        MultipartConfigElement multipartConfigElement = new MultipartConfigElement(
                uploadDirectory,
                1024 * 1024 * 10, // 최대 업로드 파일 크기: 10MB
                1024 * 1024 * 20, // 최대 요청 크기: 20MB
                1024 * 1024 * 5   // 파일 사이즈 임계값: 5MB
        );

        registration.setMultipartConfig(multipartConfigElement);
    }


}
