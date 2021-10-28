package com.uh.fuelratecheck;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PricingModelTests {
    
    @Test
    void pricingModelWasCreated() {
        PricingModule model = new PricingModule();

        assertThat(model).isNotNull();
    }

}
