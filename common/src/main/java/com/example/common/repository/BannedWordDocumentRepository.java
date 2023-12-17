package com.example.common.repository;

import com.example.common.document.BannedWordDocument;
import com.example.common.domain.BannedWord;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BannedWordDocumentRepository extends MongoRepository<BannedWordDocument, String> {
    boolean existsByWord(String word);
}
