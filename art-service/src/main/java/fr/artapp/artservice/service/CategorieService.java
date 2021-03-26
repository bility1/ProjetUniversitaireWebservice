package fr.artapp.artservice.service;

import fr.artapp.artservice.Exception.CategorieExistePasException;
import fr.artapp.artservice.Exception.CategorieNotFoundException;
import fr.artapp.artservice.Exception.OeuvreNotFoundException;
import fr.artapp.artservice.model.Categorie;
import fr.artapp.artservice.model.Oeuvre;

import java.util.Collection;

public interface CategorieService {

    Collection<Categorie> getAllCategorie() throws CategorieNotFoundException;

    Categorie ajoutCategorie(Categorie categorie);

    Collection<Oeuvre> getAllOeuvreByCategorie(String nomCategorie) throws CategorieExistePasException, OeuvreNotFoundException;


}
