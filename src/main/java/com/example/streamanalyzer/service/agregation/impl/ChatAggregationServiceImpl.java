package com.example.streamanalyzer.service.agregation.impl;

import com.example.streamanalyzer.data.entity.HotMomentsEntity;
import com.example.streamanalyzer.service.agregation.ChatAggregationService;
import com.example.streamanalyzer.service.agregation.PeakDetector;
import com.example.streamanalyzer.service.agregation.WindowStorage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Реализация сервиса агрегации чата
 */
@Service
@RequiredArgsConstructor
public class ChatAggregationServiceImpl implements ChatAggregationService {

    private final PeakDetector peakDetector;
    private final WindowStorage windowStorage;

    @Override
    public void onMessage(String channel, String message) {
        long windowStart = (System.currentTimeMillis() / 10_000) * 10_000;
        windowStorage.addMessage(channel, windowStart, message);
    }

    @Override
    public List<HotMomentsEntity> extractPeaks(String channel, LocalDateTime streamStart) {
        return peakDetector.detectPeaks(channel, streamStart, windowStorage.getWindows(channel));
    }

    @Override
    public void clear(String channel) {
        windowStorage.clear(channel);
    }
}
