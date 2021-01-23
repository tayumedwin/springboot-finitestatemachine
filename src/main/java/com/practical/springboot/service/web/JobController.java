package com.practical.springboot.service.web;

import com.practical.springboot.service.core.JobService;
import com.practical.springboot.service.domain.Job;
import com.practical.springboot.service.domain.State;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity create(@PathVariable("jobType") String jobType){
        Job job = service.createJob(jobType);

        LOG.info("Job type: {} has been created with id: {}", jobType, job.getJobId());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/update/{jobId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity update(@PathVariable("jobId") int jobId){
        service.updateJob(jobId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/update/{jobId}/{state}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity update(@PathVariable("jobId") int jobId, @PathVariable("state") String state){

        if(stateContains(state)) {
            service.updateJob(jobId, state);
        }else{
            LOG.warn("Invalid state: {}", state);
        }


        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/delete/{jobId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity delete(@PathVariable("jobId") int jobId){
        service.updateJob(jobId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    private boolean stateContains(String stateStr){
        for(State state : State.values()){
            if(state.getStateName().equalsIgnoreCase(stateStr)){
                return true;
            }
        }

        return false;
    }
}
