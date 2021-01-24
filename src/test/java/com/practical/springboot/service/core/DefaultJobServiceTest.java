package com.practical.springboot.service.core;

import com.practical.springboot.service.DataFixtures;
import com.practical.springboot.service.data.JobRepository;
import com.practical.springboot.service.domain.Job;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

public class DefaultJobServiceTest {

    private static final Logger LOG = LoggerFactory.getLogger(DefaultJobServiceTest.class);

    private DefaultJobService service;
    private Instant currentInstant;
    private JobRepository jobRepository;

    @BeforeEach
    void setUp(){
        jobRepository = mock(JobRepository.class);
        service = new DefaultJobService(jobRepository);
    }

    @Test
    void createJobTyepA(){
        service.createJob(DataFixtures.TYPE_A);
        ArgumentCaptor<Job> captor = ArgumentCaptor.forClass(Job.class);
        verify(jobRepository).save(captor.capture());
        assertNotNull(captor.getValue());
        assertEquals(DataFixtures.TYPE_A, captor.getValue().getJobType(), "job type");
        assertEquals(DataFixtures.STATUS_NEW, captor.getValue().getStatus());
        assertEquals(DataFixtures.STATE_UNALLOCATED, captor.getValue().getState());
        assertEquals(DataFixtures.CREATED_BY, captor.getValue().getCreated_by());
        assertEquals(DataFixtures.UPDATED_BY, captor.getValue().getUpdated_by());
    }

    @Test
    void transition_TypeA_Unallocated_Allocated(){
        stubClock();

        when(jobRepository.findByJobId(DataFixtures.JOB_ID_1001)).thenReturn(DataFixtures.getJobTypeA_Unallocated());
        service.updateJob(DataFixtures.JOB_ID_1001);
        ArgumentCaptor<Integer> captor = ArgumentCaptor.forClass(Integer.class);
        ArgumentCaptor<String> captor1 = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> captor2 = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> captor3 = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<Instant> captor4 = ArgumentCaptor.forClass(Instant.class);
        verify(jobRepository).updateJob(
                captor.capture(),
                captor1.capture(),
                captor2.capture(),
                captor3.capture(),
                captor4.capture()
        );

        assertEquals(DataFixtures.JOB_ID_1001, captor.getValue(), "job id");
        assertEquals(DataFixtures.STATE_ALLOCATED, captor1.getValue(), "state");
        assertEquals(DataFixtures.STATUS_ACTIVE, captor2.getValue(), "status");
        assertEquals(DataFixtures.UPDATED_BY, captor3.getValue(), "updated by");
        assertEquals(currentInstant, captor4.getValue(), "updated date");

        LOG.debug("{}, State changed from: {} to {}", DataFixtures.getJobTypeA_Unallocated().getJobType(), DataFixtures.getJobTypeA_Unallocated().getState(), captor1.getValue());
    }

    @Test
    void transition_TypeA_Allocated_StateA(){
        stubClock();

        when(jobRepository.findByJobId(DataFixtures.JOB_ID_1001)).thenReturn(DataFixtures.getJobTypeA_Allocated());
        service.updateJob(DataFixtures.JOB_ID_1001);
        ArgumentCaptor<Integer> captor = ArgumentCaptor.forClass(Integer.class);
        ArgumentCaptor<String> captor1 = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> captor2 = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> captor3 = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<Instant> captor4 = ArgumentCaptor.forClass(Instant.class);
        verify(jobRepository).updateJob(
                captor.capture(),
                captor1.capture(),
                captor2.capture(),
                captor3.capture(),
                captor4.capture()
        );

        assertEquals(DataFixtures.JOB_ID_1001, captor.getValue(), "job id");
        assertEquals(DataFixtures.STATE_STATE_A, captor1.getValue(), "state");
        assertEquals(DataFixtures.STATUS_ACTIVE, captor2.getValue(), "status");
        assertEquals(DataFixtures.UPDATED_BY, captor3.getValue(), "updated by");
        assertEquals(currentInstant, captor4.getValue(), "updated date");

        LOG.debug("{}, State changed from: {} to {}", DataFixtures.getJobTypeA_Allocated().getJobType(), DataFixtures.getJobTypeA_Allocated().getState(), captor1.getValue());
    }

