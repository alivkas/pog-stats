package com.example.streamanalyzer.service.agregation;

import com.example.streamanalyzer.data.entity.HotMomentsEntity;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Сервис агрегации чата
 */
public interface ChatAggregationService {

    /**
     * Добавить сообщение
     * @param channel канал
     * @param message сообщение
     */
    void onMessage(String channel, String message);

    /**
     * Получить пиковые моменты
     * @param channel канал
     * @param streamStart начало стрима
     * @return список пиковых моментов
     */
    List<HotMomentsEntity> extractPeaks(String channel, LocalDateTime streamStart);

    /**
     * Очистить окна
     * @param channel канал
     */
    void clear(String channel);
}
