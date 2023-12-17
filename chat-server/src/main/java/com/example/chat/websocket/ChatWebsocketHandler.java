package com.example.chat.websocket;

import com.example.chat.kafka.InChatProducer;
import com.example.common.domain.Chat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Slf4j
@Component
@RequiredArgsConstructor
public class ChatWebsocketHandler extends TextWebSocketHandler {
    private final ObjectMapper objectMapper;
    private final RoomManager roomManager;
    private final InChatProducer inChatProducer;

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        log.info("session(id: {}) connect", session.getId());
        String name = Objects.requireNonNull(session.getHandshakeHeaders().get("name")).get(0);
        String roomId = Objects.requireNonNull(session.getHandshakeHeaders().get("room")).get(0);
        roomManager.add(roomId, session);
        inChatProducer.send(Chat.builder()
                .roomId(roomId)
                .message(name + "님이 입장 했습니다.")
                .build());
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        log.info("session(id: {}) disconnect", session.getId());
        String name = Objects.requireNonNull(session.getHandshakeHeaders().get("name")).get(0);
        String roomId = Objects.requireNonNull(session.getHandshakeHeaders().get("room")).get(0);
        roomManager.remove(roomId, session);
        inChatProducer.send(Chat.builder()
                .roomId(roomId)
                .message(name + "님이 퇴장 했습니다.")
                .build());
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) {
        String payload = message.getPayload();
        log.info("payload: " + payload);
        inChatProducer.send(getChatFromPayload(payload));
    }

    private Chat getChatFromPayload(String payload) {
        try {
            Chat chat = objectMapper.readValue(payload, Chat.class);
            log.info("chat from payload: " + chat);
            return chat;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
