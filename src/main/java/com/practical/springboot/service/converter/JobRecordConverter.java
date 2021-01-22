package com.practical.springboot.service.converter;

import com.practical.springboot.data.jooq.tables.Jobstbl;
import com.practical.springboot.data.jooq.tables.records.JobstblRecord;
import com.practical.springboot.service.domain.Job;
import org.jooq.DSLContext;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;

import static com.practical.springboot.data.jooq.Tables.JOBSTBL;

public class JobRecordConverter implements Converter<Job, JobstblRecord>{

    private DSLContext dslContext;

    public JobRecordConverter(DSLContext dslContext){
        this.dslContext = dslContext;
    }

    @Override
    public JobstblRecord convert(Job source) {
        JobstblRecord record = dslContext.newRecord(JOBSTBL);
        record.setJobId(source.getJobId());
        record.setJobType(source.getJobType());
        record.setState(source.getState());
        record.setStatus(source.getStatus());
        record.setCreatedBy(source.getCreated_by());
        record.setCreatedDate(toLocalDateTimeNull(source.getCreatedDate()));
        record.setUpdatedBy(source.getUpdated_by());
        record.setUpdatedDate(toLocalDateTimeNull(source.getUpdatedDate()));
        return record;
    }

    private static LocalDateTime toLocalDateTimeNull(Instant instant) {
        return (instant == null) ? null : LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
    }
}
