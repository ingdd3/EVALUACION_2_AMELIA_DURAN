package com.platinum.service;

import com.platinum.model.User;

import java.util.Map;

public interface IUserService {

    Map<String, String> verifyLogin(String username, String password);
    User findByUsername(String username);
    void saveUser(User user);
}