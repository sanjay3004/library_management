package com.example.library_management.repository;

import com.example.library_management.entity.Entry;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface EntryRepo extends MongoRepository<Entry,String> {
    List<Entry> findByUserId(String userId);
    Optional<Entry> findByEntryId(String entryId);
    List<Entry> findByUserIdAndIsbn(String userId,int isbn);
}
