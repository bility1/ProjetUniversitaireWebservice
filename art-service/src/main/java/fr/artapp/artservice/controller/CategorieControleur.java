package fr.artapp.artservice.controller;

import fr.artapp.artservice.Exception.CategorieExistePasException;
import fr.artapp.artservice.Exception.CategorieNotFoundException;
import fr.artapp.artservice.Exception.OeuvreNotFoundException;
import fr.artapp.artservice.model.Categorie;
import fr.artapp.artservice.model.Oeuvre;
import fr.artapp.artservice.service.CategorieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
public class CategorieControleur {

    @Autowired
    CategorieService categorieService;

    @GetMapping(value = "/oeuvres/categorie/")
    public ResponseEntity<?> getAllCategorie() throws CategorieNotFoundException{
        try{
            Collection<Categorie> categorie = categorieService.getAllCategorie();
            return ResponseEntity.ok().body(categorie);
        }
        catch(CategorieNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.toString());
        }
    }

    @PostMapping(value = "/oeuvres/categorie")
    public ResponseEntity<Categorie> ajoutCategorie(@RequestBody Categorie categorie)  {
        categorieService.ajoutCategorie(categorie);
        return new ResponseEntity<>(categorie, HttpStatus.CREATED);
    }

    @GetMapping(value = "/oeuvres/categorie/{nomCategorie}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllOeuvreByCategorie(@PathVariable String nomCategorie) {
        try {
            Collection<Oeuvre> oeuvre = categorieService.getAllOeuvreByCategorie(nomCategorie);
            return ResponseEntity.ok().body(oeuvre);
        } catch (OeuvreNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.toString());
        } catch (CategorieExistePasException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.toString());
        }
    }


    @PutMapping(value = "/oeuvres/modifier/{id}/{categorie}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Categorie> modifierCategorie(@PathVariable Long id,@PathVariable String categorie) throws OeuvreNotFoundException {
        Categorie categorie1 = categorieService.modifierCategorie(id,categorie);
        return ResponseEntity.ok().body(categorie1);
    }
//delete categorie

    @DeleteMapping(value = "/oeuvres/categorie/{id}")
    public ResponseEntity<String> suppressionCategorie(@PathVariable("id") Long id)  {
        try {
            categorieService.suppressionCategorie(id);
        }
        catch(CategorieNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.toString());
        }
        return ResponseEntity.ok().build();
    }


}
