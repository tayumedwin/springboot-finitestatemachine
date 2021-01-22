package com.practical.springboot.service.web;

import com.practical.springboot.service.core.JobService;
import com.practical.springboot.service.domain.Job;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/v1/job")
public class JobController {

    private static final Logger LOG = LoggerFactory.getLogger(JobController.class);

    private final JobService service;

    public JobController(JobService notificationService){
        LOG.info("JobController");
        this.service = notificationService;
    }

    @GetMapping("/create/{jobType}")
    @ResponseStatus(HttpStatus.OK)
    public String create(@PathVariable("jobType") String jobType){
        Job job = service.createJob(jobType);

        return "Job type: " + jobType + " has been created for id:" + job.getJobId();
    }

    @GetMapping("/update/{jobId}")
    @ResponseStatus(HttpStatus.OK)
    public String update(@PathVariable("jobId") int jobId){
        String jobState = service.updateJob(jobId);
        return "Job for: " + jobId + " change state to:" + jobState;
    }

    @GetMapping("/update/{jobId}/{state}")
    @ResponseStatus(HttpStatus.OK)
    public String update(@PathVariable("jobId") int jobId, @PathVariable("state") String state){
        String jobState = service.updateJob(jobId, state);
        return "Job for: " + jobId + " change state to:" + jobState;
    }

}
