package com.uh.fuelratecheck;

public class FuelQuoteModel {
    private int gallonsRequested;
    private String DeliveryAddress;
    private double SuggestedPrice;
    private double TotalDue;

    public int getGallons() {
        System.out.print(gallonsRequested); //Temporary so I can see what number is being read
        return gallonsRequested;
    }

    public void setGallons(int gallonsRequested) {
        this.gallonsRequested = gallonsRequested;
    }
}
