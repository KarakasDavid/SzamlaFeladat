package com.example.security.Controller;

import com.example.security.Model.Person;
import com.example.security.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
public class HomeController {

    private final UserService userService;

    @Autowired
    public HomeController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/home")
    public String home(Model model) {
        Person currentUser = userService.getCurrentUser();
        currentUser.setLogin_date(LocalDate.now());
        model.addAttribute("currentUser", currentUser);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Set<String> roles = authentication.getAuthorities().stream()
                .map(Object::toString)
                .collect(Collectors.toSet());
        model.addAttribute("roles", roles);

        return "home";
    }
}
