package com.example.chat.kafka;

import com.example.chat.websocket.RoomManager;
import com.example.common.domain.Chat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;

@Slf4j
@Component
@RequiredArgsConstructor
public class OutChatConsumer {
    private final ObjectMapper objectMapper;
    private final RoomManager roomManager;

    @KafkaListener(topics = "topic-out-chat")
    public void consume(String chatKafkaMessage) {
        log.info("kafka message consume: " + chatKafkaMessage);
        try {
            Chat chat = objectMapper.readValue(chatKafkaMessage, Chat.class);
            roomManager.broadCastingSend(chat.getRoomId(), new TextMessage(chat.toString()));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
