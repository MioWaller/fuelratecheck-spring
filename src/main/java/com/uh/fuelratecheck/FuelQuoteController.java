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

    @GetMapping("/fuelquote")
    public String fuelquote(Model model, HttpServletRequest request) {
        LocalDate now = LocalDate.now();
        LocalDate later = now.plusDays(14);
        model.addAttribute("now", now);

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

        FuelQuoteForm form = new FuelQuoteForm();
        form.gallonsRequested = 100;
        form.deliveryDate = later;

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
        String suggestedPrice=String.valueOf(m.calculateSuggestedPrice());
        String totalPrice=String.valueOf(m.calculateTotalPrice());
        model.addAttribute("quotePrice", "Suggested Price: $" + suggestedPrice);
        model.addAttribute("totalPrice", "Total Price: $" + totalPrice);
        
        // Use the pricing module to calculate the quote.

        // Added both the parameters and the result to the model via addAttributes (quotePrice, gallonsRequested, deliveryDate)

        return "fuelquote";
    }
    
    @RequestMapping(value = "/fuelquote", method = RequestMethod.POST, params = "savequote")
    public String addNewUser (Model model, @ModelAttribute FuelQuoteForm form, HttpServletRequest request) {
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

        // TODO: Validate the inputs
        // Check the date, # of gallons requested
        // Copy form parameters to database entity
        // Save.

        m.setgallonsRequested(form.getGallonsRequested());
        m.setuserid(userid);

        List<ClientInfoEntity> clientInfoEntity = clientInfoRepository.findByUserid(Integer.parseInt(userid));
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