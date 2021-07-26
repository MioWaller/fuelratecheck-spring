package com.uh.fuelratecheck;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import javax.servlet.http.*;

@Controller
public class HistoryController {
    @Autowired
    private FuelQuoteRepository fuelQuoteRepository;
    @GetMapping("/fuelhistory")

    public String history(Model model, HttpServletRequest request) {
        Cookie cookie[] = request.getCookies();
        String userid = cookie[0].getValue();
        model.addAttribute("quotes", fuelQuoteRepository.findByUserid(Integer.parseInt(userid)));
        return "fuelhistory";
    }
}