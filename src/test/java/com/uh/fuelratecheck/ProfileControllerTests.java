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
public class ProfileControllerTests {
    
    @Autowired
    private ProfileController controller;

    @Autowired
    private MockMvc mockMvc;

    

    @Test
    public void contextLoads() {
        assertThat(controller).isNotNull();
    }

    @Test
    public void profileShouldReturnCorrectTemplate() throws Exception {
        mockMvc.perform(get("/profile"))
            .andExpect(status().isOk())
            .andExpect(content().string(containsString("Full Name")));
    }

    @Test
    public void profileSubmitShouldOpenProfileIfProfileFailed() throws Exception {
        mockMvc.perform(post("/profile")
            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            .param("Zipcode", " ")
            .param("address1", "address1")
            .param("address2", "address2")
            .param("city", "city")
            .param("fullName", "name")
            .param("state", "state"))
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("/profile"));

        mockMvc.perform(post("/profile")
            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            .param("zipcode", "0000")
            .param("address1", "")
            .param("address2", "address2")
            .param("city", "city")
            .param("fullName", "name")
            .param("state", "state"))
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("/profile"));

        mockMvc.perform(post("/profile")
            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            .param("zipcode", "0000")
            .param("address1", "address1")
            .param("address2", "address2")
            .param("city", "")
            .param("fullName", "name")
            .param("state", "state"))
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("/profile"));

        mockMvc.perform(post("/profile")
            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            .param("zipcode", "0000")
            .param("address1", "address1")
            .param("address2", "address2")
            .param("city", "city")
            .param("fullName", "")
            .param("state", "state"))
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("/profile"));

        mockMvc.perform(post("/profile")
            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            .param("zipcode", "0000")
            .param("address1", "address1")
            .param("address2", "address2")
            .param("city", "city")
            .param("fullName", "name")
            .param("state", ""))
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("/profile"));
    }

    @Test
    public void profileSubmitShouldOpenFuelQuoteIfProfileSucceeded() throws Exception {
        mockMvc.perform(post("/profile")
            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            .param("zipcode", "0000")
            .param("address1", "address1")
            .param("address2", "address2")
            .param("city", "city")
            .param("fullName", "name")
            .param("state", "state"))
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("/fuelquote"));
    }
}
