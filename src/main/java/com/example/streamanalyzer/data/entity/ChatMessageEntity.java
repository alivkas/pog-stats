package com.example.streamanalyzer.data.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "chat_messages")
@Getter
public class ChatMessageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column
    String username;
    @Column(columnDefinition = "TEXT")
    String message;
    @Column
    Long timestampMs;
    @OneToOne
    StreamEntity stream;
}
