package com.system.sunday_management.controller;


import com.system.sunday_management.model.Notice;
import com.system.sunday_management.model.Task;
import com.system.sunday_management.service.NoticeService;
import com.system.sunday_management.service.TasksService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserDashboardController {
    private final NoticeService noticeService;
    private final TasksService tasksService;

    @GetMapping("/index")
    public String getIndexPage() {
        return "user/index";
    }

    @GetMapping ("/dashboard")
    public String getDashboard(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean hasUserRole = authentication.getAuthorities().stream()
                .anyMatch(r -> r.getAuthority().equals("ADMIN"));
        System.out.println(hasUserRole);
        if(hasUserRole){
            return "redirect:/admin/dashboard";
        }
        return "user/user_dashboard";
    }

    @GetMapping("/notices")
    public String getNotices(Model model){
        List<Notice> notices = noticeService.getAllNotices();
        model.addAttribute("noticeList", notices.stream().map(notice ->
                Notice.builder()
                        .id(notice.getId())
                        .title(notice.getTitle())
                        .description(notice.getDescription())
                        .build()
        ));
        return "user/user_notice";
    }

    @GetMapping("/tasks")
    public String getTaskPage(Model model){
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
        return "user/user_tasks";
    }

    @GetMapping("/profile")
    public String getProfile(){
        return "user/user_profile";
    }
    
    @GetMapping("/leave")
    public String getLeave(){
        return "user/user_leave";
    }
}
