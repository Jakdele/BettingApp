package pl.coderslab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import pl.coderslab.entity.User;
import pl.coderslab.service.UserService;

@Controller
public class LoginController {
    @Autowired
    private UserService userService;


    @GetMapping("/login")
    public String login() {
        User user  = userService.getCurrentUser();
        if (user == null) {
            return "forms/login";
        } else {
            return "redirect:/homepage";
        }
    }
}
