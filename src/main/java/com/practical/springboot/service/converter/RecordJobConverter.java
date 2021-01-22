package com.practical.springboot.service.converter;

import com.practical.springboot.data.jooq.tables.records.JobstblRecord;
import com.practical.springboot.service.domain.Job;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;

import static com.practical.springboot.data.jooq.Tables.JOBSTBL;

public class RecordJobConverter implements Converter<JobstblRecord, Job>{

    @Override
    public Job convert(JobstblRecord source) {
        Job job = new Job();
        job.setJobId(source.get(JOBSTBL.JOB_ID));
        job.setJobType(source.get(JOBSTBL.JOB_TYPE));
        job.setState(source.get(JOBSTBL.STATE));
        job.setStatus(source.get(JOBSTBL.STATUS));
        job.setCreated_by(source.get(JOBSTBL.CREATED_BY));
        job.setCreatedDate(toInstantOrNull(source.get(JOBSTBL.CREATED_DATE)));
        job.setUpdated_by(source.get(JOBSTBL.UPDATED_BY));
        job.setUpdatedDate(toInstantOrNull(source.get(JOBSTBL.UPDATED_DATE)));
        return job;

    }

    private static Instant toInstantOrNull(LocalDateTime localDateTime) {
        return (localDateTime == null) ? null : localDateTime.atZone(ZoneId.systemDefault()).toInstant();
    }
}
