package com.example.library_management.controller;

import com.example.library_management.api.UserApi;
import com.example.library_management.dto.UserRequestDTO;
import com.example.library_management.entity.Book;
import com.example.library_management.entity.Entry;
import com.example.library_management.entity.User;
import com.example.library_management.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController implements UserApi {
    @Autowired
    UserService userService;
    @Override
    public ResponseEntity<List<User>> getUser() {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUsers());
    }

    @Override
    public ResponseEntity<User> addUser(UserRequestDTO user) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.addUser(user));
    }

    @Override
    public ResponseEntity<String> borrowBook(int isbn) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.borrowBook(isbn));
    }

    @Override
    public ResponseEntity<String> returnBook(int isbn) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.returnBook(isbn));
    }

    @Override
    public ResponseEntity<List<Entry>> getUserEntry() {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getEntries());
    }

    @Override
    public ResponseEntity<List<Book>> getBooks() {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getBooks());
    }


}
