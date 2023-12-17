package com.example.common.repository;

import com.example.common.document.RoomDocument;
import com.example.common.domain.Room;
import com.example.common.exception.RoomNotFoundException;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class RoomRepository {
    private final RoomDocumentRepository roomDocumentRepository;

    public void create(Room chat) {
        roomDocumentRepository.save(RoomDocument.builder()
                .title(chat.getTitle())
                .status(chat.getStatus())
                .build());
    }

    public void update(Room room) {
        roomDocumentRepository.save(RoomDocument.builder()
                .roomId(room.getRoomId())
                .title(room.getTitle())
                .build());
    }

    public Room findByRoomId(String roomId) {
        RoomDocument roomDocument = roomDocumentRepository.findById(roomId)
                .orElseThrow(() -> new RoomNotFoundException(String.format("room not found, roomId: %s", roomId)));
        return Room.builder()
                .roomId(roomDocument.getRoomId())
                .title(roomDocument.getTitle())
                .status(roomDocument.getStatus())
                .build();
    }

    public List<Room> findAll() {
        return roomDocumentRepository.findAll().stream()
                .map(e -> Room.builder()
                        .roomId(e.getRoomId())
                        .title(e.getTitle())
                        .status(e.getStatus())
                        .build())
                .collect(Collectors.toList());
    }
}
