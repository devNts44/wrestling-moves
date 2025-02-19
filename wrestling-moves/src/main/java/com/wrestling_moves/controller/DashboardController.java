package com.wrestling_moves.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/dashboard")
    public String getDashboard(Model model){
        model.addAttribute("username");

    //TODO: ajouter l'affichage de l'username pour l'OAuth2
        return "dashboardAfterLogin";
    }
}
