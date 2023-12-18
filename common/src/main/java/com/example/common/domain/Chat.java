package com.example.common.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Chat {
    private String roomId;
    private String message;

    @Override
    public String toString() {
        return "Chat{" +
                "roomId='" + roomId + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
