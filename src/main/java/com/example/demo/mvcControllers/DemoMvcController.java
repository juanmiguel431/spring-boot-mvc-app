package com.example.demo.mvcControllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DemoMvcController {

    @GetMapping("/")
    public String sayHello(Model model) {

        model.addAttribute("dateTime", java.time.LocalDateTime.now());

        return "helloWorld"; // Template name
    }
}
