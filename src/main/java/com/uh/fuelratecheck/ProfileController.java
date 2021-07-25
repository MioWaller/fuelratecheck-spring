package com.uh.fuelratecheck;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProfileController {
    @Autowired
    private ClientInfoRepository clientInfoRepository;


    @GetMapping("/profile")
	public String profile(Model model) {
        ClientProfileManagementModel client = new ClientProfileManagementModel();
        model.addAttribute("profile", client);
        return "profile";
	}

    @PostMapping("/profile")
    public String profileSubmit(@ModelAttribute ClientProfileManagementModel client, 
    @RequestParam String fullName, @RequestParam String address1, @RequestParam String address2,
    @RequestParam String city, @RequestParam String state, @RequestParam String zipcode) {
        //Go to fuel quote if input is valid, reload the page if not
        if ((isNumber(client.getZipcode()) == true) &&
            (client.getAddress1() != "") &&
            (client.getCity() != "") && 
            (client.getFullName() != "") && 
            (client.getState() != "") && 
            (client.getZipcode() != "")) {

            ClientInfoEntity clientInfoEntity = new ClientInfoEntity();
            clientInfoEntity.setFullName(fullName);
            clientInfoEntity.setAddress1(address1);
            clientInfoEntity.setAddress2(address2);
            clientInfoEntity.setCity(city);
            clientInfoEntity.setState(state);
            clientInfoEntity.setZipcode(zipcode);
            
            //store the new entity in the repository
            clientInfoRepository.save(clientInfoEntity);
            return "redirect:/fuelquote";
        } else {
            //if the input is invalid, just refresh the page and ask for profile info again

            return "redirect:/profile";
        }
        
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
