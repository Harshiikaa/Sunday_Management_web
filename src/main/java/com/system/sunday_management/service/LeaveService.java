package com.system.sunday_management.service;

import com.system.sunday_management.model.Leave;
import com.system.sunday_management.model.Notice;
import com.system.sunday_management.pojo.LeavePojo;
import com.system.sunday_management.pojo.NoticePojo;

import java.io.IOException;
import java.util.List;

public interface LeaveService {
    String saveLeave(LeavePojo leavePojo) throws IOException;
    List<Leave> getAllLeave();
    Leave fetchById(Integer id);
    void deleteById(Integer id);
}
