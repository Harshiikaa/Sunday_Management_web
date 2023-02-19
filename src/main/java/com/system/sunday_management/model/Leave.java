//package com.system.sunday_management.model;
//
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.NoArgsConstructor;
//
//import java.util.Date;
//
//@AllArgsConstructor
//@NoArgsConstructor
//@Entity
//@Table(name = "leave")
//public class Leave {
//    private Long leaveId;
//    private String subject;
//    private String description;
//    private Date fromDate;
//    private Date toDate;
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//
//    public Long getLeaveId() {
//        return leaveId;
//    }
//
//    public void setLeaveId(Long leaveId) {
//        this.leaveId = leaveId;
//    }
//
//    public String getSubject() {
//        return subject;
//    }
//
//    public void setSubject(String subject) {
//        this.subject = subject;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
//    public Date getFromDate() {
//        return fromDate;
//    }
//
//    public void setFromDate(Date fromDate) {
//        this.fromDate = fromDate;
//    }
//
//    public Date getToDate() {
//        return toDate;
//    }
//
//    public void setToDate(Date toDate) {
//        this.toDate = toDate;
//    }
//}
