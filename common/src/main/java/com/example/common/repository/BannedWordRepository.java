package com.example.common.repository;

import com.example.common.document.BannedWordDocument;
import com.example.common.domain.BannedWord;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class BannedWordRepository {
    private final BannedWordDocumentRepository bannedWordDocumentRepository;

    public List<BannedWord> read() {
        return bannedWordDocumentRepository.findAll().stream()
                .map(e -> BannedWord.builder()
                        .bannedWordId(e.getBannedWordId())
                        .word(e.getWord())
                        .build())
                .collect(Collectors.toList());
    }

    public void create(List<BannedWord> bannedWords) {
        bannedWordDocumentRepository.saveAll(bannedWords.stream()
                .map(e -> BannedWordDocument.builder()
                        .word(e.getWord())
                        .build()
                ).collect(Collectors.toList()));
    }

    public void delete(String bannedWordId) {
        bannedWordDocumentRepository.deleteById(bannedWordId);
    }

    public void update(BannedWord bannedWord) {
        bannedWordDocumentRepository.save(BannedWordDocument.builder()
                .bannedWordId(bannedWord.getBannedWordId())
                .word(bannedWord.getWord())
                .build());
    }
}
