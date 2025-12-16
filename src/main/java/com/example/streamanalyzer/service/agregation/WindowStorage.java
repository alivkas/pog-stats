package com.example.streamanalyzer.service.agregation;

import com.example.streamanalyzer.service.agregation.data.WindowData;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Хранение окон чата
 */
@Component
public class WindowStorage {

    private final Map<String, Map<Long, WindowData>> windows = new ConcurrentHashMap<>();

    /**
     * Добавить сообщение
     * @param channel канал
     * @param windowStart начало окна
     * @param message сообщение
     */
    public void addMessage(String channel, long windowStart, String message) {
        windows.computeIfAbsent(channel, k -> new ConcurrentHashMap<>())
                .computeIfAbsent(windowStart, k -> new WindowData())
                .addMessage(message);
    }

    /**
     * Получить окно
     * @param channel канал
     * @return окно чата
     */
    public Map<Long, WindowData> getWindows(String channel) {
        return windows.getOrDefault(channel, Map.of());
    }

    /**
     * Очистить окна
     * @param channel канал
     */
    public void clear(String channel) {
        windows.remove(channel);
    }
}