    @Test
    void transition_TypeA_StateA_StateB(){
        stubClock();

        when(jobRepository.findByJobId(DataFixtures.JOB_ID_1001)).thenReturn(DataFixtures.getJobTypeA_StateA());
        service.updateJob(DataFixtures.JOB_ID_1001);
        ArgumentCaptor<Integer> captor = ArgumentCaptor.forClass(Integer.class);
        ArgumentCaptor<String> captor1 = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> captor2 = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> captor3 = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<Instant> captor4 = ArgumentCaptor.forClass(Instant.class);
        verify(jobRepository).updateJob(
                captor.capture(),
                captor1.capture(),
                captor2.capture(),
                captor3.capture(),
                captor4.capture()
        );

        assertEquals(DataFixtures.JOB_ID_1001, captor.getValue(), "job id");
        assertEquals(DataFixtures.STATE_STATE_B, captor1.getValue(), "state");
        assertEquals(DataFixtures.STATUS_ACTIVE, captor2.getValue(), "status");
        assertEquals(DataFixtures.UPDATED_BY, captor3.getValue(), "updated by");
        assertEquals(currentInstant, captor4.getValue(), "updated date");

        LOG.debug("{}, State changed from: {} to {}", DataFixtures.getJobTypeA_StateA().getJobType(), DataFixtures.getJobTypeA_StateA().getState(), captor1.getValue());
    }

    @Test
    void transition_TypeA_StateB_Completed(){
        stubClock();

        when(jobRepository.findByJobId(DataFixtures.JOB_ID_1001)).thenReturn(DataFixtures.getJobTypeA_StateB());
        service.updateJob(DataFixtures.JOB_ID_1001);
        ArgumentCaptor<Integer> captor = ArgumentCaptor.forClass(Integer.class);
        ArgumentCaptor<String> captor1 = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> captor2 = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> captor3 = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<Instant> captor4 = ArgumentCaptor.forClass(Instant.class);
        verify(jobRepository).updateJob(
                captor.capture(),
                captor1.capture(),
                captor2.capture(),
                captor3.capture(),
                captor4.capture()
        );

        assertEquals(DataFixtures.JOB_ID_1001, captor.getValue(), "job id");
        assertEquals(DataFixtures.STATE_COMPLETED, captor1.getValue(), "state");
        assertEquals(DataFixtures.STATUS_ACTIVE, captor2.getValue(), "status");
        assertEquals(DataFixtures.UPDATED_BY, captor3.getValue(), "updated by");
        assertEquals(currentInstant, captor4.getValue(), "updated date");

        LOG.debug("{}, State changed from: {} to {}", DataFixtures.getJobTypeA_StateB().getJobType(), DataFixtures.getJobTypeA_StateB().getState(), captor1.getValue());
    }

    @Test
    void transition_TypeA_Completed_Deleted_State_Cannot_Change(){
        stubClock();

        when(jobRepository.findByJobId(DataFixtures.JOB_ID_1001)).thenReturn(DataFixtures.getJobTypeA_Completed());
        service.updateJob(DataFixtures.JOB_ID_1001);
    }

    @Test
    void transition_TypeA_Completed_Deleted_No_Job_Found(){
        stubClock();

        when(jobRepository.findByJobId(DataFixtures.JOB_ID_1001)).thenReturn(null);
        service.updateJob(DataFixtures.JOB_ID_1001);
    }

    @Test
    void transition_TypeB_Unallocated_Allocated(){
        stubClock();

        when(jobRepository.findByJobId(DataFixtures.JOB_ID_1001)).thenReturn(DataFixtures.getJobTypeB_Unallocated());
        service.updateJob(DataFixtures.JOB_ID_1001);
        ArgumentCaptor<Integer> captor = ArgumentCaptor.forClass(Integer.class);
        ArgumentCaptor<String> captor1 = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> captor2 = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> captor3 = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<Instant> captor4 = ArgumentCaptor.forClass(Instant.class);
        verify(jobRepository).updateJob(
                captor.capture(),
                captor1.capture(),
                captor2.capture(),
                captor3.capture(),
                captor4.capture()
        );

        assertEquals(DataFixtures.JOB_ID_1001, captor.getValue(), "job id");
        assertEquals(DataFixtures.STATE_ALLOCATED, captor1.getValue(), "state");
        assertEquals(DataFixtures.STATUS_ACTIVE, captor2.getValue(), "status");
        assertEquals(DataFixtures.UPDATED_BY, captor3.getValue(), "updated by");
        assertEquals(currentInstant, captor4.getValue(), "updated date");

        LOG.debug("{}, State changed from: {} to {}",DataFixtures.getJobTypeB_Unallocated().getJobType(), DataFixtures.getJobTypeB_Unallocated().getState(), captor1.getValue());
    }

