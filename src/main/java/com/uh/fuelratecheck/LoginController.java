package com.uh.fuelratecheck;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {
    @Autowired
    private ClientRepository clientRepository;

    @GetMapping("/login")
	public String login(Model model) {
        LoginModel loginModel = new LoginModel();
        model.addAttribute("login", loginModel);
        return "login";
	}

    @PostMapping("/login")
    public String loginSubmit(@ModelAttribute LoginModel login, HttpServletResponse response) {
        List<ClientEntity> clients = clientRepository.findByUsername(login.getUsername());

        if (clients.isEmpty()) {
            return "redirect:/login";
        }

        ClientEntity client = clients.get(0);

        String hash = PasswordEncryption.hash(login.getPassword());

        if (!client.getPassword().equals(hash)) {
            return "redirect:/login";
        }

        Cookie cookie = new Cookie("user-id", client.getId().toString());
        response.addCookie(cookie);

        return "redirect:/fuelhistory";
    }

}
