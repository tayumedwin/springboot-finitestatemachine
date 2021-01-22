package com.practical.springboot.service.core;

import com.practical.springboot.service.data.JobRepository;
import com.practical.springboot.service.domain.Event;
import com.practical.springboot.service.domain.Job;
import com.practical.springboot.service.domain.State;
import com.practical.springboot.service.domain.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.StateMachine;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;


@Transactional
public class DefaultJobService implements JobService {

    private static final Logger LOG = LoggerFactory.getLogger(DefaultJobService.class);
    private static final String CREATED_BY = "Edwin Tayum";
    private static final String UPDATED_BY = "Edwin Tayum";

    @Autowired
    private StateMachine<String, String> stateMachine;

    private final JobRepository jobRepository;

    public DefaultJobService(JobRepository jobRepository){
        this.jobRepository = jobRepository;
        LOG.info("DefaultNotificationService start");
    }

    @Override
    public Job createJob(String jobType) {
        stateMachine.start();
        stateMachine.sendEvent(Event.EVENT_B.getEventName());

        Job job = new Job();
        job.setJobType(jobType);
        job.setState(stateMachine.getInitialState().getId());
        job.setStatus(Status.NEW.getStatusName());
        job.setCreated_by(CREATED_BY);
        job.setCreatedDate(Instant.now());
        job.setUpdated_by(UPDATED_BY);
        job.setUpdatedDate(Instant.now());

        stateMachine.sendEvent(Event.EVENT_A.getEventName());
        stateMachine.sendEvent(Event.EVENT_B.getEventName());
        stateMachine.sendEvent(Event.EVENT_A.getEventName());
        stateMachine.sendEvent(Event.EVENT_C.getEventName());

        jobRepository.save(job);

        //stateMachine.stop();

        return job;
    }

    @Override
    public String updateJob(int jobId) {
        LOG.info("Update Job");

        Job job = jobRepository.findByJobId(jobId);

        if(job != null){
            if(job.getState() != State.DELETED.getStateName()) {
                if (job.getState().equalsIgnoreCase(State.UNALLOCATED.getStateName())){
                    stateMachine.start();
                    boolean isSuccess = stateMachine.sendEvent(Event.EVENT_B.getEventName());
                    LOG.info("updateJob isSuccess:"+isSuccess);
                    jobRepository.updateJob(job.getJobId(), State.ALLOCATED.getStateName(), Status.ACTIVE.getStatusName(), UPDATED_BY, Instant.now());
                }
            }

            stateMachine.stop();
            return job.getState();
        }else{
            return "No job found for jobId:"+jobId;
        }
    }

    @Override
    public Job getJob(int jobId) {
        Job job = new Job();
        LOG.info("Get Job");
        return job;
    }

    @Override
    public boolean deleteJob(int jobId) {

        return true;
    }
}
