package fr.artapp.artservice.service;

import fr.artapp.artservice.Exception.CategorieExistePasException;
import fr.artapp.artservice.Exception.CategorieNotFoundException;
import fr.artapp.artservice.Exception.OeuvreNotFoundException;
import fr.artapp.artservice.model.Categorie;
import fr.artapp.artservice.model.Oeuvre;
import fr.artapp.artservice.repository.CategorieRepositery;
import fr.artapp.artservice.repository.OeuvreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class CategorieServiceImpl implements CategorieService{

    @Autowired
    CategorieRepositery categorieRepositery;

    @Autowired
    OeuvreRepository oeuvreRepository;

    @Override
    public Collection<Categorie> getAllCategorie() throws CategorieNotFoundException{
        Collection<Categorie> categorie = (Collection<Categorie>) categorieRepositery.findAll();
        if (categorie.isEmpty()){
            throw new CategorieNotFoundException();
        }
        return categorie;
    }


    @Override
    public Categorie ajoutCategorie(Categorie categorie) {
        return categorieRepositery.save(categorie);
    }

    @Override
    public Collection<Oeuvre> getAllOeuvreByCategorie(String nomCategorie) throws CategorieExistePasException, OeuvreNotFoundException {
        Categorie categorie =categorieRepositery.findByNomCategorie(nomCategorie);
        Collection<Oeuvre> oeuvre = oeuvreRepository.findAllByCategorie(categorie);
        if(categorie==null){
            throw new CategorieExistePasException();
        }
        if(oeuvre.isEmpty()){
            throw new OeuvreNotFoundException();
        }
        return oeuvre;
    }
}
