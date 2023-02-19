package com.system.sunday_management.controller;


import com.system.sunday_management.model.Notice;
import com.system.sunday_management.model.Task;
import com.system.sunday_management.pojo.TasksPojo;
import com.system.sunday_management.service.TasksService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class TaskController {
    private final TasksService tasksService;

    @GetMapping("/addTasks")
    public String addTasks(Model model){
//        Task task = new Task();
        model.addAttribute("task",new TasksPojo());
        return "/admin/admin_addTasks";
    }

    @PostMapping("/addTasks/save")
    public String saveTasks(TasksPojo tasksPojo,
                            BindingResult bindingResult, RedirectAttributes redirectAttributes) throws IOException {
        Map<String, String> requestError = validateRequest(bindingResult);
        if (requestError != null) {
            redirectAttributes.addFlashAttribute("requestError", requestError);
            return "redirect:/admin/addTasks";
        }
        tasksService.saveTasks(tasksPojo);
        redirectAttributes.addFlashAttribute("successMsg", "User saved successfully");
        return "redirect:/admin/addTasks";
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

    @GetMapping("/taskList")
    public String getTasksList(Model model) {
        List<Task> tasks = tasksService.getAllTasks();
        model.addAttribute("taskList", tasks.stream().map(task ->
                Task.builder()
                        .id(task.getId())
                        .title(task.getTitle())
                        .description(task.getDescription())
                        .assignedTo(task.getAssignedTo())
                        .dueDate(task.getDueDate())
                        .dueTime(task.getDueTime())
                        .build()
        ));
        return "/admin/admin_taskList";
    }
    @GetMapping("/editTask/{id}")
    public String editTask(@PathVariable("id") Integer id, Model model) {
        Task task = tasksService.fetchById(id);
        model.addAttribute("task", new TasksPojo(task));
        return "admin/admin_addTasksUpdate";
    }

    @GetMapping("/deleteTask/{id}")
    public String deleteTask(@PathVariable("id") Integer id){
        System.out.println("Delete controller reached");
        Task task = tasksService.fetchById(id);
        int idtask =task.getId();
        tasksService.deleteById(idtask);
        return "redirect:/admin/taskList";
    }
}
