package com.uh.fuelratecheck;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import java.time.LocalDate;

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
      , @RequestParam String deliveryDate) {
            FuelQuoteEntity n = new FuelQuoteEntity();
            n.setgallonsRequested(gallonsRequested);
            n.setdeliveryDate(deliveryDate);
            fuelQuoteRepository.save(n);
            return "redirect:/fuelquote";
    }

	}
