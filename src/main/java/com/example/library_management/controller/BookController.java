package com.example.library_management.controller;

import com.example.library_management.api.BookApi;
import com.example.library_management.dto.BookDTO;
import com.example.library_management.entity.Book;
import com.example.library_management.entity.Entry;
import com.example.library_management.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController implements BookApi {

    @Autowired
    BookService bookService;

    @Override
    public ResponseEntity<Book> addBook(BookDTO book) {
        return ResponseEntity.status(HttpStatus.OK).body(bookService.addBook(book));
    }

    @Override
    public ResponseEntity<List<BookDTO>> getBooks() {
        return ResponseEntity.status(HttpStatus.OK).body(bookService.getAllBooks());
    }

    @Override
    public ResponseEntity<List<Entry>> getEntriesAll() {
        return ResponseEntity.status(HttpStatus.OK).body(bookService.getAllEntries());
    }


}
