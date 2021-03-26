package fr.artapp.artservice.controller;
import fr.artapp.artservice.Exception.CategorieExistePasException;
import fr.artapp.artservice.Exception.OeuvreExisteDejaException;
import fr.artapp.artservice.Exception.OeuvreNotFoundException;
import fr.artapp.artservice.model.Categorie;
import fr.artapp.artservice.model.Oeuvre;
import fr.artapp.artservice.service.ArtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.Collection;
import java.util.Optional;

@RestController
public class OeuvreControleur {

    @Autowired
    ArtService artService;


    //cases of use  : calling an other backend service to get or post data
    RestTemplate keycloakRestTemplate  = new RestTemplate(
            new BufferingClientHttpRequestFactory(
                    new SimpleClientHttpRequestFactory()
            )
    );

    @GetMapping(value = "/hello")
    @ResponseStatus(HttpStatus.OK)
    public Mono<String> hello(){
        String uri ="http://localhost:8089/api/review/hello";
        String result = keycloakRestTemplate.getForObject(uri, String.class);

        return Mono.just("hello artservice ! "+result);
    }

    @GetMapping(value = "/oeuvres")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getAllOeuvres(){
        try{
            Collection<Oeuvre> oeuvre = artService.getAllOeuvres();
            return ResponseEntity.ok().body(oeuvre);
        }
        catch(OeuvreNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.toString());
        }
    }

    @GetMapping(value = "/oeuvres/{id}")
    public ResponseEntity<?> getOeuvreById(@PathVariable Long id) {
        try {
            Optional<Oeuvre> oeuvre = artService.getOeuvreById(id);
            return ResponseEntity.ok(oeuvre.get());
        }
        catch(OeuvreNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.toString());
        }
    }

    @GetMapping(value = "/oeuvres/titre/{titre}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllOeuvreBytitle(@PathVariable String titre) {
        try{
            Collection<Oeuvre> oeuvre = artService.getAllOeuvreByTitre(titre);
            return ResponseEntity.ok().body(oeuvre);
        }
        catch(OeuvreNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.toString());
        }
    }

    @DeleteMapping(value = "/oeuvres/{id}")
    public ResponseEntity<String> suppressionOeuvre(@PathVariable("id") Long id) {
        try {
            artService.suppressionOeuvre(id);
            return ResponseEntity.ok().build();
        }
        catch(OeuvreNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.toString());
        }
    }

    @PostMapping(value = "/oeuvres/{idCategorie}")
    public ResponseEntity<?> ajoutOeuvre(@RequestBody Oeuvre oeuvre,@PathVariable Long idCategorie) {
        try {
            artService.ajoutOeuvre(oeuvre,idCategorie);
            return new ResponseEntity<>(oeuvre, HttpStatus.CREATED);
        } catch (CategorieExistePasException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.toString());
        }
    }






}

