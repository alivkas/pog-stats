package com.example.streamanalyzer.listeners;

import com.example.streamanalyzer.service.agregation.ChatAggregationService;
import com.github.twitch4j.TwitchClient;
import com.github.twitch4j.chat.events.channel.ChannelMessageEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * Слушатель событий twitch чата
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class TwitchChatEventListener {

    private final TwitchClient client;
    private final ChatAggregationService chatAggregationService;

    /**
     * Прослушка события запуска приложения, для логирования чата у стримера
     * @param event событие запуска приложения
     */
    @EventListener
    public void onApplicationReady(ApplicationReadyEvent event) {
        client.getEventManager().onEvent(ChannelMessageEvent.class, chatEvent -> {
            String channel = chatEvent.getChannel().getName();
            String username = chatEvent.getUser().getName();
            String message = chatEvent.getMessage();
            long timestamp = System.currentTimeMillis();

            chatAggregationService.onMessage(channel, message);
            log.info("[{} ms] #{} | {}: {}", timestamp, channel, username, message);
        });

        client.getChat().joinChannel("bratishkinoff");
    }
}
