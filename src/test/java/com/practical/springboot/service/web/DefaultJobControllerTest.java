package com.practical.springboot.service.web;

import com.practical.springboot.service.core.DefaultJobService;
import com.practical.springboot.service.core.JobService;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class DefaultJobControllerTest {
    private JobService jobService = mock(DefaultJobService.class);
    private JobController jobController = new JobController(jobService);


    @Test
    public void getCreate_ok() throws Exception {
        String jobType = "TypeA";
        ResponseEntity response = jobController.create(jobType);

        mockMvc().perform(get("/v1/job/create/{jobType}", 0))
                .andExpect(status().isOk());

        assertEquals(HttpStatus.OK, response.getStatusCode(), "Status Code should be 200");
    }

    @Test
    public void getCreate_method_not_allowed_no_interactions() throws Exception {

        mockMvc().perform(post("/v1/job/create/typeA",0))
                .andExpect(status().isMethodNotAllowed());

        verifyNoInteractions(jobService);
    }

    @Test
    public void url_not_found_no_interactions() throws Exception {
        mockMvc()
                .perform(get("/v1/job",0))
                .andExpect(status().isNotFound());

        verifyNoInteractions(jobService);
    }

    private MockMvc mockMvc() {
        return MockMvcBuilders.standaloneSetup(jobController).build();
    }

}
