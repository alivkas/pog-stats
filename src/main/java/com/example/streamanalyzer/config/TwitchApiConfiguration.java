package com.example.streamanalyzer.config;

import com.example.streamanalyzer.config.properties.TwitchApiProperties;
import com.github.philippheuer.credentialmanager.domain.OAuth2Credential;
import com.github.twitch4j.TwitchClient;
import com.github.twitch4j.TwitchClientBuilder;
import com.github.twitch4j.chat.events.channel.ChannelMessageEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class TwitchApiConfiguration {
    private final TwitchApiProperties properties;

    @Bean(destroyMethod = "close")
    public TwitchClient twitchClient() {
        OAuth2Credential credential = new OAuth2Credential(properties.getBotUsername(),
                properties.getAccessToken());

        TwitchClient client = TwitchClientBuilder.builder()
                .withChatAccount(credential)
                .withEnableChat(true)
                .build();

        client.getEventManager().onEvent(ChannelMessageEvent.class, event -> {
            String channel = event.getChannel().getName();
            String username = event.getUser().getName();
            String message = event.getMessage();
            long timestamp = System.currentTimeMillis();

            System.out.printf("[%d] #%s | %s: %s%n", timestamp, channel, username, message);
        });

        client.getChat().joinChannel(properties.getChannel());
        return client;
    }
}
