package kr.bit.config;

import jakarta.servlet.Filter;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;



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
}
