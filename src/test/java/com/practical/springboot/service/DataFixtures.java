package com.practical.springboot.service;

import com.practical.springboot.service.domain.Job;

import java.time.Instant;

public class DataFixtures {

    public static Job getJob(){
        Job job = new Job();
        job.setJobId(1001);
        job.setJobType("typeA");
        job.setState("Unallocated");
        job.setStatus("ACTIVE");
        job.setCreated_by("etayum");
        job.setUpdated_by("etayum");
        job.setCreatedDate(Instant.now());
        job.setUpdatedDate(Instant.now());
        return job;
    }
}
