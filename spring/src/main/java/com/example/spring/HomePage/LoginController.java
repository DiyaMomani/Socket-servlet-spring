package com.example.spring.HomePage;

import com.example.spring.Student.AuthStudent;
import com.example.spring.Student.Student;
import com.example.spring.DataBase.DBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {
    DBService databaseService;

    @Autowired
    public LoginController(DBService databaseService) {
        this.databaseService = databaseService;
    }

    @GetMapping("/login")
    public String loginForm(Model model) {
        if (AuthStudent.isAuthenticated()) {
            return "loggedIn";
        }

        model.addAttribute("student", new Student());
        return "login";
    }

    @PostMapping("/login")
    public String loginSubmit(@ModelAttribute Student student) {
        if (!databaseService.isValidStudent(student.getId())) {
            return "invalidCredentials";
        }
        if (!databaseService.isValidPassword(student.getId(), student.getPassword())) {
            return "invalidCredentials";
        }
        AuthStudent.setIsAuthenticated(true);
        AuthStudent.setAuthenticatedId(student.getId());
        return "loggedIn";
    }
}
