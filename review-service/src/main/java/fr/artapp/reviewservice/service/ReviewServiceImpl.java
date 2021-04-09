package fr.artapp.reviewservice.service;

import fr.artapp.reviewservice.exceptions.LoginNotCorrectException;
import fr.artapp.reviewservice.exceptions.NoteNotPossibleException;
import fr.artapp.reviewservice.exceptions.OeuvreNotFoundException;
import fr.artapp.reviewservice.exceptions.ReviewNotFoundException;
import fr.artapp.reviewservice.model.Review;
import fr.artapp.reviewservice.repository.ReviewRepository;
import org.keycloak.representations.AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;


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
        Set<String> roles = token.getRealmAccess().getRoles();
        if (reviewRepository.existsByIdAvis(id)){
            if(!login.equals(review.get().getLoginUtilisateur()) && !roles.contains("ADMIN")){
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

    @Override
    public Review modifierReview(String idAvis,Review review,  String login) throws LoginNotCorrectException, ReviewNotFoundException, NoteNotPossibleException {
        Review reviewModif = reviewRepository.findByIdAvis(idAvis).get();
        if(!reviewModif.getLoginUtilisateur().equals(login)){
            throw new LoginNotCorrectException();
        }
        if(!reviewRepository.existsByIdAvis(idAvis)) {
            throw new ReviewNotFoundException();
        }
        String commentaire = review.getCommentaire();
        Integer note = review.getNote();
        if(commentaire!=null){
            reviewModif.setCommentaire(commentaire);
        }
        if(note!=null){
            if(review.getNote()>20||review.getNote()<0){
                throw new NoteNotPossibleException();
            }
            reviewModif.setNote(note);
        }
        return reviewRepository.save(reviewModif);
    }




}
