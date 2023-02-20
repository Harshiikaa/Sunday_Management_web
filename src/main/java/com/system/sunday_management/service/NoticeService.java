package com.system.sunday_management.service;
import com.system.sunday_management.model.Notice;
import com.system.sunday_management.pojo.NoticePojo;
import java.io.IOException;
import java.util.List;

public interface NoticeService {
    String saveNotice(NoticePojo noticePojo) throws IOException;
    List<Notice> getAllNotices();
    Notice fetchById(Integer id);
    void deleteById(Integer id);

}
