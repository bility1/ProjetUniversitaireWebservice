package fr.artapp.artservice.repository;

import fr.artapp.artservice.model.Categorie;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CategorieRepositery  extends CrudRepository<Categorie,Long> {
    Categorie findByNomCategorie(String nomCategorie);


}
