package com.example.streamanalyzer.config;

import com.example.streamanalyzer.config.properties.TwitchApiProperties;
import com.github.philippheuer.credentialmanager.domain.OAuth2Credential;
import com.github.twitch4j.TwitchClient;
import com.github.twitch4j.TwitchClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Конфигурация Twitch4j
 */
@Configuration
public class TwitchApiConfiguration {

    /**
     * Бин конфигурации twitch4j
     * @param properties параметры для клиента твич
     * @return клиент твич
     */
    @Bean(destroyMethod = "close")
    public TwitchClient twitchClient(TwitchApiProperties properties) {
        OAuth2Credential credential = new OAuth2Credential(properties.getBotUsername(),
                properties.getAccessToken());

        return TwitchClientBuilder.builder()
                .withChatAccount(credential)
                .withEnableChat(true)
                .build();
    }
}
