package com.example.common.document;


import com.example.common.domain.RoomStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Document(collection = "rooms")
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class RoomDocument {
    @Id
    private String roomId;
    private String title;
    private RoomStatus status;
}
