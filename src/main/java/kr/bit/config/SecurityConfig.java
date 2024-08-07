package kr.bit.config;

import kr.bit.function.member.oauth2.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final SocialClientRegistration socialClientRegistration;
    private final CustomClientRegistrationRepo customClientRegistrationRepo;
    private final CustomOAuth2AuthorizedClientService customOAuth2AuthorizedClientService;
    private final CustomLogoutSuccessHandler customLogoutSuccessHandler; // LogoutSuccessHandler 주입
    private final JdbcTemplate jdbcTemplate;
    private final CustomAccessDeniedHandler customAccessDeniedHandler; // Custom AccessDeniedHandler 주입

    public SecurityConfig(
            CustomClientRegistrationRepo customClientRegistrationRepo,
            CustomOAuth2AuthorizedClientService customOAuth2AuthorizedClientService,
            JdbcTemplate jdbcTemplate,
            SocialClientRegistration socialClientRegistration,
            CustomLogoutSuccessHandler customLogoutSuccessHandler,
            CustomAccessDeniedHandler customAccessDeniedHandler) {
        this.customClientRegistrationRepo = customClientRegistrationRepo;
        this.customOAuth2AuthorizedClientService = customOAuth2AuthorizedClientService;
        this.jdbcTemplate = jdbcTemplate;
        this.socialClientRegistration = socialClientRegistration;
        this.customLogoutSuccessHandler = customLogoutSuccessHandler;
        this.customAccessDeniedHandler = customAccessDeniedHandler;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // CSRF 비활성화
                .headers(headers -> headers
                        .frameOptions(frameOptions -> frameOptions.sameOrigin()) // X-Frame-Options 설정, 최신 API 사용( 시큐리티 6.3)
                )
                .authorizeRequests(auth -> auth
                        // Publicly accessible endpoints
                        .requestMatchers("/", "/login/**", "/oauth2/**", "/resources/**", "/css/**", "/js/**", "/images/**", "/assets/**", "/fonts/**").permitAll()
                        .requestMatchers("/main", "/map", "/calendar", "/openAddFestival", "/openAddPopup", "/openAdd", "/mainPopup", "/mainFestival", "/popularAdd", "/posterInfo", "/searchResult").permitAll()
                        .requestMatchers("/comment/**").permitAll() // 댓글 기능
                        .requestMatchers("/freeBoard/**").permitAll() // 자유게시판 기능
                        .requestMatchers("/like/**").permitAll() // 좋아요 기능
                        .requestMatchers("/chat-websocket/**").permitAll() // WebSocket 엔드포인트 허용
                        .requestMatchers("/recommendations").permitAll() // 추천 기능

                        // Role-based access control
                        .requestMatchers("/money").hasAnyRole("HOST", "ADMIN") // 비즈니스 문의

                        .requestMatchers("/admin", "/visitor-stats", "/memberStats", "/likedPostsStats", "/mostViewedPostsStats", "/usersList", "/businessContents", "/chatManagement", "/recentReviews").hasAuthority("ROLE_ADMIN") // 관리자 페이지

                        // Any other request needs to be authenticated
                        .anyRequest().authenticated()
                )
                .oauth2Login(oauth2 -> oauth2
                        .loginPage("/login")
                        .clientRegistrationRepository(customClientRegistrationRepo.clientRegistrationRepository())
                        .authorizedClientService(customOAuth2AuthorizedClientService.oAuth2AuthorizedClientService(jdbcTemplate, customClientRegistrationRepo.clientRegistrationRepository()))
                        .defaultSuccessUrl("/login_success", true) // 로그인 성공 후 리디렉션할 URL 설정
                        .successHandler(new CustomAuthenticationSuccessHandler()) // Custom 성공 핸들러 등록
                        .authorizationEndpoint(authorization ->
                                authorization.authorizationRequestResolver(
                                        new CustomAuthorizationRequestResolver(customClientRegistrationRepo.clientRegistrationRepository())
                                )
                        )
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessHandler(customLogoutSuccessHandler) // 분리된 핸들러를 사용
                        .invalidateHttpSession(true)
                        .deleteCookies("refreshToken")
                )
                .exceptionHandling(exceptionHandling ->
                        exceptionHandling.accessDeniedHandler(customAccessDeniedHandler) // Custom AccessDeniedHandler 등록
                );

        return http.build();
    }

    @Bean
    public ClientRegistrationRepository clientRegistrationRepository() {
        return new InMemoryClientRegistrationRepository(
                socialClientRegistration.naverClientRegistration(),
                socialClientRegistration.googleClientRegistration(),
                socialClientRegistration.kakaoClientRegistration()
        );
    }
}
