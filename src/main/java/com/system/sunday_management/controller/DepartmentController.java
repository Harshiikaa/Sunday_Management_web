package com.system.sunday_management.controller;
import com.system.sunday_management.model.Department;
import com.system.sunday_management.pojo.DepartmentPojo;
import com.system.sunday_management.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class DepartmentController {
    private final DepartmentService departmentService;
    @GetMapping("/addDepartment")
    public String addDepartment(Model model){
        Department department = new Department();
        model.addAttribute("department",department);
        return "/admin/admin_addDepartment";
    }
    @PostMapping("/addDepartment/save")
    public String saveDepartment(DepartmentPojo departmentPojo,
                                 BindingResult bindingResult, RedirectAttributes redirectAttributes) throws IOException {
        Map<String, String> requestError = validateRequest(bindingResult);
        if (requestError != null) {
            redirectAttributes.addFlashAttribute("requestError", requestError);
            return "redirect:/admin/addDepartment";
        }
        departmentService.saveDepartment(departmentPojo);
        redirectAttributes.addFlashAttribute("successMsg", "department saved successfully");
        return "redirect:/admin/addDepartment";
    }

    private Map<String, String> validateRequest(BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            return null;
        }
        Map<String, String> errors = new HashMap<>();
        bindingResult.getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName, message);
        });
        return errors;
    }
    @GetMapping("/departmentList")
    public String getDepartmentList(Model model) {
        List<Department> departments = departmentService.getAllDepartment();
        model.addAttribute("departmentList", departments.stream().map(department ->
                Department.builder()
                        .id(department.getId())
                        .department(department.getDepartment())
                        .build()
        ));
        return "/admin/admin_departmentList";
    }
    @GetMapping("/editDepartment/{id}")
    public String editDepartment(@PathVariable("id") Integer id, Model model) {
        Department department = departmentService.fetchById(id);
        model.addAttribute("department", new DepartmentPojo(department));
        return "admin/admin_addDepartmentUpdate";
    }

    @GetMapping("/deleteDepartment/{id}")
    public String deleteDepartment(@PathVariable("id") Integer id){
        System.out.println("Delete controller reached");
        Department department = departmentService.fetchById(id);
        int iddepartment=department.getId();
        departmentService.deleteById(iddepartment);
        return "redirect:/admin/departmentList";
    }

}
