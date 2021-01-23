package com.practical.springboot.service.core;


import com.practical.springboot.service.domain.Job;

public interface JobService {

    Job createJob(String jobType);
    void updateJob(int jobId);
    void updateJob(int jobId, String state);
    Job getJob(int jobId);

}
