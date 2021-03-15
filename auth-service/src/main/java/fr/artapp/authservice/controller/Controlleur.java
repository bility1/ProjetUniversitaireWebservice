package fr.artapp.authservice.controller;

import fr.artapp.authservice.model.User;
import fr.artapp.authservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class Controlleur {
    @Autowired
    UserService userService;

    @GetMapping(value = "/")
    @ResponseStatus(HttpStatus.OK)
    public Collection<User> getAllUser() {

        return userService.getAllUser();

    }



}
