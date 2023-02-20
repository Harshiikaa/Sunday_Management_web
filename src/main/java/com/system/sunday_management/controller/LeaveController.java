package com.system.sunday_management.controller;

import com.system.sunday_management.model.Leave;
import com.system.sunday_management.pojo.LeavePojo;
import com.system.sunday_management.service.LeaveService;
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
@RequestMapping("/user")
public class LeaveController {
    private final LeaveService leaveService;
    @GetMapping("/addLeave")
    public String addLeave(Model model){
        Leave leave = new Leave();
        model.addAttribute("leave",leave);
        return "/user/user_addLeave";
    }
    @PostMapping("/addLeave/save")
    public String saveLeave(LeavePojo leavePojo,
                            BindingResult bindingResult, RedirectAttributes redirectAttributes) throws IOException {
        Map<String, String> requestError = validateRequest(bindingResult);
        if (requestError != null) {
            redirectAttributes.addFlashAttribute("requestError", requestError);
            return "redirect:/user/addLeave";
        }
        leaveService.saveLeave(leavePojo);
        redirectAttributes.addFlashAttribute("successMsg", "leave saved successfully");
        return "redirect:/user/addLeave";
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
    @GetMapping("/leaveList")
    public String getLeaveList(Model model) {
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
        return "/user/user_leaveList";
    }
    @GetMapping("/editLeave/{id}")
    public String editLeave(@PathVariable("id") Integer id, Model model) {
        Leave leave = leaveService.fetchById(id);
        model.addAttribute("leave", new LeavePojo(leave));
        return "user/user_addLeaveUpdate";
    }

    @GetMapping("/deleteLeave/{id}")
    public String deleteLeave(@PathVariable("id") Integer id){
        System.out.println("Delete controller reached");
        Leave leave = leaveService.fetchById(id);
        int idleave=leave.getId();
        leaveService.deleteById(idleave);
        return "redirect:/user/leaveList";
    }

}
