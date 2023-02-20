package com.system.sunday_management.repository;

import com.system.sunday_management.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface DepartmentRepo extends JpaRepository <Department,Integer>  {
    @Query(value = "select * from department where id=?1", nativeQuery = true)
    Optional<Department> findById(Integer id);
}
