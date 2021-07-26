package com.uh.fuelratecheck;

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

    @GetMapping("/fuelquote")
        public String fuelquote(Model model) {
        LocalDate now = LocalDate.now();
        model.addAttribute("now", now);
        FuelQuoteEntity fuelQuoteModel = new FuelQuoteEntity();
        model.addAttribute("fuelquote", fuelQuoteModel);
        return "fuelquote";
    }   
    @PostMapping("/fuelquote")
        public @ResponseBody String addNewUser (@ModelAttribute FuelQuoteEntity fuelquote, @RequestParam String gallonsRequested
      , @RequestParam String deliveryDate, HttpServletRequest request) {
            Cookie cookie[] = request.getCookies();
            String userid = cookie[0].getValue();
            FuelQuoteEntity n = new FuelQuoteEntity();
            n.setgallonsRequested(gallonsRequested);
            n.setdeliveryDate(deliveryDate);
            n.setUserId(Integer.parseInt(userid));
            fuelQuoteRepository.save(n);
            return "redirect:/fuelquote";
    }

	}
