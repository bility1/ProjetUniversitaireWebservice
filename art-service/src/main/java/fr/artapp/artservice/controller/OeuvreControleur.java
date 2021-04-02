package fr.artapp.artservice.controller;
import fr.artapp.artservice.DTO.OeuvreDTO;
import fr.artapp.artservice.Exception.*;
import fr.artapp.artservice.model.Oeuvre;
import fr.artapp.artservice.service.ArtService;
import org.keycloak.adapters.springsecurity.account.SimpleKeycloakAccount;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.keycloak.representations.AccessToken;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class OeuvreControleur {

    @Autowired
    ArtService artService;

    @Autowired
    private ModelMapper mapper;

    //cases of use  : calling an other backend service to get or post data
    RestTemplate keycloakRestTemplate  = new RestTemplate(
            new BufferingClientHttpRequestFactory(
                    new SimpleClientHttpRequestFactory()
            )
    );

    @GetMapping(value = "/oeuvres")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getAllOeuvres(){
        Collection<Oeuvre> oeuvres = artService.getAllOeuvres();
        Collection<OeuvreDTO> oeuvresDTO= oeuvres.stream()
                .map(oeuvre -> mapper.map(oeuvre,OeuvreDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(oeuvresDTO);
    }

    @GetMapping(value = "/oeuvres/{id}")
    public ResponseEntity<?> getOeuvreById( @PathVariable Long id) {
        try {
            Optional<Oeuvre> oeuvre = artService.getOeuvreById(id);
            OeuvreDTO oeuvreDTO = mapper.map(oeuvre.get(), OeuvreDTO.class);
            return ResponseEntity.ok().body(oeuvreDTO);
        }
        catch(OeuvreNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.toString());
        }
    }

    @GetMapping(value = "/oeuvres/titre/{titre}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllOeuvreBytitle(@PathVariable String titre) {
        try {
            Collection<Oeuvre> oeuvres = artService.getAllOeuvreByTitre(titre);
            Collection<OeuvreDTO> oeuvresDTO= oeuvres.stream()
                    .map(oeuvre -> mapper.map(oeuvre,OeuvreDTO.class))
                    .collect(Collectors.toList());
            return ResponseEntity.ok().body(oeuvresDTO);
        }
        catch(TitreNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.toString());
        }
    }

    @GetMapping(value = "/oeuvres/utilisateur/{idUtilisateur}")
    public ResponseEntity<?> getAllOeuvresUtilisateursParId(@PathVariable(value = "idUtilisateur") String idUtilisateur) {
        try{
            Collection<Oeuvre> oeuvres = artService.getAllOeuvresByUtilisateur(idUtilisateur);
            Collection<OeuvreDTO> oeuvresDTO= oeuvres.stream()
                    .map(oeuvre -> mapper.map(oeuvre,OeuvreDTO.class))
                    .collect(Collectors.toList());
            return ResponseEntity.ok().body(oeuvresDTO);
        }
            catch(UtilisateurNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.toString());
        }
    }

    @PostMapping(value = "/oeuvres")
    public ResponseEntity<?> ajoutOeuvre(@RequestBody OeuvreDTO oeuvredto, KeycloakAuthenticationToken principal) {
        Oeuvre oeuvre = mapper.map(oeuvredto, Oeuvre.class);
        SimpleKeycloakAccount simpleKeycloakAccount = (SimpleKeycloakAccount) principal.getDetails();
        AccessToken token  = simpleKeycloakAccount.getKeycloakSecurityContext().getToken();
        String login=token.getGivenName();
        try {
            artService.ajoutOeuvre(oeuvre, login);
            OeuvreDTO oeuvreDTO = mapper.map(oeuvre, OeuvreDTO.class);
            return new ResponseEntity<>(oeuvreDTO, HttpStatus.CREATED);
        } catch (CategorieNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.toString());
        }
    }

    @DeleteMapping(value = "/oeuvres/{id}")
    public ResponseEntity<String> suppressionOeuvre(@PathVariable("id") Long id,
                                                    KeycloakAuthenticationToken principal) {
        SimpleKeycloakAccount simpleKeycloakAccount = (SimpleKeycloakAccount) principal.getDetails();
        AccessToken token  = simpleKeycloakAccount.getKeycloakSecurityContext().getToken();
        String login=token.getGivenName();
        try {
            artService.suppressionOeuvre(id, login);
            return ResponseEntity.noContent().build();
        }
        catch(OeuvreNotFoundException | UtilisateurIncorrectException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.toString());
        }
    }

    @PutMapping(value = "/oeuvres/{idOeuvre}")
    public ResponseEntity<?> modifierOeuvre(@PathVariable(value = "idOeuvre") Long idOeuvre,
                                            @RequestBody OeuvreDTO oeuvredto,
                                            KeycloakAuthenticationToken principal) {
        SimpleKeycloakAccount simpleKeycloakAccount = (SimpleKeycloakAccount) principal.getDetails();
        AccessToken token  = simpleKeycloakAccount.getKeycloakSecurityContext().getToken();
        String login=token.getGivenName();
        try {
            Oeuvre oeuvre = mapper.map(oeuvredto, Oeuvre.class);
            artService.modifierOeuvreTitre(oeuvre, idOeuvre, login);
            Oeuvre oeuvreModif = artService.getOeuvreById(idOeuvre).get();
            OeuvreDTO oeuvreDTO = mapper.map(oeuvreModif, OeuvreDTO.class);
            return  ResponseEntity.ok().body(oeuvreDTO);
        } catch (OeuvreNotFoundException | UtilisateurIncorrectException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.toString());
        }
    }
}

