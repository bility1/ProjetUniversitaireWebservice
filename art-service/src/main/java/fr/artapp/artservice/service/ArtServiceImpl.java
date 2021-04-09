package fr.artapp.artservice.service;

import fr.artapp.artservice.Exception.*;
import fr.artapp.artservice.model.Oeuvre;
import fr.artapp.artservice.repository.CategorieRepositery;
import fr.artapp.artservice.repository.OeuvreRepository;
import org.keycloak.representations.AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Optional;
import java.util.Set;

@Service
public class ArtServiceImpl implements ArtService{

    @Autowired
    OeuvreRepository oeuvreRepository;

    @Autowired
    CategorieRepositery categorieRepositery;

    @Override
    public Collection<Oeuvre> getAllOeuvres(){
        Collection<Oeuvre> oeuvre = (Collection<Oeuvre>) oeuvreRepository.findAll();
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
    public Collection<Oeuvre> getAllOeuvreByTitre(String titre) throws TitreNotFoundException {
        if (oeuvreRepository.existsByTitre(titre)){
            return oeuvreRepository.findAllByTitre(titre);
        }
        else{
            throw new TitreNotFoundException();
        }
    }

    @Override
    public Collection<Oeuvre> getAllOeuvresByUtilisateur(String login ) throws UtilisateurNotFoundException {
        if (oeuvreRepository.existsByUtilisateurId(login)){
            return oeuvreRepository.findAllByUtilisateurId(login);
        }
        else{
            throw new UtilisateurNotFoundException();
        }
    }

    @Override
    public Oeuvre ajoutOeuvre(Oeuvre oeuvre, String login) throws CategorieNotFoundException {
        oeuvre.setCategorie(oeuvre.getCategorie());
        oeuvre.setUtilisateurId(login);  //ajout du login de l'utilisateur connecté à la création de l'oeuvre

        //recuperation de la date du jour
        DateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        oeuvre.setDate(format.format(date));

        Long idCategorie = oeuvre.getCategorie().getId();
        if (!categorieRepositery.existsById(idCategorie)) {
            throw new CategorieNotFoundException();
        }
        return oeuvreRepository.save(oeuvre);
    }

    @Override
    public void suppressionOeuvre(Long id, AccessToken token) throws OeuvreNotFoundException, UtilisateurIncorrectException {
        if (!oeuvreRepository.existsById(id)){
            throw new OeuvreNotFoundException();
        }
        Set<String> roles = token.getRealmAccess().getRoles();
        Oeuvre oeuvre = oeuvreRepository.findById(id).get();
        if(!oeuvre.getUtilisateurId().equals(token.getGivenName()) && !roles.contains("ADMIN") ){
            throw new UtilisateurIncorrectException();
        }
        oeuvreRepository.deleteById(id);
    }

    @Override
    public Oeuvre modifierOeuvreTitre(Oeuvre oeuvre, Long idOeuvre, String login) throws OeuvreNotFoundException, UtilisateurIncorrectException{
        if(!oeuvreRepository.existsById(idOeuvre)) {
            throw new OeuvreNotFoundException();
        }
        Oeuvre oeuvreModif = oeuvreRepository.findById(idOeuvre).get();
        if(!oeuvreModif.getUtilisateurId().equals(login)){
            throw new UtilisateurIncorrectException();
        }
        String titre = oeuvre.getTitre();
        oeuvreModif.setTitre(titre);
        return oeuvreRepository.save(oeuvreModif);
    }

    @Override
    public Resource telechargerImage(Long imageId) {
        byte[] image = oeuvreRepository.findById(imageId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND))
                .getContent();

        return new ByteArrayResource(image);
    }

}
