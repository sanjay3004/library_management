package com.example.library_management.service;

import com.example.library_management.dto.BookDTO;
import com.example.library_management.entity.Book;
import com.example.library_management.entity.Entry;

import java.util.List;

public interface BookService {

    Book addBook(BookDTO book);
    Book getBookById(String id);
    List<BookDTO> getAllBooks();
    List<Entry> getAllEntries();

}
