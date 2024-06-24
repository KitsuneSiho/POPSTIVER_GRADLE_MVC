package kr.bit.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/topic", "/queue");
        config.setApplicationDestinationPrefixes("/app");
        config.setUserDestinationPrefix("/user");

    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/chat-websocket") // 엔드포인트를 JSP에서 참조하는 경로와 일치//        registry.addEndpoint("/chat")
//                .setAllowedOrigins("*") // 실제 배포 시에는 특정 도메인으로 제한하는 것이 좋습니다.
//        allowedOrigins에 *를 설정하면서 allowCredentials가 true로 설정된 것이 문제

                .setAllowedOriginPatterns("*") // 모든 도메인 허용
                // .setAllowedOrigins("http://localhost:8080") // 특정 도메인을 허용할 때 사용
                .withSockJS();
    }
}
