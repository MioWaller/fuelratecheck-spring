package com.uh.fuelratecheck;

import java.time.LocalDate;
import java.time.format.*;

public class FuelQuoteForm {
    public String gallonsRequested;

    public String deliveryDate;

    public String getGallonsRequested() {
        return gallonsRequested;
    }

    public void setGallonsRequested(String gallonsRequested) {
        try{
            if(Integer.parseInt(gallonsRequested) > 0 && Integer.parseInt(gallonsRequested) <= 5000)
            {
            this.gallonsRequested = gallonsRequested;
            }
            else{
                this.gallonsRequested = "invalid";
            }
        }
        catch(NumberFormatException e)
        {
            this.gallonsRequested = "invalid";
        }
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        try {
            LocalDate.parse(deliveryDate, DateTimeFormatter.ofPattern("uuuu-M-d")
            .withResolverStyle(ResolverStyle.STRICT));
            this.deliveryDate = deliveryDate;
            }
            
        catch(DateTimeParseException e)
            {
                this.deliveryDate="invalid";
            }
    }
}
