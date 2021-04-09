package fr.artapp.artservice.controller;

import fr.artapp.artservice.DTO.CategorieDTO;
import fr.artapp.artservice.DTO.OeuvreDTO;
import fr.artapp.artservice.Exception.CategorieNotFoundException;
import fr.artapp.artservice.Exception.ForeignKeyCollisionException;
import fr.artapp.artservice.Exception.OeuvreNotFoundException;
import fr.artapp.artservice.model.Categorie;
import fr.artapp.artservice.model.Oeuvre;
import fr.artapp.artservice.service.CategorieService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.stream.Collectors;

@RestController
public class CategorieControleur {

    @Autowired
    CategorieService categorieService;

    @Autowired
    private ModelMapper mapper;

    @GetMapping(value = "/oeuvres/categorie")
    public ResponseEntity<?> getAllCategorie(){
        Collection<Categorie> categories = categorieService.getAllCategorie();
        Collection<CategorieDTO> categorieDTO= categories.stream()
                .map(categorie -> mapper.map(categorie ,CategorieDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(categorieDTO);

    }

    @GetMapping(value = "/oeuvres/categorie/{nomCategorie}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllOeuvreByCategorie(@PathVariable String nomCategorie) {
        try {
            Collection<Oeuvre> oeuvres = categorieService.getAllOeuvreByCategorie(nomCategorie);
            Collection<OeuvreDTO> oeuvresDTO= oeuvres.stream()
                    .map(oeuvre -> mapper.map(oeuvre, OeuvreDTO.class))
                    .collect(Collectors.toList());
            return ResponseEntity.ok().body(oeuvresDTO);
        } catch ( CategorieNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.toString());
        }
    }

    @PostMapping(value = "/oeuvres/categorie")
    public ResponseEntity<?> ajoutCategorie(@RequestBody CategorieDTO categorieDto)  {
        Categorie categorie = mapper.map(categorieDto, Categorie.class);
        categorieService.ajoutCategorie(categorie);
        CategorieDTO categorieDTO = mapper.map(categorie, CategorieDTO.class);
        return new ResponseEntity<>(categorieDTO, HttpStatus.CREATED);
    }

    @PutMapping(value = "/oeuvres/categorie/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> modifierCategorie(@PathVariable Long id,
                                                       @RequestBody CategorieDTO categoriedto) {
        try {
            Categorie categorie = mapper.map(categoriedto, Categorie.class);
            Categorie categorieModif = categorieService.modifierCategorie(id, categorie);
            CategorieDTO categorieDTO = mapper.map(categorieModif, CategorieDTO.class);
            return ResponseEntity.ok().body(categorieDTO);
        } catch (CategorieNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.toString());
        }
    }

    @DeleteMapping(value = "/oeuvres/categorie/{id}")
    public ResponseEntity<String> suppressionCategorie(@PathVariable("id") Long id)  {
        try {
            categorieService.suppressionCategorie(id);
            return ResponseEntity.noContent().build();
        }
        catch(CategorieNotFoundException | ForeignKeyCollisionException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.toString());
        }
    }

}
