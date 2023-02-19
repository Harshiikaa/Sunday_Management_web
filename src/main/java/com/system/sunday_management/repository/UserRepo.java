package com.system.sunday_management.repository;

import com.system.sunday_management.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

// user chahi user class ho entity ko
@Repository
public interface UserRepo extends JpaRepository<User,String> {
    @Query (value = "select * from users where email=?1", nativeQuery = true)
    Optional<User> findByEmail(String email);
}