    @Test
    void transition_TypeB_Allocated_StateA(){
        stubClock();

        when(jobRepository.findByJobId(DataFixtures.JOB_ID_1001)).thenReturn(DataFixtures.getJobTypeB_Allocated());
        service.updateJob(DataFixtures.JOB_ID_1001);
        ArgumentCaptor<Integer> captor = ArgumentCaptor.forClass(Integer.class);
        ArgumentCaptor<String> captor1 = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> captor2 = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> captor3 = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<Instant> captor4 = ArgumentCaptor.forClass(Instant.class);
        verify(jobRepository).updateJob(
                captor.capture(),
                captor1.capture(),
                captor2.capture(),
                captor3.capture(),
                captor4.capture()
        );

        assertEquals(DataFixtures.JOB_ID_1001, captor.getValue(), "job id");
        assertEquals(DataFixtures.STATE_STATE_A, captor1.getValue(), "state");
        assertEquals(DataFixtures.STATUS_ACTIVE, captor2.getValue(), "status");
        assertEquals(DataFixtures.UPDATED_BY, captor3.getValue(), "updated by");
        assertEquals(currentInstant, captor4.getValue(), "updated date");

        LOG.debug("{}, State changed from: {} to {}",DataFixtures.getJobTypeB_Allocated().getJobType(), DataFixtures.getJobTypeB_Allocated().getState(), captor1.getValue());
    }

    @Test
    void transition_TypeB_StateA_StateB(){
        stubClock();

        when(jobRepository.findByJobId(DataFixtures.JOB_ID_1001)).thenReturn(DataFixtures.getJobTypeB_StateA());
        service.updateJob(DataFixtures.JOB_ID_1001);
        ArgumentCaptor<Integer> captor = ArgumentCaptor.forClass(Integer.class);
        ArgumentCaptor<String> captor1 = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> captor2 = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> captor3 = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<Instant> captor4 = ArgumentCaptor.forClass(Instant.class);
        verify(jobRepository).updateJob(
                captor.capture(),
                captor1.capture(),
                captor2.capture(),
                captor3.capture(),
                captor4.capture()
        );

        assertEquals(DataFixtures.JOB_ID_1001, captor.getValue(), "job id");
        assertEquals(DataFixtures.STATE_STATE_B, captor1.getValue(), "state");
        assertEquals(DataFixtures.STATUS_ACTIVE, captor2.getValue(), "status");
        assertEquals(DataFixtures.UPDATED_BY, captor3.getValue(), "updated by");
        assertEquals(currentInstant, captor4.getValue(), "updated date");

        LOG.debug("{}, State changed from: {} to {}",DataFixtures.getJobTypeB_StateA().getJobType(), DataFixtures.getJobTypeB_StateA().getState(), captor1.getValue());
    }

    @Test
    void transition_TypeB_StateB_Completed(){
        stubClock();

        when(jobRepository.findByJobId(DataFixtures.JOB_ID_1001)).thenReturn(DataFixtures.getJobTypeB_StateB());
        service.updateJob(DataFixtures.JOB_ID_1001);
        ArgumentCaptor<Integer> captor = ArgumentCaptor.forClass(Integer.class);
        ArgumentCaptor<String> captor1 = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> captor2 = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> captor3 = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<Instant> captor4 = ArgumentCaptor.forClass(Instant.class);
        verify(jobRepository).updateJob(
                captor.capture(),
                captor1.capture(),
                captor2.capture(),
                captor3.capture(),
                captor4.capture()
        );

        assertEquals(DataFixtures.JOB_ID_1001, captor.getValue(), "job id");
        assertEquals(DataFixtures.STATE_COMPLETED, captor1.getValue(), "state");
        assertEquals(DataFixtures.STATUS_ACTIVE, captor2.getValue(), "status");
        assertEquals(DataFixtures.UPDATED_BY, captor3.getValue(), "updated by");
        assertEquals(currentInstant, captor4.getValue(), "updated date");

        LOG.debug("{}, State changed from: {} to {}",DataFixtures.getJobTypeB_StateB().getJobType(), DataFixtures.getJobTypeB_StateB().getState(), captor1.getValue());
    }

    @Test
    void transition_TypeB_Completed_Deleted_State_Cannot_Change(){
        stubClock();

        when(jobRepository.findByJobId(DataFixtures.JOB_ID_1001)).thenReturn(DataFixtures.getJobTypeB_Completed());
        service.updateJob(DataFixtures.JOB_ID_1001);
    }

