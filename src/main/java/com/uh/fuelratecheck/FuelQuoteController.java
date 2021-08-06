package com.uh.fuelratecheck;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import javax.servlet.http.*;

@Controller
public class FuelQuoteController {
    @Autowired
    private FuelQuoteRepository fuelQuoteRepository;
    @Autowired
    private ClientInfoRepository clientInfoRepository;
    @Autowired
    PricingModule m = new PricingModule();
    
    FuelQuoteForm form = new FuelQuoteForm();

    @GetMapping("/fuelquote")
    public String fuelquote(Model model, HttpServletRequest request) {
        LocalDate now = LocalDate.now();
        LocalDate later = now.plusYears(2);
        model.addAttribute("now", now);
        model.addAttribute("later", later);

        //get cookies to find out which user is editing their client info
        Cookie cookie1[] = request.getCookies();
        String userid="";
        for(int i=0; i<cookie1.length; i++) {
            userid = cookie1[i].getValue();
            try {
                Integer.parseInt(userid);
            }
            catch(NumberFormatException e) {
                userid=null;
            }
            if(userid != null) {
                break;
            }
        }
        // Get the client info for the userId from the database.
        List<ClientInfoEntity> clientInfoEntity = clientInfoRepository.findByUserid(Integer.parseInt(userid));

        model.addAttribute("entities", clientInfoEntity);

        form.gallonsRequested = "100";
        form.deliveryDate = now.toString();

        model.addAttribute("quoteForm", form);
        model.addAttribute("quotePrice", "");

        return "fuelquote";
    }

    @RequestMapping(value = "/fuelquote", method = RequestMethod.POST, params = "getquote")
    public String getFuelQuote(Model model, @ModelAttribute FuelQuoteForm form, HttpServletRequest request) {
        //get cookies to find out which user is editing their client info
        Cookie cookie1[] = request.getCookies();
        String userid="";
        for(int i=0; i<cookie1.length; i++) {
            userid = cookie1[i].getValue();
            try {
                Integer.parseInt(userid);
            }
            catch(NumberFormatException e) {
                userid=null;
            }
            if(userid != null) {
                break;
            }
        }
        // Get the client info for the userId from the database.
        List<ClientInfoEntity> clientInfoEntity = clientInfoRepository.findByUserid(Integer.parseInt(userid));

        model.addAttribute("entities", clientInfoEntity);

        LocalDate now = LocalDate.now();
        model.addAttribute("now", now);

        model.addAttribute("quoteForm", form);

        m.setgallonsRequested(form.getGallonsRequested());
        m.setuserid(userid);
        if(form.getGallonsRequested().equals("invalid") || form.getDeliveryDate().equals("invalid"))
        {
            model.addAttribute("quotePrice", "There is a problem with your input of gallons requested or the delivery date. \n" +
                                                "You may request up to 5000 gallons.");
        }
        else
        {
            String suggestedPrice=String.valueOf(m.calculateSuggestedPrice());
            String totalPrice=String.valueOf(m.calculateTotalPrice());
            model.addAttribute("quotePrice", "Suggested Price: $" + suggestedPrice + "/gal");
            model.addAttribute("totalPrice", "Total Price: $" + totalPrice);
        }

        return "fuelquote";
        
    }
    
    @RequestMapping(value = "/fuelquote", method = RequestMethod.POST, params = "savequote")
    public String saveFuelQuote (Model model, @ModelAttribute FuelQuoteForm form, HttpServletRequest request) {
        Cookie cookie[] = request.getCookies();
        String userid="";
        for(int i=0; i<cookie.length; i++) {
            userid = cookie[i].getValue();
            try{
                Integer.parseInt(userid);
            }
            catch(NumberFormatException e) {
                userid=null;
            }
            if(userid != null) {
                break;
            }
        }

        List<ClientInfoEntity> clientInfoEntity = clientInfoRepository.findByUserid(Integer.parseInt(userid));

        model.addAttribute("entities", clientInfoEntity);

        LocalDate now = LocalDate.now();
        model.addAttribute("now", now);

        model.addAttribute("quoteForm", form);

        m.setgallonsRequested(form.getGallonsRequested());
        m.setuserid(userid);

        if(form.getGallonsRequested().equals("invalid") || form.getDeliveryDate().equals("invalid"))
        {
            model.addAttribute("quotePrice", "There is a problem with your input of gallons requested or the delivery date. \n" +
                                                "You may request up to 5000 gallons.");
            return "fuelquote";
        }

        FuelQuoteEntity n = new FuelQuoteEntity();
        String getFullAddress = (clientInfoEntity.get(0).getAddress1() + ", " + clientInfoEntity.get(0).getCity()
        + ", " + clientInfoEntity.get(0).getState() + " " + clientInfoEntity.get(0).getZipcode());
        n.setgallonsRequested(form.getGallonsRequested());
        n.setdeliveryDate(form.getDeliveryDate().toString());
        n.setdeliveryAddress(getFullAddress);
        n.setUserId(Integer.parseInt(userid));
        n.setsuggestedPrice(String.valueOf(m.calculateSuggestedPrice()));
        n.settotalPrice(String.valueOf(m.calculateTotalPrice()));
        fuelQuoteRepository.save(n);
        return "redirect:/fuelquote";
    }

}