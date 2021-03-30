package fr.artapp.artservice.service;

import fr.artapp.artservice.Exception.CategorieExistePasException;
import fr.artapp.artservice.Exception.OeuvreExisteDejaException;
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
    public Collection<Oeuvre> getAllOeuvres() throws OeuvreNotFoundException{
        Collection<Oeuvre> oeuvre = (Collection<Oeuvre>) oeuvreRepository.findAll();
        if (oeuvre.isEmpty()){
            throw new OeuvreNotFoundException();
        }
        return oeuvre;
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
    public Oeuvre ajoutOeuvre(Oeuvre oeuvre,Long idCategorie) throws CategorieExistePasException {
        if (!categorieRepositery.existsById(idCategorie)) {
            throw new CategorieExistePasException();
        }
        Optional<Categorie> categorie = categorieRepositery.findById(idCategorie);
        oeuvre.setCategorie(categorie.get());
        return oeuvreRepository.save(oeuvre);
    }

    @Override
    public Collection<Oeuvre> getAllOeuvreByTitre(String titre) throws OeuvreNotFoundException{
        Collection<Oeuvre> oeuvre = oeuvreRepository.findAllByTitre(titre);
        if (oeuvre.isEmpty()){
            throw new OeuvreNotFoundException();
        }
        return oeuvre;
    }

    @Override
    public Oeuvre modifierOeuvreTitre(String titre, Long idOeuvre) throws OeuvreNotFoundException{
        if (!oeuvreRepository.existsById(idOeuvre)) {
            throw new OeuvreNotFoundException();
        }
        Oeuvre oeuvre = oeuvreRepository.findById(idOeuvre).orElse(new Oeuvre());
        oeuvre.setTitre(titre);
        return oeuvreRepository.save(oeuvre);
    }

/*
        @Override
    public void oeuvreByUser(String title) {
        //to do
    }

    */

}
