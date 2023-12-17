package com.example.chat.websocket;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

public class SessionGroup {
    private final Set<WebSocketSession> sessions = new HashSet<>();

    public void addSession(WebSocketSession session) {
        sessions.add(session);
    }

    public void removeSession(WebSocketSession session) {
        sessions.remove(session);
    }

    public void broadCastingSend(TextMessage message) {
        sessions.forEach(s -> sendMessage(s, message));
    }

    private void sendMessage(WebSocketSession session, TextMessage textMessage) {
        try {
            session.sendMessage(textMessage);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
