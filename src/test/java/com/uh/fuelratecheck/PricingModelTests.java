package com.uh.fuelratecheck;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;


import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

@WebMvcTest(PricingModule.class)
public class PricingModelTests {

    @MockBean
    private FuelQuoteRepository fuelQuoteRepository;
    
    @MockBean
    private ClientInfoRepository clientInfoRepository;

    @Autowired
    private PricingModule module;

    @Test
    public void contextLoads() {
        assertThat(module).isNotNull();
    }

    @Test
    void pricingModelWasCreated() {
        assertThat(this.module).isNotNull();
    }

    
    @Test
    void settersWork() {
        PricingModule prices = new PricingModule();

        prices.setgallonsRequested("1200");
        prices.setuserid("1");
    }

    @Test
    void historycheckShouldReturnZeroWhenHistoryIsEmpty() {
        List<FuelQuoteEntity> client = new ArrayList<>();
        
        when(fuelQuoteRepository.findByUserid(anyInt())).thenReturn(client);
        module.setuserid("5");

        double rateHistoryFactor = module.historycheck();
        assertThat(rateHistoryFactor).isEqualTo(0);
    }

    @Test
    void historycheckShouldReturnPointZeroOneWhenHistoryIsNotEmpty() {
        FuelQuoteEntity quote = new FuelQuoteEntity();
        List<FuelQuoteEntity> client = new ArrayList<>();
        client.add(quote);

        when(fuelQuoteRepository.findByUserid(anyInt())).thenReturn(client);
        module.setuserid("5");

        double rateHistoryFactor = module.historycheck();
        assertThat(rateHistoryFactor).isEqualTo(.01);
    }


    @Test 
    void gallonsRequestedPointZeroTwoLocationFactorPointZeroTwoRateHistoryCheckPointZeroOne() {
        FuelQuoteEntity quote = new FuelQuoteEntity();
        List<FuelQuoteEntity> client = new ArrayList<>();
        client.add(quote);

        when(fuelQuoteRepository.findByUserid(anyInt())).thenReturn(client);

        ClientInfoEntity info = new ClientInfoEntity();
        info.setState("TX");
        List<ClientInfoEntity> clients = new ArrayList<>();
        clients.add(info);

        when(clientInfoRepository.findByUserid(anyInt())).thenReturn(clients);
        module.setuserid("5");
        module.setgallonsRequested("1200");

        double suggestedPrice = module.calculateSuggestedPrice();

        assertThat(suggestedPrice).isEqualTo(1.695);
        
    }

    @Test 
    void gallonsRequestedPointZeroTwoLocationFactorPointZeroTwoRateHistoryCheckZero() {
        List<FuelQuoteEntity> client = new ArrayList<>();
        when(fuelQuoteRepository.findByUserid(anyInt())).thenReturn(client);

        ClientInfoEntity info = new ClientInfoEntity();
        info.setState("TX");
        List<ClientInfoEntity> clients = new ArrayList<>();
        clients.add(info);

        when(clientInfoRepository.findByUserid(anyInt())).thenReturn(clients);
        module.setuserid("5");
        module.setgallonsRequested("1200");

        double suggestedPrice = module.calculateSuggestedPrice();

        assertThat(suggestedPrice).isEqualTo(1.71);
        
    }

    @Test 
    void gallonsRequestedPointZeroThreelOcationFactorPointZeroTwoRateHistoryCheckPointZeroOne() {
        FuelQuoteEntity quote = new FuelQuoteEntity();
        List<FuelQuoteEntity> client = new ArrayList<>();
        client.add(quote);

        when(fuelQuoteRepository.findByUserid(anyInt())).thenReturn(client);

        ClientInfoEntity info = new ClientInfoEntity();
        info.setState("TX");
        List<ClientInfoEntity> clients = new ArrayList<>();
        clients.add(info);

        when(clientInfoRepository.findByUserid(anyInt())).thenReturn(clients);
        module.setuserid("5");
        module.setgallonsRequested("100");

        double suggestedPrice = module.calculateSuggestedPrice();

        assertThat(suggestedPrice).isEqualTo(1.71);
        
    }

    @Test 
    void gallonsRequestedPointZeroThreelOcationFactorPointZeroTwoRateHistoryCheckZero() {
        List<FuelQuoteEntity> client = new ArrayList<>();
        when(fuelQuoteRepository.findByUserid(anyInt())).thenReturn(client);

        ClientInfoEntity info = new ClientInfoEntity();
        info.setState("TX");
        List<ClientInfoEntity> clients = new ArrayList<>();
        clients.add(info);

        when(clientInfoRepository.findByUserid(anyInt())).thenReturn(clients);
        module.setuserid("5");
        module.setgallonsRequested("100");

        double suggestedPrice = module.calculateSuggestedPrice();

        assertThat(suggestedPrice).isEqualTo(1.725);
        
    }


