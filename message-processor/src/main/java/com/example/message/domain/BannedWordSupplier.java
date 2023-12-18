package com.example.message.domain;

import com.example.common.repository.BannedWordRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BannedWordSupplier {
    private final BannedWordRepository bannedWordRepository;

    public List<String> findAll() {
        return bannedWordRepository.read().stream().map(e -> e.getWord()).collect(Collectors.toList());
    }
}
