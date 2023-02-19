package com.system.sunday_management.pojo;

import com.system.sunday_management.model.Task;
import com.system.sunday_management.model.User;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Time;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TasksPojo {
    private Integer id;
    @NotEmpty(message = "title can't be empty")
    private String title;
    @NotEmpty(message = "description can't be empty")
    private String description;
    @NotEmpty(message = "this field can't be empty")
    private String assignedTo;
    @NotEmpty(message = "due date can't be empty")
    private String dueDate;
    @NotEmpty(message = "due time can't be empty")
    private String dueTime;

//    private MultipartFile image;
public TasksPojo(Task task){
    this.id= task.getId();
    this.title= task.getTitle();
    this.description= task.getDescription();
    this.assignedTo=task.getAssignedTo();
    this.dueDate= task.getDueDate();
    this.dueTime= task.getDueTime();

}


}
