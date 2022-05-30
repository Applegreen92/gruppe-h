package com.example.application.data.service;

import com.example.application.data.entity.BugReport;
import com.example.application.security.email.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class BugReportService {
@Autowired
private final EmailSenderService emailSenderService;
    public final BugReportRepository bugReportRepository;
    @Autowired
    public BugReportService(EmailSenderService emailSenderService, BugReportRepository bugReportRepository) {
        this.emailSenderService = emailSenderService;
        this.bugReportRepository = bugReportRepository;
    }
    public boolean insertBug(BugReport bugReport){
        if(bugReport.getTopic().length() < 255 && bugReport.getDescription().length() < 1000){
            bugReportRepository.save(bugReport);
        }
        if(bugReport.getBugID() != 0){
            //sendMailsToAdmin();
            return true;
        }else {
            return false;
        }
    }
//    public void sendMailsToAdmin(){
//
//    }
}
