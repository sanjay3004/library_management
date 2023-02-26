package com.example.library_management.service.impl;

import com.example.library_management.config.SecurityConfig;
import com.example.library_management.dto.UserRequestDTO;
import com.example.library_management.entity.Book;
import com.example.library_management.entity.Entry;
import com.example.library_management.entity.User;
import com.example.library_management.exception.BadRequest;
import com.example.library_management.exception.BadRequestException;
import com.example.library_management.exception.ResourceNotFound;
import com.example.library_management.repository.BookRepo;
import com.example.library_management.repository.EntryRepo;
import com.example.library_management.repository.UserRepo;
import com.example.library_management.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepo userRepo;
    @Autowired
    EntryRepo entryRepo;
    @Autowired
    BookRepo bookRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByEmail(username);
    }



    @Override
    public List<User> getUsers() {
        return userRepo.findAll();
    }

    @Override
    public User addUser(UserRequestDTO userRequestDTO) {
        return userRepo.save(new User(userRequestDTO.getName(),userRequestDTO.getEmail(),userRequestDTO.getPassword(), userRequestDTO.getRegisterNO(), userRequestDTO.getDept(),userRequestDTO.getYear()));
    }

    @Override
    public String borrowBook(int isbn) {
        Optional<Book> book = bookRepo.findByIsbn(isbn);
        if(book.isEmpty()){
            throw new ResourceNotFound("enter the valid details");
        }
        if(book.get().getStock()==0){
            throw new BadRequest("out of stock");
        }
        Book book1=book.get();
        User user= (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        user.addBooks(book1);
        Entry entry=new Entry(isbn,book.get().getName());
        Entry savedEntry= entryRepo.save(entry);
        user.addEntries(savedEntry);
        userRepo.save(user);
        book.get().setStock(book.get().getStock()-1);
        bookRepo.save(book.get());
        return "success";
    }

    @Override
    public String returnBook(int isbn) {
        User user= (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<Entry> entry = user.getEntries().stream().filter(e -> e.getIsbn() == isbn && e.getReturnDate()==null).findFirst();
        if(!entry.isPresent()){
            throw new BadRequest("you don't have that book");
        }
        user.getEntries().remove(entry.get());
        Optional<Entry> entryInRepo = entryRepo.findByEntryId(entry.get().getEntryId());
        entryInRepo.get().setReturnDate(LocalDate.now());
        entryRepo.save(entryInRepo.get());
        user.getEntries().add(entryInRepo.get());
        List<Book> books=user.getBooks();
        for (Book b:books) {
            if(b.getIsbn()==isbn){
                books.remove(b);
                break;
            }
        }
        user.setBooks(books);
        userRepo.save(user);
        Optional<Book> bookInRepo = bookRepo.findByIsbn(entry.get().getIsbn());
        bookInRepo.get().setStock(bookInRepo.get().getStock()+1);
        bookRepo.save(bookInRepo.get());
        return "success";
    }

    @Override
    public List<Entry> getEntries() {
        User user= (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return user.getEntries();
    }

    @Override
    public List<Book> getBooks() {
        User user=(User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return user.getBooks();
    }
}
