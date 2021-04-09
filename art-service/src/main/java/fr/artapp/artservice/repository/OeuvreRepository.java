package fr.artapp.artservice.repository;

import fr.artapp.artservice.model.Categorie;
import fr.artapp.artservice.model.Oeuvre;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;


@Repository
public interface OeuvreRepository  extends CrudRepository<Oeuvre,Long> {
    Collection<Oeuvre> findAllByCategorie(Categorie categorie);
    Collection<Oeuvre> findAllByTitre(String title);
    Collection<Oeuvre> findAllByUtilisateurId(String utilisateurId);
    boolean existsByTitre(String titre);
    boolean existsByUtilisateurId(String utilisateurId);
}

