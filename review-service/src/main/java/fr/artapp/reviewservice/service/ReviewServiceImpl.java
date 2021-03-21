package fr.artapp.reviewservice.service;

import fr.artapp.reviewservice.exceptions.ReviewNotFoundException;
import fr.artapp.reviewservice.model.Review;
import fr.artapp.reviewservice.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    ReviewRepository reviewRepository;


    @Override
    public Collection<Review> getAllReview() {
        return (Collection<Review>)  reviewRepository.findAll();

    }

    @Override
    public void setReview(Review review) {
        reviewRepository.save(review);
    }

    @Override
    public void suppressionReview(String id) throws ReviewNotFoundException {
        /*Optional<Review> review =  this.reviewRepository.findByIdAvis(String.valueOf(id));
        if (review.isEmpty()){
            throw new ReviewNotFoundException();
        }else{

        }*/
        if (reviewRepository.existsByIdAvis(id)){
            reviewRepository.deleteByIdAvis(id);
        }
        else{
            throw new ReviewNotFoundException();
        }
    }
}
