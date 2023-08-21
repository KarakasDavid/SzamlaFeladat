package com.example.security.Controller;

import com.example.security.Model.Person;
import com.example.security.Model.Role;
import com.example.security.Repository.PersonRepository;
import com.example.security.Repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.HashSet;
import java.util.List;

@Controller
public class PersonController {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private RoleRepository roleRepository;

    @GetMapping("/persons")
    public String listPersons(Model model) {
        List<Person> persons = personRepository.findAll();
        List<Role> roles = roleRepository.findAll();
        model.addAttribute("persons", persons);
        model.addAttribute("roles", roles);
        return "personList";
    }

    @PostMapping("/delete")
    public String deleteReceipt(@RequestParam(name = "personId") Long personId) {
        personRepository.deleteById(personId);
        return "redirect:/persons";
    }

    @PostMapping("/updateRole")
    public String updateRole(@RequestParam(name = "personId") Long personId,
                             @RequestParam(name = "roleIds") List<Long> roleIds) {
        Person person = personRepository.findById(personId).orElse(null);
        if (person != null) {
            List<Role> selectedRoles = roleRepository.findAllById(roleIds);
            person.setRoles(new HashSet<>(selectedRoles));
            personRepository.save(person);
        }

        return "redirect:/persons";
    }

}