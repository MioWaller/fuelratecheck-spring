package com.uh.fuelratecheck;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HistoryController {
    @Autowired
    private FuelQuoteRepository fuelQuoteRepository;
    @GetMapping("/fuelhistory")
    public String history(Model model) {
        model.addAttribute("quotes", fuelQuoteRepository.findAll());
        return "fuelhistory";
    }
}