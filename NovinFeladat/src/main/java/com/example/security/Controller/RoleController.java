package com.example.security.Controller;


import com.example.security.Model.Role;
import com.example.security.Repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;


@Controller
public class RoleController {

    @Autowired
    private RoleRepository roleRepository;


    @GetMapping("/roles")
    public String listReceipts(Model model) {
        List<Role> roles = roleRepository.findAll();
        model.addAttribute("roles", roles);
        return "roles";
    }

    @PostMapping("/addRole")
    public String addRole(@RequestParam String name) {

        if(roleRepository.findByName(name)==null)
        {
            Role role = new Role();
            role.setName(name);

            roleRepository.save(role);
            return "home";
        }
        return "home";
    }

    @PostMapping("/delete/Role")
    public String deleteRole(@RequestParam(name = "roleId") Long roleId) {
        roleRepository.deleteById(roleId);
        return "redirect:/persons";
    }

}