package com.uh.fuelratecheck;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class FuelQuoteFormTests {

    @Test
    void gettersAndSettersWork() {
        FuelQuoteForm fuelQuoteForm = new FuelQuoteForm();
        
        assertThat(fuelQuoteForm).isNotNull();

        fuelQuoteForm.setGallonsRequested("200");
        fuelQuoteForm.setDeliveryDate("invalid");

        assertEquals(fuelQuoteForm.getGallonsRequested(), "200");
        assertEquals(fuelQuoteForm.getDeliveryDate(), "invalid");
    }

    @Test
    void gettersAndSettersCatch() {
        FuelQuoteForm fuelQuoteForm = new FuelQuoteForm();

        assertThat(fuelQuoteForm).isNotNull();

        fuelQuoteForm.setGallonsRequested("100000");
        fuelQuoteForm.setDeliveryDate("wrong");

        assertEquals(fuelQuoteForm.getGallonsRequested(), "100000");
        assertEquals(fuelQuoteForm.getDeliveryDate(), "wrong");
    }
    

}
