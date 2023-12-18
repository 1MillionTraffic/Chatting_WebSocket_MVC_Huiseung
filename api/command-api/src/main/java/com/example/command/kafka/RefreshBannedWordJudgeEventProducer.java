package com.example.command.kafka;

import com.example.common.kafka.KafkaProducer;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class RefreshBannedWordJudgeEventProducer {
    private final KafkaProducer kafkaProducer;

    public void send() {
        String kafkaTopic = "topic-refresh-banned-word";
        kafkaProducer.send(kafkaTopic, LocalDateTime.now());
    }
}
