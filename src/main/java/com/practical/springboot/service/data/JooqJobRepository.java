package com.practical.springboot.service.data;

import com.practical.springboot.data.jooq.tables.records.JobstblRecord;
import com.practical.springboot.service.converter.Converter;
import com.practical.springboot.service.domain.Job;
import org.jooq.DSLContext;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

import static com.practical.springboot.data.jooq.tables.Jobstbl.JOBSTBL;

public class JooqJobRepository implements JobRepository {

    private DSLContext dslContext;
    private Converter<Job, JobstblRecord> jobRecordConverter;
    private Converter<JobstblRecord, Job> recordJobConverter;

    public JooqJobRepository(DSLContext dslContext, Converter<Job, JobstblRecord> jobRecordConverter, Converter<JobstblRecord, Job> recordJobConverter){
        this.dslContext = dslContext;
        this.jobRecordConverter = jobRecordConverter;
        this.recordJobConverter = recordJobConverter;
    }

    @Override
    public void save(Job job) {
        dslContext.executeInsert(jobRecordConverter.convert(job));
    }

    @Override
    public List<Job> getJob(int jobId) {
        return dslContext
                .selectFrom(JOBSTBL)
                .where(JOBSTBL.JOB_ID.eq(jobId))
                .fetch(record -> recordJobConverter.convert(record));
    }

    @Override
    public void updateJob(int jobId, String state, String status, String updatedBy, Instant updatedDate) {
        JobstblRecord jobstblRecord = dslContext.fetchOne(JOBSTBL, JOBSTBL.JOB_ID.eq(jobId));
        jobstblRecord.setState(state);
        jobstblRecord.setStatus(status);
        jobstblRecord.setUpdatedBy(updatedBy);
        jobstblRecord.setUpdatedDate(toLocalDateTimeNull(updatedDate));
        jobstblRecord.store();
    }

    @Override
    public boolean getJobExits(int jobId) {
        System.out.println("getJobExits");
        return dslContext
                .fetchExists(dslContext.selectFrom(JOBSTBL)
                        .where(JOBSTBL.JOB_ID.eq(jobId)));
    }

    @Override
    public Job findByJobId(int jobId){
        return dslContext.selectFrom(JOBSTBL)
                .where(JOBSTBL.JOB_ID.eq(jobId))
                .fetchOne(recordJobConverter::convert);
    }

    private static LocalDateTime toLocalDateTimeNull(Instant instant) {
        return (instant == null) ? null : LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
    }
}
