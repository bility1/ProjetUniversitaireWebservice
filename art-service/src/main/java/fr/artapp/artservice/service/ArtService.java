package fr.artapp.artservice.service;

import fr.artapp.artservice.Exception.CategorieExistePasException;
import fr.artapp.artservice.Exception.OeuvreExisteDejaException;
import fr.artapp.artservice.Exception.OeuvreNotFoundException;
import fr.artapp.artservice.model.Categorie;
import fr.artapp.artservice.model.Oeuvre;

import java.util.Collection;
import java.util.Optional;

public interface ArtService {

    Collection<Oeuvre> getAllOeuvres() throws OeuvreNotFoundException;

    Optional<Oeuvre> getOeuvreById(Long id) throws OeuvreNotFoundException;

    Collection<Oeuvre> getAllOeuvreByTitre(String titre) throws OeuvreNotFoundException;

    Oeuvre ajoutOeuvre(Oeuvre oeuvre,Long idCategorie) throws CategorieExistePasException;

    void suppressionOeuvre(Long id) throws OeuvreNotFoundException;



    //void modifierOeuvreTitre(String title);

    //Oeuvre getOeuvreByUser(User user);

}
