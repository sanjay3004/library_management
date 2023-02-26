package com.example.library_management.entity;

import com.example.library_management.enums.Department;
import com.example.library_management.enums.Gender;
import com.example.library_management.enums.Role;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.List;

@Document
@Data
@Getter
@Setter
@NoArgsConstructor
public class User implements UserDetails {
    @Id
    String id;

    String name;

    String email;

    String password;

    String registerNO;

    Department dept;

    @Min(1) @Max(4)
    int year;


    Role role;

    boolean accountNotExpired=true;

    boolean accountNonLocked=true;

    boolean credentialsNonExpired=true;

    boolean enabled=true;

    List<GrantedAuthority> authorities;

    List<Book> books;
    List<Entry> entries;

    public User(String name,String email, String password,  String registerNO, Department dept, int year) {
        this.name = name;
        this.registerNO = registerNO;
        this.dept = dept;
        this.year = year;
        this.email=email;
        this.password=password;
        this.role=Role.USER;
        this.authorities=new ArrayList<>();
        GrantedAuthority authority=new SimpleGrantedAuthority(role.name());
        authorities.add(authority);
        books=new ArrayList<>();
        entries =new ArrayList<>();
    }
    public User(String name,String email, String password,  String registerNO, Department dept, int year,Role role) {
        this.name = name;
        this.registerNO = registerNO;
        this.dept = dept;
        this.year = year;
        this.email=email;
        this.password=password;
        this.role=role;
        this.authorities=new ArrayList<>();
        GrantedAuthority authority=new SimpleGrantedAuthority(role.name());
        authorities.add(authority);
        books=new ArrayList<>();
        entries =new ArrayList<>();
    }


    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNotExpired;
    }

    public void addBooks(Book book){
        books.add(book);
        return;
    }
    public void addEntries(Entry entry){
        entries.add(entry);
        return;
    }


}
