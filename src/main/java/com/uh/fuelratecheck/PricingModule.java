package com.uh.fuelratecheck;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

public class PricingModule {
    @Autowired
    private static ClientInfoRepository clientInfoRepository;

    public static double calculateSuggestedPrice(String userid, double gallonsRequested){
        List<ClientInfoEntity> clientInfoEntity = clientInfoRepository.findByUserid(Integer.parseInt(userid));

        //loc true means in state, out otherwise
        double locationFactor = 0.04;
        final double companyProfitFactor = 0.1;
        double gallonsRequestedFactor = 0.03;

        if(clientInfoEntity.get(0).getState() == "TX")
            locationFactor = 0.02;
        if(gallonsRequested > 1000)
            gallonsRequestedFactor = 0.02;

        return 0;
    }

    public static double calculateTotalPrice(){
        
        return 0;
    }

}
