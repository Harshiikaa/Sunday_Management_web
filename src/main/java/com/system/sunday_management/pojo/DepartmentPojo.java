package com.system.sunday_management.pojo;

import com.system.sunday_management.model.Department;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentPojo {
    private int id;
    @NotEmpty(message = "this field can't be empty")
    private String department;

    public DepartmentPojo(Department department){
        this.id=department.getId();
        this.department=department.getDepartment();
    }

}
