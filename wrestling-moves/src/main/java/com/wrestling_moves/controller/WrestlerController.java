package com.wrestling_moves.controller;

import com.wrestling_moves.entity.Wrestler;
import com.wrestling_moves.exceptions.WrestlerNotFoundException;

import com.wrestling_moves.service.WrestlerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
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
    Wrestler newUser(@RequestBody Wrestler newWrestler) {
        return wrestlerService.saveUser(newWrestler);
    }

    @GetMapping("/wrestlers/{id}")
    Wrestler searchUser(@PathVariable Long id) {

        return wrestlerService.findUser(id)
                .orElseThrow(() -> new WrestlerNotFoundException(id));
    }

    @PutMapping("/wrestlers/{id}")
    Wrestler replaceUser(@RequestBody Wrestler newWrestler, @PathVariable Long id) {

        return wrestlerService.findUser(id)
                .map(wrestler -> {
                    wrestler.setName(newWrestler.getName());
                    return wrestlerService.saveUser(wrestler);
                })
                .orElseGet(() -> wrestlerService.saveUser(newWrestler));
    }

    @DeleteMapping("/wrestlers/{id}")
    void deleteUser(@PathVariable Long id) {
        wrestlerService.deleteUser(id);
    }
}
