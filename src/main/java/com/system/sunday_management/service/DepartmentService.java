package com.system.sunday_management.service;

import com.system.sunday_management.model.Department;
import com.system.sunday_management.model.Notice;
import com.system.sunday_management.pojo.DepartmentPojo;

import java.io.IOException;
import java.util.List;

public interface DepartmentService {
    List<Department> getAllDepartment();
    String saveDepartment(DepartmentPojo departmentPojo) throws IOException;
    Department fetchById(Integer id);
    void deleteById(Integer id);
}
