package com.uh.fuelratecheck;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class FuelQuoteModel {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    private String gallonsRequested;
    private String deliveryAddress;
    private String deliveryDate;
    private String suggestedPrice;
    private String totalPrice;

    public Integer getId() {
        return id;
    }
    
      public void setId(Integer id) {
        this.id = id;
    }

    public String getgallonsRequested() {
        return gallonsRequested;
    }

    public void setgallonsRequested(String gallonsRequested) {
        try {
            Integer.parseInt(gallonsRequested);
            }
        catch(Exception e)
            {
            this.gallonsRequested = "invalid";
            }
        this.gallonsRequested = gallonsRequested;
    }

    public String getdeliveryAddress() {
        return deliveryAddress;
    }

    public void setdeliveryAddress(String deliveryAddress){
        this.deliveryAddress = deliveryAddress;
    }

    public String getdeliveryDate() {
        return deliveryDate;
    }

    public void setdeliveryDate(String deliveryDate) {
        try {
            LocalDate.parse(deliveryDate, DateTimeFormatter.ofPattern("uuuu-M-d")
            .withResolverStyle(ResolverStyle.STRICT));
            }
            
        catch(DateTimeParseException e)
            {
                this.deliveryDate="invalid";
            }
        this.deliveryDate = deliveryDate;
    }

    public String getsuggestedPrice() {
        return suggestedPrice;
    }

    public void setsuggestedPrice(String suggestedPrice){
        this.suggestedPrice = suggestedPrice;
    }
    
    public String gettotalPrice() {
        return totalPrice;
    }

    public void settotalPrice(String totalPrice){
        this.totalPrice = totalPrice;
    }
}