package com.system.sunday_management.service.impl;

import com.system.sunday_management.model.Leave;
import com.system.sunday_management.pojo.LeavePojo;
import com.system.sunday_management.repository.LeaveRepo;
import com.system.sunday_management.service.LeaveService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LeaveServiceImpl implements LeaveService {
    private final LeaveRepo leaveRepo;

    @Override
    public String saveLeave(LeavePojo leavePojo) throws IOException {
        Leave leave = new Leave();
        leave.setId(leavePojo.getId());
        leave.setFromDate(leavePojo.getFromDate());
        leave.setToDate(leavePojo.getToDate());
        leave.setSubject(leavePojo.getSubject());
        leave.setDescription(leavePojo.getDescription());
        leaveRepo.save(leave);
        return "Saved";
    }

    @Override
    public List<Leave> getAllLeave() {
        return leaveRepo.findAll();
    }

    @Override
    public Leave fetchById(Integer id) {
        return leaveRepo.findById(id).orElseThrow(()->new RuntimeException("Not Found"));
    }

    @Override
    public void deleteById(Integer id) {
        leaveRepo.deleteById(id);
    }
}
