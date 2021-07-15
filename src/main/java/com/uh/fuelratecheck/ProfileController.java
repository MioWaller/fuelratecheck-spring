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
    public String profileSubmit(@ModelAttribute ClientProfileManagementModel client) {

        //Go to fuel quote if input is valid, reload the page if not
        if((isNumber(client.getZipcode()) == true) && (client.getAddress1() != "") && (client.getCity() != "")
            && (client.getFullName() != "") && (client.getState() != "") && (client.getZipcode() != ""))
            return "redirect:/fuelquote";
        else
            return "redirect:/profile";
        
    }

    private boolean isNumber(String str){
        boolean flag = true;

        for(int i = 0; i < str.length(); i++){
            if(Character.isDigit(str.charAt(i)) == false){
                flag = false;
                break;
            }
        }

        return flag;
    }
}
