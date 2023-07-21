package org.example.service;

import java.util.List;

public interface Job {
    void createJobTable();
    void addJob(org.example.model.Job job);
    org.example.model.Job getJobById(Long jobId);
    List<org.example.model.Job> sortByExperience(String ascOrDesc);
    org.example.model.Job getJobByEmployeeId(Long employeeId);
    void deleteDescriptionColumn();
}
