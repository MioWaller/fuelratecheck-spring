package com.uh.fuelratecheck;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {
    @GetMapping("/login")
	public String login(Model model) {
        LoginModel loginModel = new LoginModel();
        model.addAttribute("login", loginModel);
        return "login";
	}

    @PostMapping("/login")
    public String loginSubmit(@ModelAttribute LoginModel login) {
        if (login.getUsername().equals("mimi") && login.getPassword().equals("mypass")) {
            return "redirect:/fuelhistory";
        } else {
            return "redirect:/login";
        }
    }

}
