package com.example.spring.HomePage;

import com.example.spring.Student.AuthStudent;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("")
    public String run(Model model) {
        if (!AuthStudent.isAuthenticated()) {
            return "redirect:/login";
        }
        else {
            model.addAttribute("id", AuthStudent.getAuthenticatedId());
            return "indexAll";
        }
    }
}
