//package com.system.sunday_management.repository;
//
//import com.system.sunday_management.model.Employee;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.stereotype.Repository;
//
//import java.util.Optional;
//
//@Repository
//public interface EmployeeRepo extends JpaRepository <Employee,Long>{
//    @Query(value = "select * from employee where email=?1", nativeQuery = true)
//    Optional<Employee> findByEmail(String email);
//}
