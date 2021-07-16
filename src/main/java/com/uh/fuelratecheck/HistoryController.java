package com.uh.fuelratecheck;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HistoryController {
    @GetMapping("/fuelhistory")
	public String fuelhistory(Model model) {
        HistoryModel historyModel = new HistoryModel();
        model.addAttribute("fuelhistory", historyModel);
        return "fuelhistory";
	}
}