    @Test
    void transition_TypeA_Completed_Deleted(){
        stubClock();

        when(jobRepository.findByJobId(DataFixtures.JOB_ID_1001)).thenReturn(DataFixtures.getJobTypeA_Completed());
        service.updateJob(DataFixtures.JOB_ID_1001, DataFixtures.STATE_DELETED);

        ArgumentCaptor<Integer> captor = ArgumentCaptor.forClass(Integer.class);
        ArgumentCaptor<String> captor1 = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> captor2 = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> captor3 = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<Instant> captor4 = ArgumentCaptor.forClass(Instant.class);
        verify(jobRepository).updateJob(
                captor.capture(),
                captor1.capture(),
                captor2.capture(),
                captor3.capture(),
                captor4.capture()
        );

        assertEquals(DataFixtures.JOB_ID_1001, captor.getValue(), "job id");
        assertEquals(DataFixtures.STATE_DELETED, captor1.getValue(), "state");
        assertEquals(DataFixtures.STATUS_INACTIVE, captor2.getValue(), "status");
        assertEquals(DataFixtures.UPDATED_BY, captor3.getValue(), "updated by");
        assertEquals(currentInstant, captor4.getValue(), "updated date");

        LOG.debug("{}, State changed from: {} to {}",DataFixtures.getJobTypeA_Completed().getJobType(), DataFixtures.getJobTypeA_Completed().getState(), captor1.getValue());
    }

    @Test
    void transition_TypeB_Completed_Deleted(){
        stubClock();

        when(jobRepository.findByJobId(DataFixtures.JOB_ID_1001)).thenReturn(DataFixtures.getJobTypeB_Completed());
        service.updateJob(DataFixtures.JOB_ID_1001, DataFixtures.STATE_DELETED);

        ArgumentCaptor<Integer> captor = ArgumentCaptor.forClass(Integer.class);
        ArgumentCaptor<String> captor1 = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> captor2 = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> captor3 = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<Instant> captor4 = ArgumentCaptor.forClass(Instant.class);
        verify(jobRepository).updateJob(
                captor.capture(),
                captor1.capture(),
                captor2.capture(),
                captor3.capture(),
                captor4.capture()
        );

        assertEquals(DataFixtures.JOB_ID_1001, captor.getValue(), "job id");
        assertEquals(DataFixtures.STATE_DELETED, captor1.getValue(), "state");
        assertEquals(DataFixtures.STATUS_INACTIVE, captor2.getValue(), "status");
        assertEquals(DataFixtures.UPDATED_BY, captor3.getValue(), "updated by");
        assertEquals(currentInstant, captor4.getValue(), "updated date");

        LOG.debug("{}, State changed from: {} to {}",DataFixtures.getJobTypeB_Completed().getJobType(), DataFixtures.getJobTypeB_Completed().getState(), captor1.getValue());
    }

    @Test
    void transition_TypeA_StateB_StateA_Not_Allowed(){
        stubClock();

        when(jobRepository.findByJobId(DataFixtures.JOB_ID_1001)).thenReturn(DataFixtures.getJobTypeA_StateB());
        service.updateJob(DataFixtures.JOB_ID_1001, DataFixtures.STATE_STATE_A);
    }

    @Test
    void transition_TypeB_StateB_StateA(){
        stubClock();

        when(jobRepository.findByJobId(DataFixtures.JOB_ID_1001)).thenReturn(DataFixtures.getJobTypeB_StateB());
        service.updateJob(DataFixtures.JOB_ID_1001, DataFixtures.STATE_STATE_A);

        ArgumentCaptor<Integer> captor = ArgumentCaptor.forClass(Integer.class);
        ArgumentCaptor<String> captor1 = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> captor2 = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> captor3 = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<Instant> captor4 = ArgumentCaptor.forClass(Instant.class);
        verify(jobRepository).updateJob(
                captor.capture(),
                captor1.capture(),
                captor2.capture(),
                captor3.capture(),
                captor4.capture()
        );

        assertEquals(DataFixtures.JOB_ID_1001, captor.getValue(), "job id");
        assertEquals(DataFixtures.STATE_STATE_A, captor1.getValue(), "state");
        assertEquals(DataFixtures.STATUS_ACTIVE, captor2.getValue(), "status");
        assertEquals(DataFixtures.UPDATED_BY, captor3.getValue(), "updated by");
        assertEquals(currentInstant, captor4.getValue(), "updated date");

        LOG.debug("{}, State changed from: {} to {}",DataFixtures.getJobTypeB_StateB().getJobType(), DataFixtures.getJobTypeB_StateB().getState(), captor1.getValue());
    }

    private void stubClock() {
        currentInstant = Instant.now();
        service.setClock(Clock.fixed(currentInstant, ZoneId.of("UTC")));
    }
}
