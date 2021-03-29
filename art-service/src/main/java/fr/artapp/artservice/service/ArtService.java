package fr.artapp.artservice.service;

import fr.artapp.artservice.Exception.ExceptionDejaException;
import fr.artapp.artservice.Exception.OeuvreNotFoundException;
import fr.artapp.artservice.model.Categorie;
import fr.artapp.artservice.model.Oeuvre;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

public interface ArtService {

    Collection<Oeuvre> getAllOeuvres();

    Optional<Oeuvre> getOeuvreById(Long id) throws OeuvreNotFoundException;

    Oeuvre ajoutOeuvre(Oeuvre oeuvre,Long id);
    Categorie ajoutCategorie(Categorie categorie);

    void suppressionOeuvre(Long id) throws OeuvreNotFoundException;


    Collection<Oeuvre> getAllOeuvreByTitre(String titre);

    Categorie getCategorieByNomcategorie(String nomCategorie);

    Collection<Oeuvre> getAllOeuvreByCategorie(Categorie categorie);
    Categorie modifierCategorie(Long id, String categorie) throws OeuvreNotFoundException;

    //void modifierOeuvreTitre(String title);

    //Oeuvre getOeuvreByUser(User user);

}
