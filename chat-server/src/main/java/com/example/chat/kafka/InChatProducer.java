package com.example.chat.kafka;

import com.example.common.domain.Chat;
import com.example.common.kafka.KafkaProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class InChatProducer {
    private final KafkaProducer kafkaProducer;

    public void send(Chat chat) {
        String kafkaTopic = "topic-in-chat";
        kafkaProducer.send(kafkaTopic, chat);
    }
}
