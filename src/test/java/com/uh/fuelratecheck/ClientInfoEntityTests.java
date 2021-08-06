package com.uh.fuelratecheck;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class ClientInfoEntityTests {
    @Test
    void gettersAndSettersWork() {
        ClientInfoEntity myEntity = new ClientInfoEntity();

        assertThat(myEntity).isNotNull();

        myEntity.setFullName("Akif Ozbilge");
        myEntity.setAddress1("2441 South Cayuga Rd.");
        myEntity.setAddress2("");
        myEntity.setCity("Buffalo");
        myEntity.setState("NY");
        myEntity.setZipcode("14221");
        myEntity.setUserId(10);
        myEntity.setId(11);

        assertEquals(myEntity.getFullName(), "Akif Ozbilge");
        assertEquals(myEntity.getAddress1(), "2441 South Cayuga Rd.");
        assertEquals(myEntity.getAddress2(), "");
        assertEquals(myEntity.getCity(), "Buffalo");
        assertEquals(myEntity.getState(), "NY");
        assertEquals(myEntity.getZipcode(), "14221");
        assertEquals(myEntity.getUserId(), 10);
        assertEquals(myEntity.getId(), 11);
        assertEquals(myEntity.printAddress(), "Address: " + "2441 South Cayuga Rd."+ "  " + "" +
        "  City: " + "Buffalo" + 
        "  State: " + "NY" + 
        "  Zipcode: " + "14221");
    }
}
