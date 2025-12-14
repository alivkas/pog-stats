package com.example.streamanalyzer.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "twitch")
@Getter
@Setter
public class TwitchApiProperties {
    private String accessToken;
    private String botUsername;
    private String channel;
}
