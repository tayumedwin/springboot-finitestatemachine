package com.practical.springboot.service;

import com.practical.springboot.service.domain.Job;

import java.time.Instant;

public class DataFixtures {

    public static String TYPE_A = "TypeA";
    public static String TYPE_B = "TypeB";
    public static String STATE_UNALLOCATED = "Unallocated";
    public static String STATE_ALLOCATED = "Allocated";
    public static String STATE_STATE_A = "StateA";
    public static String STATE_STATE_B = "StateB";
    public static String STATE_COMPLETED = "Completed";
    public static String STATE_DELETED = "Deleted";
    public static String STATUS_ACTIVE = "ACTIVE";
    public static String STATUS_INACTIVE = "INACTIVE";
    public static String STATUS_NEW = "NEW";
    public static String CREATED_BY = "Edwin Tayum";
    public static String UPDATED_BY = "Edwin Tayum";
    public static int JOB_ID_1001 = 1001;


    public static Job getJobTypeA_Unallocated(){
        Job job = new Job();
        job.setJobId(JOB_ID_1001);
        job.setJobType(TYPE_A);
        job.setState(STATE_UNALLOCATED);
        job.setStatus(STATUS_NEW);
        job.setCreated_by(CREATED_BY);
        job.setUpdated_by(UPDATED_BY);
        job.setCreatedDate(Instant.now());
        job.setUpdatedDate(Instant.now());
        return job;
    }

    public static Job getJobTypeA_Allocated(){
        Job job = new Job();
        job.setJobId(JOB_ID_1001);
        job.setJobType(TYPE_A);
        job.setState(STATE_ALLOCATED);
        job.setStatus(STATUS_ACTIVE);
        job.setCreated_by(CREATED_BY);
        job.setUpdated_by(UPDATED_BY);
        job.setCreatedDate(Instant.now());
        job.setUpdatedDate(Instant.now());
        return job;
    }

    public static Job getJobTypeA_StateA(){
        Job job = new Job();
        job.setJobId(JOB_ID_1001);
        job.setJobType(TYPE_A);
        job.setState(STATE_STATE_A);
        job.setStatus(STATUS_ACTIVE);
        job.setCreated_by(CREATED_BY);
        job.setUpdated_by(UPDATED_BY);
        job.setCreatedDate(Instant.now());
        job.setUpdatedDate(Instant.now());
        return job;
    }

    public static Job getJobTypeA_StateB(){
        Job job = new Job();
        job.setJobId(JOB_ID_1001);
        job.setJobType(TYPE_A);
        job.setState(STATE_STATE_B);
        job.setStatus(STATUS_ACTIVE);
        job.setCreated_by(CREATED_BY);
        job.setUpdated_by(UPDATED_BY);
        job.setCreatedDate(Instant.now());
        job.setUpdatedDate(Instant.now());
        return job;
    }

    public static Job getJobTypeA_Completed(){
        Job job = new Job();
        job.setJobId(JOB_ID_1001);
        job.setJobType(TYPE_A);
        job.setState(STATE_COMPLETED);
        job.setStatus(STATUS_ACTIVE);
        job.setCreated_by(CREATED_BY);
        job.setUpdated_by(UPDATED_BY);
        job.setCreatedDate(Instant.now());
        job.setUpdatedDate(Instant.now());
        return job;
    }

    public static Job getJobTypeA_Deleted(){
        Job job = new Job();
        job.setJobId(JOB_ID_1001);
        job.setJobType(TYPE_A);
        job.setState(STATE_DELETED);
        job.setStatus(STATUS_INACTIVE);
        job.setCreated_by(CREATED_BY);
        job.setUpdated_by(UPDATED_BY);
        job.setCreatedDate(Instant.now());
        job.setUpdatedDate(Instant.now());
        return job;
    }

    public static Job getJobTypeB_Unallocated(){
        Job job = new Job();
        job.setJobId(JOB_ID_1001);
        job.setJobType(TYPE_B);
        job.setState(STATE_UNALLOCATED);
        job.setStatus(STATUS_NEW);
        job.setCreated_by(CREATED_BY);
        job.setUpdated_by(UPDATED_BY);
        job.setCreatedDate(Instant.now());
        job.setUpdatedDate(Instant.now());
        return job;
    }

    public static Job getJobTypeB_Allocated(){
        Job job = new Job();
        job.setJobId(JOB_ID_1001);
        job.setJobType(TYPE_B);
        job.setState(STATE_ALLOCATED);
        job.setStatus(STATUS_ACTIVE);
        job.setCreated_by(CREATED_BY);
        job.setUpdated_by(UPDATED_BY);
        job.setCreatedDate(Instant.now());
        job.setUpdatedDate(Instant.now());
        return job;
    }

    public static Job getJobTypeB_StateA(){
        Job job = new Job();
        job.setJobId(JOB_ID_1001);
        job.setJobType(TYPE_B);
        job.setState(STATE_STATE_A);
        job.setStatus(STATUS_ACTIVE);
        job.setCreated_by(CREATED_BY);
        job.setUpdated_by(UPDATED_BY);
        job.setCreatedDate(Instant.now());
        job.setUpdatedDate(Instant.now());
        return job;
    }

    public static Job getJobTypeB_StateB(){
        Job job = new Job();
        job.setJobId(JOB_ID_1001);
        job.setJobType(TYPE_B);
        job.setState(STATE_STATE_B);
        job.setStatus(STATUS_ACTIVE);
        job.setCreated_by(CREATED_BY);
        job.setUpdated_by(UPDATED_BY);
        job.setCreatedDate(Instant.now());
        job.setUpdatedDate(Instant.now());
        return job;
    }

    public static Job getJobTypeB_Completed(){
        Job job = new Job();
        job.setJobId(JOB_ID_1001);
        job.setJobType(TYPE_B);
        job.setState(STATE_COMPLETED);
        job.setStatus(STATUS_ACTIVE);
        job.setCreated_by(CREATED_BY);
        job.setUpdated_by(UPDATED_BY);
        job.setCreatedDate(Instant.now());
        job.setUpdatedDate(Instant.now());
        return job;
    }

    public static Job getJobTypeB_Deleted(){
        Job job = new Job();
        job.setJobId(JOB_ID_1001);
        job.setJobType(TYPE_B);
        job.setState(STATE_DELETED);
        job.setStatus(STATUS_ACTIVE);
        job.setCreated_by(CREATED_BY);
        job.setUpdated_by(UPDATED_BY);
        job.setCreatedDate(Instant.now());
        job.setUpdatedDate(Instant.now());
        return job;
    }
}
