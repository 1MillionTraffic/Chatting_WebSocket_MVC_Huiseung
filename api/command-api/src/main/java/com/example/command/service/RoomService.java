package com.example.command.service;

import com.example.command.request.CreateRoomRequest;
import com.example.command.request.UpdateRoomRequest;
import com.example.common.domain.Room;
import com.example.common.domain.RoomStatus;
import com.example.common.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RoomService {
    private final RoomRepository roomRepository;

    public Room create(CreateRoomRequest request) {
        Room chat = Room.builder()
                .title(request.getTitle())
                .status(RoomStatus.ACTIVE)
                .build();
        return roomRepository.create(chat);
    }

    public boolean existsByTitle(String title){
        return roomRepository.existsByTitle(title);
    }

    public Room update(String roomId, UpdateRoomRequest request) {
        Room room = roomRepository.findByRoomId(roomId);
        room.update(request.getTitle());
        return roomRepository.update(room);
    }

    public void delete(String chatId) {
        Room room = roomRepository.findByRoomId(chatId);
        room.delete();
        roomRepository.update(room);
    }
}
