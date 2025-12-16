package com.example.streamanalyzer.data.entity;

import com.example.streamanalyzer.data.base.BasicEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Сущность пиковых моментов чата на канале твича
 */
@Entity
@Table(name = "hot_moments", indexes = {
        @Index(name = "idx_hot_moments_channel_stream", columnList = "streamerChannel, streamStart")
})
@Getter
@Setter
public class HotMomentsEntity extends BasicEntity {

    @Column(nullable = false)
    private String streamerChannel;
    @Column
    private LocalDateTime peakTime;
    @Column
    private LocalDateTime streamStart;
    @Column
    private Integer messageCount;
    @ElementCollection
    @CollectionTable(
            name = "hot_moments_samples",
            joinColumns = @JoinColumn(name = "hot_moment_id")
    )
    @Column(name = "sample_message")
    private List<String> sampleMessages = new ArrayList<>();
}
