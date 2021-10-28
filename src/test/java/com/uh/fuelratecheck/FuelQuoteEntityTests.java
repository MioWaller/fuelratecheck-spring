package com.uh.fuelratecheck;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class FuelQuoteEntityTests {
    @Test
    void gettersAndSettersWork() {
        FuelQuoteEntity myEntity = new FuelQuoteEntity();

        assertThat(myEntity).isNotNull();

        myEntity.setId(10);
        myEntity.setUserId(20);
        myEntity.setgallonsRequested("30");
        myEntity.setdeliveryAddress("Spring Branch");
        myEntity.setdeliveryDate("10/10/2021");
        myEntity.setsuggestedPrice("40");

        myEntity.settotalPrice("50");
        assertEquals(myEntity.getId(), 10);
        assertEquals(myEntity.getUserId(), 20);
        assertEquals(myEntity.getgallonsRequested(), "30");
        assertEquals(myEntity.getdeliveryAddress(), "Spring Branch");
        assertEquals(myEntity.getdeliveryDate(), "10/10/2021");
        assertEquals(myEntity.getsuggestedPrice(), "40");
        assertEquals(myEntity.gettotalPrice(), "50");

        
    }
}