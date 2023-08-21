package com.example.security.Config;

import com.example.security.Model.Person;
import com.example.security.Model.Role;
import com.example.security.Model.UserDTO;
import com.example.security.Repository.PersonRepository;
import com.example.security.Repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;

@Controller
public class Register {
    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private RoleRepository roleRepository;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("userDTO", new UserDTO());

        List<Role> roles = roleRepository.findAll();
        roles.remove(roleRepository.findByName("ADMIN"));

        model.addAttribute("roles", roles);
        return "register";
    }




    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute UserDTO userDTO,@Valid @RequestParam("roles") List<Long> roleIds, Model model, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "error_page";
        }

        Person person = new Person(userDTO.getUsername(), userDTO.getPassword());
        person.setName(userDTO.getUsername());

        if (personRepository.findByUsername(userDTO.getUsername()) != null) {
            model.addAttribute("registrationError", "Username is already taken.");
            return "redirect:/register";
        }

        List<Role> selectedRoles = roleRepository.findAllById(roleIds);
        person.setRoles(new HashSet<>(selectedRoles));


        personRepository.save(person);

        return "redirect:/login";
    }


}