    @Test 
    void gallonsRequestedPointZeroTwoLocationFactorPointZeroFourRateHistoryCheckPointZeroOne() {
        FuelQuoteEntity quote = new FuelQuoteEntity();
        List<FuelQuoteEntity> client = new ArrayList<>();
        client.add(quote);

        when(fuelQuoteRepository.findByUserid(anyInt())).thenReturn(client);

        ClientInfoEntity info = new ClientInfoEntity();
        info.setState("NY");
        List<ClientInfoEntity> clients = new ArrayList<>();
        clients.add(info);

        when(clientInfoRepository.findByUserid(anyInt())).thenReturn(clients);
        module.setuserid("5");
        module.setgallonsRequested("1200");

        double suggestedPrice = module.calculateSuggestedPrice();

        assertThat(suggestedPrice).isEqualTo(1.725);

    }

    @Test 
    void gallonsRequestedPointZeroTwoLocationFactorPointZeroFourRateHistoryCheckZero() {
        List<FuelQuoteEntity> client = new ArrayList<>();

        when(fuelQuoteRepository.findByUserid(anyInt())).thenReturn(client);

        ClientInfoEntity info = new ClientInfoEntity();
        info.setState("NY");
        List<ClientInfoEntity> clients = new ArrayList<>();
        clients.add(info);

        when(clientInfoRepository.findByUserid(anyInt())).thenReturn(clients);
        module.setuserid("5");
        module.setgallonsRequested("1200");

        double suggestedPrice = module.calculateSuggestedPrice();

        assertThat(suggestedPrice).isEqualTo(1.74);

    }

    @Test 
    void gallonsRequestedPointZeroThreeLocationFactorPointZeroFourRateHistoryCheckPointZeroOne() {
        FuelQuoteEntity quote = new FuelQuoteEntity();
        List<FuelQuoteEntity> client = new ArrayList<>();
        client.add(quote);

        when(fuelQuoteRepository.findByUserid(anyInt())).thenReturn(client);

        ClientInfoEntity info = new ClientInfoEntity();
        info.setState("NY");
        List<ClientInfoEntity> clients = new ArrayList<>();
        clients.add(info);

        when(clientInfoRepository.findByUserid(anyInt())).thenReturn(clients);
        module.setuserid("5");
        module.setgallonsRequested("100");

        double suggestedPrice = module.calculateSuggestedPrice();

        assertThat(suggestedPrice).isEqualTo(1.74);
        
    }

    @Test 
    void gallonsRequestedPointZeroThreeLocationFactorPointZeroFourRateHistoryCheckZero() {
        List<FuelQuoteEntity> client = new ArrayList<>();

        when(fuelQuoteRepository.findByUserid(anyInt())).thenReturn(client);

        ClientInfoEntity info = new ClientInfoEntity();
        info.setState("NY");
        List<ClientInfoEntity> clients = new ArrayList<>();
        clients.add(info);

        when(clientInfoRepository.findByUserid(anyInt())).thenReturn(clients);
        module.setuserid("5");
        module.setgallonsRequested("100");

        double suggestedPrice = module.calculateSuggestedPrice();

        assertThat(suggestedPrice).isEqualTo(1.755);
        
    }

    @Test
    void calculateTotalPriceTests1() {
        gallonsRequestedPointZeroTwoLocationFactorPointZeroTwoRateHistoryCheckPointZeroOne();
        double totalPrice = module.calculateTotalPrice();
        assertThat(totalPrice).isEqualTo(2034.00);
    }

    @Test
    void calculateTotalPriceTests2() {
        gallonsRequestedPointZeroTwoLocationFactorPointZeroTwoRateHistoryCheckZero();
        double totalPrice = module.calculateTotalPrice();
        assertThat(totalPrice).isEqualTo(2052.00);
    }

    @Test
    void calculateTotalPriceTests3() {
        gallonsRequestedPointZeroThreelOcationFactorPointZeroTwoRateHistoryCheckPointZeroOne();
        double totalPrice = module.calculateTotalPrice();
        assertThat(totalPrice).isEqualTo(171.00);
    }

    @Test
    void calculateTotalPriceTests4() {
        gallonsRequestedPointZeroThreelOcationFactorPointZeroTwoRateHistoryCheckZero();
        double totalPrice = module.calculateTotalPrice();
        assertThat(totalPrice).isEqualTo(172.5);
    }
    
    
    @Test
    void calculateTotalPriceTests5() {
        gallonsRequestedPointZeroTwoLocationFactorPointZeroFourRateHistoryCheckPointZeroOne();
        double totalPrice = module.calculateTotalPrice();
        assertThat(totalPrice).isEqualTo(2070.00);
    }

    @Test
    void calculateTotalPriceTests6() {
        gallonsRequestedPointZeroTwoLocationFactorPointZeroFourRateHistoryCheckZero();
        double totalPrice = module.calculateTotalPrice();
        assertThat(totalPrice).isEqualTo(2088.00);
    }

    @Test
    void calculateTotalPriceTests7() {
        gallonsRequestedPointZeroThreeLocationFactorPointZeroFourRateHistoryCheckPointZeroOne();
        double totalPrice = module.calculateTotalPrice();
        assertThat(totalPrice).isEqualTo(174.00);
    }

    @Test
    void calculateTotalPriceTests8() {
        gallonsRequestedPointZeroThreeLocationFactorPointZeroFourRateHistoryCheckZero();
        double totalPrice = module.calculateTotalPrice();
        assertThat(totalPrice).isEqualTo(175.5);
    }


}
