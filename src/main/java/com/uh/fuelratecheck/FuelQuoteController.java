package com.uh.fuelratecheck;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import javax.servlet.http.*;
import java.util.List;

@Controller
public class FuelQuoteController {
    @Autowired
	private FuelQuoteRepository fuelQuoteRepository;
    @Autowired
    private ClientInfoRepository clientInfoRepository;

    @GetMapping("/fuelquote")
        public String fuelquote(Model model, HttpServletRequest request) {
        LocalDate now = LocalDate.now();
        model.addAttribute("now", now);
        FuelQuoteEntity fuelQuoteModel = new FuelQuoteEntity();
        model.addAttribute("fuelquote", fuelQuoteModel);
        return "fuelquote";
    }   
    @PostMapping("/fuelquote")
        public String addNewUser (@ModelAttribute FuelQuoteEntity fuelquote, @RequestParam String gallonsRequested
      , @RequestParam String deliveryDate, HttpServletRequest request) {
        Cookie cookie[] = request.getCookies();
        String userid="";
        for(int i=0; i<cookie.length; i++) {
            userid = cookie[i].getValue();
            try{
                Integer.parseInt(userid);
            }
            catch(NumberFormatException e)
            {
                userid=null;
            }
            if(userid != null)
            {
                break;
            }
        }
            List<ClientInfoEntity> clientInfoEntity = clientInfoRepository.findByUserid(Integer.parseInt(userid));
            FuelQuoteEntity n = new FuelQuoteEntity();
            String getFullAddress = (clientInfoEntity.get(0).getAddress1() + ", " + clientInfoEntity.get(0).getCity()
            + ", " + clientInfoEntity.get(0).getState() + " " + clientInfoEntity.get(0).getZipcode());
            n.setgallonsRequested(gallonsRequested);
            n.setdeliveryDate(deliveryDate);
            n.setdeliveryAddress(getFullAddress);
            n.setUserId(Integer.parseInt(userid));
            fuelQuoteRepository.save(n);
            return "redirect:/fuelquote";
    }

	}
