package com.embarkx.firstjobapp.job.impl;

import com.embarkx.firstjobapp.job.Job;
import com.embarkx.firstjobapp.job.JobRepository;
import com.embarkx.firstjobapp.job.JobService;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.*;

@Service
public class JobServiceImpl implements JobService {
    //private List<Job> jobs = new ArrayList<>();
    private JobRepository jobRepository;

    public JobServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    //private Long nextId = 1L;
    @Override
    public List<Job> findAll() {
        return jobRepository.findAll();
    }

    @Override
    public void createJob(Job job) {
//        job.setId(nextId++);
//        jobs.add(job);
        jobRepository.save(job);
    }
    public Job getJobById(Long id) {
//        for(Job job : jobs) {
//            if(Objects.equals(job.getId(), id)) {
//                return job;
//            }
//        }
//        return null;
        return jobRepository.findById(id).orElse(null);
    }
    @Override
    public boolean deleteJobById(Long id) {
//        for(Job job : jobs) {
//            if(Objects.equals(job.getId(), id)) {
//                jobs.remove(job);
//                return true;
//            }
//        }
//        Iterator<Job> iterator = jobs.iterator();
//        while(iterator.hasNext()) {
//            Job job = iterator.next();
//            if(job.getId().equals(id)) {
//                iterator.remove();
//                return true;
//            }
//        }
//        return false;
        try {
            jobRepository.deleteById(id);
            return true;
        } catch(Exception e) {
            return false;
        }
    }

    @Override
    public boolean updateJob(Long id, Job updatedJob) {
        Optional<Job> optionalJob = jobRepository.findById(id);
            if(optionalJob.isPresent()) {
                Job job = optionalJob.get();
                job.setDescription(updatedJob.getDescription());
                job.setLocation(updatedJob.getLocation());
                job.setMaxSalary(updatedJob.getMaxSalary());
                job.setMinSalary(updatedJob.getMinSalary());
                job.setTitle(updatedJob.getTitle());
                jobRepository.save(job);
                return true;
            }
        return false;
    }

}
