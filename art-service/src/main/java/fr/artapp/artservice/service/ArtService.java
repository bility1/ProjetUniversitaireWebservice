package fr.artapp.artservice.service;

import fr.artapp.artservice.Exception.*;
import fr.artapp.artservice.model.Oeuvre;
import org.keycloak.representations.AccessToken;
import org.springframework.core.io.Resource;

import java.util.Collection;
import java.util.Optional;

public interface ArtService {

    Collection<Oeuvre> getAllOeuvres();

    Optional<Oeuvre> getOeuvreById(Long id) throws OeuvreNotFoundException;

    Collection<Oeuvre> getAllOeuvreByTitre(String titre) throws TitreNotFoundException;

    Collection<Oeuvre> getAllOeuvresByUtilisateur(String login) throws UtilisateurNotFoundException;

    Oeuvre ajoutOeuvre(Oeuvre oeuvre, String loginId) throws CategorieNotFoundException;

    Oeuvre modifierOeuvreTitre(Oeuvre oeuvre, Long idOeuvre, String loginId) throws OeuvreNotFoundException, UtilisateurIncorrectException;

    void suppressionOeuvre(Long id, AccessToken token) throws OeuvreNotFoundException, UtilisateurIncorrectException;

    Resource telechargerImage(Long imageId);
}
