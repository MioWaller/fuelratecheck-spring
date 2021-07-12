package com.uh.fuelratecheck;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProfileController {
    @GetMapping("/profile")
	public String profile() {
        ClientProfileManagement client = new ClientProfileManagement();
        return "profile";
	}

    @PostMapping("/profile")
    public String setupProfile(@ModelAttribute ClientProfileManagement client) {



        // if (login.getUsername().equals("mimi") && login.getPassword().equals("mypass")) {
        //     return "redirect:/fuelhistory";
        // } else {
        //     return "redirect:/login";
        // }

        return 0;
    }
}
