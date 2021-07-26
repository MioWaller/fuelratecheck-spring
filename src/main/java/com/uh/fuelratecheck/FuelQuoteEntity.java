package com.uh.fuelratecheck;

import javax.persistence.*;

@Entity
public class FuelQuoteEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    private Integer userid;
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

    public Integer getUserId() {
        return userid;
    }
    
      public void setUserId(Integer userid) {
        this.userid = userid;
    }
}