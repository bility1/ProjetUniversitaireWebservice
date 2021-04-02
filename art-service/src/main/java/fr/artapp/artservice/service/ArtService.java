package fr.artapp.artservice.service;

import fr.artapp.artservice.Exception.*;
import fr.artapp.artservice.model.Oeuvre;

import java.util.Collection;
import java.util.Optional;

public interface ArtService {

    Collection<Oeuvre> getAllOeuvres();

    Optional<Oeuvre> getOeuvreById(Long id) throws OeuvreNotFoundException;

    Collection<Oeuvre> getAllOeuvreByTitre(String titre) throws TitreNotFoundException;

    Collection<Oeuvre> getAllOeuvresByUtilisateur(String login) throws UtilisateurNotFoundException;

    void ajoutOeuvre(Oeuvre oeuvre, String loginId) throws CategorieNotFoundException;

    void suppressionOeuvre(Long id, String loginId) throws OeuvreNotFoundException, UtilisateurIncorrectException;

    void modifierOeuvreTitre(Oeuvre oeuvre, Long idOeuvre, String loginId) throws OeuvreNotFoundException, UtilisateurIncorrectException;

}
