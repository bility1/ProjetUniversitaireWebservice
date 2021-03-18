package fr.artapp.reviewservice.repository;

import fr.artapp.reviewservice.model.Review;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ReviewRepository extends MongoRepository<Review, Long> { // Long: Type of Employee ID.

   // Review findByReviewId(String idReview);

   List<Review> findAll();

}
