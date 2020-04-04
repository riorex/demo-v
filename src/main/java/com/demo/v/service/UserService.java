package com.demo.v.service;

import com.demo.v.model.User;

import java.util.List;

public interface UserService {

    User findByUserId(Long userId);
    User findUserByEmail(String email) ;
    User saveUser(User user);
    User getInfo(Long userId);
    List<User> getAll();
}
