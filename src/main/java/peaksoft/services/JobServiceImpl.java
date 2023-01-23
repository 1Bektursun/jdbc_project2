package peaksoft.services;

import peaksoft.dao.JobDao;
import peaksoft.dao.JobDaoImpl;
import peaksoft.model.Job;

import java.util.List;

public class JobServiceImpl implements JobService{
    JobDao jobDao = new JobDaoImpl();
    @Override
    public void createJobTable() {
        jobDao.createJobTable();
    }

    @Override
    public void addJob(Job job) {
        jobDao.addJob(job);
    }

    @Override
    public Job getJobById(Long jobId) {

        return jobDao.getJobById(jobId);
    }

    @Override
    public List<Job> sortByExperience(String ascOrDesc) {

        return jobDao.sortByExperience(ascOrDesc);
    }

    @Override
    public Job getJobByEmployeeId(Long employeeId) {
        return null;
    }

    @Override
    public void deleteDescriptionColumn() {

    }
}
