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

    public boolean existsByWord(String word){
        return bannedWordDocumentRepository.existsByWord(word);
    }

    public BannedWord create(BannedWord bannedWord) {
        BannedWordDocument bannedWordDocument = bannedWordDocumentRepository.save(BannedWordDocument.builder()
                .bannedWordId(bannedWord.getBannedWordId())
                .word(bannedWord.getWord())
                .build());
        return BannedWord.builder()
                .bannedWordId(bannedWordDocument.getBannedWordId())
                .word(bannedWordDocument.getWord())
                .build();
    }

    public BannedWord update(BannedWord bannedWord) {
        BannedWordDocument bannedWordDocument = bannedWordDocumentRepository.save(BannedWordDocument.builder()
                .bannedWordId(bannedWord.getBannedWordId())
                .word(bannedWord.getWord())
                .build());
        return BannedWord.builder()
                .bannedWordId(bannedWordDocument.getBannedWordId())
                .word(bannedWordDocument.getWord())
                .build();
    }

    public void delete(String bannedWordId) {
        bannedWordDocumentRepository.deleteById(bannedWordId);
    }
}
