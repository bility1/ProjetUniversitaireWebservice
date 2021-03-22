package fr.artapp.artservice.service;

import fr.artapp.artservice.Exception.OeuvreNotFoundException;
import fr.artapp.artservice.model.Categorie;
import fr.artapp.artservice.model.Oeuvre;
import fr.artapp.artservice.repository.CategorieRepositery;
import fr.artapp.artservice.repository.OeuvreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class ArtServiceImpl implements ArtService{

    @Autowired
    OeuvreRepository oeuvreRepository;

    @Autowired
    CategorieRepositery categorieRepositery;

    @Override
    public Collection<Oeuvre> getAllOeuvres() {
        return (Collection<Oeuvre>)  oeuvreRepository.findAll();
    }

    @Override
    public Optional<Oeuvre> getOeuvreById(Long id) throws OeuvreNotFoundException {
        if (oeuvreRepository.existsById(id)){
            return oeuvreRepository.findById(id);
        }
        else{
            throw new OeuvreNotFoundException();
        }
    }

    @Override
    public void suppressionOeuvre(Long id) throws OeuvreNotFoundException {
        if (oeuvreRepository.existsById(id)){
            oeuvreRepository.deleteById(id);
        }
        else{
            throw new OeuvreNotFoundException();
        }
    }

    @Override
    public Oeuvre ajoutOeuvre(Oeuvre oeuvre,Long id) {
        Optional<Categorie> categorie =categorieRepositery.findById(id);
        oeuvre.setCategorie(categorie.get());
        return oeuvreRepository.save(oeuvre);
    }

    @Override
    public Categorie ajoutCategorie(Categorie categorie) {
        return categorieRepositery.save(categorie);
    }

    @Override
    public Collection<Oeuvre> getAllOeuvreByCategorie(Categorie categorie) {
        return oeuvreRepository.findAllByCategorie(categorie);
    }

    @Override
    public Collection<Oeuvre> getAllOeuvreByTitre(String titre) {
        return oeuvreRepository.findAllByTitre(titre);
    }

    @Override
    public Categorie getCategorieByNomcategorie(String nomCategorie){
        return categorieRepositery.findByNomCategorie(nomCategorie);
    }


    /*
    @Override
    public void modifierOeuvreTitre(String title) {
        //to do
    }*/
    //Methode ajout catégorie : to do
}
