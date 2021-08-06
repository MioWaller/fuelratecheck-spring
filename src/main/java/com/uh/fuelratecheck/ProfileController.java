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

@Controller
public class ProfileController {
    @Autowired
    private ClientInfoRepository clientInfoRepository;

    @GetMapping("/profile")
	public String profile(Model model, HttpServletRequest request) {
        ClientProfileManagementModel temp = new ClientProfileManagementModel();

        Cookie cookie1[] = request.getCookies();
        String userid="";
        for(int i=0; i<cookie1.length; i++) {
            userid = cookie1[i].getValue();
            try{
                Integer.parseInt(userid);
            }
            catch(NumberFormatException e)
            {
                userid=null;
            }
            if(userid != null)
            {
                break;
            }
        }

        List<ClientInfoEntity> clientInfoEntity = clientInfoRepository.findByUserid(Integer.parseInt(userid));

        //pre-fill client info if needed
        if (clientInfoEntity.isEmpty()){
            temp.setFullName("");
            temp.setAddress1("");
            temp.setAddress2("");
            temp.setCity("");
            temp.setState("");
            temp.setZipcode("");
        }
        else{
            temp.setFullName(clientInfoEntity.get(0).getFullName());
            temp.setAddress1(clientInfoEntity.get(0).getAddress1());
            temp.setAddress2(clientInfoEntity.get(0).getAddress2());
            temp.setCity(clientInfoEntity.get(0).getCity());
            temp.setState(clientInfoEntity.get(0).getState());
            temp.setZipcode(clientInfoEntity.get(0).getZipcode());
        }

        model.addAttribute("temp", temp);

        return "profile";
	}

    @PostMapping("/profile")
    public String profileSubmit(HttpServletRequest request, @ModelAttribute ClientProfileManagementModel temp) {
        // Validate the inputs, if invalid refresh profile page

        if (temp.getZipcode().length() < 5 ||
            temp.getZipcode().length() > 9 ||
            !isNumber(temp.getZipcode()) ||
            temp.getAddress1() == "" || 
            temp.getAddress1().length() > 100 ||
            temp.getCity() == "" ||
            temp.getCity().length() > 100 ||
            temp.getState() == "" ||
            temp.getState().length() != 2 ||
            temp.getFullName() == "" || 
            temp.getFullName().length() > 50)
        {
            return "redirect:/profile";
        }

        // Inputs are good, so lets fetch the cookie
        Optional<String> userIdCookie = Arrays.stream(request.getCookies())
            .filter(cookie -> "user-id".equals(cookie.getName()))
            .map(Cookie::getValue)
            .findFirst();

        if (!userIdCookie.isPresent()) {
            // Cookie does not exist. No user is logged in.
            return "redirect:/login";
        }

        //get cookies to find out which user is editing their client info
        Cookie cookie1[] = request.getCookies();
        String userid="";
        for(int i=0; i<cookie1.length; i++) {
            userid = cookie1[i].getValue();
            try{
                Integer.parseInt(userid);
            }
            catch(NumberFormatException e)
            {
                userid=null;
            }
            if(userid != null)
            {
                break;
            }
        }

        // Get the client info for the userId from the database.
        List<ClientInfoEntity> clientInfoEntity = clientInfoRepository.findByUserid(Integer.parseInt(userid));

        if (clientInfoEntity.isEmpty()) {
            // No user with that client id exists, lets create it.
            ClientInfoEntity newClientInfo = new ClientInfoEntity();

            newClientInfo.setFullName(temp.getFullName());
            newClientInfo.setAddress1(temp.getAddress1());
            newClientInfo.setAddress2(temp.getAddress2());
            newClientInfo.setCity(temp.getCity());
            newClientInfo.setState(temp.getState());
            newClientInfo.setZipcode(temp.getZipcode());
            newClientInfo.setUserId(Integer.parseInt(userid));

            clientInfoRepository.save(newClientInfo);
        } else {
            // User client info exists, lets update it.
            clientInfoEntity.get(0).setFullName(temp.getFullName());
            clientInfoEntity.get(0).setAddress1(temp.getAddress1());
            clientInfoEntity.get(0).setAddress2(temp.getAddress2());
            clientInfoEntity.get(0).setCity(temp.getCity());
            clientInfoEntity.get(0).setState(temp.getState());
            clientInfoEntity.get(0).setZipcode(temp.getZipcode());

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
