package com.example.streamanalyzer.service.agregation.data;

import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

/**
 * Вспомогательный класс для хранения данных окна
 */
@Getter
@ToString
public class WindowData {
    private int count = 0;
    private final List<String> samples = new ArrayList<>();

    /**
     * Добавить сообщение
     * @param msg сообщение
     */
    public void addMessage(String msg) {
        count++;
        if (samples.size() < 3) {
            samples.add(msg);
        }
    }
}
