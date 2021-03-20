package fr.artapp.artservice.controller;

import fr.artapp.artservice.service.ArtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class Controlleur {


    @Autowired
    ArtService artService;

    @GetMapping(value = "/hello")
    @ResponseStatus(HttpStatus.OK)
    public Mono<String> hello(){
        return Mono.just("hello !");
    }

/*
    @GetMapping(value = "/oeuvre")
    @ResponseStatus(HttpStatus.OK)
    public Collection<Oeuvre> getAllOeuvre() {

        return artService.getAllOeuvre();

    }


 */









}
