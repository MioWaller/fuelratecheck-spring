package com.uh.fuelratecheck;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

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
