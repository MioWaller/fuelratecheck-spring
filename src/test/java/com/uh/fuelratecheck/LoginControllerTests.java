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
public class LoginControllerTests {
    
    @Autowired
    private LoginController controller;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void contextLoads() {
        assertThat(controller).isNotNull();
    }

    @Test
    public void loginShouldReturnCorrectTemplate() throws Exception {
        mockMvc.perform(get("/login"))
            .andExpect(status().isOk())
            .andExpect(content().string(containsString("Register Now")));
    }

    @Test
    public void loginSubmitShouldOpenLoginIfLoginFailed() throws Exception {
        mockMvc.perform(post("/login")
            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            .param("username", "notuser")
            .param("password", "notpass"))
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("/login"));
    }

    @Test
    public void loginSubmitShouldOpenLoginIfLoginSucceeded() throws Exception {
        mockMvc.perform(post("/login")
            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            .param("username", "mimi")
            .param("password", "mypass"))
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("/fuelhistory"));
    }
}
