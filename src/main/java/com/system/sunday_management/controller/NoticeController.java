package com.system.sunday_management.controller;

import com.system.sunday_management.model.Notice;
import com.system.sunday_management.pojo.NoticePojo;
import com.system.sunday_management.service.NoticeService;
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
public class NoticeController {
        private final NoticeService noticeService;
        @GetMapping("/addNotice")
        public String addNotice(Model model){
            Notice notice = new Notice();
            model.addAttribute("notice",notice);
            return "/admin/admin_addNotice";
        }
        @PostMapping("/addNotice/save")
        public String saveNotice(NoticePojo noticePojo,
                                 BindingResult bindingResult, RedirectAttributes redirectAttributes) throws IOException {
            Map<String, String> requestError = validateRequest(bindingResult);
            if (requestError != null) {
                redirectAttributes.addFlashAttribute("requestError", requestError);
                return "redirect:/admin/addNotice";
            }
            noticeService.saveNotice(noticePojo);
            redirectAttributes.addFlashAttribute("successMsg", "User saved successfully");
            return "redirect:/admin/addNotice";
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
    @GetMapping("/noticeList")
    public String getNoticeList(Model model) {
        List<Notice> notices = noticeService.getAllNotices();
        model.addAttribute("noticeList", notices.stream().map(notice ->
                Notice.builder()
                        .id(notice.getId())
                        .title(notice.getTitle())
                        .description(notice.getDescription())
                        .build()
        ));
        return "/admin/admin_noticeList";
    }
    @GetMapping("/editNotice/{id}")
    public String editNotice(@PathVariable("id") Integer id, Model model) {
        Notice notice = noticeService.fetchById(id);
        model.addAttribute("notice", new NoticePojo(notice));
        return "admin/admin_addNoticeUpdate";
    }

    @GetMapping("/deleteNotice/{id}")
    public String deleteNotice(@PathVariable("id") Integer id){
        System.out.println("Delete controller reached");
        Notice notice = noticeService.fetchById(id);
        int idnotice=notice.getId();
        noticeService.deleteById(idnotice);
        return "redirect:/admin/noticeList";
    }

}
