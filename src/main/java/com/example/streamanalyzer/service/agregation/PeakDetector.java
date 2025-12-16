package com.example.streamanalyzer.service.agregation;

import com.example.streamanalyzer.data.entity.HotMomentsEntity;
import com.example.streamanalyzer.service.agregation.data.WindowData;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Map;

/**
 * Анализ пиковых моментов
 */
@Component
public class PeakDetector {

    private final static double PEAK_THRESHOLD_MULTIPLIER = 2.0;
    private final static int WINDOWS_LIMIT = 3;

    /**
     * Найти пиковый момент
     * @param channel канал
     * @param streamStart начало стрима
     * @param windows окна
     * @return список пиковых моментов
     */
    public List<HotMomentsEntity> detectPeaks(String channel,
                                              LocalDateTime streamStart,
                                              Map<Long, WindowData> windows) {
        if (windows.isEmpty()) {
            return List.of();
        }

        double avg = windows.values().stream()
                .mapToInt(WindowData::getCount)
                .average().orElse(0);

        return windows.entrySet().stream()
                .filter(e -> e.getValue().getCount() > (int)(avg * PEAK_THRESHOLD_MULTIPLIER))
                .sorted()
                .limit(WINDOWS_LIMIT)
                .map(e -> toHotMoment(channel, streamStart, e.getKey(), e.getValue()))
                .toList();
    }

    /**
     * Замаппить в пиковый момент
     * @param channel канал
     * @param streamStart начало стрима
     * @param windowMs время окна в мс
     * @param data данные окна
     * @return пиковый момент
     */
    private HotMomentsEntity toHotMoment(String channel,
                                         LocalDateTime streamStart,
                                         Long windowMs,
                                         WindowData data) {
        HotMomentsEntity hotMoments = new HotMomentsEntity();
        hotMoments.setStreamerChannel(channel);
        hotMoments.setStreamStart(streamStart);
        hotMoments.setPeakTime(LocalDateTime.ofInstant(Instant.ofEpochMilli(windowMs), ZoneOffset.UTC));
        hotMoments.setMessageCount(data.getCount());
        hotMoments.setSampleMessages(data.getSamples());
        return hotMoments;
    }
}
