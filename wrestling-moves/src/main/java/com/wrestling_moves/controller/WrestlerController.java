package com.wrestling_moves.controller;

import com.wrestling_moves.entity.Wrestler;
import com.wrestling_moves.exceptions.WrestlerNotFoundException;

import com.wrestling_moves.service.WrestlerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class WrestlerController {

    private final WrestlerService wrestlerService; //récupérer les données depuis la DB (grâce au repo) pour les traiter.

    public WrestlerController(WrestlerService wrestlerService) {
        this.wrestlerService = wrestlerService;
    }

    @GetMapping("/wrestlers")
    List<Wrestler> findAll() {
        return wrestlerService.findAll();
    }


    @PostMapping("/wrestlers")
    Wrestler newWrestler(@RequestBody Wrestler newWrestler) {
        return wrestlerService.saveWrestler(newWrestler);
    }

    @GetMapping("/wrestlers/{id}")
    Wrestler searchWrestler(@PathVariable Long id) {

        return wrestlerService.findWrestler(id)
                .orElseThrow(() -> new WrestlerNotFoundException(id));
    }

    @PutMapping("/wrestlers/{id}")
    Wrestler replaceWrestler(@RequestBody Wrestler newWrestler, @PathVariable Long id) {

        return wrestlerService.findWrestler(id)
                .map(wrestler -> {
                    wrestler.setFirstName(newWrestler.getFirstName());
                    wrestler.setLastName(newWrestler.getLastName());
                    wrestler.setUsername(newWrestler.getUsername());
                    wrestler.setEmail(newWrestler.getEmail());
                    wrestler.setPassword(newWrestler.getPassword());
                    return wrestlerService.saveWrestler(wrestler);
                })
                .orElseGet(() -> wrestlerService.saveWrestler(newWrestler));
    }


    @DeleteMapping("/wrestlers/{id}")
    void deleteWrestler(@PathVariable Long id) {
        wrestlerService.deleteWrestler(id);
    }
}
