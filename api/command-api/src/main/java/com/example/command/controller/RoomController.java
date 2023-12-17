package com.example.command.controller;

import com.example.command.request.CreateRoomRequest;
import com.example.command.request.UpdateRoomRequest;
import com.example.command.service.RoomService;
import com.example.common.exception.RoomDuplicateException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/rooms")
@RestController
@RequiredArgsConstructor
public class RoomController {
    private final RoomService roomService;

    @PostMapping("")
    public String create(@RequestBody CreateRoomRequest request) {
        if(roomService.existsByTitle(request.getTitle())){
            throw new RoomDuplicateException("duplicate title: "+request.getTitle());
        }
        return roomService.create(request).getRoomId();

    }

    @PutMapping("/{roomId}")
    public String update(@PathVariable("roomId") String roomId, @RequestBody UpdateRoomRequest request) {
        return roomService.update(roomId, request).getRoomId();
    }

    @DeleteMapping("/{roomId}")
    public String delete(@PathVariable("roomId") String roomId) {
        roomService.delete(roomId);
        return "delete-success";
    }
}
