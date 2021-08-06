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

        fuelQuoteForm.setGallonsRequested("invalid");
        fuelQuoteForm.setDeliveryDate("invalid");

        assertEquals(fuelQuoteForm.getGallonsRequested(), "invalid");
        assertEquals(fuelQuoteForm.getDeliveryDate(), "invalid");
    }

    @Test
    void gettersAndSetters() {
        FuelQuoteForm fuelQuoteForm = new FuelQuoteForm();

        assertThat(fuelQuoteForm).isNotNull();

        fuelQuoteForm.setGallonsRequested("invalid");
        fuelQuoteForm.setDeliveryDate("2021-2-24");

        assertEquals(fuelQuoteForm.getGallonsRequested(), "invalid");
        assertEquals(fuelQuoteForm.getDeliveryDate(), "2021-2-24");
    }
}
