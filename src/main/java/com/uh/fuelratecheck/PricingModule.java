package com.uh.fuelratecheck;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;

@Component
public class PricingModule {
    @Autowired
	private FuelQuoteRepository fuelQuoteRepository;

    String state;
    String gallonsrequested;
    double locationFactor;
    double rateHistoryFactor;
    double gallonsRequestedFactor;
    double companyProfitFactor = .1;
    double suggestedPrice;
    double totalPrice;
    String userid;

    public double historycheck()
    {
        List<FuelQuoteEntity> history = fuelQuoteRepository.findByUserid(Integer.parseInt(userid));  
        if (history.isEmpty())
        {
            rateHistoryFactor = 0;
        }
        else
        {
            rateHistoryFactor = .01;
        }
        return rateHistoryFactor;
    }

    
    public double calculateSuggestedPrice(){
        historycheck();
        return suggestedPrice;
    }

    public double calculateTotalPrice(){
        calculateSuggestedPrice();
        return totalPrice;
    }

}
