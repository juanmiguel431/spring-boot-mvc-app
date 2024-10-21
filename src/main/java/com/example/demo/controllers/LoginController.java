package com.example.demo.controllers;

import com.example.demo.models.LoginDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("login-page")
    public String login(Model model) {

        var dto = new LoginDto();
        model.addAttribute("model", dto);

        return "plain-login";
    }
}
