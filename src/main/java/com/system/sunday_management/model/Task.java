package com.system.sunday_management.model;

import jakarta.persistence.*;
import jdk.jshell.Snippet;
import lombok.*;

import java.sql.Time;
import java.util.Date;
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "task")
public class Task {
    @Id
    @SequenceGenerator(name = "tasks_seq_gen", sequenceName = "tasks_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "tasks_seq_gen", strategy = GenerationType.SEQUENCE)
    private Integer id;
    private String title;
    private String description;
    private String assignedTo;
    private String dueDate;
    private String dueTime;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getAssignedTo() {
        return assignedTo;
    }
    public void setAssignedTo(String assignedTo) {
        this.assignedTo = assignedTo;
    }
    public String getDueDate() {
        return dueDate;
    }
    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }
    public String getDueTime() {
        return dueTime;
    }
    public void setDueTime(String dueTime) {
        this.dueTime = dueTime;
    }



}
