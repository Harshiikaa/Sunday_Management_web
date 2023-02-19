package com.system.sunday_management.repository;

import com.system.sunday_management.model.Notice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface NoticeRepo extends JpaRepository<Notice,Integer> {
    @Query(value = "select * from notice where id=?1", nativeQuery = true)
    Optional<Notice> findById(Integer id);
}
