package com.system.sunday_management.service.impl;

import com.system.sunday_management.config.PasswordEncoderUtil;
//import com.system.sunday_management.model.Role;
import com.system.sunday_management.model.User;
import com.system.sunday_management.pojo.UserPojo;
//import com.system.sunday_management.repository.RoleRepo;
import com.system.sunday_management.repository.UserRepo;
import com.system.sunday_management.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

// required waala decorator ley constructor banaairakhna pardaina

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;


    @Override
    public String save(UserPojo userPojo) throws IOException {
        User user = new User();
        user.setEmail(userPojo.getEmail());
        user.setFullName(userPojo.getFullName());
        user.setMobileNo(userPojo.getMobileNumber());
        user.setPassword(PasswordEncoderUtil.getInstance().encode(userPojo.getPassword()));
        userRepo.save(user);
        return "Saved";

    }

    @Override
    public List<User> fetchAll() {
        return userRepo.findAll();
    }

    @Override
    public User fetchById(Integer id) {
        return null;
    }

    @Override
    public User findByEmail(String email) {
        return userRepo.findByEmail(email).orElseThrow(()->new RuntimeException("Not Found"));
    }

    @Override
    public void deleteById(Integer id) {

    }
}
