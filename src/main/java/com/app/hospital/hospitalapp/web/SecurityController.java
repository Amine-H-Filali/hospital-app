package com.app.hospital.hospitalapp.web;

import com.app.hospital.hospitalapp.security.entities.AppUser;
import com.app.hospital.hospitalapp.security.services.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
public class SecurityController {

    AccountService accountService;

    @GetMapping("/notAuthorized")
    public String notAuthorized() {
        return "notAuthorized";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    public String register(@RequestParam String username, @RequestParam String email,@RequestParam String password,@RequestParam String confirmpassword){



       accountService.addNewUser(username,password,email,confirmpassword);
       accountService.addRoleToUser(username,"ADMIN");

       return  "redirect:/login";



    }
}
