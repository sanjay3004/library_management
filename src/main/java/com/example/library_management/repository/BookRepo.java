package com.example.library_management.repository;

import com.example.library_management.entity.Book;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepo extends MongoRepository<Book,String> {
   Optional<Book> findByBookId(String id);
   Optional<Book> findByIsbn(int isbn);
}
