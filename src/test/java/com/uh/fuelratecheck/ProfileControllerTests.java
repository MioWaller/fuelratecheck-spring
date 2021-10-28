package com.uh.fuelratecheck;

import static org.assertj.core.api.Assertions.assertThat;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(ProfileController.class)
public class ProfileControllerTests {
    
    @Autowired
    private ProfileController controller;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClientInfoRepository clientInfoRepository;

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
    public void profileSubmitShouldFailOnShortZipcode() throws Exception {
        mockMvc.perform(post("/profile")
            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            .param("zipcode", "00"))
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("/profile"));
    }

    @Test
    public void profileSubmitShouldFailOnLongZipcode() throws Exception {
        mockMvc.perform(post("/profile")
            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            .param("zipcode", "00000000000000000000000000000000"))
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("/profile"));
    }

    @Test
    public void profileSubmitShouldFailOnNonNumberZipcode() throws Exception {
        mockMvc.perform(post("/profile")
            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            .param("zipcode", "akjfhbskdja"))
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("/profile"));
    }

    @Test
    public void profileSubmitShouldFailOnEmptyAddress1() throws Exception {
        mockMvc.perform(post("/profile")
            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            .param("zipcode", "000000")
            .param("address1", ""))
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("/profile"));
    }

    @Test
    public void profileSubmitShouldFailOnTooLongAddress1() throws Exception {
        mockMvc.perform(post("/profile")
            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            .param("zipcode", "000000")
            .param("address1", "mmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmm"))
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("/profile"));
    }

    @Test
    public void profileSubmitShouldFailOnEmptyCity() throws Exception {
        mockMvc.perform(post("/profile")
            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            .param("zipcode", "000000")
            .param("address1", "mmsksnfkiub")
            .param("city", ""))
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("/profile"));
    }

    @Test
    public void profileSubmitShouldFailOnTooLongCity() throws Exception {
        mockMvc.perform(post("/profile")
            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            .param("zipcode", "000000")
            .param("address1", "akjfhie")
            .param("city", "mmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmm"))
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("/profile"));
    }

    @Test
    public void profileSubmitShouldFailOnEmptyState() throws Exception {
        mockMvc.perform(post("/profile")
            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            .param("zipcode", "000000")
            .param("address1", "akjfhie")
            .param("city", "mmmm")
            .param("state", ""))
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("/profile"));
    }

    @Test
    public void profileSubmitShouldFailOnTooLongState() throws Exception {
        mockMvc.perform(post("/profile")
            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            .param("zipcode", "000000")
            .param("address1", "akjfhie")
            .param("city", "mmmm")
            .param("state", "ytafvsd"))
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("/profile"));
    }

    @Test
    public void profileSubmitShouldFailOnEmptyName() throws Exception {
        mockMvc.perform(post("/profile")
            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            .param("zipcode", "000000")
            .param("address1", "akjfhie")
            .param("city", "mmmm")
            .param("state", "ty")
            .param("fullName", ""))
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("/profile"));
    }

    @Test
    public void profileSubmitShouldFailOnNonTooLongName() throws Exception {
        mockMvc.perform(post("/profile")
            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            .param("zipcode", "000000")
            .param("address1", "akjfhie")
            .param("city", "mmmm")
            .param("state", "ty")
            .param("fullName", "group11mmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmm"))
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("/profile"));
    }

    @Test
    public void profileSubmitShouldOpenFuelQuoteIfProfileSucceeded() throws Exception {
        Cookie cookie = new Cookie("user-id", "a");
        Cookie cookie1 = new Cookie("user-id", "1");
        ClientInfoEntity info = new ClientInfoEntity();
        info.setId(1);
        info.setUserId(2);
        info.setFullName("Group11");
        info.setAddress1("Address1");
        info.setAddress2("Address2");
        info.setCity("Houston");
        info.setState("TX");
        info.setZipcode("00000");
        List<ClientInfoEntity> client = new ArrayList<>();
        client.add(info);

        when(clientInfoRepository.findByUserid(anyInt())).thenReturn(client);
    
        mockMvc.perform(post("/profile")
            .cookie(cookie)
            .cookie(cookie1)
            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            .param("zipcode", "00000")
            .param("address1", "Address1")
            .param("address2", "Address2")
            .param("city", "Houston")
            .param("fullName", "Group11")
            .param("state", "TX"))
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("/fuelquote"));
    }

    
    @Test
    public void profileSubmitShouldCreateNewInfo() throws Exception {
        Cookie cookie = new Cookie("user-id", "a");
        Cookie cookie1 = new Cookie("user-id", "1");
        List<ClientInfoEntity> clients = new ArrayList<>();
        when(clientInfoRepository.findByUserid(anyInt())).thenReturn(clients);
    
        mockMvc.perform(post("/profile")
            .cookie(cookie)
            .cookie(cookie1)
            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            .param("zipcode", "00000")
            .param("address1", "Address1")
            .param("address2", "Address2")
            .param("city", "Houston")
            .param("fullName", "Group11")
            .param("state", "TX"))
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("/fuelquote"));
    }

    @Test
    public void profileSubmitShouldNotIncludeCookie() throws Exception {
        Cookie cookie = new Cookie("random", "random");
        ClientInfoEntity info = new ClientInfoEntity();
        info.setId(1);
        info.setUserId(2);
        info.setFullName("Group11");
        info.setAddress1("Address1");
        info.setAddress2("Address2");
        info.setCity("Houston");
        info.setState("TX");
        info.setZipcode("00000");


        List<ClientInfoEntity> clients = new ArrayList<>();
        clients.add(info);
        when(clientInfoRepository.findByUserid(anyInt())).thenReturn(clients);
    
        mockMvc.perform(post("/profile")
            .cookie(cookie)
            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            .param("zipcode", "00000")
            .param("address1", "Address1")
            .param("address2", "Address2")
            .param("city", "Houston")
            .param("fullName", "Group11")
            .param("state", "TX"))
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("/login"));
    }
}
