package com.system.sunday_management.repository;

import com.system.sunday_management.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface TasksRepo extends JpaRepository<Task, Integer> {
    @Query(value = "select * from task where id=?1", nativeQuery = true)
    Optional<Task> findById(Integer id);
}
