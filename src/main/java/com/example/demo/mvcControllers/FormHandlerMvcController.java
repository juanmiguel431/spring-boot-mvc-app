package com.example.demo.mvcControllers;

import com.example.demo.models.Member;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/user-create")
public class FormHandlerMvcController {

    @GetMapping("")
    public String showForm() {
        return "showForm";
    }

    @GetMapping("/process-form")
    public String processForm(HttpServletRequest request, @RequestParam("username") String username, Model model) {

        System.out.println(username);

        var name = request.getParameter("username");

        var member = new Member();
        member.setName(name.toUpperCase());

        model.addAttribute("model", member);

        return "processForm";
    }
}
