package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DemoController {

    @GetMapping("/")
    public String sayHello(Model model) {

        model.addAttribute("dateTime", java.time.LocalDateTime.now());

        return "hello-world"; // Template name
    }
}