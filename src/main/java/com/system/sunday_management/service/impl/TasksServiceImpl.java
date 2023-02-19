package com.system.sunday_management.service.impl;

import com.system.sunday_management.model.Task;
import com.system.sunday_management.pojo.TasksPojo;
import com.system.sunday_management.repository.TasksRepo;
import com.system.sunday_management.service.TasksService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TasksServiceImpl implements TasksService {
    private final TasksRepo tasksRepo;
    @Override
    public String saveTasks(TasksPojo tasksPojo) throws IOException {
        Task task = new Task();
        task.setTitle(tasksPojo.getTitle());
        task.setDescription(tasksPojo.getDescription());
        task.setAssignedTo(tasksPojo.getAssignedTo());
        task.setDueDate(tasksPojo.getDueDate());
        task.setDueTime(tasksPojo.getDueTime());
        tasksRepo.save(task);
        return "Saved";
    }

//    @Override
//    public String save(TasksPojo tasksPojo) throws IOException {
//        return null;
//    }

    @Override
    public List<Task> getAllTasks() {
        return tasksRepo.findAll();
    }

    @Override
    public Task fetchById(Integer id) {
        return tasksRepo.findById(id).orElseThrow(()->new RuntimeException("Not Found"));
    }

    @Override
    public void deleteById(Integer id) {
        tasksRepo.deleteById(id);

    }

//    @Override
//    public List<Task> fetchAll() {
//        return tasksRepo.findAll();
//    }


}
