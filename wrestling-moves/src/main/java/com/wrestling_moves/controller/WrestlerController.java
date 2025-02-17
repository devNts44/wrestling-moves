package com.wrestling_moves.controller;

import com.wrestling_moves.entity.Wrestler;
import com.wrestling_moves.service.WrestlerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class WrestlerController {

    private final WrestlerService wrestlerService; //récupérer les données depuis la DB (grâce au repo) pour les traiter.

    public WrestlerController(WrestlerService wrestlerService) {
        this.wrestlerService = wrestlerService;
    }

    @GetMapping("/wrestlers")
    public String displayWrestlers(Model model){
       List<Wrestler> wrestlers = wrestlerService.findAll();
       model.addAttribute("wrestlers",wrestlers);
       return "wrestlersPage";
    }

    @DeleteMapping("/delete/wrestlers/{id}")
    public String deleteWrestler(@PathVariable Long id) {
        wrestlerService.delete(id);
        return "redirect:/wrestlers";
    }
}
