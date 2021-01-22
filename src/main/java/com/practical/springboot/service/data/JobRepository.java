package com.practical.springboot.service.data;

import com.practical.springboot.service.domain.Job;

import java.time.Instant;
import java.util.List;

public interface JobRepository {

    void save(Job job);
    List<Job> getJob(int jobId);
    void updateJob(int jobId, String state, String status, String updatedBy, Instant updatedDate);
    boolean getJobExits(int jobId);
    Job findByJobId(int jobId);

}
