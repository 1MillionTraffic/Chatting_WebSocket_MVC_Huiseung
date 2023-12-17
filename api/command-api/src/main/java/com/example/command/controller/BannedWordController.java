package com.example.command.controller;

import com.example.command.request.CreateBannedWordRequest;
import com.example.command.request.UpdateBannedWordRequest;
import com.example.command.service.BannedWordService;
import com.example.common.exception.BannedWordDuplicateException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/banned-words")
@RestController
@RequiredArgsConstructor
public class BannedWordController {
    private final BannedWordService bannedWordService;

    @PostMapping("")
    public String create(@RequestBody CreateBannedWordRequest request) {
        if (bannedWordService.existsByWord(request.getWord())){
            throw new BannedWordDuplicateException("duplicate word: " + request.getWord());
        }
        return bannedWordService.create(request).getBannedWordId();
    }

    @PutMapping("/{bannedWordId}")
    public String update(@PathVariable("bannedWordId") String bannedWordI, @RequestBody UpdateBannedWordRequest request) {
        return bannedWordService.update(bannedWordI, request).getBannedWordId();
    }

    @DeleteMapping("/{bannedWordId}")
    public String delete(@PathVariable("bannedWordId") String bannedWordId) {
        bannedWordService.delete(bannedWordId);
        return "delete-success";
    }
}
