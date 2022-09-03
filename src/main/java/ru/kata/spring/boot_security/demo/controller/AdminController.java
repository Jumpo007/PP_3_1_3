package ru.kata.spring.boot_security.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;


@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;
    @Autowired
    public AdminController(UserService userDao, RoleService roleService) {
        this.userService = userDao;
        this.roleService = roleService;
    }


    @GetMapping()
    public String index(Model model, Principal principal) {
        model.addAttribute("user", userService.index());
        model.addAttribute("currentName", principal.getName());
        return "index";
    }
    @GetMapping("/{id}")
    public String showUser(@PathVariable("id") int id, Model model,Principal principal) {
        model.addAttribute("user", userService.showUser(id));
        model.addAttribute("currentName", principal.getName());
        return "show";
    }
    @GetMapping("/new")
    public String newUser(@ModelAttribute("User") User User, Model model) {
        model.addAttribute("rolesList", roleService.index());
        return "new";
    }
    @PostMapping
    public String saveUser(@ModelAttribute("User") User user){
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/{id}/edit")
    public String editUser(Model model,@PathVariable("id") int id,Principal principal) {
        model.addAttribute("user", userService.showUser(id));
        model.addAttribute("rolesList", roleService.index());
        model.addAttribute("currentName", principal.getName());
        return "edit";
    }
    @PatchMapping("/{id}")
    public String updateUser(@ModelAttribute("user") User user, @PathVariable("id") int id) {
        userService.updateUser(id, user);
        return "redirect:/admin";
    }
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }
}
