package com.practical.springboot.service.core;


import com.practical.springboot.service.domain.Job;

public interface JobService {

    Job createJob(String jobType);
    String updateJob(int jobId);
    Job getJob(int jobId);
    boolean deleteJob(int jobId);

}
