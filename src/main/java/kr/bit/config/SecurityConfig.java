package kr.bit.config;

import kr.bit.function.member.oauth2.CustomAuthorizationRequestResolver;
import kr.bit.function.member.oauth2.CustomClientRegistrationRepo;
import kr.bit.function.member.oauth2.CustomLogoutSuccessHandler;
import kr.bit.function.member.oauth2.CustomOAuth2AuthorizedClientService;
import kr.bit.function.member.oauth2.SocialClientRegistration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.web.SecurityFilterChain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final SocialClientRegistration socialClientRegistration;
//    private final CustomOAuth2UserService customOAuth2UserService;
    private final CustomClientRegistrationRepo customClientRegistrationRepo;
    private final CustomOAuth2AuthorizedClientService customOAuth2AuthorizedClientService;
    private final CustomLogoutSuccessHandler customLogoutSuccessHandler; // LogoutSuccessHandler 주입
    private final JdbcTemplate jdbcTemplate;

    private static final Logger logger = LoggerFactory.getLogger(SecurityConfig.class);

    public SecurityConfig(
                          CustomClientRegistrationRepo customClientRegistrationRepo,
                          CustomOAuth2AuthorizedClientService customOAuth2AuthorizedClientService,
                          JdbcTemplate jdbcTemplate,
                          SocialClientRegistration socialClientRegistration,
                          CustomLogoutSuccessHandler customLogoutSuccessHandler) {
//        this.customOAuth2UserService = customOAuth2UserService;
        this.customClientRegistrationRepo = customClientRegistrationRepo;
        this.customOAuth2AuthorizedClientService = customOAuth2AuthorizedClientService;
        this.jdbcTemplate = jdbcTemplate;
        this.socialClientRegistration = socialClientRegistration;
        this.customLogoutSuccessHandler = customLogoutSuccessHandler;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        logger.debug("Configuring SecurityFilterChain...");
        http
                .csrf(csrf -> csrf.disable())
                .formLogin(login -> login.disable())
                .httpBasic(basic -> basic.disable())
                .authorizeRequests(auth -> auth
                        .requestMatchers("/", "/login/**", "/oauth2/**", "/resources/**", "/css/**", "/js/**", "/images/**").permitAll()
                        .anyRequest().authenticated()
                )
                .oauth2Login(oauth2 -> oauth2
                        .loginPage("/login")
                        .clientRegistrationRepository(customClientRegistrationRepo.clientRegistrationRepository())
                        .authorizedClientService(customOAuth2AuthorizedClientService.oAuth2AuthorizedClientService(jdbcTemplate, customClientRegistrationRepo.clientRegistrationRepository()))
                        .defaultSuccessUrl("/join_information", true)  // 로그인 성공 후 리디렉션할 URL 설정
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

