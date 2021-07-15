package com.uh.fuelratecheck;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class FuelQuoteController {
    @GetMapping("/fuelquote")
	public String fuelquote(Model model) {
        FuelQuoteModel fuelQuoteModel = new FuelQuoteModel();
        model.addAttribute("fuelquote", fuelQuoteModel);
        return "fuelquote";
    }

    @PostMapping("/fuelquote")
        public String fuelQuoteSubmit(@ModelAttribute FuelQuoteModel fuelquote) {
            if (fuelquote.getgallonsRequested().equals("invalid") && fuelquote.getdeliveryDate().equals("invalid"))
            {
            return "redirect:/fuelquote";
            }
            else
            {
            return "redirect:/fuelhistory";
            }
        }
	}
