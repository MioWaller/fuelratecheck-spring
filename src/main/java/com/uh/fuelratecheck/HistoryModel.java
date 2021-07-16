package com.uh.fuelratecheck;

public class HistoryModel {
    private long id;
    private String gallons;
    private String deliveryDate;
    
    public String getgallons() {
        System.out.print(gallons);
        return gallons;
    }

    public void setgallons(String gallons) {
        this.gallons = gallons;
    }
}
