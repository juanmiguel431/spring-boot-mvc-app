package com.example.demo.controllers;

import com.example.demo.models.Member;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user-create")
public class FormHandlerController {

    @GetMapping("")
    public String showForm() {
        return "show-form";
    }

//    @RequestMapping(value = "/process-form", method = RequestMethod.POST) // This is the same as below
    @PostMapping("/process-form")
    public String processForm(HttpServletRequest request, @RequestParam("username") String username, Model model) {

        System.out.println(username);

        var name = request.getParameter("username");

        var member = new Member();
        member.setName(name.toUpperCase());

        model.addAttribute("model", member);

        return "process-form";
    }
}
