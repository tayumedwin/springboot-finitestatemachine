package com.practical.springboot.service.converter;

import com.practical.springboot.data.jooq.tables.records.JobstblRecord;
import com.practical.springboot.service.DataFixtures;
import com.practical.springboot.service.domain.Job;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RecordJobConverterTest {

    private RecordJobConverter converter;
    private Job job;
    private JobstblRecord jobstblRecord;

    @BeforeEach
    void setUp(){
        this.job = DataFixtures.getJobTypeA_Unallocated();
        DSLContext dslContext = DSL.using(SQLDialect.MYSQL);
        this.converter = new RecordJobConverter();
        this.jobstblRecord = new JobRecordConverter(dslContext).convert(job);
    }

    @Test
    void shouldSuccessfullyMapAllFields() throws Exception {
        assertApplicationFields(job, converter.convert(jobstblRecord));
    }

    private void assertApplicationFields(Job expected, Job actual) throws Exception {
        assertEquals(expected.getJobId(), actual.getJobId(), "job id");
        assertEquals(expected.getJobType(), actual.getJobType(), "job type");
        assertEquals(expected.getState(), actual.getState(), "state");
        assertEquals(expected.getStatus(), actual.getStatus(), "status");
        assertEquals(expected.getCreated_by(), actual.getCreated_by(), "created by");
        assertEquals(expected.getUpdated_by(), actual.getUpdated_by(), "updated by");
        assertEquals(expected.getCreatedDate(), actual.getCreatedDate(), "created date");
        assertEquals(expected.getUpdatedDate(), actual.getUpdatedDate(), "updated date");
    }
}
