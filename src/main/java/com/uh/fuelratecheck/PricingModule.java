package com.uh.fuelratecheck;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;

@Component
public class PricingModule {
    @Autowired
	private FuelQuoteRepository fuelQuoteRepository;
    @Autowired
	private ClientInfoRepository clientInfoRepository;

    String state;
    String gallonsrequested;
    double locationFactor;
    double rateHistoryFactor;
    double gallonsRequestedFactor;
    double companyProfitFactor = .1;
    double suggestedPrice;
    double totalPrice;
    String userid;

    public void setgallonsRequested(String gallonsrequested) {
        this.gallonsrequested = gallonsrequested;
    }
    
    public void setuserid(String userid) {
        this.userid = userid;
    }

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
        List<ClientInfoEntity> clientInfoEntity = clientInfoRepository.findByUserid(Integer.parseInt(userid));
        state = clientInfoEntity.get(0).getState();
        if(Integer.parseInt(gallonsrequested) > 1000)
        {
            gallonsRequestedFactor = .02;
        }
        else
        {
            gallonsRequestedFactor = .03;
        }
        if(state.equals("TX"))
        {
            locationFactor = .02;
        }
        else
        {
            locationFactor = .04;
        }
        double margin = (gallonsRequestedFactor - rateHistoryFactor + locationFactor + companyProfitFactor) * 1.50;
        suggestedPrice = 1.50 + margin;

        return suggestedPrice;
    }

    public double calculateTotalPrice(){
        calculateSuggestedPrice();
        totalPrice = Integer.parseInt(gallonsrequested) * suggestedPrice;
        return totalPrice;
    }

}
