package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DemoController {

    @GetMapping("/datetime")
    public String sayHello(Model model) {

        model.addAttribute("dateTime", java.time.LocalDateTime.now());

        return "hello-world"; // Template name
    }

    @GetMapping("/leaders")
    public String getLeaders() {
        return "leaders";
    }

    @GetMapping("/systems")
    public String getSystems() {
        return "systems";
    }
}
