package com.uh.fuelratecheck;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegistrationController {
    @Autowired
    private ClientRepository clientRepository;

    @GetMapping("/registration")
	public String registration(Model model) {
        RegistrationModel registrationModel = new RegistrationModel();
        model.addAttribute("registration", registrationModel);
        return "registration";
	}
    
    @PostMapping("/registration")
    public String registrationSubmit(@ModelAttribute RegistrationModel registration, @RequestParam String username
    , @RequestParam String password) {
        if (registration.getUsername().equals("") || registration.getPassword().equals("")) {
            return "redirect:/registration";
        }

        else {
            ClientEntity n = new ClientEntity();
            n.setName(username);
            n.setPassword(password);
            clientRepository.save(n);
            return "redirect:/profile";
        } 
    } 
}