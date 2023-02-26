package com.example.library_management.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Document
@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Entry {
    @Id
    String entryId;
    String userId;
    String userName;
    int isbn;
    String book;
    LocalDate borrowDate;
    LocalDate due;
    LocalDate returnDate;

    public Entry( int isbn, String book) {
        User user= (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        this.userId = user.getId();
        this.userName = user.getName();
        this.isbn = isbn;
        this.book = book;
        this.borrowDate =LocalDate.now();
        this.due = LocalDate.now().plusDays(10);
        this.returnDate = null;
    }
}
