//package com.system.sunday_management.pojo;
//
//import com.system.sunday_management.model.Leave;
//import jakarta.validation.constraints.NotEmpty;
//
//import java.util.Date;
//
//public class LeavePojo {
//    private long leaveId;
//    @NotEmpty(message = "this field can't be empty")
//    private Date fromDate;
//    @NotEmpty(message = "this field can't be empty")
//    private Date toDate;
//    @NotEmpty(message = "subject can't be empty")
//    private String subject;
//    @NotEmpty(message = "description can't be empty")
//    private String description;
//
//
//
//    public LeavePojo(Leave leaveModel){
//        this.leaveId= leaveModel.getLeaveId();
//        this.fromDate= leaveModel.getFromDate();
//        this.toDate= leaveModel.getToDate();
//        this.subject= leaveModel.getSubject();
//        this.description= leaveModel.getDescription();
//
//    }
//
//}
