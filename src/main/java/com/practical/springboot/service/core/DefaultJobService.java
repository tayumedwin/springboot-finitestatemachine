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
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;


@Transactional
public class DefaultJobService implements JobService {

    private static final Logger LOG = LoggerFactory.getLogger(DefaultJobService.class);
    private static final String CREATED_BY = "Edwin Tayum";
    private static final String UPDATED_BY = "Edwin Tayum";

    @Autowired
    private StateMachine<String, String> stateMachine;

    private final JobRepository jobRepository;
    //private final StateMachine stateMachine;

    public DefaultJobService(JobRepository jobRepository/*, StateMachine stateMachine*/){
        this.jobRepository = jobRepository;
        //this.stateMachine = stateMachine;
        LOG.info("DefaultNotificationService start");
    }

    @Override
    public void createJob(String jobType) {
        stateMachine.start();
        LOG.info("initial state 1:" + stateMachine.getInitialState());
        LOG.info("extended state 1:" + stateMachine.getExtendedState());
        LOG.info("transitions 1:" + stateMachine.getTransitions());
        boolean isSuccess = stateMachine.sendEvent(Event.EVENT_A.getEventName());
        LOG.info("initial state 2:" + stateMachine.getInitialState());
        LOG.info("extended state 2:" + stateMachine.getExtendedState());
        LOG.info("transitions 2:" + stateMachine.getTransitions());
        LOG.info("isSuccess:"+isSuccess);
        LOG.info("states:"+stateMachine.getState().getIds().toArray().toString());

        //TODO: create Job entity to be persist
        Job job = new Job();
        job.setJobType(jobType);
        job.setState(stateMachine.getInitialState().getId());
        job.setStatus(Status.NEW.getStatusName());
        job.setCreated_by(CREATED_BY);
        job.setCreatedDate(Instant.now());
        job.setUpdated_by(UPDATED_BY);
        job.setUpdatedDate(Instant.now());

        jobRepository.save(job);
        LOG.info("Create Job");
    }

    @Override
    public void updateJob(int jobId) {
        LOG.info("Update Job");
    }

    @Override
    public void getJob(int jobId) {
        LOG.info("Get Job");
    }

    @Override
    public void deleteJob(int jobId) {

    }
}
