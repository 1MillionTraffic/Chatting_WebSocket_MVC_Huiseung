package com.example.query.service;


import com.example.common.domain.BannedWord;
import com.example.common.repository.BannedWordRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class BannedWordService {
    private final BannedWordRepository bannedWordRepository;

    public List<BannedWord> findAll() {
        return bannedWordRepository.read();
    }
}
