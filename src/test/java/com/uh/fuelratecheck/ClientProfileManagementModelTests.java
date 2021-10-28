package com.uh.fuelratecheck;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ClientProfileManagementModelTests {

    @Test
    void gettersAndSettersWork() {
        ClientProfileManagementModel myModel = new ClientProfileManagementModel();

        assertThat(myModel).isNotNull();

        myModel.setFullName("Akif Ozbilge");
        myModel.setAddress1("2441 South Cayuga Rd.");
        myModel.setAddress2("");
        myModel.setCity("Buffalo");
        myModel.setState("NY");
        myModel.setZipcode("14221");

        assertEquals(myModel.getFullName(), "Akif Ozbilge");
        assertEquals(myModel.getAddress1(), "2441 South Cayuga Rd.");
        assertEquals(myModel.getAddress2(), "");
        assertEquals(myModel.getCity(), "Buffalo");
        assertEquals(myModel.getState(), "NY");
        assertEquals(myModel.getZipcode(), "14221");

        assertThat(myModel.toString()).isNotEmpty();
    }
}
