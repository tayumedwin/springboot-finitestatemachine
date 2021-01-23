package com.practical.springboot.service.core;

import com.practical.springboot.service.data.JobRepository;
import com.practical.springboot.service.domain.Job;
import com.practical.springboot.service.domain.State;
import com.practical.springboot.service.domain.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;


@Transactional
public class DefaultJobService implements JobService {

    private static final Logger LOG = LoggerFactory.getLogger(DefaultJobService.class);
    private static final String CREATED_BY = "Edwin Tayum";
    private static final String UPDATED_BY = "Edwin Tayum";
    private static final String TYPE_B = "TypeB";


    private final JobRepository jobRepository;

    public DefaultJobService(JobRepository jobRepository){
        this.jobRepository = jobRepository;
        LOG.info("DefaultNotificationService start");
    }

    @Override
    public Job createJob(String jobType) {

        Job job = new Job();
        job.setJobType(jobType);
        job.setState(State.UNALLOCATED.getStateName());
        job.setStatus(Status.NEW.getStatusName());
        job.setCreated_by(CREATED_BY);
        job.setCreatedDate(Instant.now());
        job.setUpdated_by(UPDATED_BY);
        job.setUpdatedDate(Instant.now());

        jobRepository.save(job);

        return job;
    }

    @Override
    public void updateJob(int jobId) {
        Job job = jobRepository.findByJobId(jobId);

        if(job != null){
            if(!job.getState().equalsIgnoreCase(State.DELETED.getStateName()) &&
                    !job.getState().equalsIgnoreCase(State.COMPLETED.getStateName())) {
                if (job.getState().equalsIgnoreCase(State.UNALLOCATED.getStateName())){
                    jobRepository.updateJob(
                            job.getJobId(), State.ALLOCATED.getStateName(),
                            Status.ACTIVE.getStatusName(), UPDATED_BY, Instant.now());
                }else if (job.getState().equalsIgnoreCase(State.ALLOCATED.getStateName())){
                    jobRepository.updateJob(
                            job.getJobId(), State.STATEA.getStateName(),
                            Status.ACTIVE.getStatusName(), UPDATED_BY, Instant.now());
                }else if (job.getState().equalsIgnoreCase(State.STATEA.getStateName())){
                    jobRepository.updateJob(
                            job.getJobId(), State.STATEB.getStateName(),
                            Status.ACTIVE.getStatusName(), UPDATED_BY, Instant.now());
                }else{
                    jobRepository.updateJob(
                            job.getJobId(), State.COMPLETED.getStateName(),
                            Status.ACTIVE.getStatusName(), UPDATED_BY, Instant.now());
                }
                job = jobRepository.findByJobId(jobId);

                LOG.info("Job: {} change state to: {}", jobId, job.getState());
            }else{

                LOG.warn("Cannot change the state, the job was already {}", job.getState());
            }
        }else{
            LOG.warn("No job found for jobId {}", jobId);
        }
    }

    @Override
    public void updateJob(int jobId, String stateStr) {
        Job job = jobRepository.findByJobId(jobId);

        if(job != null){
            if(job.getState().equalsIgnoreCase(State.DELETED.getStateName())) {
                LOG.warn("Job: {} is already in state: {}", job, job.getState());
            }else {
                if (stateStr.equalsIgnoreCase(State.DELETED.getStateName())) {
                    //can be changed to delete sql
                    jobRepository.updateJob(
                        job.getJobId(), State.DELETED.getStateName(),
                        Status.INACTIVE.getStatusName(), UPDATED_BY, Instant.now());

                    job = jobRepository.findByJobId(jobId);

                    LOG.info("Job: {} change state to: {}", jobId, job.getState());
                }else {
                    LOG.warn("Job type: {} is not allowed to change state", job.getJobType());
                }


                /*TypeB, can change from any state : Unallocated : Allocated : StateA : StateB
                to Unallocated : Allocated : StateA : StateB : Completed
                */
                if(job.getJobType().equalsIgnoreCase(TYPE_B)) {
                    if (!job.getState().equalsIgnoreCase(State.DELETED.getStateName())) {
                        jobRepository.updateJob(
                                job.getJobId(), stateStr,
                                Status.ACTIVE.getStatusName(), UPDATED_BY, Instant.now());

                        job = jobRepository.findByJobId(jobId);
                        LOG.info("Job: {} change state to: {}", jobId, job.getState());
                    }
                }
            }
        }else{
            LOG.warn("No job found for jobId {}", jobId);
        }
    }

    @Override
    public Job getJob(int jobId) {
        Job job = new Job();
        LOG.info("Get Job");
        return job;
    }
}
