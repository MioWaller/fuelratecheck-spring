package com.uh.fuelratecheck;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.hamcrest.Matchers.containsString;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;

@WebMvcTest(FuelQuoteController.class)
public class FuelQuoteControllerTests {
    
    @Autowired
    private FuelQuoteController controller;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FuelQuoteRepository fuelQuoteRepository;
    
    @MockBean
    private ClientInfoRepository clientInfoRepository;

    @Test
    public void contextLoads() {
        assertThat(controller).isNotNull();
    }

    @Test
    public void fuelQuoteShouldReturnCorrectTemplate() throws Exception {
        mockMvc.perform(get("/fuelquote"))
            .andExpect(status().isOk())
            .andExpect(content().string(containsString("Enter the fuel quote info for")));
    }

    @Test
    public void fuelquoteSubmitShouldOpenfuelquoteIffuelquoteSucceeded() throws Exception {
        Cookie cookie = new Cookie("user-id", "a");
        Cookie cookie1 = new Cookie("user-id", "1");
        ClientInfoEntity info = new ClientInfoEntity();
        info.setId(1);
        info.setUserId(2);
        info.setFullName("Group11");
        info.setAddress1("Address1");
        info.setCity("Houston");
        info.setState("TX");
        info.setZipcode("00000");
        List<ClientInfoEntity> client = new ArrayList<>();
        client.add(info);

        when(clientInfoRepository.findByUserid(anyInt())).thenReturn(client);
    
        mockMvc.perform(post("/fuelquote")
            .cookie(cookie)
            .cookie(cookie1)
            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            .param("gallonsRequested", "invalid")
            .param("deliveryDate", "invalid"))
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("/fuelquote"));
    }
}
