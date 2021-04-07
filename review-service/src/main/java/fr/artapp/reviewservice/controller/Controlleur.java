package fr.artapp.reviewservice.controller;

import fr.artapp.reviewservice.DTO.ReviewDTO;
import fr.artapp.reviewservice.exceptions.LoginNotCorrectException;
import fr.artapp.reviewservice.exceptions.NoteNotPossibleException;
import fr.artapp.reviewservice.exceptions.OeuvreNotFoundException;
import fr.artapp.reviewservice.exceptions.ReviewNotFoundException;
import fr.artapp.reviewservice.model.Review;
import fr.artapp.reviewservice.repository.ReviewRepository;
import fr.artapp.reviewservice.service.ReviewService;
import org.keycloak.adapters.springsecurity.account.SimpleKeycloakAccount;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.keycloak.representations.AccessToken;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.ReplayProcessor;
import java.net.URI;
import java.security.Principal;
import java.util.*;
import java.util.stream.Collectors;

@RestController
public class Controlleur {
    @Autowired
    ReviewService reviewService;
    @Autowired
    private ModelMapper mapper;
  /*  @Autowired
    private RestTemplate restTemplate;
   */
    @Autowired
    private WebClient.Builder webClientBuilder;

    @Value("${hostUrl}")
    private String hostUrl;



    private Map<Long,ReplayProcessor<ReviewDTO>> listNotifications  =  new HashMap<Long,ReplayProcessor<ReviewDTO>>();


    private void verifReplayProcessorExist(Long idOeuvre) {
        //Si il n'existe pas, on le créer pour la personne
        if(!listNotifications.containsKey(idOeuvre)){
            ReplayProcessor<ReviewDTO> notifications = ReplayProcessor.create(0, false);
            listNotifications.put(idOeuvre,notifications);
        }
    }



    @GetMapping(value = "/avis/subscribe/{idOeuvre}", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Flux<ReviewDTO> notification( @PathVariable("idOeuvre") Long idOeuvre) {
        verifReplayProcessorExist(idOeuvre);
        ReplayProcessor<ReviewDTO> notifications = listNotifications.get(idOeuvre);
        return Flux.from(notifications);
    }



    @PostMapping(value = "/avis")
    public ResponseEntity<?> create(KeycloakAuthenticationToken principal, @RequestBody ReviewDTO reviewBodyDTO, UriComponentsBuilder base) {
        SimpleKeycloakAccount simpleKeycloakAccount = (SimpleKeycloakAccount) principal.getDetails();
        AccessToken token  = simpleKeycloakAccount.getKeycloakSecurityContext().getToken();
        Review reviewBody = mapper.map(reviewBodyDTO, Review.class);
        reviewBody.setLoginUtilisateur(token.getGivenName());
        String uri =hostUrl+"/api/art/oeuvres/"+reviewBody.getIdOeuvre();


        try {

            ResponseEntity reponse= webClientBuilder.build().get()
                                            .uri(uri)
                                            .retrieve()
                                            .toBodilessEntity()
                                            .block();
          //   ResponseEntity<String> reponse = restTemplate.getForEntity(uri, String.class);
          //  reviewService.verifOeuvreExist(reponse);
            reviewService.setReview(reviewBody);
            // notification d'un nouveau avis dans le Stream
            verifReplayProcessorExist(reviewBody.getIdOeuvre());
            ReviewDTO reviewDTO = mapper.map(reviewBody, ReviewDTO.class);
            ReplayProcessor<ReviewDTO> notifications = listNotifications.get(reviewBody.getIdOeuvre());
            notifications.onNext(reviewDTO);
            URI location = base.path("/api/avis/{id}").buildAndExpand(reviewBody).toUri();
            return ResponseEntity.created(location).body(reviewDTO);
        } catch (NoteNotPossibleException e ) {
           // e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(e.toString());
        } catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.toString());
        }

    }

    @GetMapping(value = "/avis")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Collection<ReviewDTO>> getAllAvis() {
        Collection<Review> reviews = reviewService.getAllReview();
        Collection<ReviewDTO> reviewDTO= reviews.stream()
                .map(review -> mapper.map(review,ReviewDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(reviewDTO);
    }

    @DeleteMapping(value = "/avis/{id}")
    public ResponseEntity<?> suppressionAvis(KeycloakAuthenticationToken principal, @PathVariable("id") String id) {
        SimpleKeycloakAccount simpleKeycloakAccount = (SimpleKeycloakAccount) principal.getDetails();
        AccessToken token  = simpleKeycloakAccount.getKeycloakSecurityContext().getToken();
        //String login=token.getGivenName();
        try {
            reviewService.suppressionReview(id,token);
        } catch (ReviewNotFoundException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.toString());
        } catch (LoginNotCorrectException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.toString());
        }
        return ResponseEntity.ok().body("Suppression effectuée : "+id);
    }

    @GetMapping(value = "/avis/{id}")
    public ResponseEntity<?> getAvisById(@PathVariable String id) {
        try {
            Optional<Review> review = reviewService.getReviewById(id);
            ReviewDTO reviewDTO = mapper.map(review.get(), ReviewDTO.class);
            return ResponseEntity.ok(reviewDTO);
        }
        catch(ReviewNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.toString());
        }
    }

    @GetMapping(value = "/oeuvre/{idOeuvre}/avis")
    public ResponseEntity<?> getAllReviewByIdOeuvre(@PathVariable Long idOeuvre) {
        Collection<Review> reviews = reviewService.getAllReviewByIdOeuvre(idOeuvre);
        Collection<ReviewDTO> reviewDTO= reviews.stream()
                .map(review -> mapper.map(review,ReviewDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(reviewDTO);
    }

    @PutMapping(value = "/avis/{idAvis}")
    public ResponseEntity<?> modifierReview(@PathVariable(value = "idAvis") String idAvis,
                                            @RequestBody ReviewDTO reviewDTO,
                                            KeycloakAuthenticationToken principal) {
        SimpleKeycloakAccount simpleKeycloakAccount = (SimpleKeycloakAccount) principal.getDetails();
        AccessToken token  = simpleKeycloakAccount.getKeycloakSecurityContext().getToken();
        Review review = mapper.map(reviewDTO, Review.class);
        String login=token.getGivenName();
        try {
            Review newReview = reviewService.modifierReview(idAvis,review, login);
            ReviewDTO newReviewDTO = mapper.map(newReview, ReviewDTO.class);
            return ResponseEntity.ok().body(newReviewDTO);
        } catch (LoginNotCorrectException |ReviewNotFoundException|NoteNotPossibleException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.toString());
        }
    }


}
