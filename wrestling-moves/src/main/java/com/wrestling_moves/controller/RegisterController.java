package com.wrestling_moves.controller;

import com.wrestling_moves.entity.Wrestler;
import com.wrestling_moves.service.WrestlerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegisterController {

    private final WrestlerService wrestlerService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public RegisterController(WrestlerService wrestlerService) {
        this.wrestlerService = wrestlerService;
    }

    @GetMapping("/register/save")
    public String displayRegistrationPage(Model model) {
        model.addAttribute("wrestler", new Wrestler());
        return "registerPage";
    }

    @PostMapping("/register/save")
    public String saveWrestlerRegistration(
            @Valid @ModelAttribute("wrestler") Wrestler wrestler,
            BindingResult result,
            Model model) {

        if (result.hasErrors()) {
            result.getAllErrors().forEach(error -> 
                model.addAttribute("errorMessage", error.getDefaultMessage())
            );
            return "registerPage";
        }

        if (wrestlerService.findByEmail(wrestler.getEmail()).isPresent()) {
            model.addAttribute("errorMessage", "Un compte avec cet email existe déjà.");
            return "registerPage";
        }

        try {
            String hashedPassword = passwordEncoder.encode(wrestler.getPassword());
            wrestler.setPassword(hashedPassword);
            wrestlerService.saveWrestler(wrestler);
            return "redirect:/login";
        } catch (Exception e) {
            System.err.println("Erreur lors de l'inscription: " + e.getMessage());
            model.addAttribute("errorMessage", 
                "Une erreur est survenue lors de l'inscription: " + e.getMessage());
            return "registerPage";
        }
    }

}
