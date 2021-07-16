package com.uh.fuelratecheck;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PricingModelTests {
    
    @Test
    void pricingModelWasCreated() {
        PricingModel model = new PricingModel();

        assertThat(model).isNotNull();

        model.setPrice(10.0);
        assertThat(model.getPrice()).isEqualTo(10.0);
    }

}
