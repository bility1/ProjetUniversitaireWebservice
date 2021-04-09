package fr.artapp.artservice.service;

import fr.artapp.artservice.Exception.PropositionNotFoundException;
import fr.artapp.artservice.model.Categorie;
import fr.artapp.artservice.model.Proposition;

import java.util.Collection;
import java.util.Optional;

public interface PropositionService {

    Collection<Proposition> getAllPropositions();

    Optional<Proposition> getPropositionById(Long id) throws PropositionNotFoundException;

    Proposition ajoutPropositionCategorie(Proposition proposition, String loginId);

    // TO DO: ajouter exception
    Proposition voterPourProposition(Long id, String loginId)throws PropositionNotFoundException;

    void suppressionProposition(Long id) throws PropositionNotFoundException;

    // TO DO: ajouter exception
    Categorie propositionValidee(Long id) throws PropositionNotFoundException;

}
