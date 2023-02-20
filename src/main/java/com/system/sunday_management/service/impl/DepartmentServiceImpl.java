package com.system.sunday_management.service.impl;
import com.system.sunday_management.model.Department;
import com.system.sunday_management.pojo.DepartmentPojo;
import com.system.sunday_management.repository.DepartmentRepo;
import com.system.sunday_management.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepo departmentRepo;

    @Override
    public String saveDepartment(DepartmentPojo departmentPojo) throws IOException {
        Department department = new Department();
        department.setId(departmentPojo.getId());
        department.setDepartment(departmentPojo.getDepartment());
        departmentRepo.save(department);
        return "Saved";
    }

    @Override
    public List<Department> getAllDepartment() {
        return departmentRepo.findAll();
    }

    @Override
    public Department fetchById(Integer id) {
        return departmentRepo.findById(id).orElseThrow(()->new RuntimeException("Not Found"));
    }

    @Override
    public void deleteById(Integer id) {
        departmentRepo.deleteById(id);
    }
}
