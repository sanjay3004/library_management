package com.example.library_management.repository;

import com.example.library_management.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepo extends MongoRepository<User,String> {
    User findByEmail(String name);
}
