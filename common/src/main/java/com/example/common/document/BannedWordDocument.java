package com.example.common.document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Document(collection = "banned_words")
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class BannedWordDocument {
    @Id
    private String bannedWordId;
    private String word;
}
