package com.uh.fuelratecheck;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HistoryController {
    @GetMapping("/fuelhistory")
    public String history(Model model) {
        ArrayList<FuelQuoteModel> quotes = new ArrayList<>();

        // Sample data
        FuelQuoteModel q1 = new FuelQuoteModel();
        q1.setsuggestedPrice("15");
        q1.setgallonsRequested("1000");
        q1.setdeliveryDate("2021-07-31");
        q1.setdeliveryAddress("My place");
        q1.settotalPrice("15000");

        FuelQuoteModel q2 = new FuelQuoteModel();
        q2.setsuggestedPrice("15");
        q2.setgallonsRequested("1000");
        q2.setdeliveryDate("2021-07-31");
        q2.setdeliveryAddress("My place");
        q2.settotalPrice("15000");

        FuelQuoteModel q3 = new FuelQuoteModel();
        q3.setsuggestedPrice("15");
        q3.setgallonsRequested("1000");
        q3.setdeliveryDate("2021-07-31");
        q3.setdeliveryAddress("My place");
        q3.settotalPrice("15000");

        quotes.add(q1);
        quotes.add(q2);
        quotes.add(q3);

        //FuelQuoteModel fuelQuoteModel = new FuelQuoteModel();
        model.addAttribute("quotes", quotes);
        return "fuelhistory";
    }
}