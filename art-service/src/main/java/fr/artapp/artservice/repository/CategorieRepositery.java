package fr.artapp.artservice.repository;

import fr.artapp.artservice.model.Categorie;
import fr.artapp.artservice.model.Oeuvre;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategorieRepositery  extends CrudRepository<Categorie,Long> {
    Categorie findByNomCategorie(String nomCategorie);
}
