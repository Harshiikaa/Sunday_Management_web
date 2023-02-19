package com.system.sunday_management.controller;
import com.system.sunday_management.model.User;
import com.system.sunday_management.pojo.UserPojo;
import com.system.sunday_management.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
//    public static String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/sunday_management";
    @GetMapping("/forgotPassword")
    public String getForgotPass() {
        return "user/forgot_password";
    }

    @GetMapping("/register")
    public String getRegister(Model model) {
        model.addAttribute("user", new UserPojo());
        return "user/signup";
    }

    @PostMapping("/register/save")
    public String createUser(UserPojo userPojo,
                             BindingResult bindingResult, RedirectAttributes redirectAttributes) throws IOException {

        Map<String, String> requestError = validateRequest(bindingResult);
        if (requestError != null) {
            redirectAttributes.addFlashAttribute("requestError", requestError);
            return "redirect:/user/register";
        }
        userService.save(userPojo);
        redirectAttributes.addFlashAttribute("successMsg", "User saved successfully");
        return "redirect:/user/login";
    }
    public Map<String, String> validateRequest(BindingResult bindingResult) {
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

    @GetMapping("/userList")
    public String getUser(Model model) {
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

        @GetMapping("/edit/{id}")
    public String editUser(@PathVariable("id") Integer id, Model model) {
        User user = userService.fetchById(id);
        model.addAttribute("user", new UserPojo(user));
        return "user/user_profile";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
        userService.deleteById(id);
        redirectAttributes.addFlashAttribute("deleteMsg", "Row delete successfully");
        return "redirect:/user/profile";
    }

    //    private String convertImageToBase64(String filename) {
//        String filePath = System.getProperty("user.dir") + "/canteen_mgmt/" + filename;
//    }
//    public String getImageBase64(String fileName) {
//        String filePath = System.getProperty("user.dir") + "/sunday_management/";
//        File file = new File(filePath + fileName);
//        byte[] bytes = new byte[0];
//        try {
//            bytes = Files.readAllBytes(file.toPath());
//        } catch (IOException e) {
//            e.printStackTrace();
//            return null;
//        }
//        String base64 = Base64.getEncoder().encodeToString(bytes);
//        return base64;
//    }
}
