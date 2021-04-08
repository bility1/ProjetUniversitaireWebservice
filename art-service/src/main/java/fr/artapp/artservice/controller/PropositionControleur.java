package fr.artapp.artservice.controller;

import fr.artapp.artservice.DTO.CategorieDTO;
import fr.artapp.artservice.DTO.PropositionDTO;
import fr.artapp.artservice.Exception.PropositionNotFoundException;
import fr.artapp.artservice.model.Categorie;
import fr.artapp.artservice.model.Proposition;
import fr.artapp.artservice.service.PropositionService;
import org.keycloak.adapters.springsecurity.account.SimpleKeycloakAccount;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.keycloak.representations.AccessToken;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.stream.Collectors;

@RestController
public class PropositionControleur {

    @Autowired
    PropositionService propositionService;

    @Autowired
    private ModelMapper mapper;

    @GetMapping(value = "/oeuvres/proposition")
    public ResponseEntity<Collection<PropositionDTO>> getAllPropositions(){
        Collection<Proposition> propositions = propositionService.getAllPropositions();
        Collection<PropositionDTO> propositionDTOS= propositions.stream()
                .map(proposition -> mapper.map(proposition ,PropositionDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(propositionDTOS);
    }

    @PostMapping(value = "/oeuvres/proposition")
    public ResponseEntity<?> ajoutProposition(@RequestBody PropositionDTO propositionDTO, KeycloakAuthenticationToken principal) {
        Proposition proposition = mapper.map(propositionDTO, Proposition.class);
        SimpleKeycloakAccount simpleKeycloakAccount = (SimpleKeycloakAccount) principal.getDetails();
        AccessToken token  = simpleKeycloakAccount.getKeycloakSecurityContext().getToken();
        String login=token.getGivenName();

        Proposition propositionNouveau = propositionService.ajoutPropositionCategorie(proposition, login);
        PropositionDTO propositionDto = mapper.map(propositionNouveau, PropositionDTO.class);
        return new ResponseEntity<>(propositionDto, HttpStatus.CREATED);

    }

    @PostMapping(value = "/oeuvres/proposition/{id}")
    public ResponseEntity<?> propositionValidee(@PathVariable Long id) {
        try {
            Categorie categorieNouveau = propositionService.propositionValidee(id);
            CategorieDTO categorieDTO = mapper.map(categorieNouveau, CategorieDTO.class);
            return new ResponseEntity<>(categorieDTO, HttpStatus.CREATED);
        } catch (PropositionNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.toString());
        }
    }

    @PutMapping(value = "/oeuvres/proposition/{id}")
    public ResponseEntity<?> voterPourProposition(@PathVariable Long id,
                                                  KeycloakAuthenticationToken principal) {
        SimpleKeycloakAccount simpleKeycloakAccount = (SimpleKeycloakAccount) principal.getDetails();
        AccessToken token  = simpleKeycloakAccount.getKeycloakSecurityContext().getToken();
        String login=token.getGivenName();
        try {
            Proposition propositionModif = propositionService.voterPourProposition(id, login);
            PropositionDTO propositionDto = mapper.map(propositionModif, PropositionDTO.class);
            return ResponseEntity.ok().body(propositionDto);
        } catch (PropositionNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.toString());
        }
    }

    @DeleteMapping(value = "/oeuvres/proposition/{id}")
    public ResponseEntity<String> suppressionProposition(@PathVariable("id") Long id)  {
        try {
            propositionService.suppressionProposition(id);
            return ResponseEntity.noContent().build();
        }
        catch(PropositionNotFoundException  e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.toString());
        }
    }

}
