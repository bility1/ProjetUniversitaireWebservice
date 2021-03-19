package fr.artapp.artservice.controller;
import fr.artapp.artservice.Exception.ExceptionDejaException;
import fr.artapp.artservice.Exception.ExisteDejaException;
import fr.artapp.artservice.Exception.OeuvreNotFoundException;
import fr.artapp.artservice.model.Categorie;
import fr.artapp.artservice.model.Oeuvre;
import fr.artapp.artservice.service.ArtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
public class Controleur  {

    @Autowired
    ArtService artService;

    @GetMapping(value = "/oeuvres")
    @ResponseStatus(HttpStatus.OK)
    public Collection<Oeuvre> getAllOeuvres() {
        return artService.getAllOeuvres();
    }

    @GetMapping(value = "/oeuvres/{id}")
    public ResponseEntity<Oeuvre> getOeuvreById(@PathVariable Long id) {
        Optional<Oeuvre> oeuvre = artService.getOeuvreById(id);
        return ResponseEntity.ok(oeuvre.get());
    }

    @DeleteMapping(value = "/oeuvres/{id}")
    public ResponseEntity<Long> suppressionOeuvre(@PathVariable("id") Long id, UriComponentsBuilder base) throws OeuvreNotFoundException {
        artService.suppressionOeuvre(id);
        URI location = base.path("/art/oeuvres/{id}").buildAndExpand(id).toUri();
        return ResponseEntity.created(location).body(id);
    }

    @PostMapping(value = "/oeuvres")
    public ResponseEntity<Oeuvre> ajoutOeuvre(@RequestBody Oeuvre oeuvre, UriComponentsBuilder base) {
        artService.ajoutOeuvre(oeuvre);
        URI location = base.path("/art/oeuvres/{id}").buildAndExpand(oeuvre).toUri();
        return ResponseEntity.created(location).body(oeuvre);
    }


    @GetMapping(value = "/oeuvres/{categorie}", params = "category")
    public ResponseEntity<Collection<Oeuvre>> getAllOeuvreByCategorie(@RequestParam ("category") Categorie categorie) {
        Collection<Oeuvre> oeuvre = artService.getAllOeuvreByCategorie(categorie);
        // il reste à ajouter la configuration pour l'authentification


        if (oeuvre!=null){
            return ResponseEntity.ok(oeuvre);
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }

    @GetMapping(value = "/oeuvres/{title}")
    public ResponseEntity<Collection<Oeuvre>> getAllOeuvreBytitle(@PathVariable String titre) {
        Collection<Oeuvre> oeuvre = artService.getAllOeuvreByTitre(titre);

        // il reste à ajouter la configuration pour l'authentification

        if(oeuvre!=null){
            return ResponseEntity.ok(oeuvre);
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }

    @PostMapping(value = "/oeuvre")
    public ResponseEntity<Oeuvre> creerOeuvre(Principal principal,@RequestBody String titre){

        Oeuvre oeuvre= null;
        try {
            oeuvre = artService.creerOeuvre(titre);
        }catch ( ExceptionDejaException e){
            e.printStackTrace();
        }
       /* URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(oeuvre.getId()).toUri();

        return ResponseEntity.created(location).body(oeuvre);*/
        // c'est pas la bonne requete
        return ResponseEntity.status(HttpStatus.MULTI_STATUS).build();

    }

}

