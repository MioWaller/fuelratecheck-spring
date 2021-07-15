package com.uh.fuelratecheck;

public class FuelQuoteModel {
    private String gallonsRequested;
    private String DeliveryAddress;
    private double SuggestedPrice;
    private double TotalDue;

    public String getGallons() {
        return gallonsRequested;
    }

    public void setGallons(String gallonsRequested) {
        this.gallonsRequested = gallonsRequested;
    }
}
