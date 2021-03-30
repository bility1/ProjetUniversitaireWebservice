package fr.artapp.reviewservice.service;

import fr.artapp.reviewservice.exceptions.LoginNotCorrectException;
import fr.artapp.reviewservice.exceptions.ReviewNotFoundException;
import fr.artapp.reviewservice.model.Review;

import java.util.Collection;
import java.util.Optional;

public interface ReviewService {

    Collection<Review> getAllReview();
    void setReview(Review review);
    void suppressionReview(String id,String login) throws ReviewNotFoundException, LoginNotCorrectException;

    Optional<Review> getReviewById(String id) throws ReviewNotFoundException;


    Collection<Review> getAllReviewByIdOeuvre(Long idOeuvre);
}
