package com.example.library_management.service;

import com.example.library_management.dto.UserRequestDTO;
import com.example.library_management.entity.Book;
import com.example.library_management.entity.Entry;
import com.example.library_management.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    List<User> getUsers();

    User addUser(UserRequestDTO userRequestDTO);

    String borrowBook(int isbn);

    String returnBook(int isbn);

    List<Entry> getEntries();

    List<Book> getBooks();
}
