package com.example.application.data.service;

import com.example.application.data.entity.BugReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BugReportService {

    public final BugReportRepository bugReportRepository;
    @Autowired
    public BugReportService(BugReportRepository bugReportRepository) {
        this.bugReportRepository = bugReportRepository;
    }
    public boolean insertBug(BugReport bugReport){
        bugReportRepository.save(bugReport);
        if(bugReport.getBugID() != 0){
            return true;
        }else {
            return false;
        }
    }
}
