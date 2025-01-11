package com.wrestling_moves.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String afficherPageConnexion() {
        return "loginPage"; // Nom du fichier connexion.html
    }

    @PostMapping("/login")
    public String traiterConnexion(String username, String password) {
        if ("admin".equals(username) && "admin123".equals(password)) {
            return "redirect:/dashboard"; // Rediriger vers une autre page après connexion
        }
        return "loginPage"; // Rester sur la page si connexion échoue
    }
}