package com.system.sunday_management;

import com.system.sunday_management.model.Notice;
import com.system.sunday_management.model.User;
import com.system.sunday_management.repository.NoticeRepo;
import com.system.sunday_management.repository.UserRepo;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class NoticeRepositoryTests {
    @Autowired
    private NoticeRepo noticeRepo;
    @Test
    @Order(1)
    @Rollback(value = false)
    public void saveNoticeTest(){
        Notice notice = Notice.builder()
                .title("Osama Bin Laden")
                .description("make a list of his terrors and present")
                .build();
        noticeRepo.save(notice);
        Assertions.assertThat(notice.getId()).isGreaterThan(0);
    }
    @Test
    @Order(2)
    public void getNoticeTest(){
        Notice noticeC = noticeRepo.findById(1).get();
        Assertions.assertThat(noticeC.getId()).isEqualTo(1);
    }
    @Test
    @Order(3)
    public void getListOfNoticesTest(){
        List<Notice> notices = noticeRepo.findAll();
        Assertions.assertThat(notices.size()).isGreaterThan(1);
    }
}
