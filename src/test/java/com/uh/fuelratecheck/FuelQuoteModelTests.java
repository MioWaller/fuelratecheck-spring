package com.uh.fuelratecheck;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class FuelQuoteModelTests {

    @Test
    void gettersAndSettersWork() {
        FuelQuoteModel myModel = new FuelQuoteModel();

        assertThat(myModel).isNotNull();

        myModel.setdeliveryAddress("7744 Spring Branch Apt 111");
        myModel.setdeliveryDate("invalid");
        myModel.setgallonsRequested("10");
        myModel.settotalPrice("13");
        myModel.setsuggestedPrice("15");

        assertEquals(myModel.getdeliveryAddress(), "7744 Spring Branch Apt 111");
        assertEquals(myModel.getdeliveryDate(), "invalid");
        assertEquals(myModel.getgallonsRequested(), "10");
        assertEquals(myModel.gettotalPrice(), "13");
        assertEquals(myModel.getsuggestedPrice(), "15");

    }
    
    @Test
    void gettersAndSettersWorkCach() {
        FuelQuoteModel myModel = new FuelQuoteModel();

        assertThat(myModel).isNotNull();
        
        myModel.setdeliveryAddress("7744 Spring Branch Apt 111");
        myModel.setdeliveryDate("wrongInput");
        myModel.setgallonsRequested("wrongInput");
        myModel.settotalPrice("13");
        myModel.setsuggestedPrice("15");

        assertEquals(myModel.getdeliveryAddress(), "7744 Spring Branch Apt 111");
        assertEquals(myModel.getdeliveryDate(), "wrongInput");
        assertEquals(myModel.getgallonsRequested(), "wrongInput");
        assertEquals(myModel.gettotalPrice(), "13");
        assertEquals(myModel.getsuggestedPrice(), "15");
    }

}