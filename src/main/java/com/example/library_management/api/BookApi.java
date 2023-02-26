package com.example.library_management.api;

import com.example.library_management.dto.BookDTO;
import com.example.library_management.entity.Book;
import com.example.library_management.entity.Entry;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("book")
public interface BookApi {

    @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE},consumes = {MediaType.APPLICATION_JSON_VALUE},value = "/add")
    ResponseEntity<Book> addBook(@RequestBody BookDTO book);

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<List<BookDTO>> getBooks();

    @GetMapping("/entries")
    ResponseEntity<List<Entry>> getEntriesAll();



}
