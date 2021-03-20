package fr.artapp.artservice.controller;
import fr.artapp.artservice.Exception.OeuvreNotFoundException;
import fr.artapp.artservice.model.Categorie;
import fr.artapp.artservice.model.Oeuvre;
import fr.artapp.artservice.service.ArtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.*;

@RestController
public class Controleur {

    @Autowired
    ArtService artService;

    @GetMapping(value = "/oeuvres")
    @ResponseStatus(HttpStatus.OK)
    public Collection<Oeuvre> getAllOeuvres() {
        return artService.getAllOeuvres();
    }

    @GetMapping(value = "/oeuvres/{id}")
    public ResponseEntity getOeuvreById(@PathVariable Long id) {
        try {
            Optional<Oeuvre> oeuvre = artService.getOeuvreById(id);
            return ResponseEntity.ok(oeuvre.get());
        }
        catch(OeuvreNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.toString());
        }
    }

    @DeleteMapping(value = "/oeuvres/{id}")
    public ResponseEntity<String> suppressionOeuvre(@PathVariable("id") Long id) {
        try {
            artService.suppressionOeuvre(id);
        }
        catch(OeuvreNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.toString());
        }
        return ResponseEntity.ok().build();
    }

    //to do Elodie: ajouter une exception
    @PostMapping(value = "/oeuvres")
    public ResponseEntity<Oeuvre> ajoutOeuvre(@RequestBody Oeuvre oeuvre, UriComponentsBuilder base) {
        artService.ajoutOeuvre(oeuvre);
        URI location = base.path("/art/oeuvres/{id}").buildAndExpand(oeuvre).toUri();
        return ResponseEntity.created(location).body(oeuvre);
    }

    /*
    @GetMapping(value = "/oeuvres/{categorie}", params = "category")
    public ResponseEntity<Collection<Oeuvre>> getAllOeuvreByCategorie(@RequestParam ("category") Categorie categorie) {
        Collection<Oeuvre> oeuvre = artService.getAllOeuvreByCategorie(categorie);
        return ResponseEntity.ok(oeuvre);
    }

    @GetMapping(value = "/oeuvres/{title}")
    public ResponseEntity<Collection<Oeuvre>> getAllOeuvreBytitle(@PathVariable String titre) {
        Collection<Oeuvre> oeuvre = artService.getAllOeuvreBytitle(titre);
        return ResponseEntity.ok(oeuvre);
    }
    */

}

