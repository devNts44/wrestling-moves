package com.wrestling_moves.controller;

import com.wrestling_moves.entity.User;
import com.wrestling_moves.exceptions.UserNotFoundException;

import com.wrestling_moves.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserController {

    private final UserService userService; //récupérer les données depuis la DB (grâce au repo) pour les traiter.

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    List<User> findAll() {
        return userService.findAll();
    }


    @PostMapping("/users")
    User newUser(@RequestBody User newUser) {
        return userService.saveUser(newUser);
    }

    @GetMapping("/users/{id}")
    User searchUser(@PathVariable Long id) {

        return userService.findUser(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @PutMapping("/users/{id}")
    User replaceUser(@RequestBody User newUser, @PathVariable Long id) {

        return userService.findUser(id)
                .map(user -> {
                    user.setName(newUser.getName());
                    return userService.saveUser(user);
                })
                .orElseGet(() -> {
                    return userService.saveUser(newUser);
                });
    }

    @DeleteMapping("/users/{id}")
    void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}
