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
import org.jooq.DSLContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class JobConfig {

    private final DSLContext dslContext;
    private final ObjectMapper objectMapper;

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }

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
    public JobService jobService(){
        return new DefaultJobService(jobRepository());
    }

}
