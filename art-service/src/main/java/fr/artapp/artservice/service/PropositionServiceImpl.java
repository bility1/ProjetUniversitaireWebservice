package fr.artapp.artservice.service;

import fr.artapp.artservice.Exception.PropositionNotFoundException;
import fr.artapp.artservice.model.Categorie;
import fr.artapp.artservice.model.Proposition;
import fr.artapp.artservice.repository.CategorieRepositery;
import fr.artapp.artservice.repository.PropositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class PropositionServiceImpl implements PropositionService {

    @Autowired
    PropositionRepository propositionRepository;

    @Autowired
    CategorieRepositery categorieRepository;

    @Override
    public Collection<Proposition> getAllPropositions() {
        Collection<Proposition> propositions = (Collection<Proposition>) propositionRepository.findAll();
        return propositions;
    }

    @Override
    public Optional<Proposition> getPropositionById(Long id) throws PropositionNotFoundException {
        if (propositionRepository.existsById(id)){
            return propositionRepository.findById(id);
        }
        else{
            throw new PropositionNotFoundException();
        }
    }

    @Override
    public Proposition ajoutPropositionCategorie(Proposition proposition, String login) {
        proposition.setDescription(proposition.getDescription());
        proposition.setNomCategorie(proposition.getNomCategorie());
        proposition.setAuteur(login);

        return propositionRepository.save(proposition);
    }

    //TO DO: ajouter exception : si le user existe deja dans le vote, exception
    @Override
    public Proposition voterPourProposition(Long id, String loginId) throws PropositionNotFoundException{
        if(!propositionRepository.existsById(id)){
            throw new PropositionNotFoundException();
        }
        Proposition proposition = getPropositionById(id).get();
        proposition.getUtilisateurs().add(loginId);
        return propositionRepository.save(proposition);
    }

    @Override
    public void suppressionProposition(Long id) throws PropositionNotFoundException {
        if(!propositionRepository.existsById(id)){
            throw new PropositionNotFoundException();
        }
        propositionRepository.deleteById(id);
    }

    @Override
    public Categorie propositionValidee(Long id) throws PropositionNotFoundException {
        if(!propositionRepository.existsById(id)){
            throw new PropositionNotFoundException();
        }
        Proposition proposition = getPropositionById(id).get();
        Categorie categorie = new Categorie();
        categorie.setNomCategorie(proposition.getNomCategorie());
        categorie.setDescription(proposition.getDescription());
        categorieRepository.save(categorie);
        suppressionProposition(id); //suppression de la proposition apres creation de la categorie
        return categorie;
    }

}
