package com.uh.fuelratecheck;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HistoryController {
    @GetMapping("/fuelhistory")
	public String history() {
        return "fuelhistory";
	}
}
