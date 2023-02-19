package com.system.sunday_management.service;

import com.system.sunday_management.model.User;
import com.system.sunday_management.pojo.UserPojo;

import java.io.IOException;
import java.util.List;

public interface UserService {
    String save(UserPojo userPojo) throws IOException;

    List<User> fetchAll();

    User fetchById(Integer id);

    User findByEmail(String email);

    void deleteById(Integer id);
}
