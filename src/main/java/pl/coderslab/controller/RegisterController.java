package pl.coderslab.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import pl.coderslab.entity.User;
import pl.coderslab.service.UserService;

import javax.validation.Valid;

@Controller
public class RegisterController {
    @Autowired
    private UserService userService;

    @GetMapping("/user/register")
    public String registerNewUser(Model model){
        User user = new User();
        model.addAttribute("user", user);
        return "forms/register";
    }

    @PostMapping("/user/register")
    public String addUser(@Valid User user, BindingResult result){
        if(result.hasErrors()){
            return "register";
        }
        userService.saveUser(user);
        return "redirect:/home";
    }
}
