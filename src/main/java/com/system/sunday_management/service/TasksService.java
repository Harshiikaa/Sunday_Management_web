package com.system.sunday_management.service;

import com.system.sunday_management.model.Task;
import com.system.sunday_management.pojo.TasksPojo;

import java.io.IOException;
import java.util.List;

public interface TasksService {
    String saveTasks(TasksPojo tasksPojo) throws IOException;

    List<Task> getAllTasks();
    Task fetchById(Integer id);

    void deleteById(Integer id);

//    List<Task> fetchAll();
}
