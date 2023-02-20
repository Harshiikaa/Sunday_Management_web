package com.system.sunday_management.controller;

import com.system.sunday_management.model.Leave;
import com.system.sunday_management.model.User;
import com.system.sunday_management.service.LeaveService;
import com.system.sunday_management.service.NoticeService;
import com.system.sunday_management.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminDashboardController {
    private final UserService userService;
    private final LeaveService leaveService;

    @GetMapping("/dashboard")
    public String getAdminDashboard(){
        return "admin/admin_dashboard";
    }

//    @GetMapping("/addTasks")
//    public String addTasks(){
//        return "admin/admin_addTasks";
//    }

    @GetMapping("/addNotices")
    public String addNotices(){
        return "admin/admin_addNotice";
    }

//    @GetMapping("/addDepartment")
//    public String addDepartment(){
//        return "admin/admin_addDepartment";
//    }

    @GetMapping("/leaveSection")
    public String leaveSection(Model model){
        List<Leave> leaves = leaveService.getAllLeave();
        model.addAttribute("leaveList", leaves.stream().map(leave ->
                Leave.builder()
                        .id(leave.getId())
                        .fromDate(leave.getFromDate())
                        .toDate(leave.getToDate())
                        .subject(leave.getSubject())
                        .description(leave.getDescription())
                        .build()
        ));
        return "admin/admin_leave";
    }

    @GetMapping("/employeeSection")
    public String employeeSection(Model model){
        List<User> users = userService.fetchAll();
        model.addAttribute("userList", users.stream().map(user ->
                        User.builder()
                                .id(user.getId())
//                        .imageBase64(getImageBase64(user.getImage()))
                                .fullName(user.getFullName())
                                .email(user.getEmail())
                                .mobileNo(user.getMobileNo())
                                .build()
        ));
//        model.addAttribute("UPLOAD_DIRECTORY", "/" + UPLOAD_DIRECTORY);
        return "admin/admin_staffs";
    }
//        return "admin/admin_staffs";
//    }
}
