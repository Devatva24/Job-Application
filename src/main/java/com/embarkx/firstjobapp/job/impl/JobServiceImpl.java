package com.embarkx.firstjobapp.job.impl;

import com.embarkx.firstjobapp.job.Job;
import com.embarkx.firstjobapp.job.JobService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

@Service
public class JobServiceImpl implements JobService {
    private List<Job> jobs = new ArrayList<>();
    private Long nextId = 1L;
    @Override
    public List<Job> findAll() {
        return jobs;
    }

    @Override
    public void createJob(Job job) {
        job.setId(nextId++);
        jobs.add(job);
    }
    public Job getJobById(Long id) {
        for(Job job : jobs) {
            if(Objects.equals(job.getId(), id)) {
                return job;
            }
        }
        return null;
    }
    @Override
    public boolean deleteJobById(Long id) {
//        for(Job job : jobs) {
//            if(Objects.equals(job.getId(), id)) {
//                jobs.remove(job);
//                return true;
//            }
//        }
        Iterator<Job> iterator = jobs.iterator();
        while(iterator.hasNext()) {
            Job job = iterator.next();
            if(job.getId().equals(id)) {
                iterator.remove();
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean updateJob(Long id, Job updatedJob) {
        for(Job job : jobs) {
            if(Objects.equals(job.getId(), id)) {
                job.setDescription(updatedJob.getDescription());
                job.setLocation(updatedJob.getLocation());
                job.setMaxSalary(updatedJob.getMaxSalary());
                job.setMinSalary(updatedJob.getMinSalary());
                job.setTitle(updatedJob.getTitle());
                return true;
            }
        }
        return false;
    }

}
