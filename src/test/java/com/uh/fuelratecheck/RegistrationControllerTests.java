package com.uh.fuelratecheck;

import static org.assertj.core.api.Assertions.assertThat;

import static org.hamcrest.Matchers.containsString;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest
public class RegistrationControllerTests {
    
    @Autowired
    private RegistrationController controller;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void contextLoads() {
        assertThat(controller).isNotNull();
    }

    @Test
    public void registrationShouldReturnCorrectTemplate() throws Exception {
        mockMvc.perform(get("/registration"))
            .andExpect(status().isOk())
            .andExpect(content().string(containsString("Register")));
    }

    @Test
    public void registrationSubmitShouldOpenRegistrationIfLoginFailed() throws Exception {
        mockMvc.perform(post("/registration")
            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            .param("username", "")
            .param("password", ""))
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("/registration"));

        mockMvc.perform(post("/registration")
            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            .param("username", "mimi")
            .param("password", ""))
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("/registration"));
    }

    @Test
    public void registrationSubmitShouldOpenProfileIfLoginSucceeded() throws Exception {
        mockMvc.perform(post("/registration")
            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            .param("username", "mimi")
            .param("password", "mypass"))
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("/profile"));
    }
}
