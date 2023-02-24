package com.system.sunday_management.service.impl;
import com.system.sunday_management.model.Notice;
import com.system.sunday_management.pojo.NoticePojo;
import com.system.sunday_management.repository.NoticeRepo;
import com.system.sunday_management.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService {
    private final NoticeRepo noticeRepo;
    @Override
    public String saveNotice(NoticePojo noticePojo) throws IOException {
        Notice notice = new Notice();
        notice.setId(noticePojo.getId());
        notice.setTitle(noticePojo.getTitle());
        notice.setDescription(noticePojo.getDescription());
        noticeRepo.save(notice);
        return "Saved";
    }
    @Override
    public List<Notice> getAllNotices() {
        return noticeRepo.findAll();
    }
    @Override
    public Notice fetchById(Integer id) {return noticeRepo.findById(id).orElseThrow(()->new RuntimeException("Not Found"));}
    @Override
    public void deleteById(Integer id) {
    noticeRepo.deleteById(id);
    }
}
