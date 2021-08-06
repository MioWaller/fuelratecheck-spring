package com.uh.fuelratecheck;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

public class FuelQuoteForm {
    public int gallonsRequested;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public LocalDate deliveryDate;

    public int getGallonsRequested() {
        return gallonsRequested;
    }

    public void setGallonsRequested(int gallonsRequested) {
        this.gallonsRequested = gallonsRequested;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(LocalDate deliveryDate) {
        this.deliveryDate = deliveryDate;
    }
}
