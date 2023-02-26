package com.example.library_management.api;


import com.example.library_management.dto.UserRequestDTO;
import com.example.library_management.entity.Book;
import com.example.library_management.entity.Entry;
import com.example.library_management.entity.User;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("user")
public interface UserApi {


    @GetMapping("/get")
    ResponseEntity<List<User>> getUser();

    @PostMapping("/add")
    ResponseEntity<User> addUser(@RequestBody @Valid UserRequestDTO user);

    @PostMapping("/borrow/{isbn}")
    ResponseEntity<String> borrowBook(@PathVariable int isbn);

    @PostMapping("/return/{isbn}")
    ResponseEntity<String> returnBook(@PathVariable int isbn);

    @GetMapping("/entries")
    ResponseEntity<List<Entry>> getUserEntry();

    @GetMapping("/books")
    ResponseEntity<List<Book>> getBooks();


}
