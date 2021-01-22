package com.practical.springboot.service.core;


public interface JobService {

    void createJob(String jobType);
    void updateJob(int jobId);
    void getJob(int jobId);
    void deleteJob(int jobId);

}
