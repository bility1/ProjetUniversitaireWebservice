package fr.artapp.artservice.service;

import fr.artapp.artservice.Exception.ExceptionDejaException;
import fr.artapp.artservice.Exception.OeuvreNotFoundException;
import fr.artapp.artservice.model.Categorie;
import fr.artapp.artservice.model.Oeuvre;

import java.util.Collection;
import java.util.Optional;

public interface ArtService {

    Collection<Oeuvre> getAllOeuvres();

    Optional<Oeuvre> getOeuvreById(Long id);

    Oeuvre ajoutOeuvre(Oeuvre oeuvre);

    void suppressionOeuvre(Long id) throws OeuvreNotFoundException;


    Collection<Oeuvre> getAllOeuvreByCategorie(Categorie categorie);


    Collection<Oeuvre> getAllOeuvreByTitre(String titre);


    Categorie getCategorieByNomcategorie(String nomCategorie);

/*
    void modifierOeuvreTitre(String title);

    boolean oeuvreExiste(Long id);

    Oeuvre getOeuvreByUser(User user);
    */


}
