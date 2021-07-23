package com.uh.fuelratecheck;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class FuelQuoteController {
    @Autowired
	private FuelQuoteRepository fuelQuoteRepository;

    @GetMapping("/fuelquote")
        public String fuelquote(Model model) {
        FuelQuoteModel fuelQuoteModel = new FuelQuoteModel();
        model.addAttribute("fuelquote", fuelQuoteModel);
        return "fuelquote";
    }   

    @PostMapping("/fuelquote")
        public @ResponseBody String addNewUser (@ModelAttribute FuelQuoteModel fuelquote, @RequestParam String gallonsRequested
      , @RequestParam String deliveryDate) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request

        FuelQuoteModel n = new FuelQuoteModel();
        n.setgallonsRequested(gallonsRequested);
        n.setdeliveryAddress(deliveryDate);
        fuelQuoteRepository.save(n);
        return "redirect:/fuelquote";
    }

	}
