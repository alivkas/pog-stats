package com.example.streamanalyzer.data.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Table(name = "streams")
@Getter
public class StreamEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column
    String streamerChannel;
    @Column
    LocalDateTime startedAt;
    @Column
    LocalDateTime endedAt;
}
