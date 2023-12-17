package com.example.query.controller;


import com.example.common.domain.BannedWord;
import com.example.query.service.BannedWordService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/banned-words")
@RestController
@RequiredArgsConstructor
public class BannedWordController {
    private final BannedWordService bannedWordService;

    @GetMapping("")
    public List<BannedWord> findAll() {
        return bannedWordService.findAll();
    }
}
