package com.uh.fuelratecheck;

import java.util.Arrays;
import java.util.Optional;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

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
    public String profileSubmit(HttpServletRequest request, @ModelAttribute ClientProfileManagementModel client) {
        // Validate the inputs
        if ((isNumber(client.getZipcode()) == true) &&
            (client.getAddress1() != "") &&
            (client.getCity() != "") && 
            (client.getFullName() != "") && 
            (client.getState() != "") && 
            (client.getZipcode() != "")) {
            
        }
        else
            return "redirect:/profile";


        Cookie cookie1[] = request.getCookies();
        String userid = cookie1[0].getValue();


        // Inputs are good, so lets fetch the cookie
        Optional<String> userIdCookie = Arrays.stream(request.getCookies())
            .filter(cookie -> "user-id".equals(cookie.getName()))
            .map(Cookie::getValue)
            .findFirst();

        if (!userIdCookie.isPresent()) {
            // Cookie does not exist. No user is logged in.
            return "redirect:/login";
        }


        // Get the client info for the userId from the database.
        List<ClientInfoEntity> clientInfoEntity = clientInfoRepository.findByUserid(Integer.parseInt(userid));

        if (clientInfoEntity.isEmpty()) {
            // No user with that client id exists, lets create it.
            ClientInfoEntity newClientInfo = new ClientInfoEntity();
            // newClientInfo.setId(userId);
            newClientInfo.setFullName(client.getFullName());
            newClientInfo.setAddress1(client.getAddress1());
            newClientInfo.setAddress2(client.getAddress2());
            newClientInfo.setCity(client.getCity());
            newClientInfo.setState(client.getState());
            newClientInfo.setZipcode(client.getZipcode());
            newClientInfo.setUserId(Integer.parseInt(userid));

            clientInfoRepository.save(newClientInfo);
        } else {
            // User client info exists, lets update it.
            clientInfoEntity.get(0).setFullName(client.getFullName());
            clientInfoEntity.get(0).setAddress1(client.getAddress1());
            clientInfoEntity.get(0).setAddress2(client.getAddress2());
            clientInfoEntity.get(0).setCity(client.getCity());
            clientInfoEntity.get(0).setState(client.getState());
            clientInfoEntity.get(0).setZipcode(client.getZipcode());

            clientInfoRepository.save(clientInfoEntity.get(0));
        }

        return "redirect:/fuelquote";
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
