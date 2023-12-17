package com.example.common.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Room {
    private String roomId;
    private String title;
    private RoomStatus status;

    public void delete(){
        this.status = RoomStatus.DELETED;
    }
    public void update(String title){
        this.title = title;
    }
}
