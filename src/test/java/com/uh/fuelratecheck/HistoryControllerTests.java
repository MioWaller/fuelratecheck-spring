package com.uh.fuelratecheck;

import static org.assertj.core.api.Assertions.assertThat;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.servlet.http.Cookie;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(HistoryController.class)
public class HistoryControllerTests {
    
    @Autowired
    private HistoryController controller;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FuelQuoteRepository fuelQuoteRepository;

    @Test
    public void contextLoads() {
        assertThat(controller).isNotNull();
    }

    @Test
    public void fuelhistoryShouldReturnCorrectTemplate() throws Exception {
        Cookie cookie = new Cookie("user-id", "a");
        Cookie cookie1 = new Cookie("user-id", "1");
        
        mockMvc.perform(get("/fuelhistory")
            .cookie(cookie)
            .cookie(cookie1))
            .andExpect(status().isOk())
            .andExpect(content().string(containsString("Return to fuel quote page")));
    }
}