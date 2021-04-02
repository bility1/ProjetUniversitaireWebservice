package fr.artapp.reviewservice.service;

import fr.artapp.reviewservice.exceptions.LoginNotCorrectException;
import fr.artapp.reviewservice.exceptions.NoteNotPossibleException;
import fr.artapp.reviewservice.exceptions.ReviewNotFoundException;
import fr.artapp.reviewservice.model.Review;
import fr.artapp.reviewservice.repository.ReviewRepository;
import org.keycloak.representations.AccessToken;
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
    public void setReview(Review review) throws NoteNotPossibleException {
        if(review.getNote()>20||review.getNote()<0){
            throw new NoteNotPossibleException();
        }
        reviewRepository.save(review);
    }

    @Override
    public void suppressionReview(String id, AccessToken token) throws ReviewNotFoundException,LoginNotCorrectException {
        Optional<Review> review = reviewRepository.findByIdAvis(id);
        String login = token.getGivenName();
        if (reviewRepository.existsByIdAvis(id)){
            if(!login.equals(review.get().getLoginUtilisateur())){
                throw new LoginNotCorrectException();
            }else{
                reviewRepository.deleteByIdAvis(id);
            }
        }
        else{
            throw new ReviewNotFoundException();
        }
    }

    @Override
    public Optional<Review> getReviewById(String id) throws ReviewNotFoundException {
        if (reviewRepository.existsByIdAvis(id)){
            return reviewRepository.findByIdAvis(id);
        }
        else{
            throw new ReviewNotFoundException();
        }
    }

    @Override
    public Collection<Review> getAllReviewByIdOeuvre(Long idOeuvre) {
       return reviewRepository.findAllReviewByIdOeuvre(idOeuvre);
    }


}
