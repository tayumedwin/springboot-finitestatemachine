package com.practical.springboot.service.core;


import com.practical.springboot.service.domain.Job;

public interface JobService {

    Job createJob(String jobType);
    String updateJob(int jobId);
    String updateJob(int jobId, String state);
    Job getJob(int jobId);
    boolean deleteJob(int jobId);

}
