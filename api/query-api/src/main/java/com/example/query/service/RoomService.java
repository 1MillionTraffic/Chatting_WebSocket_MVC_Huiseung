package com.example.query.service;

import com.example.common.domain.Room;
import com.example.common.repository.RoomRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RoomService {
    private final RoomRepository chatRepository;

    public List<Room> findAll() {
        return chatRepository.findAll();
    }
}
