package com.uh.fuelratecheck;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FuelQuoteController {
    @GetMapping("/fuelquote")
	public String fuelquote) {
        return "fuelquote";
	}
}