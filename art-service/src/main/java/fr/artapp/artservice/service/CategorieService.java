package fr.artapp.artservice.service;

import fr.artapp.artservice.Exception.CategorieNotFoundException;
import fr.artapp.artservice.Exception.ForeignKeyCollisionException;
import fr.artapp.artservice.Exception.OeuvreNotFoundException;
import fr.artapp.artservice.model.Categorie;
import fr.artapp.artservice.model.Oeuvre;

import java.util.Collection;
import java.util.Optional;

public interface CategorieService {

    Collection<Categorie> getAllCategorie();

    Optional<Categorie> getCategorieById(Long id) throws CategorieNotFoundException;

    Collection<Oeuvre> getAllOeuvreByCategorie(String nomCategorie) throws CategorieNotFoundException;

    Categorie ajoutCategorie(Categorie categorie);

    void modifierCategorie(Long id, Categorie categorie) throws CategorieNotFoundException;

    void suppressionCategorie(Long id) throws CategorieNotFoundException, ForeignKeyCollisionException;


}
