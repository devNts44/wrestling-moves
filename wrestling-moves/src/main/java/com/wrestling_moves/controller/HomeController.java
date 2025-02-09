package com.wrestling_moves.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Model model) {
        return "mainPage"; // Correspond au fichier mainPage.html dans le dossier templates
    }
}
