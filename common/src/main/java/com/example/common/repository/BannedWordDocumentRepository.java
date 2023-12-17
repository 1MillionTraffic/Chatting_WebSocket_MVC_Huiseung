package com.example.common.repository;

import com.example.common.document.BannedWordDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BannedWordDocumentRepository extends MongoRepository<BannedWordDocument, String> {
}
