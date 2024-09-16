package com.example.demo.mvcControllers;

import com.example.demo.models.Member;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user-create")
public class FormHandlerMvcController {

    @GetMapping("")
    public String showForm() {
        return "showForm";
    }

//    @RequestMapping(value = "/process-form", method = RequestMethod.POST) // This is the same as below
    @PostMapping("/process-form")
    public String processForm(HttpServletRequest request, @RequestParam("username") String username, Model model) {

        System.out.println(username);

        var name = request.getParameter("username");

        var member = new Member();
        member.setName(name.toUpperCase());

        model.addAttribute("model", member);

        return "processForm";
    }
}
