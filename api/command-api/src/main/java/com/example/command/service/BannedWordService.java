package com.example.command.service;

import com.example.command.request.CreateBannedWordRequest;
import com.example.command.request.UpdateBannedWordRequest;
import com.example.common.domain.BannedWord;
import com.example.common.repository.BannedWordRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class BannedWordService {
    private final BannedWordRepository bannedWordRepository;

    public BannedWord create(CreateBannedWordRequest request) {
        return bannedWordRepository.create(BannedWord.builder()
                .word(request.getWord())
                .build());
    }

    public boolean existsByWord(String word){
        return bannedWordRepository.existsByWord(word);
    }

    public BannedWord update(String bannedWordId, UpdateBannedWordRequest request) {
        return bannedWordRepository.update(BannedWord.builder()
                .bannedWordId(bannedWordId)
                .word(request.getWord())
                .build());
    }

    public void delete(String bannedWordId) {
        bannedWordRepository.delete(bannedWordId);
    }
}
