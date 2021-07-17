package com.uh.fuelratecheck;

import static org.assertj.core.api.Assertions.assertThat;

import static org.hamcrest.Matchers.containsString;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest
public class FuelQuoteControllerTests {
    
    @Autowired
    private FuelQuoteController controller;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void contextLoads() {
        assertThat(controller).isNotNull();
    }

    @Test
    public void fuelQuoteShouldReturnCorrectTemplate() throws Exception {
        mockMvc.perform(get("/fuelquote"))
            .andExpect(status().isOk())
            .andExpect(content().string(containsString("How many gallons of fuel do you request?")));
    }

    @Test
    public void fuelquoteSubmitShouldOpenFuelHistoryIffuelquoteFailed() throws Exception {
        mockMvc.perform(post("/fuelquote")
            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            .param("gallonsRequested", "wrong")
            .param("deliveryDate", "wrong"))
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("/fuelhistory"));

        mockMvc.perform(post("/fuelquote")
            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            .param("gallonsRequested", "invalid")
            .param("deliveryDate", "wrong"))
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("/fuelhistory"));
    }

    @Test
    public void fuelquoteSubmitShouldOpenfuelquoteIffuelquoteSucceeded() throws Exception {
        mockMvc.perform(post("/fuelquote")
            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            .param("gallonsRequested", "invalid")
            .param("deliveryDate", "invalid"))
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("/fuelquote"));
    }
}

