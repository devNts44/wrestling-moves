package com.wrestling_moves.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class DashboardController {

    @GetMapping("/dashboard")
    public String getDashboard(Model model, Principal principal){
        model.addAttribute("username", principal.getName());
        return "dashboardAfterLogin";
    }
}
