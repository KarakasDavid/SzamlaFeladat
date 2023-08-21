package com.example.security.Controller;

import com.example.security.Service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }


    @RequestMapping(value = "/login", method = {RequestMethod.GET, RequestMethod.POST})
    public String processLogin(Model model) {
        int loginCount = userService.getLoginAttemptCount();

        model.addAttribute("loginCount", loginCount);

        return "login";
    }


}
