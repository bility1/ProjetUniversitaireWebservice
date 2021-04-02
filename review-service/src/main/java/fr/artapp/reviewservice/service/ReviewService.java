package fr.artapp.reviewservice.service;

import fr.artapp.reviewservice.exceptions.LoginNotCorrectException;
import fr.artapp.reviewservice.exceptions.NoteNotPossibleException;
import fr.artapp.reviewservice.exceptions.ReviewNotFoundException;
import fr.artapp.reviewservice.model.Review;
import org.keycloak.representations.AccessToken;

import java.util.Collection;
import java.util.Optional;

public interface ReviewService {

    Collection<Review> getAllReview();
    void setReview(Review review) throws NoteNotPossibleException;
    void suppressionReview(String id, AccessToken token) throws ReviewNotFoundException, LoginNotCorrectException;

    Optional<Review> getReviewById(String id) throws ReviewNotFoundException;


    Collection<Review> getAllReviewByIdOeuvre(Long idOeuvre);

    Review modifierReview(String idAvis, Review review, String login) throws NoteNotPossibleException, LoginNotCorrectException, ReviewNotFoundException;
}
