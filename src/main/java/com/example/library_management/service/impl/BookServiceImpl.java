package com.example.library_management.service.impl;

import com.example.library_management.dto.BookDTO;
import com.example.library_management.entity.Book;
import com.example.library_management.entity.Entry;
import com.example.library_management.exception.AlreadyFoundException;
import com.example.library_management.exception.ResourceNotFound;
import com.example.library_management.repository.BookRepo;
import com.example.library_management.repository.EntryRepo;
import com.example.library_management.service.BookService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    BookRepo bookRepo;
    @Autowired
    ModelMapper mapper;
    @Autowired
    EntryRepo entryRepo;
    @Override
    public Book addBook(BookDTO book) {

        if(bookRepo.findByIsbn(book.getIsbn()).isEmpty()) {
            return bookRepo.save(mapper.map(book,Book.class));
        }
        throw new AlreadyFoundException("already exists");
    }

    @Override
    public Book getBookById(String id) {
        Optional<Book> book = bookRepo.findByBookId(id);
        if(book.isEmpty()){
            throw new ResourceNotFound("book not found");
        }
        return book.get();
    }

    @Override
    public List<BookDTO> getAllBooks() {
        System.out.println(SecurityContextHolder.getContext().getAuthentication().getPrincipal().getClass().getName());
        return bookRepo.findAll().stream().map(book ->mapper.map(book,BookDTO.class)).collect(Collectors.toList());
    }

    @Override
    public List<Entry> getAllEntries() {
        return entryRepo.findAll();
    }
}
