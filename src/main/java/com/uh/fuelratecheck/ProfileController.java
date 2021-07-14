package com.uh.fuelratecheck;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProfileController {
    @GetMapping("/profile")
	public String profile(Model model) {
        ClientProfileManagementModel client = new ClientProfileManagementModel();
        model.addAttribute("profile", client);
        return "profile";
	}

    @PostMapping("/profile")
    public String setupProfile(@ModelAttribute ClientProfileManagementModel client) {

        //this method is probably unnecessary, so leaving it blank for now

        return "";
    }
}
