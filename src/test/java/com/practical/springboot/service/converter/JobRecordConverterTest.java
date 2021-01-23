package com.practical.springboot.service.converter;

import com.practical.springboot.data.jooq.tables.records.JobstblRecord;
import com.practical.springboot.service.DataFixtures;
import com.practical.springboot.service.domain.Job;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.SQLDialect;
import org.jooq.TableField;
import org.jooq.impl.DSL;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;

import static com.practical.springboot.data.jooq.tables.Jobstbl.JOBSTBL;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class JobRecordConverterTest {

    private JobRecordConverter converter;
    private Job job;

    @BeforeEach
    void setUp(){
        this.job = DataFixtures.getJob();
        DSLContext dslContext = DSL.using(SQLDialect.MYSQL);
        this.converter = new JobRecordConverter(dslContext);
    }

    @Test
    void assertShouldSuccessfullyUnmappedFields(){
        assertRecordFields(job, converter.convert(job));
    }

    private void assertRecordFields(Job expected, Record actual){
        assertTableField(expected.getJobId(), actual, JOBSTBL.JOB_ID);
        assertTableField(expected.getJobType(), actual, JOBSTBL.JOB_TYPE);
        assertTableField(expected.getState(), actual, JOBSTBL.STATE);
        assertTableField(expected.getCreated_by(), actual, JOBSTBL.CREATED_BY);
        assertTableField(expected.getUpdated_by(), actual, JOBSTBL.UPDATED_BY);
        assertTime(expected.getCreatedDate(), actual, JOBSTBL.CREATED_DATE);
        assertTime(expected.getUpdatedDate(), actual, JOBSTBL.UPDATED_DATE);
    }

    private void assertTableField(Object expected, Record actual, TableField<JobstblRecord, ?> tableField){
        assertEquals(expected, actual.get(tableField), tableField.getName());
    }

    private void assertTime(Object expected, Record actual, TableField<JobstblRecord, LocalDateTime> tableField) {
        assertEquals(expected, actual.get(tableField).atZone(ZoneId.systemDefault()).toInstant(), tableField.getName());
    }


}
