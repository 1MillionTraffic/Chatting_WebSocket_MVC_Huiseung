package com.example.chat.websocket;

import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

@Component
public class RoomManager {
    private final ConcurrentHashMap<String, SessionGroup> rooms = new ConcurrentHashMap<>();

    public void add(String roomId, WebSocketSession session) {
        getSessionGroup(roomId).addSession(session);
    }

    public void remove(String roomId, WebSocketSession session) {
        getSessionGroup(roomId).removeSession(session);
    }

    public void broadCastingSend(String roomId, TextMessage message) {
        getSessionGroup(roomId).broadCastingSend(message);
    }

    private SessionGroup getSessionGroup(String roomId) {
        if (!rooms.containsKey(roomId)) {
            rooms.put(roomId, new SessionGroup());
        }
        return rooms.get(roomId);
    }
}
