package com.practical.springboot.service.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.practical.springboot.data.jooq.tables.records.JobstblRecord;
import com.practical.springboot.service.converter.Converter;
import com.practical.springboot.service.converter.JobRecordConverter;
import com.practical.springboot.service.converter.RecordJobConverter;
import com.practical.springboot.service.core.DefaultJobService;
import com.practical.springboot.service.core.JobService;
import com.practical.springboot.service.data.JobRepository;
import com.practical.springboot.service.data.JooqJobRepository;
import com.practical.springboot.service.domain.Job;
import com.practical.springboot.service.fsm.DefaultStateMachine;
import org.jooq.DSLContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.web.client.RestTemplate;

@Configuration
public class JobConfig {

    private final DSLContext dslContext;
    private final ObjectMapper objectMapper;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    public JobConfig(ObjectMapper objectMapper, DSLContext dslContext){
        this.dslContext = dslContext;
        this.objectMapper = objectMapper;
    }

    @Bean
    public Converter<Job, JobstblRecord> jobRecordConverter() {
        return new JobRecordConverter(dslContext);
    }

    @Bean
    public Converter<JobstblRecord, Job> recordJobConverter() {
        return new RecordJobConverter();
    }

    @Bean
    public JobRepository jobRepository(){
        return new JooqJobRepository(dslContext, jobRecordConverter(), recordJobConverter());
    }

    @Bean
    public StateMachine<String, String> getStateMachine(){
        return new DefaultStateMachine();
    }


    @Bean
    public JobService jobService(){
        return new DefaultJobService(jobRepository()/*, getStateMachine()*/);
    }



}
