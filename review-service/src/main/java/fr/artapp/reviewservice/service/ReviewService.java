package fr.artapp.reviewservice.service;

import fr.artapp.reviewservice.model.Review;

import java.util.Collection;

public interface ReviewService {

    Collection<Review> getAllReview();
    void setReview(Review review);


}
