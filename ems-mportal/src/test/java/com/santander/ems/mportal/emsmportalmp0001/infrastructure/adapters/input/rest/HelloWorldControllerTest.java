package com.santander.ems.mportal.emsmportalmp0001.infrastructure.adapters.input.rest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser
class HelloWorldControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testCallHelloEndpoint() throws Exception {
        mockMvc.perform(get("/ems-mportal-mp0001/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello World!"));
    }
}

