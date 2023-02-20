package com.system.sunday_management.repository;

import com.system.sunday_management.model.Leave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface LeaveRepo extends JpaRepository<Leave,Integer> {
    @Query(value = "select * from leave where id=?1", nativeQuery = true)
    Optional<Leave> findById(Integer id);
}
