package org.example.service;

import org.example.dao.JopDaoImpl;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class JobServiceImpl implements Job {
    JopDaoImpl jopDao = new JopDaoImpl();
    @Override
    public void createJobTable() {
        jopDao.createJobTable();

    }

    @Override
    public void addJob(org.example.model.Job job) {
        jopDao.addJob(job);

    }

    @Override
    public org.example.model.Job getJobById(Long jobId) {
        return  jopDao.getJobById(jobId);
    }

    @Override
    public List<org.example.model.Job> sortByExperience(String ascOrDesc) {
       return jopDao.sortByExperience(ascOrDesc);

    }

    @Override
    public org.example.model.Job getJobByEmployeeId(Long employeeId) {
        return jopDao.getJobByEmployeeId(employeeId);
    }

    @Override
    public void deleteDescriptionColumn() {
        jopDao.deleteDescriptionColumn();

    }
}
