package com.system.sunday_management;

import com.system.sunday_management.model.User;
import com.system.sunday_management.repository.UserRepo;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserRepositoryTests {
    @Autowired
    private UserRepo userRepo;
    @Test
    @Order(1)
    @Rollback(value = false)
    public void saveUserTest(){
        User user = User.builder()
                .fullName("Osama Bin Laden")
                .email("osama@gmail.com")
                .mobileNo("9864711111")
                .image("abc.png")
                .password("attackNewYork")
                .build();
        userRepo.save(user);
        Assertions.assertThat(user.getId()).isGreaterThan(0);
    }
    @Test
    @Order(2)
    public void getUserTest(){
        User userC = userRepo.findByEmail("osama@gmail.com").get();
        Assertions.assertThat(userC.getEmail()).isEqualTo("osama@gmail.com");
    }
    @Test
    @Order(3)
    public void getListOfUserTest(){
        List<User> users = userRepo.findAll();
        Assertions.assertThat(users.size()  ).isGreaterThan(0);
    }
}
