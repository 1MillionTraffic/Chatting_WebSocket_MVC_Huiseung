package com.example.common.repository;

import com.example.common.document.RoomDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RoomDocumentRepository extends MongoRepository<RoomDocument, String> {
}
