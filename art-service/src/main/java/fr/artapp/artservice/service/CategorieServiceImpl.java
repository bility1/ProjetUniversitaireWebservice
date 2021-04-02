package fr.artapp.artservice.service;

import fr.artapp.artservice.Exception.CategorieNotFoundException;
import fr.artapp.artservice.Exception.ForeignKeyCollisionException;
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
public class CategorieServiceImpl implements CategorieService{

    @Autowired
    CategorieRepositery categorieRepositery;

    @Autowired
    OeuvreRepository oeuvreRepository;

    @Override
    public Collection<Categorie> getAllCategorie() {
        Collection<Categorie> categorie = (Collection<Categorie>) categorieRepositery.findAll();
        return categorie;
    }

    @Override
    public Collection<Oeuvre> getAllOeuvreByCategorie(String nomCategorie) throws CategorieNotFoundException {
        Categorie categorie =categorieRepositery.findByNomCategorie(nomCategorie);
        Collection<Oeuvre> oeuvre = oeuvreRepository.findAllByCategorie(categorie);
        if(categorie==null){
            throw new CategorieNotFoundException();
        }
        return oeuvre;
    }

    @Override
    public Optional<Categorie> getCategorieById(Long id) throws CategorieNotFoundException {
        if (categorieRepositery.existsById(id)){
            return categorieRepositery.findById(id);
        }
        else{
            throw new CategorieNotFoundException();
        }
    }

    @Override
    public Categorie ajoutCategorie(Categorie categorie) {
        return categorieRepositery.save(categorie);
    }

    @Override
    public void modifierCategorie(Long id, Categorie categorie) throws CategorieNotFoundException {
        if(!categorieRepositery.existsById(id)) {
            throw new CategorieNotFoundException();
        }
        Categorie categorieModif = categorieRepositery.findById(id).get();
        String nomCategorie = categorie.getNomCategorie();
        categorieModif.setNomCategorie(nomCategorie);
        categorieRepositery.save(categorieModif);
    }

    @Override
    public void suppressionCategorie(Long id) throws CategorieNotFoundException, ForeignKeyCollisionException {
        if(!categorieRepositery.existsById(id)){
            throw new CategorieNotFoundException();
        }
        Categorie categorie = categorieRepositery.findById(id).get();
        if (getAllOeuvreByCategorie(categorie.getNomCategorie()).size() != 0){
            throw new ForeignKeyCollisionException();
        }
        categorieRepositery.deleteById(id);
    }

}
