package com.example.demo.model;


import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserInfoRepo extends MongoRepository<UserInfo, String> {
    UserInfo findByEmail(String email);
}