package com.uh.fuelratecheck;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class ClientEntityTests {
    @Test
    void gettersAndSettersWork() {
        ClientEntity myEntity = new ClientEntity();

        assertThat(myEntity).isNotNull();

        myEntity.setName("Sparky");
        myEntity.setPassword("Houston123");
        myEntity.setId(10);

        assertEquals(myEntity.getName(), "Sparky");
        assertEquals(myEntity.getPassword(), "Houston123");
        assertEquals(myEntity.getId(), 10);
        
    }
}
