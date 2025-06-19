package com.embarkx.firstjobapp.job;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.ArrayList;

@RestController
//@RequestMapping("/jobs")
public class JobController {
  private JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping("/jobs")
    public ResponseEntity<List<Job>> findAll() {
        return ResponseEntity.ok(jobService.findAll());
    }

    @PostMapping("/jobs")
    public ResponseEntity<String> createJob(@RequestBody Job job) {
        jobService.createJob(job);
        return new ResponseEntity<>("Job added successfully", HttpStatus.OK);
    }

    @GetMapping("/jobs/{id}")
    public ResponseEntity<Job> getJobById(@PathVariable Long id) {
        Job job = jobService.getJobById(id);
        if(job != null) {
            return new ResponseEntity<Job>(job, HttpStatus.OK);
        }
        return new ResponseEntity<Job>(HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("/jobs/{id}")
    public ResponseEntity<String> deleteJobById(@PathVariable Long id) {
//        Job job = jobService.deleteJobById(id);
//        if(job != null) {
//            return new ResponseEntity<>(job, HttpStatus.OK);
//        }
//        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        boolean deleted = jobService.deleteJobById(id);
        if(deleted) {
            return new ResponseEntity<>("Job deleted successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
    }
    //@PutMapping("/jobs/{id}")
    @RequestMapping(value = "/jobs/{id}",method = RequestMethod.PUT)
    public ResponseEntity<String> updateJob(@PathVariable Long id, @RequestBody Job updatedJob) {
        boolean update = jobService.updateJob(id, updatedJob);
        if(update) {
            return new ResponseEntity<>("Updated the details", HttpStatus.OK);
        }
        return new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
    }
}
